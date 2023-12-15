package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Worker {
	
	DBConnection conn = new DBConnection();
	Connection con = conn.connDB();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	private int user_id;
	private int clinic_id;
	
	public Worker(int user_id , int clinic_id) {
		super();
		this.clinic_id=clinic_id;
		this.user_id=user_id;
	}
	public Worker() {
		
	}
	
	public ArrayList<Worker> Ekleme(int doctor_id){
		ArrayList<Worker> list = new ArrayList<>();
		Worker obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM worker WHERE user_id="+doctor_id);

			while (rs.next()) {
				obj = new Worker();
				obj.setClinic_id(rs.getInt("clinic_id"));
				obj.setUser_id(rs.getInt("user_id"));
				list.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	public int getClinicID(int doctor_id) {
		int clinic_id = 0 ;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT *FROM worker WHERE user_id =" + doctor_id);

			while (rs.next()) {
				clinic_id =  rs.getInt("clinic_id");

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return clinic_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getClinic_id() {
		return clinic_id;
	}

	public void setClinic_id(int clinic_id) {
		this.clinic_id = clinic_id;
	}

}
