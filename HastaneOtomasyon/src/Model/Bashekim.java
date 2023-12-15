package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Bashekim extends User {

	Connection con = conn.connDB();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Bashekim(int id, String tcno, String sifre, String tip, String isim) {
		super(id, tcno, sifre, tip, isim);
	}

	public Bashekim() {

	}

	public ArrayList<User> getDoctorList() {
		ArrayList<User> list = new ArrayList<>();
		User obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tip = 'doktor'");

			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("sifre"), rs.getString("tip"),
						rs.getString("isim"));
				list.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	public ArrayList<User> getClinicDoctorList(int clinic_id) {
		ArrayList<User> list = new ArrayList<>();
		User obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT u.id , u.tcno , u.sifre , u.isim , u.tip FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id = "
							+ clinic_id);

			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("sifre"), rs.getString("tip"),
						rs.getString("isim"));
				list.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	public boolean addDoctor(String tcno, String sifre, String isim) {

		String query = "INSERT INTO user" + "(tcno,sifre,isim,tip) VALUES" + "(?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, sifre);
			preparedStatement.setString(3, isim);
			preparedStatement.setString(4, "doktor");
			preparedStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;

	}

	public boolean deleteDoctor(int id) {

		String query = "DELETE FROM user WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;

	}

	public boolean updateDoctor(String isim, String tcno, String sifre, int id) {

		String query = "UPDATE user SET isim=? , tcno=? , sifre=? WHERE id =?";

		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, isim);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, sifre);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;

	}

	public boolean addWorker(int user_id, int clinic_id) {

		String query = "INSERT INTO worker " + "(user_id , clinic_id) VALUES" + "(?,?)";

		boolean key = false;

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, clinic_id);
			preparedStatement.executeUpdate();

			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;

	}

	public boolean updateApointmentDoctorModel(int doctor_id) {

		String query = "DELETE FROM worker WHERE user_id =?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;

	}



	public boolean updateApointmentHastaDoctor(int doctor_id) {

		String query = "DELETE FROM appointment WHERE doctor_id =?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;

	}

}
