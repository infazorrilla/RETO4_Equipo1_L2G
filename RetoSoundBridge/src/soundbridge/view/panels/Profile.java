package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.view.factory.PanelFactory;

public class Profile extends JPanel {
	
	private static final long serialVersionUID = -6645561962016339329L;

	public Profile(JFrame frame, Client client) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);
		
		JPanel panelProfileIcon = new JPanel();
		panelProfileIcon.setBounds(30, 30, 150, 150);
		add(panelProfileIcon);
		panelProfileIcon.setLayout(new BorderLayout(0, 0));
		panelProfileIcon.setOpaque(false);
		
		JLabel lblProfileIcon = new JLabel("");
		panelProfileIcon.add(lblProfileIcon, BorderLayout.CENTER);
		
		JPanel panelHomeIcon = new JPanel();
		panelHomeIcon.setBounds(900, 45, 50, 50);
		add(panelHomeIcon);
		panelHomeIcon.setLayout(new BorderLayout(0, 0));
		panelHomeIcon.setOpaque(false);
		panelHomeIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame, client));
				frame.revalidate();
				frame.repaint();
			}
		});
		
		JLabel lblHomeIcon = new JLabel("");
		panelHomeIcon.add(lblHomeIcon, BorderLayout.CENTER);
		
		JLabel lblNameOfClient = new JLabel(client.getName() + " " + client.getLastName());
		lblNameOfClient.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNameOfClient.setBounds(200, 50, 301, 27);
		lblNameOfClient.setForeground(Color.white);
		add(lblNameOfClient);
		
		JLabel lblUsername = new JLabel("@" + client.getUsername());
		lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblUsername.setBounds(200, 90, 301, 27);
		lblUsername.setForeground(Color.white);
		add(lblUsername);
		
		JButton btnLogOut = new JButton("Cerrar Sesión");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logOut(frame);
			}
		});
		btnLogOut.setBounds(200, 130, 100, 30);
		add(btnLogOut);
		btnLogOut.setForeground(Color.white);
		btnLogOut.setFont(new Font("Lucida Grande", Font.ITALIC, 15));
		btnLogOut.setBackground(new Color(0, 0, 0, 0));
		btnLogOut.setBorder(new LineBorder(Color.black, 0));
		btnLogOut.setOpaque(true);
		
		JPanel panelEditSubscriptionIcon = new JPanel();
		panelEditSubscriptionIcon.setBounds(50, 200, 25, 25);
		add(panelEditSubscriptionIcon);
		panelEditSubscriptionIcon.setLayout(new BorderLayout(0, 0));
		panelEditSubscriptionIcon.setOpaque(false);
		
		JLabel lblEditSubscriptionIcon = new JLabel("");
		panelEditSubscriptionIcon.add(lblEditSubscriptionIcon, BorderLayout.CENTER);
		
		JLabel lblSubscription = new JLabel("Suscripción:");
		lblSubscription.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblSubscription.setBounds(90, 200, 301, 27);
		lblSubscription.setForeground(Color.white);
		add(lblSubscription);
		
		JPanel panelSubscriptionIcon = new JPanel();
		panelSubscriptionIcon.setBounds(50, 250, 200, 200);
		add(panelSubscriptionIcon);
		panelSubscriptionIcon.setLayout(new BorderLayout(0, 0));
		panelSubscriptionIcon.setOpaque(false);
		
		JLabel lblSubscriptionIcon = new JLabel("");
		panelSubscriptionIcon.add(lblSubscriptionIcon, BorderLayout.CENTER);
		
		JPanel panelEditInfoIcon = new JPanel();
		panelEditInfoIcon.setBounds(400, 200, 25, 25);
		add(panelEditInfoIcon);
		panelEditInfoIcon.setLayout(new BorderLayout(0, 0));
		panelEditInfoIcon.setOpaque(false);
		
		JLabel lblEditInfoIcon = new JLabel("");
		panelEditInfoIcon.add(lblEditInfoIcon, BorderLayout.CENTER);
		
		JLabel lblInfo = new JLabel("Datos de la cuenta:");
		lblInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblInfo.setBounds(440, 200, 301, 27);
		lblInfo.setForeground(Color.white);
		add(lblInfo);
		
		addImage(panelProfileIcon, lblProfileIcon, "img/icon/profile.png");
		addImage(panelHomeIcon, lblHomeIcon, "img/icon/home.png");
		addImage(panelEditSubscriptionIcon, lblEditSubscriptionIcon, "img/icon/pen.png");
		addImage(panelEditInfoIcon, lblEditInfoIcon, "img/icon/pen.png");
		addSubscriptionImage(client, panelSubscriptionIcon, lblSubscriptionIcon);
	}
	
	private void addImage(JPanel panel, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);
		label.setIcon(icon);
	}
	
	private void logOut(JFrame frame) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null));
		frame.revalidate();
		frame.repaint();
	}
	
	private void addSubscriptionImage(Client client, JPanel panel, JLabel lbl) {
		if (client instanceof ClientPP) {
			addImage(panel, lbl, "img/icon/sbpp.png");
		} else if (client instanceof ClientP) {
			addImage(panel, lbl, "img/icon/sbp.png");
		} else {
			addImage(panel, lbl, "img/icon/sbbasic.png");
		}
	}

}
