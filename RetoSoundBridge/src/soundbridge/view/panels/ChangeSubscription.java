package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.view.factory.PanelFactory;

public class ChangeSubscription extends JPanel {

	private static final long serialVersionUID = -8063689617197980268L;
	
	public ChangeSubscription(JFrame frame, Client client) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);
		
		JPanel panelPremiumPlusIcon = new JPanel();
		panelPremiumPlusIcon.setBounds(100, 250, 200, 200);
		add(panelPremiumPlusIcon);
		panelPremiumPlusIcon.setLayout(new BorderLayout(0, 0));
		panelPremiumPlusIcon.setOpaque(false);
		
		JLabel lblPremiumPlusIcon = new JLabel("");
		panelPremiumPlusIcon.add(lblPremiumPlusIcon, BorderLayout.CENTER);
		
		JPanel panelPremiumIcon = new JPanel();
		panelPremiumIcon.setBounds(400, 250, 200, 200);
		add(panelPremiumIcon);
		panelPremiumIcon.setLayout(new BorderLayout(0, 0));
		panelPremiumIcon.setOpaque(false);
		
		JLabel lblPremiumIcon = new JLabel("");
		panelPremiumIcon.add(lblPremiumIcon, BorderLayout.CENTER);
		
		JPanel panelBasicIcon = new JPanel();
		panelBasicIcon.setBounds(700, 250, 200, 200);
		add(panelBasicIcon);
		panelBasicIcon.setLayout(new BorderLayout(0, 0));
		panelBasicIcon.setOpaque(false);
		
		JLabel lblBasicIcon = new JLabel("");
		panelBasicIcon.add(lblBasicIcon, BorderLayout.CENTER);
		
		JPanel panelBackIcon = new JPanel();
		panelBackIcon.setBounds(900, 45, 50, 50);
		add(panelBackIcon);
		panelBackIcon.setLayout(new BorderLayout(0, 0));
		panelBackIcon.setOpaque(false);
		panelBackIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.PROFILE, frame, client));
				frame.revalidate();
				frame.repaint();
			}
		});
		panelBackIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblBackIcon = new JLabel("");
		panelBackIcon.add(lblBackIcon, BorderLayout.CENTER);
		
		addImagePremiumPlus(client, panelPremiumPlusIcon, lblPremiumPlusIcon);
		addImagePremium(client, panelPremiumIcon, lblPremiumIcon);
		addImageBasic(client, panelBasicIcon, lblBasicIcon);
		addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");
	}
	
	private void addImage(JPanel panel, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);
		label.setIcon(icon);
	}
	
	private void addImagePremiumPlus(Client client, JPanel panel, JLabel lbl) {
		if (client instanceof ClientPP) {
			addImage(panel, lbl, "img/icon/sbpp.png");
		} else {
			addImage(panel, lbl, "img/icon/sbpp_grey.png");
		}
	}
	
	private void addImagePremium(Client client, JPanel panel, JLabel lbl) {
		if (client instanceof ClientP) {
			addImage(panel, lbl, "img/icon/sbp.png");
		} else {
			addImage(panel, lbl, "img/icon/sbp_grey.png");
		}
	}
	
	private void addImageBasic(Client client, JPanel panel, JLabel lbl) {
		if (client instanceof ClientP) {
			addImage(panel, lbl, "img/icon/sbbasic.png");
		} else {
			addImage(panel, lbl, "img/icon/sbbasic_grey.png");
		}
	}

}
