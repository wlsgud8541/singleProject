package com.test.member;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.project.login.Login_M;
import com.project.logindb.UserDB;
import com.project.logindb.UserDBSave;

public class Member extends JFrame implements ActionListener{

	//필드값 생성
	JPanel m_t_panel, m_b_panel;
	JLabel l_m_name,l_m_id_num,l_m_addr,l_m_call;
	JTextField t_m_name,t_m_id,t_m_addr,t_m_call;
	JButton m_ok_button,m_cancle_button;
	BorderLayout bl;
	
	UserDB ud; //유저 데이터베이스
	
	public Member() {
		setTitle("회원가입");
		setSize(300,200);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		setLocation(screenSize.width/2-150,screenSize.height/2-100);
		setLayout(bl=new BorderLayout());
		
		m_t_panel=new JPanel(new GridLayout(0, 2));
		m_b_panel=new JPanel(new GridLayout(1, 2));
		
		l_m_name=new JLabel("             이        름");
		l_m_id_num=new JLabel("         주민등록번호");
		l_m_addr=new JLabel("             주        소");
		l_m_call=new JLabel("           전  화  번  호");
		
		t_m_name=new JTextField(10);
		t_m_id=new JTextField(10);
		t_m_addr=new JTextField(10);
		t_m_call=new JTextField(10);

		m_ok_button=new JButton("가입하기");
		m_cancle_button=new JButton("취소하기");
		
		m_ok_button.addActionListener(this);
		m_cancle_button.addActionListener(this);
		
		m_t_panel.add(l_m_name);
		m_t_panel.add(t_m_name);
		m_t_panel.add(l_m_id_num);
		m_t_panel.add(t_m_id);
		m_t_panel.add(l_m_addr);
		m_t_panel.add(t_m_addr);
		m_t_panel.add(l_m_call);
		m_t_panel.add(t_m_call);
		
		m_b_panel.add(m_ok_button);
		m_b_panel.add(m_cancle_button);
		
		add(m_t_panel,BorderLayout.CENTER);
		add(m_b_panel, BorderLayout.SOUTH);
		
		
		//기본설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	
	void insertUser() {
		UserDBSave udbs=new UserDBSave();
		udbs.setName(t_m_name.getText());
		udbs.setIdnum(t_m_id.getText());
		udbs.setAddr(t_m_addr.getText());
		udbs.setCall(t_m_call.getText());
		System.out.println("aaaa : "+udbs.getName());
		System.out.println("bbbb : "+udbs.getIdnum());
		System.out.println("cccc : "+udbs.getCall());
		System.out.println("dddd : "+udbs.getAddr());
		
		
		try {
			ud=new UserDB();
			ud.insertUser1(udbs);
			JOptionPane.showMessageDialog(null, "회원가입완료");
			t_m_name.setText(null);
			t_m_id.setText(null);
			t_m_addr.setText(null);
			t_m_call.setText(null);
		} catch (Exception e) {
			System.out.println("회원가입실패");
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==m_ok_button) {
			insertUser();
			setVisible(false);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
			new Login_M();
			
		}else if (e.getSource()==m_cancle_button) {
			setVisible(false);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
			new Login_M();
		}
			
	}
	
	
}
