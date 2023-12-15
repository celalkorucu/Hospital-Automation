package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;

import com.toedter.calendar.JDayChooser;



public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField hasta_TC;
	private JTextField doktorTC;
	private JPasswordField doktorSifre;
	private JPasswordField hasta_pass;
	private DBConnection conn = new DBConnection();

	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setForeground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel logo = new JLabel(new ImageIcon(getClass().getResource("HastaneImage.png")));
		logo.setBounds(186, 11, 82, 70);
		contentPane.add(logo);

		JLabel hosgeldiniz = new JLabel("Hastane Yönetim Sistemine Hoþgeldiniz");
		hosgeldiniz.setBounds(43, 93, 376, 33);
		hosgeldiniz.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		contentPane.add(hosgeldiniz);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 140, 464, 210);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Hasta Giriþ", null, panel, null);
		panel.setLayout(null);

		JLabel hastaTCGiris = new JLabel("TC Numaranýzý Giriniz :");
		hastaTCGiris.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		hastaTCGiris.setBounds(12, 25, 172, 33);
		panel.add(hastaTCGiris);

		JLabel hastaSifreGiris = new JLabel("Þifre :");
		hastaSifreGiris.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		hastaSifreGiris.setBounds(12, 69, 172, 33);
		panel.add(hastaSifreGiris);

		hasta_TC = new JTextField();
		hasta_TC.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 15));
		hasta_TC.setBackground(Color.WHITE);
		hasta_TC.setBounds(194, 25, 257, 33);
		panel.add(hasta_TC);
		hasta_TC.setColumns(10);
		
		
		JButton btnHastaGiris = new JButton("Giriþ Yap");
		btnHastaGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (hasta_TC.getText().length() == 0 || hasta_pass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

					try {
						Connection con = conn.connDB();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (hasta_TC.getText().equals(rs.getString("tcno"))
									&& hasta_pass.getText().equals(rs.getString("sifre"))) {
								
								if(rs.getString("tip").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setIsim(rs.getString("isim"));
									hasta.setSifre(rs.getString("sifre"));
									hasta.setTcno(rs.getString("tcno"));
									hasta.setTip(rs.getString("tip"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
								}
					
							}

						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
		
			}
		});
		btnHastaGiris.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnHastaGiris.setBounds(246, 124, 205, 44);
		panel.add(btnHastaGiris);

		JButton btnHastaKayýt = new JButton("Kayýt Ol");
		btnHastaKayýt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new  RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btnHastaKayýt.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnHastaKayýt.setBounds(12, 125, 205, 44);
		panel.add(btnHastaKayýt);

		hasta_pass = new JPasswordField();
		hasta_pass.setBounds(194, 69, 257, 33);
		panel.add(hasta_pass);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Giriþ", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel doktorTCGiris = new JLabel("TC Numaranýzý Giriniz :");
		doktorTCGiris.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		doktorTCGiris.setBounds(12, 25, 172, 33);
		panel_1.add(doktorTCGiris);

		doktorTC = new JTextField();
		doktorTC.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 15));
		doktorTC.setColumns(10);
		doktorTC.setBackground(Color.WHITE);
		doktorTC.setBounds(194, 25, 257, 33);
		panel_1.add(doktorTC);

		JLabel doktorSifreGiris = new JLabel("Þifre :");
		doktorSifreGiris.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		doktorSifreGiris.setBounds(12, 69, 172, 33);
		panel_1.add(doktorSifreGiris);

		JButton btnDoktorGiris = new JButton("Giriþ Yap");
		btnDoktorGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doktorTC.getText().length() == 0 || doktorSifre.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

					try {
						Connection con = conn.connDB();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (doktorTC.getText().equals(rs.getString("tcno"))
									&& doktorSifre.getText().equals(rs.getString("sifre"))) {
								
								if(rs.getString("tip").equals("bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setIsim(rs.getString("isim"));
									bhekim.setSifre(rs.getString("sifre"));
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setTip(rs.getString("tip"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								if(rs.getString("tip").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setIsim(rs.getString("isim"));
									doctor.setSifre(rs.getString("sifre"));
									doctor.setTcno(rs.getString("tcno"));
									doctor.setTip(rs.getString("tip"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
							}

						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

			}
		});
		btnDoktorGiris.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btnDoktorGiris.setBounds(59, 125, 332, 44);
		panel_1.add(btnDoktorGiris);

		doktorSifre = new JPasswordField();
		doktorSifre.setBounds(194, 69, 257, 33);
		panel_1.add(doktorSifre);
		

	}
}
