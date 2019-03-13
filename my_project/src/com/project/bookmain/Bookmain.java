package com.project.bookmain;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.project.librarybook.InfoLibrary;
import com.project.librarybook.Manager;
import com.project.librarybook.SearchBook;

public class Bookmain extends JFrame {

	SearchBook searchbook= new SearchBook();
	InfoLibrary infolibrary= new InfoLibrary();
	Manager manager= new Manager();
	
	public Bookmain() {
		JTabbedPane pane=new JTabbedPane();
		pane.addTab("도서목록", searchbook);
		pane.addTab("도서관정보", infolibrary);
		pane.addTab("관리자", manager);
		pane.setSelectedIndex(0);
		add(pane);
		
		setTitle("도서관");
		setSize(800, 460);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setLocation(screenSize.width/2-400,screenSize.height/2-230);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
}
