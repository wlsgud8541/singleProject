package com.project.librarybook;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import com.project.bookdatadb.bookDBSave;
import com.project.bookdatadb.bookModelData;

public class SearchBook extends JPanel implements ActionListener {
	// 오류확인
	JPanel west_panel;
	JPanel west_panel_center;
	JPanel west_panel_up;

	JLabel sb_l_code, sb_l_cf, sb_l_name, sb_l_publisher, sb_l_year, sb_l_writer,sb_l_use, sb_l_pic;
	JTextField sb_t_code, sb_t_cf, sb_t_name, sb_t_publisher, sb_t_year, sb_t_writer,sb_t_use;

	JPanel east_panel;
	JPanel east_north_panel;
	JLabel e_sb_l_book;
	JTextField e_sb_t_book;
	JComboBox search_book;

	JTextArea sb_ta_info;

	JTable table_book;

	
	JPanel east_south_panel;
	JButton returnBook, rentalBook;
	
	BookTableModel bookModel;
	bookModelData bmdt;
	bookDBSave bdbs;
	
	//이미지
	File f = null;
	String fName="";
	ImageIcon icon;
	

	public SearchBook() {
		addLayout();
		evenProc();
		connectDB();
	}

	private void connectDB() {
		try {
			bmdt=new bookModelData();
			System.out.println("searchBook 데이터베이스 연결성공");
		} catch (Exception e) {
			System.out.println("searchBook 데이터베이스 연결실패");
			e.printStackTrace();
		}
	}

	private void evenProc() {
		table_book.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table_book.getSelectedRow();
				int col = 0;
				String data = (String) table_book.getValueAt(row, col);
				int no = Integer.parseInt(data);
				try {
					bookDBSave bdbs = bmdt.selectedBybook(no);
					selectedBybook(bdbs);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				super.mouseClicked(e);
			}
		});

	}

	void selectedBybook(bookDBSave bdbs) {
		sb_t_code.setText(String.valueOf(bdbs.getB_code()));
		sb_t_cf.setText(bdbs.getB_cf());
		sb_t_name.setText(bdbs.getB_name());
		sb_t_publisher.setText(bdbs.getB_publisher());
		sb_t_year.setText(bdbs.getB_year());
		sb_t_writer.setText(bdbs.getB_writer());
		sb_ta_info.setText(bdbs.getB_story());
		sb_t_use.setText(bdbs.getB_use());
		
		icon=new ImageIcon(".\\upload2\\"+bdbs.getB_Imgfname());
		ImageIcon newIcon;
		Image image=icon.getImage();
		image.getScaledInstance(sb_l_pic.getWidth(), sb_l_pic.getHeight(), 0);
		int imgW=sb_l_pic.getWidth();
		int imgH=sb_l_pic.getHeight();
		Image img=icon.getImage();
		Image newimg=img.getScaledInstance(imgW, imgH, java.awt.Image.SCALE_SMOOTH);
		System.out.println("no   =.png");
		System.out.println("icon  : "+icon);
		newIcon=new ImageIcon(newimg);
		sb_l_pic.setIcon(newIcon);
		
		
	}

	private void addLayout() {
		// 왼쪽패널
		west_panel = new JPanel();
		west_panel.setLayout(new BorderLayout());

		// 왼쪽가운데
		west_panel_center = new JPanel();

		// 왼쪽위에
		west_panel_up = new JPanel();
		west_panel_up.setLayout(new GridLayout(7, 1));

		east_south_panel=new JPanel();
		
		// 필드정의
		sb_l_code = new JLabel("등록코드");
		sb_l_cf = new JLabel("분류");
		sb_l_name = new JLabel("책이름");
		sb_l_publisher = new JLabel("출판사");
		sb_l_year = new JLabel("저자");
		sb_l_use=new JLabel("대여상태");
		sb_l_writer = new JLabel("연도");
		sb_l_pic=new JLabel();
		
		sb_t_code = new JTextField();
		sb_t_code.setEditable(false);
		sb_t_cf = new JTextField();
		sb_t_cf.setEditable(false);
		sb_t_name = new JTextField();
		sb_t_name.setEditable(false);
		sb_t_publisher = new JTextField();
		sb_t_publisher.setEditable(false);
		sb_t_year = new JTextField();
		sb_t_year.setEditable(false);
		sb_t_writer = new JTextField();
		sb_t_writer.setEditable(false);
		sb_t_use=new JTextField();
		sb_t_use.setEditable(false);
		sb_ta_info = new JTextArea(11,32);
		sb_ta_info.setLineWrap(true);
		sb_ta_info.setEditable(false);
		
		west_panel_center.setLayout(new GridLayout(0, 2));
		
		west_panel_center.add(sb_l_pic);
		west_panel_center.add(sb_ta_info);
		
		west_panel_up.add(sb_l_code);
		west_panel_up.add(sb_t_code);

		west_panel_up.add(sb_l_cf);
		west_panel_up.add(sb_t_cf);

		west_panel_up.add(sb_l_name);
		west_panel_up.add(sb_t_name);

		west_panel_up.add(sb_l_publisher);
		west_panel_up.add(sb_t_publisher);

		west_panel_up.add(sb_l_year);
		west_panel_up.add(sb_t_year);

		west_panel_up.add(sb_l_writer);
		west_panel_up.add(sb_t_writer);
		
		west_panel_up.add(sb_l_use);
		west_panel_up.add(sb_t_use);

		west_panel_up.setBorder(new TitledBorder("도서정보"));
		west_panel_center.setBorder(new TitledBorder("줄거리"));

		west_panel.add(west_panel_up, BorderLayout.NORTH);
		west_panel.add(west_panel_center, BorderLayout.CENTER);

		rentalBook = new JButton("대여");
		returnBook = new JButton("반납");
		rentalBook.addActionListener(this);
		returnBook.addActionListener(this);
		east_south_panel.add(rentalBook);
		east_south_panel.add(returnBook);
		
		east_panel = new JPanel();
		east_panel.setLayout(new BorderLayout());
		east_north_panel = new JPanel();
		e_sb_l_book = new JLabel("도서검색");

		String[] sb = { "제목", "저자" };
		search_book = new JComboBox(sb);
		e_sb_t_book = new JTextField(10);
		e_sb_t_book.addActionListener(this);
		east_north_panel.add(e_sb_l_book);
		east_north_panel.add(search_book);
		east_north_panel.add(e_sb_t_book);

		// 테이블 설정부
		east_panel.add(east_south_panel, BorderLayout.SOUTH);
		bookModel = new BookTableModel();
		table_book = new JTable(bookModel);
		table_book.setModel(bookModel);
		east_panel.add(east_north_panel, BorderLayout.NORTH);
		east_panel.add(new JScrollPane(table_book), BorderLayout.CENTER);

		setLayout(new GridLayout(1, 2));
		add(west_panel);
		add(east_panel);

		// 테이블 액션 세팅
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		
		
		if (evt == e_sb_t_book) {
			System.out.println("s신호");
			try {
				searchBook();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if (evt==rentalBook) {
			System.out.println("rental 신호");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 2);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = df.format(cal.getTime());
			System.out.println("날짜 계산 결과 : "+strDate);
			
			
			if (rentalbooks()==false) {
				JOptionPane.showMessageDialog(null, "도서 대출 불가. 이미 대출중인 도서입니다.");
			}else if (rentalbooks()==true) {
				JOptionPane.showMessageDialog(null, "도서 대출 완료. 반납 예정일은"+ ( strDate ) +"입니다.");
			}
			
		}else if (evt==returnBook) {
			System.out.println("return 신호");
			if (returnbooks()==false) {
				JOptionPane.showMessageDialog(null, "도서 대출 불가. 이미 대출중인 도서입니다.");
			}else if (returnbooks()==true) {
				JOptionPane.showMessageDialog(null, "도서 반납 완료");
			}
			
		}
	}

	
	private boolean returnbooks() {
		boolean check=false;
		if (sb_t_use.getText().equals("불가능")) {
			try {
				bmdt.BookReturnData(sb_t_code.getText());
				researchBook();
			} catch (Exception e) {
				e.printStackTrace();
			}
			check=true;
		}else{
			check=false;
		}
		return check;
	}

	private boolean rentalbooks() {
		boolean check=false;
		if (sb_t_use.getText().equals("가능")) {
			try {
				bmdt.BookRantalData(sb_t_code.getText());
				researchBook();
			} catch (Exception e) {
				e.printStackTrace();
			}
			check=true;
			
		}else{
			check=false;
		}
		return check;
	}

	private void researchBook() {
		int idx = search_book.getSelectedIndex();
		System.out.println("idx : " + idx);
		String str = e_sb_t_book.getText();

		try {
			ArrayList data = bmdt.researchBook(idx, str);
			bookModel.data = data;
			System.out.println("bookmodel data : " + bookModel.data);
			table_book.setModel(bookModel);
			bookModel.fireTableDataChanged();
			System.out.println("셋팅확인");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void searchBook() throws Exception {

		int idx = search_book.getSelectedIndex();
		System.out.println("idx : " + idx);
		String str = e_sb_t_book.getText();

		try {
			ArrayList data = bmdt.researchBook(idx, str);
			bookModel.data = data;
			System.out.println("bookmodel data : " + bookModel.data);
			table_book.setModel(bookModel);
			bookModel.fireTableDataChanged();
			System.out.println("셋팅확인");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class BookTableModel extends AbstractTableModel {
		ArrayList data = new ArrayList();
		String[] btm = { "등록코드", "분류", "책이름", "출판사", "연도", "저자", "대여상태" };

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public int getColumnCount() {
			return btm.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

		@Override
		public String getColumnName(int col) {
			return btm[col];
		}
	}

}
