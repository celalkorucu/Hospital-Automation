package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIsim = new JLabel("Ad Soyad");
		lblIsim.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblIsim.setBounds(10, 0, 220, 31);
		contentPane.add(lblIsim);
		
		fld_name = new JTextField();
		fld_name.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 17));
		fld_name.setColumns(10);
		fld_name.setBackground(Color.WHITE);
		fld_name.setBounds(10, 31, 264, 31);
		contentPane.add(fld_name);
		
		JLabel lblTCNo = new JLabel("T.C.  No");
		lblTCNo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblTCNo.setBounds(10, 73, 220, 31);
		contentPane.add(lblTCNo);
		
		fld_tcno = new JTextField();
		fld_tcno.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 17));
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 104, 264, 31);
		contentPane.add(fld_tcno);
		
		JLabel lblNewLabel = new JLabel("\u015Eifre");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 146, 220, 31);
		contentPane.add(lblNewLabel);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(10, 177, 264, 31);
		contentPane.add(fld_pass);
		
		JButton btn_register = new JButton("Kay\u0131t Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length() == 0 || fld_name.getText().length() == 0 || fld_pass.getText().length() == 0) {
					Helper.showMsg("fill");
				}
				else {
					boolean control  = hasta.addRegister(fld_name.getText(), fld_tcno.getText(), fld_pass.getText());
					if(control) {
						Helper.showMsg("success");
						LoginGUI login  = new LoginGUI();
						login.setVisible(true);
						dispose();
					}
					else {
						Helper.showMsg("error");
						
					}
				}
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btn_register.setBounds(10, 216, 264, 31);
		contentPane.add(btn_register);
		
		JButton btn_backto = new JButton("Geri D\u00F6n");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backto.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btn_backto.setBounds(10, 249, 264, 31);
		contentPane.add(btn_backto);
	}
}
