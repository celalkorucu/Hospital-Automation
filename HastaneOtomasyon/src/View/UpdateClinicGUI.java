package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Clinic;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fldClinicName;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 230, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 194, 25);
		contentPane.add(lblNewLabel);

		fldClinicName = new JTextField();
		fldClinicName.setBounds(10, 39, 194, 33);
		contentPane.add(fldClinicName);
		fldClinicName.setColumns(10);

		fldClinicName.setText(clinic.getIsim());

		JButton UptadeClinicName = new JButton("D\u00FCzenle");
		UptadeClinicName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Helper.confirm("sure")) {

					clinic.updateClinic(fldClinicName.getText(), clinic.getId());
					Helper.showMsg("success");
					dispose();
				}
			}
		});
		UptadeClinicName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		UptadeClinicName.setBounds(10, 77, 194, 33);
		contentPane.add(UptadeClinicName);
	}
}
