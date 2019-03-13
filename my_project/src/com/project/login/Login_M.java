package com.project.login;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.project.bookmain.Bookmain;
import com.project.logindb.UserDB;
import com.project.logindb.UserDBSave;
import com.test.member.Member;
import com.test.member.MemberUpdate;

public class Login_M extends JFrame implements ActionListener {

	// 필드생성
	JPanel M_panel, L_panel, T_panel, B_panel;
	JLabel name_lable, pass_lable;
	JTextField name_text;
	JButton log_b, in_b, update_b;
	JPasswordField pass_text;
	UserDB ud;

	private boolean flag;

	public void connect_Log_DB() {
		try {
			ud = new UserDB();
			System.out.println("데이터베이스 연결 성공");
		} catch (Exception e) {
			System.out.println("데이터베이스 연결 실패");
		}
	}

	public Login_M() {
		connect_Log_DB();// DB연결

		// 패널생성
		M_panel = new JPanel();
		M_panel.setLayout(new BorderLayout());

		L_panel = new JPanel();
		L_panel.setLayout(new GridLayout(0, 1));

		T_panel = new JPanel();
		T_panel.setLayout(new GridLayout(0, 1));

		B_panel = new JPanel();
		B_panel.setLayout(new GridLayout(0, 1));

		// jframe구성
		name_lable = new JLabel("                  I     D");
		
		pass_lable = new JLabel("          PASSWORD");
		name_text = new JTextField(10);
		pass_text= new JPasswordField();
		pass_text.setEchoChar('*');

		log_b = new JButton("로그인");
		in_b = new JButton("회원가입");
		update_b = new JButton("회원정보수정");

		// 리스너부착
		log_b.addActionListener(this);
		in_b.addActionListener(this);
		update_b.addActionListener(this);

		// 패널부착
		L_panel.add(name_lable);
		L_panel.add(pass_lable);
		T_panel.add(name_text);
		T_panel.add(pass_text);
		B_panel.add(log_b);
		B_panel.add(in_b);
		B_panel.add(update_b);

		M_panel.add(L_panel, BorderLayout.WEST);
		M_panel.add(T_panel, BorderLayout.EAST);
		M_panel.add(B_panel, BorderLayout.SOUTH);

		add(M_panel);

		// 기본설정
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setTitle("Login");
		setSize(260, 200);
		setLocation(screenSize.width / 2 - 130, screenSize.height / 2 - 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	// 액션구현부
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == log_b) {
			flag = logcheck();
			if (flag == true) {
				JOptionPane.showMessageDialog(null, "로그인되었습니다.");
				Bookmain bookmain = new Bookmain();
				setVisible(false);
				addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						dispose();
					}
				});
			} else if (flag == false) {
				JOptionPane.showMessageDialog(null, "로그인 실패");
			}

		} else if (e.getSource() == in_b) {
			Member member = new Member();
			setVisible(false);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});

		} else if (e.getSource() == update_b) {
			MemberUpdate memberupdate = new MemberUpdate();
			setVisible(false);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}
	}

	// 로그인 체크
	public boolean logcheck() {
		boolean check = false;
		UserDBSave udbs = new UserDBSave();
		udbs.setName(name_text.getText());
		udbs.setIdnum(pass_text.getText());

		try {
			ud = new UserDB();
			check = ud.checkUser(udbs);
			System.out.println("Check : " + check);
			name_text.setText(null);
			pass_text.setText(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;

	}

	public static void main(String[] args) {
		Login_M login_m = new Login_M();
	}
}
