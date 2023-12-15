package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Bashekim;
import Model.Clinic;
import Model.Hasta;
import Model.Whour;
import Model.Worker;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {

	private JPanel contentPane;
	Worker worker = new Worker();
	private static Bashekim bashekim = new Bashekim();
	private static Hasta hasta = new Hasta();
	private static Clinic clinic = new Clinic();
	private DefaultTableModel doctorModel;
	private Object[] doctorData;
	private JTable tableDoctor;
	private DefaultTableModel whourModel;
	private Object[] whourData;
	private JTable tableWhour;
	private Whour whour = new Whour();
	private int selectDoctorID = 0;
	private String selectDoctorName = null;
	private JTable table_appoint;
	private DefaultTableModel appointmentModel = null;
	private Object[] appointmentData = null;
	Appointment appoint = new Appointment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
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
	public HastaGUI(Hasta hasta) {

		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2];
		colDoctor[0] = "ID";
		colDoctor[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2];

		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];

		appointmentModel = new DefaultTableModel();
		Object[] colAppo = new Object[4];
		colAppo[0] = "ID";
		colAppo[1] = "Doktor Adý";
		colAppo[2] = "Tarih";
		colAppo[3] = "Poliklinik Adý";
		appointmentModel.setColumnIdentifiers(colAppo);
		appointmentData = new Object[4];

		for (int i = 0; i < appoint.getHastaList(hasta.getId()).size(); i++) {
			appointmentData[0] = appoint.getHastaList(hasta.getId()).get(i).getId();
			appointmentData[1] = appoint.getHastaList(hasta.getId()).get(i).getDoctorName();
			appointmentData[2] = appoint.getHastaList(hasta.getId()).get(i).getAppDate();
			int clinicID = worker.getClinicID(appoint.getHastaList(hasta.getId()).get(i).getDoctorID());
			String clinic_name = clinic.Clinic_name(clinicID);
			appointmentData[3] = clinic_name;
			appointmentModel.addRow(appointmentData);
		}

		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz " + hasta.getIsim());

		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 356, 41);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çýkýþ  Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lGUI = new LoginGUI();
				lGUI.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btnNewButton.setBounds(601, 11, 123, 41);
		contentPane.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 89, 714, 361);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Randevu Sistemi", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 232, 280);
		panel.add(scrollPane);

		tableDoctor = new JTable(doctorModel);
		scrollPane.setViewportView(tableDoctor);

		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(10, 11, 200, 29);
		panel.add(lblNewLabel_1);

		JComboBox select_clinic = new JComboBox();
		select_clinic.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 14));
		select_clinic.setBounds(280, 42, 150, 35);
		select_clinic.addItem("---Poliklinik Seç---");

		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			select_clinic
					.addItem(new Item(clinic.getClinicList().get(i).getId(), clinic.getClinicList().get(i).getIsim()));
		}
		select_clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();

					DefaultTableModel clearModel = (DefaultTableModel) tableDoctor.getModel();
					clearModel.setRowCount(0);

					for (int i = 0; i < clinic.getClinicDoctorList(item.getKey()).size(); i++) {
						doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(i).getId();
						doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(i).getIsim();
						doctorModel.addRow(doctorData);
					}
				} else {
					DefaultTableModel clearModel = (DefaultTableModel) tableDoctor.getModel();
					clearModel.setRowCount(0);
				}
			}
		});
		panel.add(select_clinic);

		JLabel lblNewLabel_2 = new JLabel("Poliklinikler");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(280, 11, 150, 29);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Doktor Se\u00E7");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_1.setBounds(280, 88, 150, 29);
		panel.add(lblNewLabel_2_1);

		JButton btnNewButton_1 = new JButton("Se\u00E7");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableDoctor.getSelectedRow();
				if (row >= 0) {
					String value = (String) tableDoctor.getModel().getValueAt(row, 0).toString();
					int selID = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) tableWhour.getModel();
					clearModel.setRowCount(0);

					for (int i = 0; i < whour.getWhourList(selID).size(); i++) {
						whourData[0] = whour.getWhourList(selID).get(i).getId();
						whourData[1] = whour.getWhourList(selID).get(i).getWdate();
						whourModel.addRow(whourData);
					}

					tableWhour.setModel(whourModel);
					selectDoctorID = selID;
					selectDoctorName = tableDoctor.getModel().getValueAt(row, 1).toString();

				} else {
					Helper.showMsg("Lütfen bir doktor seçiniz ");
				}
			}
		});
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btnNewButton_1.setBounds(280, 131, 150, 35);
		panel.add(btnNewButton_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(499, 42, 200, 280);
		panel.add(scrollPane_1);

		tableWhour = new JTable(whourModel);
		scrollPane_1.setViewportView(tableWhour);
		tableWhour.getColumnModel().getColumn(0).setPreferredWidth(1);

		JLabel lblNewLabel_3 = new JLabel("Ravdevu Saatleri");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(499, 11, 200, 29);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_2_1_1 = new JLabel("Randevu");
		lblNewLabel_2_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_1_1.setBounds(280, 206, 150, 29);
		panel.add(lblNewLabel_2_1_1);

		JButton btnNewButton_1_1 = new JButton("Randevu al ");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tableWhour.getSelectedRow();
				if (selRow >= 0) {

					String date = tableWhour.getModel().getValueAt(selRow, 1).toString();
					try {
						
						boolean control = hasta.addAppointment(selectDoctorID, hasta.getId(), selectDoctorName,
								hasta.getIsim(), date);
						if (control) {
							Helper.showMsg("success");
							hasta.updateWhourStatus(selectDoctorID, date);
							updateWhourModel(selectDoctorID);
							updateAppointmentModel(hasta.getId());

						} else {
							Helper.showMsg("error");
						}
					} catch (Exception e2) {

					}

				} else {
					Helper.showMsg("Lütfen geçerli bir tarih seçiniz ");
				}
			}
		});

		btnNewButton_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btnNewButton_1_1.setBounds(280, 249, 150, 35);
		panel.add(btnNewButton_1_1);

		JPanel w_appoint = new JPanel();
		w_appoint.setBackground(Color.WHITE);
		tabbedPane.addTab("Randevularým", null, w_appoint, null);
		w_appoint.setLayout(null);

		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(10, 11, 689, 311);
		w_appoint.add(w_scrollAppoint);

		table_appoint = new JTable(appointmentModel);
		w_scrollAppoint.setViewportView(table_appoint);
		table_appoint.getColumnModel().getColumn(0).setPreferredWidth(1);
		table_appoint.getColumnModel().getColumn(1).setPreferredWidth(200);

		JPopupMenu randevuMenu = new JPopupMenu();
		JMenuItem deleteMenu = new JMenuItem("Ýptal");
		randevuMenu.add(deleteMenu);
		table_appoint.setComponentPopupMenu(randevuMenu);
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID = Integer
							.parseInt(table_appoint.getValueAt(table_appoint.getSelectedRow(), 0).toString());

					
					whour.activate(appoint.date(selID));

					if (appoint.deleteAppoint(selID)) {
						Helper.showMsg("success");
						updateAppointmentModel(hasta.getId());

					} else {
						Helper.showMsg("error");
					}

				}

			}
		});
		table_appoint.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				Point point = e.getPoint();
				int selectedRow = table_appoint.rowAtPoint(point);
				table_appoint.setRowSelectionInterval(selectedRow, selectedRow);

			}
		});

	}

	public void updateWhourModel(int doctor_id) {
		DefaultTableModel clearModel = (DefaultTableModel) tableWhour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getClinicDoctorList(doctor_id).size(); i++) {
			whourData[0] = clinic.getClinicDoctorList(doctor_id).get(i).getId();
			whourData[1] = clinic.getClinicDoctorList(doctor_id).get(i).getIsim();
			doctorModel.addRow(doctorData);
		}

	}

	public void updateAppointmentModel(int hasta_id) {
		DefaultTableModel clearModel = (DefaultTableModel) tableWhour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < appoint.getHastaList(hasta_id).size(); i++) {
			appointmentData[0] = appoint.getHastaList(hasta_id).get(i).getId();
			appointmentData[1] = appoint.getHastaList(hasta_id).get(i).getDoctorName();
			appointmentData[2] = appoint.getHastaList(hasta_id).get(i).getAppDate();
			int clinicID = worker.getClinicID(appoint.getHastaList(hasta_id).get(i).getDoctorID());
			String clinic_name = clinic.Clinic_name(clinicID);
			appointmentData[3] = clinic_name;
			appointmentModel.addRow(appointmentData);
		}

	}
}
