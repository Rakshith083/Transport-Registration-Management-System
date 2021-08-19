import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import net.proteanit.sql.DbUtils;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class Transaction extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transaction frame = new Transaction("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
   private static JTextField tidtxt;
   private static JTextField amttxt;
   private static Connection con=null;
   private static JTable table;
   private JButton addbutton;
   private JButton deletebutton;
   private JButton clearbutton;
   private JScrollPane scrollPane;
   private static JTextField bid;
   private static PreparedStatement pst3;
   private static JTextField textField;
   private static JTextField textField_1;
   
   /*public void fillComboBox()
   {
	   try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","momotc21");
		    String query="select * from PASSENGER";
		   
		    PreparedStatement pst3=con.prepareStatement(query);
	        ResultSet rs=pst3.executeQuery();
	       	   
	        while(rs.next())
	        {
	        	comboBox.addItem(rs.getString("Name"));
	        	 
	        }
	        
	        String query2="select Booking_ID from BOOKING";
		   
		    pst3=con.prepareStatement(query2);
	        rs=pst3.executeQuery();
	       	   
	        while(rs.next())
	        {
	        	comboBox2.addItem(rs.getString("Booking_ID"));
	        	
	        }
	        con.close();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
   }*/
   
   public static void fill() {
	   String v= textField_1.getText();
		String query="select b.BOOKING_ID,p.DESTINATION from booking b,PASSENGER p Where b.PASS_ID='"+v+"' and p.PASS_ID=b.PASS_ID";
		try {
			pst3=con.prepareStatement(query);
			//pst3.setString(1,v);
			ResultSet rs=pst3.executeQuery();
			
			while(rs.next())
	        {

	        	bid.setText(rs.getString("BOOKING_ID"));
	        	textField.setText(rs.getString("DESTINATION"));
	        	
	        	randgenAmt();
	        }
	      //con.close();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
   
   public static void randgenTid() {
		Random r= new Random();
		int n=r.nextInt(100000)+1;
		String v=String.valueOf(n);
		tidtxt.setText("TID"+v);
	}
   
   public static void randgenAmt() {
		Random r= new Random();
		int n=r.nextInt(6500)+1;
		String v=String.valueOf(n);
		amttxt.setText(v);
	}
   
   public static void view() {
	   try 
		{
			String query1="select Name,b.PASS_ID,b.BOOKING_ID,Transaction_id,BUS_NUMBER,DESTINATION,Amount from PASSENGER p,BOOKING b,TRANSACTION t where b.PASS_ID=p.PASS_ID and t.PASS_ID=b.PASS_ID and t.BOOKING_ID=b.BOOKING_ID";
			PreparedStatement pst1=con.prepareStatement(query1);
			ResultSet rs=pst1.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
   }

	/**
	 * Create the frame.
	 */
	public Transaction(String n) {
		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
		try {
			con=TransportManagementDB.getConnection();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		setTitle("Transaction");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1931, 1040);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTransaction = new JLabel("Transaction");
		lblTransaction.setForeground(Color.RED);
		lblTransaction.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 75));
		lblTransaction.setBounds(54, 108, 385, 66);
		contentPane.add(lblTransaction);
		
		JLabel lblTransactionId = new JLabel("Transaction ID :");
		lblTransactionId.setForeground(new Color(128, 0, 0));
		lblTransactionId.setFont(new Font("Times New Roman", Font.BOLD, 43));
		lblTransactionId.setBounds(12, 205, 322, 54);
		contentPane.add(lblTransactionId);
		
		tidtxt = new JTextField();
		tidtxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		tidtxt.setColumns(10);
		tidtxt.setBounds(330, 213, 282, 46);
		contentPane.add(tidtxt);
		randgenTid();
		
		JLabel lblAmount = new JLabel("Amount :");
		lblAmount.setForeground(new Color(128, 0, 0));
		lblAmount.setFont(new Font("Times New Roman", Font.BOLD, 43));
		lblAmount.setBounds(800, 348, 183, 40);
		contentPane.add(lblAmount);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		textField.setColumns(10);
		textField.setBounds(993, 282, 282, 44);
		contentPane.add(textField);
		
		amttxt = new JTextField();
		amttxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		amttxt.setColumns(10);
		amttxt.setBounds(993, 349, 282, 46);
		contentPane.add(amttxt);
		
		bid = new JTextField();
		bid.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		bid.setColumns(10);
		bid.setBounds(327, 349, 282, 46);
		contentPane.add(bid);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		textField_1.setColumns(10);
		textField_1.setBounds(330, 285, 282, 46);
		contentPane.add(textField_1);
		textField_1.setText(n);
		
		fill();
		
		JLabel lblBookingId = new JLabel("Booking ID :");
		lblBookingId.setForeground(new Color(128, 0, 0));
		lblBookingId.setFont(new Font("Times New Roman", Font.BOLD, 43));
		lblBookingId.setBounds(67, 344, 246, 49);
		contentPane.add(lblBookingId);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 511, 1889, 482);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setRowHeight(40);
		
		JButton viewbutton = new JButton("View");
		viewbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				view();
			}
		});
		viewbutton.setFont(new Font("Times New Roman", Font.BOLD, 29));
		viewbutton.setBounds(582, 430, 129, 54);
		contentPane.add(viewbutton);
		
		addbutton = new JButton("Pay...");
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			  	   String tid = tidtxt.getText();
				   String amt = amttxt.getText();
				   String p = textField_1.getText();
				   String b =bid.getText();
				    try {
				        Class.forName("oracle.jdbc.driver.OracleDriver");
				        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","momotc21");
				        
				        String sql = "Insert Into TRANSACTION values('"+tid+"','"+amt+"','"+p+"','"+b+"')";
				        PreparedStatement pst=con.prepareStatement(sql);
				         pst.executeQuery();
				        JOptionPane.showMessageDialog(null,	"Data Inserted Successfully");
				        
				        view();
				        randgenTid();
						amttxt.setText(null);
						//comboBox.setSelectedItem(null);
						bid.setText(null);
						//pid.setText(null);
						textField.setText(null);
						
						
						
						Summary frame = new Summary(tid,p);
						frame.setVisible(true);
						
						dispose();
				       		       
				    } catch(Exception e) {System.out.println(e);
				    JOptionPane.showMessageDialog(null," Amount cant be 0 or less");
				}
			}
		});
		addbutton.setFont(new Font("Times New Roman", Font.BOLD, 29));
		addbutton.setBounds(355, 430, 107, 54);
		contentPane.add(addbutton);
		
		deletebutton = new JButton("Cancel");
		deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				System.out.println(row);
				String cell = table.getModel().getValueAt(row,3).toString();
				String query="delete from TRANSACTION where Transaction_id  ='"+cell+"'";
				
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
		deletebutton.setFont(new Font("Times New Roman", Font.BOLD, 29));
		deletebutton.setBounds(853, 430, 173, 54);
		contentPane.add(deletebutton);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1913, 29);
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
		
		JMenuItem menuItem = new JMenuItem("Log out");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login frame = new login();
				frame.setVisible(true);
				dispose();
			}
		});
		menuItem.setFont(new Font("Times New Roman", Font.BOLD, 20));
		mnNewMenu.add(menuItem);
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
		
		JMenu mnTranzactions = new JMenu("Busses");
		menuBar.add(mnTranzactions);
		mnTranzactions.addMenuListener(new MenuListener() {
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
		mnTranzactions.setFont(new Font("Times New Roman", Font.BOLD, 23));
		
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
		
		JMenu mnBooking = new JMenu("Booking");
		menuBar.add(mnBooking);
		mnBooking.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}
			public void menuDeselected(MenuEvent arg0) {
			}
			public void menuSelected(MenuEvent arg0) {
				try {
					booking1 frame = new booking1("","","");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		
		clearbutton = new JButton("Clear");
		clearbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				randgenTid();
				amttxt.setText(null);
				//comboBox.setSelectedItem(null);
				bid.setText(null);
				textField_1.setText(null);
				textField.setText(null);
				//comboBox2.setSelectedItem(null);

			}
		});
		clearbutton.setFont(new Font("Times New Roman", Font.BOLD, 29));
		clearbutton.setBounds(1169, 430, 129, 54);
		contentPane.add(clearbutton);
		
		JLabel label = new JLabel("Passenger ID :");
		label.setForeground(new Color(128, 0, 0));
		label.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label.setBounds(42, 285, 276, 54);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Destination :");
		label_1.setForeground(new Color(128, 0, 0));
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_1.setBounds(745, 284, 238, 40);
		contentPane.add(label_1);
		
		//fillComboBox();
	}
}


