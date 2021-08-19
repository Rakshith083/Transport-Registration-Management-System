import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import net.proteanit.sql.DbUtils;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class booking1 extends JFrame {

	private JPanel contentPane;
	public JFrame frame;
	private static final long serialVersionUID = 1L;
	private static Connection con=null;
	private PreparedStatement pst3;
	   private ResultSet rs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					booking1 frame = new booking1("","","");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	   private static JTextField bidtxt;
	   private JTextField btimetxt;
	   private JTextField bdatetxt;
	   private static JTable table_1;
	   private JTextField textField_1;
	   private JTextField textField_2;
	   
	   /*public void fillComboBox()
	   {
		   try {
			    Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","momotc21");
			    String query="select * from PASSENGER";
			    pst3=con.prepareStatement(query);
		        rs=pst3.executeQuery();
		       	   
		        while(rs.next())
		        {
		        	comboBox.addItem(rs.getString("NAME"));
		        }
		        
		        con.close();
		        
			} catch (Exception e) {
				e.printStackTrace();
			}
	   }*/
	   
	   public static void view() {
		   try 
			{
				String query="select b.Pass_Id,p.Name,p.BUS_NUMBER,Booking_ID,Booking_time,Booking_date from BOOKING b,passenger p where b.Pass_Id=p.Pass_Id";
				PreparedStatement pst1=con.prepareStatement(query);
				ResultSet rs=pst1.executeQuery();
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
	   }
	   
		public static void randgen() {
			Random r= new Random();
			int n=r.nextInt(100000)+1;
			String v=String.valueOf(n);
			bidtxt.setText("BKID"+v);
		}
	   
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public booking1(String p,String d,String b) throws Exception {
setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
		
		con=TransportManagementDB.getConnection();
		setTitle("Booking");
		//final JFrame e = new JFrame("BUS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1931, 1041);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Booking");
		label.setForeground(Color.RED);
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 75));
		label.setBounds(23, 64, 322, 104);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Booking Id :");
		label_1.setForeground(new Color(128, 0, 0));
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_1.setBounds(76, 185, 260, 60);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Booking Time :");
		label_2.setForeground(new Color(128, 0, 0));
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_2.setBounds(23, 258, 298, 50);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Booking Date :");
		label_3.setForeground(new Color(128, 0, 0));
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_3.setBounds(34, 321, 298, 59);
		contentPane.add(label_3);
		
		bidtxt = new JTextField();
		bidtxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		bidtxt.setColumns(10);
		bidtxt.setBounds(310, 187, 273, 54);
		contentPane.add(bidtxt);
		randgen();
		
		btimetxt = new JTextField();
		btimetxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		btimetxt.setColumns(10);
		btimetxt.setBounds(310, 254, 273, 54);
		contentPane.add(btimetxt);
		
		bdatetxt = new JTextField();
		bdatetxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		bdatetxt.setColumns(10);
		bdatetxt.setBounds(310, 321, 273, 54);
		contentPane.add(bdatetxt);
		bdatetxt.setText(d);
		
		textField_1 = new JTextField();
		/*textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String p=textField.getText();
				try 
				{
					String query1="select * from PASSENGER where PASS_ID='"+p+"'";
					PreparedStatement pst1=con.prepareStatement(query1);
					ResultSet rs=pst1.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});*/
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		textField_1.setColumns(10);
		textField_1.setBounds(1129, 321, 259, 54);
		contentPane.add(textField_1);
		textField_1.setText(b);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		textField_2.setColumns(10);
		textField_2.setBounds(1129, 260, 259, 54);
		contentPane.add(textField_2);
		textField_2.setText(p);
		
		JLabel label_5 = new JLabel("Bus No :");
		label_5.setForeground(new Color(128, 0, 0));
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_5.setBounds(955, 323, 162, 54);
		contentPane.add(label_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 523, 1900, 471);
		contentPane.add(scrollPane_1);
		
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		table_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table_1.setRowHeight(40);
		
		JButton button = new JButton("View");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				view();
			}
		});
		button.setFont(new Font("Times New Roman", Font.BOLD, 36));
		button.setBounds(586, 439, 130, 60);
		contentPane.add(button);
		
		JButton btnConfirmBooking = new JButton("Confirm booking...");
		btnConfirmBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String bid=bidtxt.getText();
				String btime=btimetxt.getText();
				String bdate=bdatetxt.getText() ;
				String pid=textField_2.getText();
				//String bno=textField.getText();
				//String bno=(String) comboBox_1.getSelectedItem();
				 try {
				        Class.forName("oracle.jdbc.driver.OracleDriver");
				        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","momotc21");
				        
				        String sql = "Insert Into BOOKING values('"+bid+"','"+btime+"','"+bdate+"','"+pid+"')";
				        PreparedStatement pst=con.prepareStatement(sql);
				         rs=pst.executeQuery();
				        JOptionPane.showMessageDialog(null,	"Data Inserted Successfully");
				        randgen();
				        view();		
				        btimetxt.setText(null);
						bdatetxt.setText(null);
						textField_2.setText(null);
						//textField.setText(null);
						textField_1.setText(null);
						
						Transaction frame = new Transaction(pid);
						frame.setVisible(true);
						
						dispose();
						
				    } catch(Exception e) {System.out.println(e);
				}
			}
		});
		btnConfirmBooking.setFont(new Font("Times New Roman", Font.BOLD, 36));
		btnConfirmBooking.setBounds(116, 439, 358, 60);
		contentPane.add(btnConfirmBooking);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table_1.getSelectedRow();
				System.out.println(row);
				String cell = table_1.getModel().getValueAt(row, 0).toString();
				String query="delete from BOOKING where Booking_ID='"+cell+"'";
				System.out.println(query);
				
				try 
				{
					PreparedStatement pst2=con.prepareStatement(query);
					pst2.executeQuery();
					JOptionPane.showMessageDialog(null,"Row Deleted Successful");
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			view();
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 29));
		btnCancel.setBounds(893, 444, 173, 54);
		contentPane.add(btnCancel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1923, 29);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Home");
		mnNewMenu.setFont(new Font("Times New Roman", Font.BOLD, 23));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login frame = new login();
				frame.setVisible(true);
				dispose();
			}
		});
		mntmLogOut.setFont(new Font("Times New Roman", Font.BOLD, 20));
		mnNewMenu.add(mntmLogOut);
		mnNewMenu.add(mntmExit);
		
		JMenu mnNewMenu_1 = new JMenu("Transport Agency");
		mnNewMenu_1.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}
			public void menuDeselected(MenuEvent arg0) {
			}
			public void menuSelected(MenuEvent arg0) {
				TransportAgency frame = new TransportAgency();
				frame.setVisible(true);
				dispose();
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnNewMenu_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		mnNewMenu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		menuBar.add(mnNewMenu_1);
		
		JMenu mnBooking = new JMenu("Busses");
		menuBar.add(mnBooking);
		mnBooking.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}
			public void menuDeselected(MenuEvent arg0) {
			}
			public void menuSelected(MenuEvent arg0) {
				bus frame = new bus();
				frame.setVisible(true);
				dispose();
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnBooking.setFont(new Font("Times New Roman", Font.BOLD, 23));
		
		JMenu mnBusses = new JMenu("Passenger");
		menuBar.add(mnBusses);
		mnBusses.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}
			public void menuDeselected(MenuEvent arg0) {
			}
			public void menuSelected(MenuEvent arg0) {
				Passenger frame = new Passenger();
				frame.setVisible(true);
				dispose();
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnBusses.setFont(new Font("Times New Roman", Font.BOLD, 23));
		
		JMenu mnTranzactions = new JMenu("Transactions");
		menuBar.add(mnTranzactions);
		mnTranzactions.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}
			public void menuDeselected(MenuEvent arg0) {
			}
			public void menuSelected(MenuEvent arg0) {

				Transaction frame = new Transaction("");
				frame.setVisible(true);
				dispose();
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnTranzactions.setFont(new Font("Times New Roman", Font.BOLD, 23));
		
		JButton button_4 = new JButton("Clear");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				randgen();
				btimetxt.setText(null);
				bdatetxt.setText(null);
				textField_2.setText(null);
				//textField.setText(null);
				textField_1.setText(null);
				//comboBox_1.setSelectedItem(null);

			}
		});
		button_4.setFont(new Font("Times New Roman", Font.BOLD, 29));
		button_4.setBounds(1270, 444, 129, 54);
		contentPane.add(button_4);
		
		JLabel lblPassengerName = new JLabel("Passenger ID :");
		lblPassengerName.setForeground(new Color(128, 0, 0));
		lblPassengerName.setFont(new Font("Times New Roman", Font.BOLD, 43));
		lblPassengerName.setBounds(841, 256, 280, 54);
		contentPane.add(lblPassengerName);
		
	
		
		//fillComboBox();
	
	}

}
