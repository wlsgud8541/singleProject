package com.project.bookdatadb;

import java.sql.Date;

public class bookDBSave {
	int b_code;
	String b_cf;
	String b_name;
	String b_publisher;
	String b_year;
	String b_writer;
	String b_story;
	String b_use;
	String b_Imgfname;
	
	
	public String getB_Imgfname() {
		return b_Imgfname;
	}
	
	public void setB_Imgfname(String b_Imgfname) {
		this.b_Imgfname = b_Imgfname;
	}

	public String getB_use() {
		return b_use;
	}

	public void setB_use(String b_use) {
		this.b_use = b_use;
	}

	public int getB_code() {
		return b_code;
	}

	public void setB_code(int b_code) {
		this.b_code = b_code;
	}

	public String getB_cf() {
		return b_cf;
	}

	public void setB_cf(String b_cf) {
		this.b_cf = b_cf;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

	public String getB_publisher() {
		return b_publisher;
	}

	public void setB_publisher(String b_publisher) {
		this.b_publisher = b_publisher;
	}

	public String getB_year() {
		return b_year;
	}

	public void setB_year(String string) {
		this.b_year = string;
	}

	public String getB_writer() {
		return b_writer;
	}

	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}

	public String getB_story() {
		return b_story;
	}

	public void setB_story(String b_story) {
		this.b_story = b_story;
	}

}
