package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import soundbridge.database.managers.ClientPManager;
import soundbridge.database.managers.ClientPPManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.view.factory.PanelFactory;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;

public class ChangeSubscription extends JPanel {

	private static final long serialVersionUID = -8063689617197980268L;
	private String actualSubscription = null;
	private String newSubscription = null;

	public ChangeSubscription(JFrame frame, Client client) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

		JLabel lblTitle = new JLabel("Seleccione la suscripción que desee:");
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTitle.setBounds(90, 89, 510, 38);
		lblTitle.setForeground(Color.white);
		add(lblTitle);

		JLabel lblPremiumPlus = new JLabel("PREMIUM PLUS");
		lblPremiumPlus.setHorizontalAlignment(SwingConstants.CENTER);
		lblPremiumPlus.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblPremiumPlus.setBounds(100, 200, 200, 27);
		lblPremiumPlus.setForeground(Color.white);
		add(lblPremiumPlus);

		JPanel panelPremiumPlusIcon = new JPanel();
		panelPremiumPlusIcon.setBounds(100, 250, 200, 200);
		add(panelPremiumPlusIcon);
		panelPremiumPlusIcon.setLayout(new BorderLayout(0, 0));
		panelPremiumPlusIcon.setOpaque(false);

		JLabel lblPremiumPlusIcon = new JLabel("");
		panelPremiumPlusIcon.add(lblPremiumPlusIcon, BorderLayout.CENTER);

		JLabel lblPremium = new JLabel("PREMIUM");
		lblPremium.setHorizontalAlignment(SwingConstants.CENTER);
		lblPremium.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblPremium.setBounds(400, 200, 200, 27);
		lblPremium.setForeground(Color.white);
		add(lblPremium);

		JPanel panelPremiumIcon = new JPanel();
		panelPremiumIcon.setBounds(400, 250, 200, 200);
		add(panelPremiumIcon);
		panelPremiumIcon.setLayout(new BorderLayout(0, 0));
		panelPremiumIcon.setOpaque(false);

		JLabel lblPremiumIcon = new JLabel("");
		panelPremiumIcon.add(lblPremiumIcon, BorderLayout.CENTER);

		JLabel lblBasic = new JLabel("BASIC");
		lblBasic.setHorizontalAlignment(SwingConstants.CENTER);
		lblBasic.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblBasic.setBounds(700, 200, 200, 27);
		lblBasic.setForeground(Color.white);
		add(lblBasic);

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

		JLabel lblPricePP = new JLabel("9.99 €");
		lblPricePP.setHorizontalAlignment(SwingConstants.CENTER);
		lblPricePP.setForeground(Color.WHITE);
		lblPricePP.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblPricePP.setBounds(100, 475, 200, 27);
		add(lblPricePP);

		JLabel lblPriceP = new JLabel("1.99 €");
		lblPriceP.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriceP.setForeground(Color.WHITE);
		lblPriceP.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblPriceP.setBounds(400, 475, 200, 27);
		add(lblPriceP);

		JLabel lblPriceB = new JLabel("Gratis");
		lblPriceB.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriceB.setForeground(Color.WHITE);
		lblPriceB.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblPriceB.setBounds(700, 475, 200, 27);
		add(lblPriceB);

		JRadioButton rbtnPP = new JRadioButton("");
		rbtnPP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSubscription = "premium plus";
			}
		});
		rbtnPP.setBounds(100, 475, 23, 23);
		add(rbtnPP);

		JRadioButton rbtnP = new JRadioButton("");
		rbtnP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSubscription = "premium";
			}
		});
		rbtnP.setBounds(400, 475, 23, 23);
		add(rbtnP);

		JRadioButton rbtnB = new JRadioButton("");
		rbtnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSubscription = "basic";
			}
		});
		rbtnB.setBounds(700, 475, 23, 23);
		add(rbtnB);

		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rbtnPP);
		btnGroup.add(rbtnP);
		btnGroup.add(rbtnB);

		JButton btnConfirm = new JButton("Confirmar selección");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doCheckSubscription(client);
			}
		});
		btnConfirm.setBounds(400, 575, 200, 50);
		add(btnConfirm);
		btnConfirm.setForeground(Color.white);
		btnConfirm.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		btnConfirm.setBackground(new Color(244, 135, 244, 20));
		btnConfirm.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		btnConfirm.setOpaque(false);
		btnConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
			actualSubscription = "premium plus";
		} else {
			addImage(panel, lbl, "img/icon/sbpp_grey.png");
		}
	}

	private void addImagePremium(Client client, JPanel panel, JLabel lbl) {
		if (client instanceof ClientP) {
			addImage(panel, lbl, "img/icon/sbp.png");
			actualSubscription = "premium";
		} else {
			addImage(panel, lbl, "img/icon/sbp_grey.png");
		}
	}

	private void addImageBasic(Client client, JPanel panel, JLabel lbl) {
		if (!(client instanceof ClientPP) && !(client instanceof ClientP)) {
			addImage(panel, lbl, "img/icon/sbbasic.png");
			actualSubscription = "basic";
		} else {
			addImage(panel, lbl, "img/icon/sbbasic_grey.png");
		}
	}

	public int askToConfirmChange() {
		System.out.println("1." + actualSubscription);
		System.out.println("2." + newSubscription);
		JFrame frame = new JFrame();
		String[] options = new String[2];
		options[0] = "Sí";
		options[1] = "No";

		String titulo = "Cambio de Suscripción";

		String msg = "¿Desea cambiar su suscripción?";

		int ret = JOptionPane.showOptionDialog(frame.getContentPane(), msg, titulo, 0, JOptionPane.INFORMATION_MESSAGE,
				null, options, null);

		return ret;
	}

	private void checkNewSubscription(Client client) throws SQLException, Exception {
		ClientPManager clientPManager = new ClientPManager();
		ClientPPManager clientPPManager = new ClientPPManager();

		if (actualSubscription.equalsIgnoreCase(newSubscription)) {
			JOptionPane.showMessageDialog(null, "Ya posee ese nivel de suscripción.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			int confirm = askToConfirmChange();
			if (confirm == 0) {

				if (actualSubscription.equalsIgnoreCase("basic")) {
					String reply = JOptionPane.showInputDialog(null, "Introduzca su número de cuenta:",
							"Número de cuenta", JOptionPane.PLAIN_MESSAGE);

					if ((reply != null) && (reply.length() > 0)) {
						if (newSubscription.equals("premium")) {
							ClientP clientP = new ClientP();
							clientP.setId(client.getId());
							clientP.setBankAccount(reply);
							clientP.setSuscriptionDate(new Date());
							clientPManager.insert(clientP);
						} else {
							ClientPP clientPP = new ClientPP();
							clientPP.setId(client.getId());
							clientPP.setBankAccount(reply);
							clientPP.setSuscriptionDate(new Date());
							clientPPManager.insert(clientPP);
						}
					}
				} else if (actualSubscription.equalsIgnoreCase("premium")) {
					if (newSubscription.equalsIgnoreCase("premium plus")) {
						ClientPP clientPP = (ClientPP) client;
						ClientP clientP = clientPManager.getClientPById(client.getId());
						clientPP.setBankAccount(clientP.getBankAccount());
						clientPP.setSuscriptionDate(new Date());
						clientPPManager.insert(clientPP);
					}
					clientPManager.delete((ClientP) client);
				} else if (actualSubscription.equalsIgnoreCase("premium plus")) {
					if (newSubscription.equalsIgnoreCase("premium")) {
						ClientP clientP = (ClientP) client;
						ClientPP clientPP = clientPPManager.getClientPPById(client.getId());
						clientP.setBankAccount(clientPP.getBankAccount());
						clientP.setSuscriptionDate(new Date());
						clientPManager.insert(clientP);
					}
					clientPPManager.delete((ClientPP) client);
				}
			}
		}
	}
	
	private void doCheckSubscription(Client client) {
		try {
			checkNewSubscription(client);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido cambiar su suscripción.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error general.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
