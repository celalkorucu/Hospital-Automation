package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;

public class Hasta extends User {

	Connection con = conn.connDB();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Hasta() {
		super();

	}

	public Hasta(int id, String tcno, String sifre, String tip, String isim) {
		super(id, tcno, sifre, tip, isim);

	}

	public boolean addRegister(String name, String tcno, String password) {

		String query = "INSERT INTO user  " + "(tcno, isim , sifre ,tip) VALUES (?,?,?,?)";
		int key = 0;
		int count = 0;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tcno ='" + tcno + "'");

			while (rs.next()) {
				Helper.showMsg("Bu TC numarasýna ait bir kayýt zaten var ");
				count++;
				break;
			}

			if (count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, password);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();
				key = 1;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (key == 1) {
			return true;
		} else {
			return false;
		}

	}

	public boolean addAppointment(int doctor_id, int hasta_id, String doctor_name, String hasta_name, String app_date) {

		String query = "INSERT INTO appointment  "
				+ "(doctor_id,doctor_name,hasta_id,hasta_name,app_date) VALUES (?,?,?,?,?)";
		int key = 0;

		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.setString(2, doctor_name);
			preparedStatement.setInt(3, hasta_id);
			preparedStatement.setString(4, hasta_name);
			preparedStatement.setString(5, app_date);
			preparedStatement.executeUpdate();

			key = 1;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (key == 1) {
			return true;
		} else {
			return false;
		}

	}

	public boolean updateWhourStatus(int doctor_id, String wdate) {
		String query = "UPDATE whour SET status =? WHERE doctor_id =? AND wdate =?";

		int key = 0;

		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, doctor_id);
			preparedStatement.setString(3, wdate);

			preparedStatement.executeUpdate();
			key = 1;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (key == 1) {
			return true;
		} else {
			return false;
		}

	}

}
