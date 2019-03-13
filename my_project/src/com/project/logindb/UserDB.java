package com.project.logindb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.test.member.MemberUpdate;

public class UserDB {
	Connection con;
	boolean check;
	private ResultSet rs;

	public UserDB() throws Exception {
		con = DBconnect.getUserConnection();
	}

	public void insertUser1(UserDBSave udbs) throws Exception {
		System.out.println("insertuser");
		con.setAutoCommit(false);

		String sql = "insert into user_info(name, idnum, addr, call) values(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		System.out.println("insert : " + sql);
		ps.setString(1, udbs.getName());
		ps.setString(2, udbs.getIdnum());
		ps.setString(3, udbs.getAddr());
		ps.setString(4, udbs.getCall());

		int r1 = ps.executeUpdate();
		System.out.println(r1);
		if (r1 != 1) {
			con.rollback();
		}
		con.commit();
		ps.close();
		con.setAutoCommit(true);

	}

	public boolean checkUser(UserDBSave udbs) throws Exception {

		System.out.println("checkUser");
		   String sql="select name,idnum from user_info where name=? and idnum=?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, udbs.getName());
		ps.setString(2, udbs.getIdnum());
		System.out.println("select : " + sql);

		System.out.println("¿Ã∏ß " + udbs.getName());
		System.out.println("¡÷πŒ " + udbs.getIdnum());

		rs=ps.executeQuery();
		
		if (rs.next()) {
			check=true;
		}else {
			check=false;
		}
		
		ps.close();
		return check;

	}

	public UserDBSave searchUser(String str) throws Exception { 
		UserDBSave udbs=new UserDBSave();
		String sql="SELECT * FROM user_info "
				+ "WHERE name LIKE '%"+str+"%'";
		
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		
		while (rs.next()) {
			udbs.setName(rs.getString("name"));
			udbs.setIdnum(rs.getString("idnum"));
			udbs.setAddr(rs.getString("addr"));
			udbs.setCall(rs.getString("call"));
		}
		rs.close();
		ps.close();
		
		return udbs;
	}

	public void UserUpdate(UserDBSave udbs) throws Exception {
		String sql="update user_info set name=?,idnum=?,addr=?,call=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, udbs.getName());
		ps.setString(2, udbs.getIdnum());
		ps.setString(3, udbs.getAddr());
		ps.setString(4, udbs.getCall());
		
		ps.executeQuery();
		ps.close();
		
	}


}
