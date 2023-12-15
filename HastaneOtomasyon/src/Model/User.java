package Model;

import java.sql.Connection;

import Helper.DBConnection;

public class User {

	private int id;
	private String tcno, sifre, tip, isim;
	DBConnection conn = new DBConnection();

	public User(int id, String tcno, String sifre, String tip, String isim) {
		this.id = id;
		this.tcno = tcno;
		this.sifre = sifre;
		this.tip = tip;
		this.isim = isim;
	}

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTcno() {
		return tcno;
	}

	public void setTcno(String tcno) {
		this.tcno = tcno;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

}
