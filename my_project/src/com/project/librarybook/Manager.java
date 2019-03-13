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

//관리자
public class Manager extends JPanel implements ActionListener {
	JPanel panel_west, panel_east, panel_south, panel_north, panel_center;
	JLabel l_code, l_cf, l_name, l_publisher, l_year, l_writer, l_pic;
	JTextField tf_code, tf_cf, tf_name, tf_publisher, tf_year, tf_writer, tf_pic;
	JButton b_search, b_insert, b_delete, b_update, b_choose;
	JTextArea tf_story;

	bookModelData bmdt;
	bookDBSave bdbs;
	
	//이미지 출력부
	File f = null; //파일열수 있게 필드선언
	String fName="";
	
	public Manager() {
		setLayout(new BorderLayout());
		panel_west = new JPanel();
		panel_east = new JPanel();
		panel_north = new JPanel(); // 컴포넌트
		panel_south = new JPanel(); // 버튼 5개
		panel_center = new JPanel();
		// panel_center.setLayout(new GridLayout(0, 2));

		l_code = new JLabel("------------   등    록    코    드   ------------");
		l_code.setHorizontalAlignment(SwingConstants.CENTER);
		l_cf = new JLabel("------------   분                     류   ------------");
		l_cf.setHorizontalAlignment(SwingConstants.CENTER);
		l_name = new JLabel("------------   책        이        름   ------------");
		l_name.setHorizontalAlignment(SwingConstants.CENTER);
		l_publisher = new JLabel("------------   출        판        사   ------------");
		l_publisher.setHorizontalAlignment(SwingConstants.CENTER);
		l_year = new JLabel("------------   연                     도   ------------");
		l_year.setHorizontalAlignment(SwingConstants.CENTER);
		l_writer = new JLabel("------------   저                     자   ------------");
		l_writer.setHorizontalAlignment(SwingConstants.CENTER);
		l_pic = new JLabel("------------   사    진    파    일   ------------");
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

		
		b_search = new JButton("검색하기");
		b_search.addActionListener(this);
		b_insert = new JButton("추가하기");
		b_insert.addActionListener(this);
		b_delete = new JButton("삭제하기");
		b_delete.addActionListener(this);
		b_update = new JButton("수정하기");
		b_update.addActionListener(this);
		b_choose = new JButton("사진파일선택");
		b_choose.addActionListener(this);
		// 부착
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

		panel_north.setBorder(BorderFactory.createTitledBorder("도서 기본정보"));
		panel_center.setBorder(BorderFactory.createEmptyBorder(0, 50, 100, 100));
		panel_center.setBorder(BorderFactory.createTitledBorder("줄거리 입력"));

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
		// 검색부
		if (e.getSource() == b_search) {
			try {
				bmdt = new bookModelData();
				bdbs = bmdt.search(tf_name.getText());
				search();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			// 추가부
		} else if (e.getSource() == b_insert) {
			try {
				fName=System.currentTimeMillis()+f.getName();
				bmdt = new bookModelData();
				insert(fName);
				fileSave(f, ".//upload2", fName);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			// 업데이트부
		} else if (e.getSource() == b_update) {
			try {
				fName=System.currentTimeMillis()+f.getName(); //파일이름
				bmdt = new bookModelData();
				update(fName);
				fileSave(f, ".//upload2", fName); //파일이 저장되는 경로
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			// 삭제부
		} else if (e.getSource() == b_delete) {
			try {
				bmdt = new bookModelData();
				delete();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			//파일 선택부 ,이미지 저장
		} else if (e.getSource() == b_choose) {
			JFileChooser jc = new JFileChooser(); //파일 선택할수있는 창 오픈
			jc.showOpenDialog(this);
			f = jc.getSelectedFile();  
			tf_pic.setText(f.getPath());
		}
	}

	//정보교환부
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
			JOptionPane.showMessageDialog(null, "삭제 완료");

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
			JOptionPane.showMessageDialog(null, "수정 완료");

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
			JOptionPane.showMessageDialog(null, "자료삽입 완료");
			tf_code.setText(null);
			tf_cf.setText(null);
			tf_name.setText(null);
			tf_publisher.setText(null);
			tf_year.setText(null);
			tf_writer.setText(null);
			tf_story.setText(null);
			tf_pic.setText(null);
		} catch (Exception e) {
			System.out.println("자료삽입 실패");
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
