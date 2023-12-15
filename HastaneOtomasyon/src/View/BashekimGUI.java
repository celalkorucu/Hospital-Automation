package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.html.HTMLTableElement;

import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.MenuItem;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.JComboBox;

import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;

public class BashekimGUI extends JFrame {
	Clinic clinic = new Clinic();
	static Bashekim bashekim = new Bashekim();
	private JPanel contentPane;
	private JTextField fldIsim;
	private JTextField fldTCNo;
	private JTextField fldSifre;
	private JTextField fldDoktorID;
	private JTable tableDoktor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTextField fldClinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData;
	private JTable tableClinic;
	private JTable clinicworker;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BashekimGUI(Bashekim bashekim) {

		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC No";
		colDoctorName[3] = "Þifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getIsim();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getSifre();
			doctorModel.addRow(doctorData);
		}
		// Clinic Model
		clinicModel = new DefaultTableModel();
		Object[] colClinicName = new Object[2];
		colClinicName[0] = "ID";
		colClinicName[1] = "Poliklinik Adý";
		clinicModel.setColumnIdentifiers(colClinicName);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			clinicData[0] = clinic.getClinicList().get(i).getId();
			clinicData[1] = clinic.getClinicList().get(i).getIsim();
			clinicModel.addRow(clinicData);
		}

		// worker Model

		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		setBackground(Color.WHITE);
		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz Sayýn " + bashekim.getIsim() + " bey");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 356, 41);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çýkýþ Yap");
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
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(10, 74, 714, 376);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();

		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Yönetimi", null, panel, null);
		panel.setLayout(null);

		JLabel lblIsim = new JLabel("Ad Soyad");
		lblIsim.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblIsim.setBounds(479, 5, 220, 31);
		panel.add(lblIsim);

		fldIsim = new JTextField();
		fldIsim.setBackground(Color.WHITE);
		fldIsim.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 17));
		fldIsim.setBounds(479, 36, 220, 31);
		panel.add(fldIsim);
		fldIsim.setColumns(10);

		JLabel lblTCNo = new JLabel("T.C.  No");
		lblTCNo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblTCNo.setBounds(479, 65, 220, 31);
		panel.add(lblTCNo);

		fldTCNo = new JTextField();
		fldTCNo.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 17));
		fldTCNo.setBounds(479, 95, 220, 31);
		panel.add(fldTCNo);
		fldTCNo.setColumns(10);
		fldTCNo.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

				char kartNumaraGirilenDeger = e.getKeyChar();

				if ((kartNumaraGirilenDeger < '0' || kartNumaraGirilenDeger > '9') && kartNumaraGirilenDeger != '\b') {
					e.consume();
				}
				if (fldTCNo.getText().length() > 10) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		JLabel lblSifre = new JLabel("\u015Eifre");
		lblSifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblSifre.setBounds(479, 126, 220, 31);
		panel.add(lblSifre);

		fldSifre = new JTextField();
		fldSifre.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 17));
		fldSifre.setBounds(479, 155, 220, 31);
		panel.add(fldSifre);
		fldSifre.setColumns(10);

		fldSifre.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

				char kartNumaraGirilenDeger = e.getKeyChar();

				if ((kartNumaraGirilenDeger < '0' || kartNumaraGirilenDeger > '9') && kartNumaraGirilenDeger != '\b') {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		JLabel lblNewLabel_4 = new JLabel("Kullan\u0131c\u0131 ID");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(479, 239, 220, 31);
		panel.add(lblNewLabel_4);

		fldDoktorID = new JTextField();
		fldDoktorID.setEnabled(false);
		fldDoktorID.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		fldDoktorID.setBounds(479, 268, 220, 31);
		panel.add(fldDoktorID);
		fldDoktorID.setColumns(10);

		JScrollPane scrollPaneDoktor = new JScrollPane();
		scrollPaneDoktor.setBounds(10, 11, 459, 326);
		panel.add(scrollPaneDoktor);

		tableDoktor = new JTable(doctorModel);
		tableDoktor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		tableDoktor.setBackground(Color.WHITE);
		scrollPaneDoktor.setViewportView(tableDoktor);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Poliklinikler", null, panel_1, null);
		panel_1.setLayout(null);

		JPanel scrollPaneClinic_1 = new JPanel();
		scrollPaneClinic_1.setBackground(Color.WHITE);
		scrollPaneClinic_1.setBounds(-10, 11, 709, 348);
		panel_1.add(scrollPaneClinic_1);
		scrollPaneClinic_1.setLayout(null);

		JLabel lblIsim_1_1 = new JLabel("Poliklinik Ad\u0131");
		lblIsim_1_1.setBounds(240, 11, 229, 31);
		lblIsim_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		scrollPaneClinic_1.add(lblIsim_1_1);

		fldClinicName = new JTextField();
		fldClinicName.setBounds(240, 41, 229, 31);
		fldClinicName.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 17));
		fldClinicName.setColumns(10);
		fldClinicName.setBackground(Color.WHITE);
		scrollPaneClinic_1.add(fldClinicName);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 220, 326);
		scrollPaneClinic_1.add(scrollPane_2);

		JPopupMenu clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selID = Integer.parseInt(tableClinic.getValueAt(tableClinic.getSelectedRow(), 0).toString());

				Clinic selectClinic = clinic.getFech(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {

						updateClinicModel();

					}
				});

			}
		});
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(tableClinic.getValueAt(tableClinic.getSelectedRow(), 0).toString());
					if (clinic.deleteClinic(selID)) {
						Helper.showMsg("success");
						updateClinicModel();
					} else {
						Helper.showMsg("error");
					}

				}

			}
		});

		tableClinic = new JTable(clinicModel);
		scrollPane_2.setViewportView(tableClinic);

		tableClinic.setComponentPopupMenu(clinicMenu);

		tableClinic.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				Point point = e.getPoint();
				int selectedRow = tableClinic.rowAtPoint(point);
				tableClinic.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(479, 11, 220, 326);
		scrollPaneClinic_1.add(scrollPane_1_1);

		clinicworker = new JTable(workerModel);
		scrollPane_1_1.setViewportView(clinicworker);
		clinicworker.getColumnModel().getColumn(0).setPreferredWidth(5);

		JButton btnAddClinic = new JButton("Ekle");
		btnAddClinic.setBounds(240, 83, 229, 37);
		btnAddClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fldClinicName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean control = clinic.addClinic(fldClinicName.getText());
					updateClinicModel();
					if (control == true) {
						Helper.showMsg("success");
						fldClinicName.setText(null);

					}
				}

			}
		});
		btnAddClinic.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		scrollPaneClinic_1.add(btnAddClinic);

		JComboBox selectDoctorc = new JComboBox();

		selectDoctorc.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 14));
		selectDoctorc.setBounds(240, 242, 229, 37);
		scrollPaneClinic_1.add(selectDoctorc);
		JButton btnDoktorEkle = new JButton("Ekle");
		btnDoktorEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fldIsim.getText().length() == 0 || fldSifre.getText().length() == 0
						|| fldTCNo.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean control = bashekim.addDoctor(fldTCNo.getText(), fldSifre.getText(), fldIsim.getText());
					updateDoctorModel();
					if (control == true) {

						selectDoctorc.removeAllItems();

						for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
							selectDoctorc.addItem(new Item(bashekim.getDoctorList().get(i).getId(),
									bashekim.getDoctorList().get(i).getIsim()));
							System.out.println("Doktorlar : " + bashekim.getDoctorList().get(i).getIsim() + ",");
						}

						Helper.showMsg("success");

						fldTCNo.setText(null);
						fldSifre.setText(null);
						fldIsim.setText(null);

					}
				}

			}
		});
		btnDoktorEkle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btnDoktorEkle.setBounds(479, 191, 220, 37);
		panel.add(btnDoktorEkle);

		JButton btnNewButton_2 = new JButton("Sil");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (fldDoktorID.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçiniz ! ");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fldDoktorID.getText());
						boolean control = bashekim.deleteDoctor(selectID);
						if (control) {
							Helper.showMsg("success");
							selectDoctorc.removeAllItems();

							 bashekim.updateApointmentDoctorModel(selectID);
							 bashekim.updateApointmentHastaDoctor(selectID);
						
							for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
								selectDoctorc.addItem(new Item(bashekim.getDoctorList().get(i).getId(),
										bashekim.getDoctorList().get(i).getIsim()));
								
							}

							fldDoktorID.setText(null);
							updateDoctorModel();

						}
					}
				}
			}
		});
		btnNewButton_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btnNewButton_2.setBounds(479, 311, 220, 37);
		panel.add(btnNewButton_2);

		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			selectDoctorc.addItem(
					new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getIsim()));
			System.out.println("Doktorlar : " + bashekim.getDoctorList().get(i).getIsim() + ",");
		}

		selectDoctorc.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();

		});

		JButton btnAddWorker = new JButton("Ekle");
		btnAddWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tableClinic.getSelectedRow();
				if (selRow >= 0) {

					String selClinic = tableClinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) selectDoctorc.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);
						if (control) {
							Helper.showMsg("success");

						} else {
							Helper.showMsg("error");
						}

					} catch (Exception e2) {

					}

				} else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz ");
				}
			}
		});
		btnAddWorker.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btnAddWorker.setBounds(240, 284, 229, 37);
		scrollPaneClinic_1.add(btnAddWorker);

		JLabel lblIsim_1_1_1 = new JLabel("Poliklinik Ad\u0131");
		lblIsim_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblIsim_1_1_1.setBounds(240, 145, 229, 31);
		scrollPaneClinic_1.add(lblIsim_1_1_1);

		JButton btnAddClinic_1 = new JButton("Seç");
		btnAddClinic_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tableClinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = tableClinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel;
					clearModel = (DefaultTableModel) clinicworker.getModel();
					clearModel.setRowCount(0);

					for (int i = 0; i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
						workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
						workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getIsim();
						workerModel.addRow(workerData);
					}
					clinicworker.setModel(workerModel);

				} else {
					Helper.showMsg("Lütfen Klinik Seçiniz ");
				}
			}
		});
		btnAddClinic_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btnAddClinic_1.setBounds(240, 175, 229, 37);
		scrollPaneClinic_1.add(btnAddClinic_1);
		tableDoktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fldDoktorID.setText(tableDoktor.getValueAt(tableDoktor.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
				}

			}
		});

		tableDoktor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(tableDoktor.getValueAt(tableDoktor.getSelectedRow(), 0).toString());
					String selectIsim = tableDoktor.getValueAt(tableDoktor.getSelectedRow(), 1).toString();
					String tcno = tableDoktor.getValueAt(tableDoktor.getSelectedRow(), 2).toString();
					String sifre = tableDoktor.getValueAt(tableDoktor.getSelectedRow(), 3).toString();

					boolean control = bashekim.updateDoctor(selectIsim, tcno, sifre, selectID);

				}

			}
		});

	}

	public void updateDoctorModel() {
		DefaultTableModel clear = (DefaultTableModel) tableDoktor.getModel();
		clear.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getIsim();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getSifre();
			doctorModel.addRow(doctorData);
		}

	}

	public void updateClinicModel() {
		DefaultTableModel clear = (DefaultTableModel) tableClinic.getModel();
		clear.setRowCount(0);
		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			clinicData[0] = clinic.getClinicList().get(i).getId();
			clinicData[1] = clinic.getClinicList().get(i).getIsim();
			clinicModel.addRow(clinicData);
		}

	}

}
