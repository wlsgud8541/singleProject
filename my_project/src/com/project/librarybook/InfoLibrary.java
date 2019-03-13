package com.project.librarybook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.project.bookdatadb.bookModelData;

//������ ����
public class InfoLibrary extends JPanel {
	JPanel panel_west, panel_east;
	JLabel imglable,infolable;
	Loadmap lm;
	LibrarySize ls;
	
	
	public InfoLibrary() {
		lm=new Loadmap();
		ls=new LibrarySize();
		
		
		setLayout(new GridLayout(0, 2));
		
		panel_west=new JPanel(); //�̹��� ���� �г�
		panel_west.setLayout(new BorderLayout());
		imglable=new JLabel();
		infolable=new JLabel("�̷��ɷ°��� ������");
		infolable.setHorizontalAlignment(SwingConstants.CENTER);
		infolable.setFont(new Font("Serif", Font.BOLD , 35));
		ImageIcon img=new ImageIcon("img1.png");
		imglable.setIcon(img);
		panel_west.add(infolable, BorderLayout.SOUTH);
		panel_west.add(imglable,BorderLayout.CENTER);
		add(panel_west);
		
		panel_east=new JPanel();
		JTabbedPane pane=new JTabbedPane();
		pane.addTab("������ �൵",lm);
		pane.addTab("������ �Ը�",ls);
		pane.setSelectedIndex(0);
		panel_east.add(pane);
		add(panel_east);
		
	}
}

class Loadmap extends JPanel{
	JLabel imglabel;
	public Loadmap() {
		imglabel =new JLabel();
		ImageIcon img=new ImageIcon("map.png");
		imglabel.setIcon(img);
		
		add(imglabel);
	}
}

class LibrarySize extends JPanel{
	JLabel l_Open,l_Close,l_BookCount,l_room;
	JTextField tf_Open,tf_Close,tf_BookCount;
	JTextArea tf_room;
	bookModelData bmdt;
	
	public LibrarySize() {
		setLayout(new GridLayout(0, 2));
		ArrayList bookcount;
		try {
			bmdt=new bookModelData();
			bookcount = bmdt.bcount();
			String str=(String) bookcount.get(0);
			tf_BookCount=new JTextField(str+"��");
			tf_BookCount.setEditable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		l_Open=new JLabel("������ �ð�");
		l_Close=new JLabel("�ݴ� �ð�");
		l_BookCount=new JLabel("���� ����");
		l_room=new JLabel("����");
		
		tf_Open=new JTextField("AM 09:00");
		tf_Open.setEditable(false);
		tf_Close=new JTextField("PM 22:00");
		tf_Close.setEditable(false);
		tf_room=new JTextArea("����������1, ����������2\n��Ʈ�Ͽ�����1, ��Ʈ�Ͽ�����2 \n����");
		tf_room.setEditable(false);
//		tf_room.setEnabled(false);
		
		add(l_Open);
		add(tf_Open);
		add(l_Close);
		add(tf_Close);
		add(l_BookCount);
		add(tf_BookCount);
		add(l_room);
		add(tf_room);
		
	}
}




