import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class Passenger extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField datetxt;
	private static JTextField passIdtxt;
	private JTextField nametxt;
	private JTextField phonetxt;
	private static Connection con=null;
	private static ResultSet rs;
	private static JTable table;
	private JTextField desttxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Passenger frame = new Passenger();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void randgen() {
		 try {
		        Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","momotc21");
		        	
		        int getval;
		        String sql="select count(Pass_Id  )+1 from PASSENGER";
		        PreparedStatement pst=con.prepareStatement(sql);
		         rs=pst.executeQuery();
						
						while(rs.next())
				        {
							getval=Integer.parseInt(rs.getString(1));
							passIdtxt.setText("PID00"+getval);
				        }

		        con.close();
				}
			catch(Exception e) {e.printStackTrace();
		        	}
	}
	public static void view(){
		try 
		{
			String query1="select * from PASSENGER ORDER BY Pass_Id  ";
			PreparedStatement pst1=con.prepareStatement(query1);
			ResultSet rs=pst1.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void view_bno(String p) {
		try 
		{
			String query1="select * from Bus where Destination='"+p+"'";
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
	public Passenger() {
		setTitle("Passenger");
		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
		try {
			con=TransportManagementDB.getConnection();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1932, 1043);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		datetxt = new JTextField();
		datetxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		datetxt.setColumns(10);
		datetxt.setBounds(697, 204, 291, 54);
		contentPane.add(datetxt);
		
		JLabel label_3 = new JLabel("Passenger");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 75));
		label_3.setBounds(27, 72, 340, 84);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Pass_Id :");
		label_4.setForeground(new Color(128, 0, 0));
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_4.setBounds(28, 207, 174, 40);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Name :");
		label_5.setForeground(new Color(128, 0, 0));
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_5.setBounds(72, 276, 138, 40);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("Phone :");
		label_6.setForeground(new Color(128, 0, 0));
		label_6.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_6.setBounds(533, 276, 150, 54);
		contentPane.add(label_6);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(1328, 284, 274, 52);
		//comboBox.addItem(null);
		
		   
		contentPane.add(comboBox);
		
		passIdtxt = new JTextField();
		passIdtxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		passIdtxt.setColumns(10);
		passIdtxt.setBounds(215, 198, 272, 54);
		contentPane.add(passIdtxt);
		randgen();
		
		nametxt = new JTextField();
		nametxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		nametxt.setColumns(10);
		nametxt.setBounds(215, 278, 272, 52);
		contentPane.add(nametxt);
		
		phonetxt = new JTextField();
		phonetxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		phonetxt.setColumns(10);
		phonetxt.setBounds(697, 280, 291, 55);
		contentPane.add(phonetxt);
		
		desttxt = new JTextField();
		desttxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String v=desttxt.getText();
				try {
				    Class.forName("oracle.jdbc.driver.OracleDriver");
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","momotc21");
				    String query="select BUS_NUMBER from BUS where Destination ='"+v+"'";
				    PreparedStatement pst3=con.prepareStatement(query);
			         rs=pst3.executeQuery();
			       	   
			        while(rs.next())
			        {
			        	comboBox.addItem(rs.getString("BUS_NUMBER"));
			        }
			        view_bno(v);
					
			        con.close();
			        
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		desttxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		desttxt.setColumns(10);
		desttxt.setBounds(1328, 217, 291, 54);
		contentPane.add(desttxt);
		
		JLabel lblBusNo = new JLabel("Bus No :");
		lblBusNo.setForeground(new Color(128, 0, 0));
		lblBusNo.setFont(new Font("Times New Roman", Font.BOLD, 43));
		lblBusNo.setBounds(1150, 295, 166, 40);
		contentPane.add(lblBusNo);
		
		
		JButton addbutton = new JButton("Book Now...");
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String passId = passIdtxt.getText();
				   String name = nametxt.getText();
				   String phone = phonetxt.getText();
				   String da =datetxt.getText();
				   String dest=desttxt.getText();
				   String busno=(String) comboBox.getSelectedItem();
				    try {
				        Class.forName("oracle.jdbc.driver.OracleDriver");
				        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","momotc21");
				        
				        String sql = "Insert Into PASSENGER values('"+passId+"','"+name+"','"+phone+"','"+da+"','"+dest+"','"+busno+"')";
				        PreparedStatement pst=con.prepareStatement(sql);
				        rs=pst.executeQuery();
				        JOptionPane.showMessageDialog(null,	"Data Inserted Successfully");
				        
				        view();
				        randgen();
						nametxt.setText(null);
						phonetxt.setText(null);
						desttxt.setText(null);
						comboBox.removeAllItems();
						datetxt.setText(null);
				        
						booking1 frame = new booking1(passId,da,busno);
						frame.setVisible(true);
						
						dispose();
				       		       
				    } catch(Exception e) {System.out.println(e);
				}
			}
		});
		addbutton.setFont(new Font("Times New Roman", Font.BOLD, 29));
		addbutton.setBounds(240, 417, 189, 54);
		contentPane.add(addbutton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 532, 1890, 451);
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
		viewbutton.setBounds(588, 417, 147, 54);
		contentPane.add(viewbutton);
		
		JButton deletebutton = new JButton("Delete");
		deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				System.out.println(row);
				String cell = table.getModel().getValueAt(row, 0).toString();
				passIdtxt.setText(1+cell);
				String query="delete from PASSENGER where Pass_Id ='"+cell+"'";
				
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
		deletebutton.setBounds(999, 417, 147, 54);
		contentPane.add(deletebutton);
		
		JButton clearbutton = new JButton("Clear");
		clearbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				randgen();
				nametxt.setText(null);
				phonetxt.setText(null);
				desttxt.setText(null);
				comboBox.removeAllItems();
				datetxt.setText(null);
			}
		});
		clearbutton.setFont(new Font("Times New Roman", Font.BOLD, 29));
		clearbutton.setBounds(1429, 417, 147, 54);
		contentPane.add(clearbutton);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setForeground(new Color(128, 0, 0));
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 43));
		lblDate.setBounds(546, 200, 150, 54);
		contentPane.add(lblDate);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1914, 29);
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
		
		JMenu mnBusses = new JMenu("Busses");
		menuBar.add(mnBusses);
		mnBusses.addMenuListener(new MenuListener() {
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
		
		JLabel label = new JLabel("Destination :");
		label.setForeground(new Color(128, 0, 0));
		label.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label.setBounds(1080, 229, 238, 40);
		contentPane.add(label);
	}
	
}
