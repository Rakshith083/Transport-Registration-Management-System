import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class bus extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JComboBox<String> comboBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bus frame = new bus();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
   private static JTextField bnotxt;
   private JTextField snotxt;
   private JTextField ogntxt;
   private JTextField dsntxt; 
   private JTextField dptimetxt;
   private JTextField artimetxt;
   private static Connection con=null;
   private static ResultSet rs;
   private static PreparedStatement pst3;
   private static JTable table;
   public static JTextField AgcCode;
   
   
   
   public void fillComboBox()
   {
	  // initComponents();
	   try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","momotc21");
		    String query="select * from TRANSPORT_AGENCY";
		    PreparedStatement pst3=con.prepareStatement(query);
	         rs=pst3.executeQuery();
	       	   
	        while(rs.next())
	        {
	        	comboBox.addItem(rs.getString("Agc_Name"));
	        }
	      con.close();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
   
 
   public static void randgen() {
		/*Random r= new Random();
		int n=r.nextInt(100000)+1;
		String v=String.valueOf(n);
		bnotxt.setText("BN"+v);*/
	   try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","momotc21");
	        	
	        int getval;
	        String sql="select count(BUS_NUMBER)+1 from BUS";
	        PreparedStatement pst=con.prepareStatement(sql);
	         rs=pst.executeQuery();
					
					while(rs.next())
			        {
						getval=Integer.parseInt(rs.getString(1));
						bnotxt.setText("BN00"+getval);
			        }

	        con.close();
			}
		catch(Exception e) {e.printStackTrace();
	        	}
	}
   
   	public static void view() {
   		try 
		{
			String query="select BUS_NUMBER,SEATS_AVAILABLE,ORIGION,DESTINATION,DEPARTURE_TIME,ARRIVAL_TIME,b.AGC_CODE,Agc_Name from BUS b,TRANSPORT_AGENCY t where b.AGC_CODE=t.AGC_CODE ORDER BY BUS_NUMBER";
			PreparedStatement pst1=con.prepareStatement(query);
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
	public bus() {
		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);

		try {
			con=TransportManagementDB.getConnection();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}	
		setTitle("BUS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1928, 1042);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				String v= (String) comboBox.getSelectedItem();
				String query="select * from TRANSPORT_AGENCY Where Agc_Name=? ";
				try {
					pst3=con.prepareStatement(query);
					pst3.setString(1, v);
					rs=pst3.executeQuery();
					
					while(rs.next())
			        {
			        	String add=rs.getString("AGC_CODE");
			        	AgcCode.setText(add);
			        }
			      //con.close();
			        
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		comboBox.addItem(null);
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(1525, 276, 274, 54);
		contentPane.add(comboBox);
		
		JLabel label = new JLabel("Bus No:");
		label.setForeground(new Color(128, 0, 0));
		label.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label.setBounds(90, 217, 166, 40);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Seats :");
		label_1.setForeground(new Color(128, 0, 0));
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_1.setBounds(90, 307, 151, 54);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Origion  :");
		label_2.setForeground(new Color(128, 0, 0));
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_2.setBounds(724, 190, 179, 54);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Destination :");
		label_3.setForeground(new Color(128, 0, 0));
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_3.setBounds(665, 260, 238, 40);
		contentPane.add(label_3);
		
		bnotxt = new JTextField();
		bnotxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		bnotxt.setColumns(10);
		bnotxt.setBounds(257, 203, 272, 54);
		contentPane.add(bnotxt);
		randgen();
		
		snotxt = new JTextField();
		snotxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		snotxt.setColumns(10);
		snotxt.setBounds(257, 312, 272, 52);
		contentPane.add(snotxt);
		
		ogntxt = new JTextField();
		ogntxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		ogntxt.setColumns(10);
		ogntxt.setBounds(903, 194, 272, 54);
		contentPane.add(ogntxt);
		
		dsntxt = new JTextField();
		dsntxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		dsntxt.setColumns(10);
		dsntxt.setBounds(903, 261, 272, 52);
		contentPane.add(dsntxt);
		
		AgcCode = new JTextField();
		AgcCode.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		AgcCode.setColumns(10);
		AgcCode.setBounds(1525, 359, 274, 54);
		contentPane.add(AgcCode);
		
		JLabel label_4 = new JLabel("Departure Time :");
		label_4.setForeground(new Color(128, 0, 0));
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_4.setBounds(581, 332, 334, 40);
		contentPane.add(label_4);
		
		dptimetxt = new JTextField();
		dptimetxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		dptimetxt.setColumns(10);
		dptimetxt.setBounds(903, 327, 274, 52);
		contentPane.add(dptimetxt);
		
		JLabel label_5 = new JLabel("Agency Code :");
		label_5.setForeground(new Color(128, 0, 0));
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 43));
		label_5.setBounds(1237, 359, 286, 54);
		contentPane.add(label_5);
		
		JLabel lblArrivalTime = new JLabel("Arrival Time :");
		lblArrivalTime.setForeground(new Color(128, 0, 0));
		lblArrivalTime.setFont(new Font("Times New Roman", Font.BOLD, 43));
		lblArrivalTime.setBounds(1249, 183, 274, 40);
		contentPane.add(lblArrivalTime);
		
		artimetxt = new JTextField();
		artimetxt.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		artimetxt.setColumns(10);
		artimetxt.setBounds(1525, 181, 274, 54);
		contentPane.add(artimetxt);
		
		JLabel label_8 = new JLabel("Bus");
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 75));
		label_8.setBounds(23, 65, 220, 106);
		contentPane.add(label_8);
		
		JButton addbutton = new JButton("Add");
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bno,sno,origion,destination, departureTime,arrivalTime,agcCode;
				bno=bnotxt.getText();
				sno=snotxt.getText();
				origion=ogntxt.getText();
				destination=dsntxt.getText();						
				departureTime=dptimetxt.getText();
				arrivalTime=artimetxt.getText();
				
				agcCode=AgcCode.getText();
				//agcCode= (String) comboBox.getSelectedItem();
				    try {
				        Class.forName("oracle.jdbc.driver.OracleDriver");
				        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","momotc21");
				        
				        String sql = "Insert Into BUS values('"+bno+"','"+sno+"','"+origion+"','"+destination+"','"+departureTime+"','"+arrivalTime+"','"+agcCode+"')";
				        PreparedStatement pst=con.prepareStatement(sql);
				         rs=pst.executeQuery();
				        JOptionPane.showMessageDialog(null,	"Data Inserted Successfully");
				        
				        view();
				        randgen();
						snotxt.setText(null);
						ogntxt.setText(null);
						dsntxt.setText(null);
						dptimetxt.setText(null);
						artimetxt.setText(null);
						AgcCode.setText(null);
						comboBox.setSelectedItem(null);
				       		       
				    } catch(Exception e) {
				    	JOptionPane.showMessageDialog(null, "Can't Insert -ve Values to No_Of_Seats");
				}
			}
		});
		addbutton.setFont(new Font("Times New Roman", Font.BOLD, 29));
		addbutton.setBounds(118, 477, 107, 54);
		contentPane.add(addbutton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 571, 1886, 411);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		table.setRowHeight(40);
		
		JButton button = new JButton("View");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				view();
			}
		});
		button.setFont(new Font("Times New Roman", Font.BOLD, 29));
		button.setBounds(641, 477, 129, 54);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Delete");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int row = table.getSelectedRow();
				System.out.println(row);
				String cell = table.getModel().getValueAt(row, 0).toString();
				bnotxt.setText(1+cell);
				String query="delete from BUS where Bus_number ='"+cell+"'";
				
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
		button_1.setFont(new Font("Times New Roman", Font.BOLD, 29));
		button_1.setBounds(1141, 477, 173, 54);
		contentPane.add(button_1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(240, 255, 255));
		menuBar.setBounds(0, 0, 1910, 29);
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
		
		JButton button_2 = new JButton("Clear");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				randgen();
				snotxt.setText(null);
				ogntxt.setText(null);
				dsntxt.setText(null);
				dptimetxt.setText(null);
				artimetxt.setText(null);
				AgcCode.setText(null);
				comboBox.setSelectedItem(null);
				//comboBox_1.setSelectedItem(null);
			}
		});
		button_2.setFont(new Font("Times New Roman", Font.BOLD, 29));
		button_2.setBounds(1601, 477, 129, 54);
		contentPane.add(button_2);
		
		JLabel lblAgencyName = new JLabel("Agency Name :");
		lblAgencyName.setForeground(new Color(128, 0, 0));
		lblAgencyName.setFont(new Font("Times New Roman", Font.BOLD, 43));
		lblAgencyName.setBounds(1237, 276, 286, 54);
		contentPane.add(lblAgencyName);
		
		fillComboBox();
	}
}
