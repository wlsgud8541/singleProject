package com.project.bookdatadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.project.logindb.DBconnect;

public class bookModelData {
	static Connection con;
	bookDBSave bdbs;

	public bookModelData() throws Exception {
		con = DBconnect.getUserConnection();
	}

	public ArrayList searchBook(int idx, String str) throws Exception {
		String[] key = { "b_name", "b_writer" };
		String sql = "select b_code,b_cf,b_name,b_publisher,b_writer,b_year,b_story,b_use from book_info "
				+ "where " + key[idx]+ " like '%" + str + "%'";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		System.out.println(rs);
		ArrayList data = new ArrayList();
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("b_code"));
			temp.add(rs.getString("b_cf"));
			temp.add(rs.getString("b_name"));
			temp.add(rs.getString("b_publisher"));
			temp.add(rs.getString("b_writer"));
			temp.add(rs.getString("b_year"));
			temp.add(rs.getString("b_use"));
			data.add(temp);
		}
		rs.close();
		ps.close();
		return data;
	}

	public bookDBSave selectedBybook(int no) throws Exception {
		bookDBSave bdbs = new bookDBSave();
		String sql = "select * from book_info where b_code=" + no;
		System.out.println(sql);
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			bdbs.setB_code(Integer.parseInt(rs.getString("b_code")));
			bdbs.setB_cf(rs.getString("b_cf"));
			bdbs.setB_name(rs.getString("b_name"));
			bdbs.setB_publisher(rs.getString("b_publisher"));
			bdbs.setB_year(rs.getString("b_year"));
			bdbs.setB_writer(rs.getString("b_writer"));
			bdbs.setB_story(rs.getString("b_story"));
			bdbs.setB_use(rs.getString("b_use"));
			bdbs.setB_Imgfname(rs.getString("b_pic"));
		}
		rs.close();
		ps.close();
		return bdbs;
	}


	public ArrayList researchBook(int idx, String str) throws Exception {
		String[] key = { "b_name", "b_writer" };
		String sql = "select b_code,b_cf,b_name,b_publisher,b_writer,b_year,b_story,b_use from book_info "
				+ "where " + key[idx]+ " like '%" + str + "%'";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		System.out.println(rs);
		ArrayList redata = new ArrayList();
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("b_code"));
			temp.add(rs.getString("b_cf"));
			temp.add(rs.getString("b_name"));
			temp.add(rs.getString("b_publisher"));
			temp.add(rs.getString("b_writer"));
			temp.add(rs.getString("b_year"));
			temp.add(rs.getString("b_use"));
			redata.add(temp);
		}
		rs.close();
		ps.close();
		return redata;		
	}
	public void BookRantalData(String rantalb) throws Exception {
		System.out.println(rantalb);
		String sql="update book_info set b_use='불가능' where b_code="+rantalb;
		PreparedStatement ps=con.prepareStatement(sql);
		ps.executeQuery();
		ps.close();
	}

	public void BookReturnData(String returnb) throws Exception {
		System.out.println(returnb);
		String sql="update book_info set b_use='가능' where b_code="+returnb;
		PreparedStatement ps=con.prepareStatement(sql);
		ps.executeQuery();
		ps.close();
	}

	public bookDBSave search(String str) throws Exception {
		String sql="SELECT * FROM book_info "
				+ "WHERE b_name LIKE '%"+str+"%'";
		bdbs=new bookDBSave();
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		while (rs.next()) {
			bdbs.setB_code(Integer.parseInt(rs.getString("b_code")));
			bdbs.setB_name(rs.getString("b_name"));
			bdbs.setB_publisher(rs.getString("b_publisher"));
			bdbs.setB_writer(rs.getString("b_writer"));
			bdbs.setB_cf(rs.getString("b_cf"));
			bdbs.setB_year(rs.getString("b_year"));
			bdbs.setB_story(rs.getString("b_story"));
			bdbs.setB_Imgfname(rs.getString("b_pic"));
		}
		rs.close();
		ps.close();
		
		return bdbs;
	}

	public void insertbook(bookDBSave bdbs) throws Exception {
		con.setAutoCommit(false);

		String sql = "insert into book_info (b_code,b_cf,b_name,b_publisher,b_year,b_writer,b_story,b_pic,b_use) values(?,?,?,?,?,?,?,?,'가능')";
		PreparedStatement ps = con.prepareStatement(sql);
		System.out.println("insert : " + sql);
		ps.setString(1, ""+bdbs.getB_code());
		ps.setString(2, bdbs.getB_cf());
		ps.setString(3, bdbs.getB_name());
		ps.setString(4, bdbs.getB_publisher());
		ps.setString(5, bdbs.getB_year());
		ps.setString(6, bdbs.getB_writer());
		ps.setString(7, bdbs.getB_story());
		ps.setString(8, bdbs.getB_Imgfname());
		int r1 = ps.executeUpdate();
		System.out.println(r1);
		if (r1 != 1) {
			con.rollback();
		}
		con.commit();
		ps.close();
		con.setAutoCommit(true);
	}

	public void UserUpdate(bookDBSave bdbs, String str) throws Exception {
		con.setAutoCommit(false);

		String sql="update book_info set b_code=?,b_cf=?,b_name=?,b_publisher=?,b_year=?,b_writer=?,b_story=?,b_pic=? "
				+ "where b_code="+str;
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, ""+bdbs.getB_code());
		ps.setString(2, bdbs.getB_cf());
		ps.setString(3, bdbs.getB_name());
		ps.setString(4, bdbs.getB_publisher());
		ps.setString(5, bdbs.getB_year());
		ps.setString(6, bdbs.getB_writer());
		ps.setString(7, bdbs.getB_story());
		ps.setString(8, bdbs.getB_Imgfname());
		ps.executeQuery();
		con.commit();
		ps.close();
		con.setAutoCommit(true);
	}

	public void delete(bookDBSave bdbs) throws Exception {
	      con.setAutoCommit(false);
	      String sql="delete book_info where b_code=?";
	      PreparedStatement ps1=con.prepareStatement(sql);
	      ps1.setInt(1, bdbs.getB_code());
	      
	      int r=ps1.executeUpdate();
	      if (r!=1) {
	         con.rollback();
	      }
	      con.commit();
	      ps1.close();
	      con.setAutoCommit(true);

	}

	public ArrayList bcount() throws Exception {
		String sql="select count(*) from book_info";
		PreparedStatement ps1=con.prepareStatement(sql);
		ResultSet rs=ps1.executeQuery();
		ArrayList data=new ArrayList();
		while (rs.next()) {
			data.add(rs.getString("count(*)"));
		}
		rs.close();
		ps1.close();
		return data;
	}


}

