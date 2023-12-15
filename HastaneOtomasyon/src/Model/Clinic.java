package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Clinic {

	private int id;
	private String isim;
	
	
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Clinic(int id, String isim) {
		super();
		this.id = id;
		this.isim = isim;
	}

	public Clinic() {
	}

	public ArrayList<Clinic> getClinicList() {
		Connection con = conn.connDB();
		ArrayList<Clinic> list = new ArrayList<>();

		Clinic obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic");

			while (rs.next()) {
				obj = new Clinic();
				obj.setId(rs.getInt("id"));
				obj.setIsim(rs.getString("isim"));
				list.add(obj);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;
	}

	public boolean addClinic(String isim) {
		Connection con = conn.connDB();

		String query = "INSERT INTO clinic" + "(isim) VALUES" + "(?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, isim);
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

	public boolean deleteClinic(int id) {
		Connection con = conn.connDB();

		String query = "DELETE FROM clinic WHERE id = ?";
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

	public boolean updateClinic(String isim, int id) {
		Connection con = conn.connDB();

		String query = "UPDATE clinic SET isim=?  WHERE id =?";

		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, isim);
			preparedStatement.setInt(2, id);
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

	public Clinic getFech(int id) {
	

		Clinic c = new Clinic();

		try {

			rs = st.executeQuery("SELECT * FROM clinic WHERE id =" + id);
			while (rs.next()) {
				System.out.println("Girdi");
				c.setId(rs.getInt("id"));
				c.setIsim(rs.getString("isim"));
				break;

			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		return c;

	}
	public ArrayList<User> getClinicDoctorList(int clinic_id) {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		Connection con = conn.connDB();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT u.id , u.tcno , u.sifre , u.isim , u.tip FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id = "+clinic_id);

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
	
	public String Clinic_name(int clinic_id) {
		String clinic_name = null ;
		try {
			rs = st.executeQuery("SELECT * FROM clinic WHERE id="+clinic_id);
			
			while(rs.next()) {
				clinic_name = rs.getString("isim");
				return clinic_name ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clinic_name;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

}
