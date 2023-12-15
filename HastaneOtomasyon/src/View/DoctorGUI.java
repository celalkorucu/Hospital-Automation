package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JYearChooser;

import Helper.Helper;
import Model.Doctor;
import com.toedter.calendar.JMonthChooser;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {

	private JPanel contentPane;
	private static Doctor doctor = new Doctor();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
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
	public DoctorGUI(Doctor doctor) {
		
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
	for(int i = 0 ; i<doctor.getWhourList(doctor.getId()).size() ; i++) {
		whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
		whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
		whourModel.addRow(whourData);
	}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoþgeldiniz "+doctor.getIsim());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 356, 41);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
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
		tabbedPane.setBounds(10, 99, 714, 351);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Çalýþma Saatleri", null, panel, null);
		panel.setLayout(null);
		
		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 11, 116, 25);
		panel.add(select_date);
		
		JComboBox select_time = new JComboBox();
		select_time.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		select_time.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00"}));
		select_time.setBounds(142, 11, 60, 25);
		panel.add(select_time);
		
		JButton btn_addwhour = new JButton("Ekle");
		btn_addwhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					 date = sdp.format(select_date.getDate());	
				} catch (Exception e2) {
					
				}
				
				if(date.length()==0) {
					Helper.showMsg("Lütfen geçerli bir tarih giriniz !");
				}
				else {
					String time = " "+select_time.getSelectedItem().toString() ;
					String selectDate = date+time ;
					
						boolean control = doctor.addWhour(doctor.getId(), doctor.getIsim(), selectDate);
					if(control) {
						updateWhourModel(doctor);
						Helper.showMsg("success");
						
					}
					else {
						Helper.showMsg("error");
					}
						
					
					
				}
			}
		});
		btn_addwhour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btn_addwhour.setBounds(212, 11, 79, 25);
		panel.add(btn_addwhour);
		
		JScrollPane w_scrollwhour = new JScrollPane();
		w_scrollwhour.setBounds(0, 49, 709, 274);
		panel.add(w_scrollwhour);
		
		table_whour = new JTable(whourModel);
		w_scrollwhour.setViewportView(table_whour);
		
		JButton btn_addwhour_1 = new JButton("Sil");
		btn_addwhour_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				int selRow =  table_whour.getSelectedRow();
				if(selRow >= 0) {
					if(Helper.confirm("sure")) {
					String selectRow = table_whour.getModel().getValueAt(selRow, 0).toString();
					int selID = Integer.parseInt(selectRow);
					boolean control ;
					control = doctor.deleteWhour(selID);
					if(control) {
						updateWhourModel(doctor);
						Helper.showMsg("success");
						
					}
					else {
						Helper.showMsg("error");
					}
					
				}
				}
				else {
					Helper.showMsg("Lütfen bir tarih seçiniz");
				}
			
			}
		});
		btn_addwhour_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btn_addwhour_1.setBounds(620, 11, 79, 25);
		panel.add(btn_addwhour_1);
	}
	public void updateWhourModel(Doctor doctor) {
		DefaultTableModel clear = (DefaultTableModel) table_whour.getModel();
		clear.setRowCount(0);
		for(int i = 0 ; i<doctor.getWhourList(doctor.getId()).size() ; i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}

	}
}
