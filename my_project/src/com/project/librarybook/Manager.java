package com.project.librarybook;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.project.bookdatadb.bookDBSave;
import com.project.bookdatadb.bookModelData;

//������
public class Manager extends JPanel implements ActionListener {
	JPanel panel_west, panel_east, panel_south, panel_north, panel_center;
	JLabel l_code, l_cf, l_name, l_publisher, l_year, l_writer, l_pic;
	JTextField tf_code, tf_cf, tf_name, tf_publisher, tf_year, tf_writer, tf_pic;
	JButton b_search, b_insert, b_delete, b_update, b_choose;
	JTextArea tf_story;

	bookModelData bmdt;
	bookDBSave bdbs;
	
	//�̹��� ��º�
	File f = null; //���Ͽ��� �ְ� �ʵ弱��
	String fName="";
	
	public Manager() {
		setLayout(new BorderLayout());
		panel_west = new JPanel();
		panel_east = new JPanel();
		panel_north = new JPanel(); // ������Ʈ
		panel_south = new JPanel(); // ��ư 5��
		panel_center = new JPanel();
		// panel_center.setLayout(new GridLayout(0, 2));

		l_code = new JLabel("------------   ��    ��    ��    ��   ------------");
		l_code.setHorizontalAlignment(SwingConstants.CENTER);
		l_cf = new JLabel("------------   ��                     ��   ------------");
		l_cf.setHorizontalAlignment(SwingConstants.CENTER);
		l_name = new JLabel("------------   å        ��        ��   ------------");
		l_name.setHorizontalAlignment(SwingConstants.CENTER);
		l_publisher = new JLabel("------------   ��        ��        ��   ------------");
		l_publisher.setHorizontalAlignment(SwingConstants.CENTER);
		l_year = new JLabel("------------   ��                     ��   ------------");
		l_year.setHorizontalAlignment(SwingConstants.CENTER);
		l_writer = new JLabel("------------   ��                     ��   ------------");
		l_writer.setHorizontalAlignment(SwingConstants.CENTER);
		l_pic = new JLabel("------------   ��    ��    ��    ��   ------------");
		l_pic.setHorizontalAlignment(SwingConstants.CENTER);

		tf_code = new JTextField();
		tf_cf = new JTextField();
		tf_name = new JTextField();
		tf_publisher = new JTextField();
		tf_year = new JTextField();
		tf_writer = new JTextField();
		tf_pic = new JTextField();
		tf_story = new JTextArea(7, 60);
		tf_story.setLineWrap(true);

		
		b_search = new JButton("�˻��ϱ�");
		b_search.addActionListener(this);
		b_insert = new JButton("�߰��ϱ�");
		b_insert.addActionListener(this);
		b_delete = new JButton("�����ϱ�");
		b_delete.addActionListener(this);
		b_update = new JButton("�����ϱ�");
		b_update.addActionListener(this);
		b_choose = new JButton("�������ϼ���");
		b_choose.addActionListener(this);
		// ����
		panel_north.setLayout(new GridLayout(0, 2));
		panel_north.add(l_code);
		panel_north.add(tf_code);
		panel_north.add(l_name);
		panel_north.add(tf_name);
		panel_north.add(l_cf);
		panel_north.add(tf_cf);
		panel_north.add(l_writer);
		panel_north.add(tf_writer);
		panel_north.add(l_publisher);
		panel_north.add(tf_publisher);
		panel_north.add(l_year);
		panel_north.add(tf_year);
		panel_north.add(l_pic);
		panel_north.add(tf_pic);

		panel_center.add(tf_story);

		panel_north.setBorder(BorderFactory.createTitledBorder("���� �⺻����"));
		panel_center.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 100));
		panel_center.setBorder(BorderFactory.createTitledBorder("�ٰŸ� �Է�"));

		panel_south.add(b_search);
		panel_south.add(b_insert);
		panel_south.add(b_delete);
		panel_south.add(b_update);
		panel_south.add(b_choose);

		add(panel_west, BorderLayout.WEST);
		add(panel_east, BorderLayout.EAST);
		add(panel_north, BorderLayout.NORTH);
		add(panel_south, BorderLayout.SOUTH);
		add(panel_center, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// �˻���
		if (e.getSource() == b_search) {
			try {
				bmdt = new bookModelData();
				bdbs = bmdt.search(tf_name.getText());
				search();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			// �߰���
		} else if (e.getSource() == b_insert) {
			try {
				fName=System.currentTimeMillis()+f.getName();
				bmdt = new bookModelData();
				insert(fName);
				fileSave(f, ".//upload2", fName);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			// ������Ʈ��
		} else if (e.getSource() == b_update) {
			try {
				fName=System.currentTimeMillis()+f.getName(); //�����̸�
				bmdt = new bookModelData();
				update(fName);
				fileSave(f, ".//upload2", fName); //������ ����Ǵ� ���
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			// ������
		} else if (e.getSource() == b_delete) {
			try {
				bmdt = new bookModelData();
				delete();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			//���� ���ú� ,�̹��� ����
		} else if (e.getSource() == b_choose) {
			JFileChooser jc = new JFileChooser(); //���� �����Ҽ��ִ� â ����
			jc.showOpenDialog(this);
			f = jc.getSelectedFile();  
			tf_pic.setText(f.getPath());
		}
	}

	//������ȯ��
	private void fileSave(File file, String path, String name) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				f.mkdir();
			}
			String filePath = path + "\\" + name;

			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(filePath);

			int i = 0;
			byte[] buffer = new byte[1024];

			while ((i = fis.read(buffer, 0, 1024)) != -1) {
				fos.write(buffer, 0, i);

			}
			fis.close();
			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void delete() {
		bdbs = new bookDBSave();
		bdbs.setB_code(Integer.parseInt(tf_code.getText()));
		bdbs.setB_cf(tf_cf.getText());
		bdbs.setB_name(tf_name.getText());
		bdbs.setB_publisher(tf_publisher.getText());
		bdbs.setB_year(tf_year.getText());
		bdbs.setB_writer(tf_writer.getText());
		bdbs.setB_story(tf_story.getText());

		try {
			bmdt = new bookModelData();
			bmdt.delete(bdbs);
			JOptionPane.showMessageDialog(null, "���� �Ϸ�");

			tf_code.setText(null);
			tf_cf.setText(null);
			tf_name.setText(null);
			tf_publisher.setText(null);
			tf_year.setText(null);
			tf_writer.setText(null);
			tf_story.setText(null);
			tf_pic.setText(null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void update(String fName2) {
		bookDBSave bdbs = new bookDBSave();
		bdbs.setB_code(Integer.parseInt(tf_code.getText()));
		bdbs.setB_cf(tf_cf.getText());
		bdbs.setB_name(tf_name.getText());
		bdbs.setB_publisher(tf_publisher.getText());
		bdbs.setB_year(tf_year.getText());
		bdbs.setB_writer(tf_writer.getText());
		bdbs.setB_story(tf_story.getText());
		bdbs.setB_Imgfname(fName2);

		try {
			String str=tf_code.getText();
			bmdt = new bookModelData();
			bmdt.UserUpdate(bdbs,str);
			JOptionPane.showMessageDialog(null, "���� �Ϸ�");

			tf_code.setText(null);
			tf_cf.setText(null);
			tf_name.setText(null);
			tf_publisher.setText(null);
			tf_year.setText(null);
			tf_writer.setText(null);
			tf_story.setText(null);
			tf_pic.setText(null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insert(String fName2) {
		bookDBSave bdbs = new bookDBSave();

		bdbs.setB_code(Integer.parseInt(tf_code.getText()));
		bdbs.setB_cf(tf_cf.getText());
		bdbs.setB_name(tf_name.getText());
		bdbs.setB_publisher(tf_publisher.getText());
		bdbs.setB_year(tf_year.getText());
		bdbs.setB_writer(tf_writer.getText());
		bdbs.setB_story(tf_story.getText());
		bdbs.setB_Imgfname(fName2);

		try {
			bmdt = new bookModelData();
			bmdt.insertbook(bdbs);
			JOptionPane.showMessageDialog(null, "�ڷ���� �Ϸ�");
			tf_code.setText(null);
			tf_cf.setText(null);
			tf_name.setText(null);
			tf_publisher.setText(null);
			tf_year.setText(null);
			tf_writer.setText(null);
			tf_story.setText(null);
			tf_pic.setText(null);
		} catch (Exception e) {
			System.out.println("�ڷ���� ����");
			e.printStackTrace();
		}

	}

	private void search() {
		tf_code.setText("" + (bdbs.getB_code()));
		tf_cf.setText(bdbs.getB_cf());
		tf_name.setText(bdbs.getB_name());
		tf_publisher.setText(bdbs.getB_publisher());
		tf_year.setText(bdbs.getB_year());
		tf_writer.setText(bdbs.getB_writer());
		tf_story.setText(bdbs.getB_story());
		

	}
}
