import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Summary extends JFrame {

	private JPanel ContentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Summary frame = new Summary("", "");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	   private static ResultSet rs;
	   private static PreparedStatement pst3;
	   private static Connection con;
	/**
	 * Create the frame.
	 */
	public Summary(String t,String b) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 525);
		ContentPane = new JPanel();
		ContentPane.setBackground(Color.WHITE);
		ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContentPane);
		ContentPane.setLayout(null);
		
		JLabel lblThankYouFor = new JLabel("Happy journey");
		lblThankYouFor.setForeground(Color.RED);
		lblThankYouFor.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 34));
		lblThankYouFor.setBounds(12, 13, 213, 50);
		ContentPane.add(lblThankYouFor);
		
		JLabel agc = new JLabel("");
		agc.setForeground(new Color(0, 0, 0));
		agc.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		agc.setBounds(392, 416, 268, 38);
		ContentPane.add(agc);
		
		JLabel lblHeresYourBooking = new JLabel("Here's your booking summary");
		lblHeresYourBooking.setForeground(new Color(128, 0, 0));
		lblHeresYourBooking.setFont(new Font("Times New Roman", Font.BOLD, 29));
		lblHeresYourBooking.setBounds(12, 69, 371, 44);
		ContentPane.add(lblHeresYourBooking);
		
		JLabel label_1 = new JLabel("Passenger ID :");
		label_1.setForeground(new Color(128, 0, 0));
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		label_1.setBounds(12, 115, 158, 28);
		ContentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Booking ID :");
		label_2.setForeground(new Color(128, 0, 0));
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 24));
		label_2.setBounds(27, 152, 135, 28);
		ContentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Transaction ID :");
		label_3.setForeground(new Color(128, 0, 0));
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 24));
		label_3.setBounds(1, 183, 176, 28);
		ContentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Destination :");
		label_4.setForeground(new Color(128, 0, 0));
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 24));
		label_4.setBounds(15, 259, 135, 28);
		ContentPane.add(label_4);
		
		JLabel lblAmountPayed = new JLabel("Amount Payed:");
		lblAmountPayed.setForeground(new Color(128, 0, 0));
		lblAmountPayed.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblAmountPayed.setBounds(12, 314, 167, 28);
		ContentPane.add(lblAmountPayed);
		
		JLabel lblBusNo = new JLabel("Bus No :");
		lblBusNo.setForeground(new Color(128, 0, 0));
		lblBusNo.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblBusNo.setBounds(66, 224, 99, 28);
		ContentPane.add(lblBusNo);
		
		JLabel label_7 = new JLabel("Departure Time :");
		label_7.setForeground(new Color(128, 0, 0));
		label_7.setFont(new Font("Times New Roman", Font.BOLD, 24));
		label_7.setBounds(2, 284, 194, 28);
		ContentPane.add(label_7);
		
		JLabel lblThankYouFor_1 = new JLabel("Thank you for choosing us....");
		lblThankYouFor_1.setForeground(Color.RED);
		lblThankYouFor_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 34));
		lblThankYouFor_1.setBounds(81, 355, 459, 63);
		ContentPane.add(lblThankYouFor_1);
		
		JLabel pn = new JLabel("");
		pn.setForeground(Color.BLACK);
		pn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 34));
		pn.setBounds(223, 13, 247, 50);
		ContentPane.add(pn);
		
		
		JLabel pid = new JLabel("");
		pid.setForeground(Color.BLACK);
		pid.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		pid.setBounds(159, 105, 239, 38);
		ContentPane.add(pid);
		pid.setText(b);
		
		JLabel bid = new JLabel("afcxrs");
		bid.setForeground(Color.BLACK);
		bid.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		bid.setBounds(158, 142, 225, 38);
		ContentPane.add(bid);
		
		
		JLabel tid = new JLabel("afcxrs");
		tid.setForeground(Color.BLACK);
		tid.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		tid.setBounds(173, 179, 225, 38);
		ContentPane.add(tid);
		tid.setText(t);
		
		JLabel bno = new JLabel("");
		bno.setForeground(Color.BLACK);
		bno.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		bno.setBounds(158, 214, 225, 38);
		ContentPane.add(bno);
		
		JLabel dest = new JLabel("");
		dest.setForeground(Color.BLACK);
		dest.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		dest.setBounds(158, 255, 225, 38);
		ContentPane.add(dest);
		
		JLabel dept = new JLabel("");
		dept.setForeground(Color.BLACK);
		dept.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		dept.setBounds(184, 284, 225, 38);
		ContentPane.add(dept);
		
		JLabel amt = new JLabel("");
		amt.setForeground(Color.BLACK);
		amt.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		amt.setBounds(173, 314, 225, 38);
		ContentPane.add(amt);
		
		String bkid=pid.getText();
		//String tzid=tid.getText();
		
		String query="select Name,b.BOOKING_ID,Agc_Name,p.BUS_NUMBER,p.DESTINATION,DEPARTURE_TIME,Amount from PASSENGER p,BOOKING b,TRANSACTION t,Bus s,transport_agency a where p.PASS_ID='"+bkid+"' and  p.PASS_ID=b.PASS_ID and t.BOOKING_ID=b.BOOKING_ID and t.PASS_ID=p.PASS_ID and p.BUS_NUMBER=s.BUS_NUMBER and a.AGC_CODE=s.AGC_CODE ";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","momotc21");
	        PreparedStatement pst1=con.prepareStatement(query);
			ResultSet rs=pst1.executeQuery();
			
			while(rs.next())
	        {
	        	String s1,s2,s3,s4,s5,s6,s7;
	        	s1=rs.getString("Name");
	        	s2=rs.getString("BOOKING_ID");
	        	s3=rs.getString("BUS_NUMBER");
	        	s4=rs.getString("DESTINATION");
	        	s5=rs.getString("DEPARTURE_TIME");
	        	s6=rs.getString("Amount");
	        	s7=rs.getString("Agc_Name");
	        	
	        	pn.setText(s1);
	        	bid.setText(s2);
	        	bno.setText(s3);
	        	dest.setText(s4);
	        	dept.setText(s5);
	        	amt.setText(s6);
	        	agc.setText("-"+s7);
	        	
	        }
	}catch (Exception e) {
		e.printStackTrace();
	}
		}
}
