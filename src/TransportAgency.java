import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import java.util.Calendar;
import java.util.Date;

public class TransportAgency extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField agcCodetxt;
	private JTextField nametxt;
	private JTextField noBustxt;
	private JTextField headtxt;
	private static Connection con=null;
	private static ResultSet rs;
	private static JTable table;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransportAgency frame = new TransportAgency();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void view() {
		try 
		{
			String query="select * from TRANSPORT_AGENCY Order by AGC_CODE ASC";
			PreparedStatement pst1=con.prepareStatement(query);
			ResultSet rs=pst1.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	 //Generating Random number
	public static void randgen() {
		 try {
		        Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","momotc21");
		        	
		        int getval;
		        String sql="select count(AGC_CODE)+1 from TRANSPORT_AGENCY";
		        PreparedStatement pst=con.prepareStatement(sql);
		         rs=pst.executeQuery();
						
						while(rs.next())
				        {
							getval=Integer.parseInt(rs.getString(1));
							agcCodetxt.setText("AGC000"+getval);
				        }

		        con.close();
				}
			catch(Exception e) {e.printStackTrace();
		        	}
			}
	
	
	/**
	 * Create the frame.
	 */
	public TransportAgency() {
		//initComponents();
		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
		
		setTitle("Transport Agency");
		try {
			con=TransportManagementDB.getConnection();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1923, 1033);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Transport Agency");
		label.setForeground(Color.RED);
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 75));
		label.setBounds(12, 47, 566, 104);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("AgcCode :");
		label_1.setForeground(new Color(128, 0, 0));
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_1.setBounds(68, 198, 231, 54);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Name :");
		label_2.setForeground(new Color(128, 0, 0));
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_2.setBounds(117, 276, 142, 40);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("NoOfBusses :");
		label_3.setForeground(new Color(128, 0, 0));
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_3.setBounds(12, 329, 247, 54);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("HeadQuater :");
		label_4.setForeground(new Color(128, 0, 0));
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_4.setBounds(12, 396, 260, 54);
		contentPane.add(label_4);
		
		agcCodetxt = new JTextField();
		agcCodetxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		agcCodetxt.setColumns(10);
		agcCodetxt.setBounds(271, 198, 282, 46);
		contentPane.add(agcCodetxt);
		randgen();
		
		
		nametxt = new JTextField();
		nametxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		nametxt.setColumns(10);
		nametxt.setBounds(271, 265, 282, 46);
		contentPane.add(nametxt);
		
		noBustxt = new JTextField();
		noBustxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		noBustxt.setColumns(10);
		noBustxt.setBounds(271, 337, 283, 46);
		contentPane.add(noBustxt);
		
		headtxt = new JTextField();
		headtxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		headtxt.setColumns(10);
		headtxt.setBounds(272, 404, 282, 46);
		contentPane.add(headtxt);
		
		JButton addbutton = new JButton("Add");
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String uid="system";
			String pw="momotc21";
				    try {
				        Class.forName("oracle.jdbc.driver.OracleDriver");
				        Connection con = DriverManager.getConnection(url,uid,pw);
				        CallableStatement stm=con.prepareCall("{call PROCE(?,?,?,?)}");
				        stm.setString(1,agcCodetxt.getText());
				        stm.setString(2,nametxt.getText());
				        stm.setString(3,noBustxt.getText());
				        stm.setString(4,headtxt.getText());
				        stm.execute();
				        randgen();
				        JOptionPane.showMessageDialog(null,	"Data Inserted Successfully");
				        
				        view();
				        
				        nametxt.setText(null);
						 noBustxt.setText(null);
						 headtxt.setText(null);
				       		       
				    } catch(Exception e) {
				    	e.printStackTrace();
				    	JOptionPane.showMessageDialog(null," Can't insert -ve/ null value to No_Of_Busses");
				}
			}
		});
		addbutton.setFont(new Font("Times New Roman", Font.BOLD, 29));
		addbutton.setBounds(677, 370, 107, 54);
		contentPane.add(addbutton);
		
		JButton clearbutton = new JButton("Clear");
		clearbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 randgen();
				 nametxt.setText(null);
				 noBustxt.setText(null);
				 headtxt.setText(null);
			}
		});
		clearbutton.setFont(new Font("Times New Roman", Font.BOLD, 29));
		clearbutton.setBounds(1465, 370, 129, 54);
		contentPane.add(clearbutton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 515, 1905, 471);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		table.setRowHeight(40);
		
		JButton viewbutton = new JButton("View");
		viewbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				view();
			}
		});
		viewbutton.setFont(new Font("Times New Roman", Font.BOLD, 29));
		viewbutton.setBounds(887, 370, 129, 54);
		contentPane.add(viewbutton);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1905, 34);
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
		
		JMenu mnBusses = new JMenu("Busses");
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
		menuBar.add(mnBusses);
		
		JMenu mnBooking = new JMenu("Booking");
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
		
		JMenu mnNewMenu_1 = new JMenu("Passenger");
		mnNewMenu_1.addMenuListener(new MenuListener() {
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
		mnNewMenu_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		mnNewMenu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		menuBar.add(mnNewMenu_1);
		mnBooking.setFont(new Font("Times New Roman", Font.BOLD, 23));
		menuBar.add(mnBooking);
		
		JMenu mnTranzactions = new JMenu("Transactions");
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
		menuBar.add(mnTranzactions);
		
		JButton deletebutton = new JButton("Delete");
		deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				System.out.println(row);
				String cell = table.getModel().getValueAt(row, 0).toString();
				agcCodetxt.setText(1+cell);
				String query="delete from TRANSPORT_AGENCY where AGC_CODE='"+cell+"'";
				System.out.println(query);
				//delcel(cell);
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
		deletebutton.setBounds(1127, 370, 173, 54);
		contentPane.add(deletebutton);
		
		}
	}

