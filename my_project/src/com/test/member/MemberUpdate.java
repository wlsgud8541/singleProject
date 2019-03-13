package com.test.member;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.project.login.Login_M;
import com.project.logindb.UserDB;
import com.project.logindb.UserDBSave;

public class MemberUpdate extends JFrame implements ActionListener{

	JPanel m_t_panel, m_b_panel;
	JLabel l_m_name, l_m_id_num, l_m_addr, l_m_call;
	JTextField t_m_name, t_m_id, t_m_addr, t_m_call;
	JButton m_select_button,m_update_button ,m_cancle_button;
	BorderLayout bl;
	
	UserDBSave udbs=new UserDBSave();
	UserDB ud;

	public MemberUpdate() {

		setTitle("회원정보수정");
		setSize(300, 300);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setLocation(screenSize.width / 2 - 150, screenSize.height / 2 - 150);
		setLayout(bl = new BorderLayout());

		m_t_panel = new JPanel(new GridLayout(0, 2));
		m_b_panel = new JPanel(new GridLayout(1, 2));

		l_m_name = new JLabel("             이        름");
		l_m_id_num = new JLabel("         주민등록번호");
		l_m_addr = new JLabel("             주        소");
		l_m_call = new JLabel("           전  화  번  호");

		t_m_name = new JTextField(10);
		t_m_id = new JTextField(10);
		t_m_addr = new JTextField(10);
		t_m_call = new JTextField(10);

		m_select_button = new JButton("검색하기");
		m_update_button = new JButton("수정하기");
		m_cancle_button = new JButton("취소하기");

		m_select_button.addActionListener(this);
		m_update_button.addActionListener(this);
		m_cancle_button.addActionListener(this);

		m_t_panel.add(l_m_name);
		m_t_panel.add(t_m_name);
		m_t_panel.add(l_m_id_num);
		m_t_panel.add(t_m_id);
		m_t_panel.add(l_m_addr);
		m_t_panel.add(t_m_addr);
		m_t_panel.add(l_m_call);
		m_t_panel.add(t_m_call);

		m_b_panel.add(m_select_button);
		m_b_panel.add(m_update_button);
		m_b_panel.add(m_cancle_button);

		add(m_t_panel, BorderLayout.CENTER);
		add(m_b_panel, BorderLayout.SOUTH);

		// 기본설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
//t_m_name, t_m_id, t_m_addr, t_m_call
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==m_select_button) {
			try {
				ud=new UserDB();
				udbs=ud.searchUser(t_m_name.getText());
				searchUser();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}else if (e.getSource()==m_update_button) {
			try {
				UserUpdate();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
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

	private void UserUpdate() {
		
		udbs=new UserDBSave();
		udbs.setName(t_m_name.getText());
		udbs.setIdnum(t_m_id.getText());
		udbs.setAddr(t_m_addr.getText());
		udbs.setCall(t_m_call.getText());
		
		try {
			ud=new UserDB();
			ud.UserUpdate(udbs);
			JOptionPane.showMessageDialog(null, "수정 완료");
			t_m_name.setText(null);
			t_m_id.setText(null);
			t_m_addr.setText(null);
			t_m_call.setText(null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	private void searchUser() throws Exception {
		JOptionPane.showMessageDialog(null, "검색되었습니다.");
		t_m_name.setText(udbs.getName());
		t_m_id.setText(udbs.getIdnum());
		t_m_addr.setText(udbs.getAddr());
		t_m_call.setText(udbs.getCall());

	   }		

}
