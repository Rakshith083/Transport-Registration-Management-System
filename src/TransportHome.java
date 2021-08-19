import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

//import TransportBookingManagement.ImagePanel;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransportHome extends JFrame {

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
					TransportHome frame = new TransportHome();
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
	public TransportHome() {
		
		
		
		setBackground(new Color(255, 255, 255));
		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1931, 1039);
		
		ImagePanel contentPane = new ImagePanel(new ImageIcon("D:\\Downloads\\home.PNG").getImage());
		
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTransportRegisterManagement = new JLabel("Transportation Register Management");
		lblTransportRegisterManagement.setBackground(new Color(102, 204, 255));
		lblTransportRegisterManagement.setForeground(new Color(253, 245, 230));
		lblTransportRegisterManagement.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 99));
		lblTransportRegisterManagement.setBounds(87, 331, 1623, 158);
		contentPane.add(lblTransportRegisterManagement);
		
		JButton btnClose = new JButton("Next-->");
		btnClose.setForeground(new Color(0, 0, 139));
		btnClose.setBackground(new Color(255, 255, 255));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Passenger frame = new Passenger();
				frame.setVisible(true);
			}
		});
		btnClose.setFont(new Font("Times New Roman", Font.BOLD, 34));
		btnClose.setBounds(817, 762, 212, 73);
		contentPane.add(btnClose);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1913, 34);
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
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Summary frame = new Summary("", "");
				frame.setVisible(true);
			}
		});
		mntmNewMenuItem.setFont(new Font("Times New Roman", Font.BOLD, 20));
		mnNewMenu.add(mntmNewMenuItem);
		menuItem.setFont(new Font("Times New Roman", Font.BOLD, 20));
		mnNewMenu.add(menuItem);
		mnNewMenu.add(mntmExit);
		
		JMenu menu = new JMenu("Transport Agency");
		menu.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}
			public void menuDeselected(MenuEvent arg0) {
			}
			public void menuSelected(MenuEvent arg0) {
				TransportAgency frame = new TransportAgency();
				frame.setVisible(true);
				dispose();
			}
		});
		menu.setFont(new Font("Times New Roman", Font.BOLD, 23));
		menuBar.add(menu);
		
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
			}
		});
		mnBusses.setFont(new Font("Times New Roman", Font.BOLD, 23));
		
		JMenu mnNewMenu_1 = new JMenu("Passenger");
		menuBar.add(mnNewMenu_1);
		mnNewMenu_1.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}
			public void menuDeselected(MenuEvent arg0) {
			}
			public void menuSelected(MenuEvent arg0) {
				Passenger frame = new Passenger();
				frame.setVisible(true);
				dispose();
			}
		});
		mnNewMenu_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		
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
			}
		});
		mnTranzactions.setFont(new Font("Times New Roman", Font.BOLD, 23));
	}
}

class ImagePanel extends JPanel {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;

	  public ImagePanel(String img) {
	    this(new ImageIcon(img).getImage());
	  }

	  public ImagePanel(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }
	}