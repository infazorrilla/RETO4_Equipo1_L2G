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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import soundbridge.database.managers.ClientManager;
import soundbridge.database.managers.ClientPManager;
import soundbridge.database.managers.ClientPPManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.view.factory.PanelFactory;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ChangeSubscription extends JPanel {

	private static final long serialVersionUID = -8063689617197980268L;
	private String actualSubscription = null;
	private String newSubscription = null;
	private Client changedClient = null;
	private JRadioButton rbtnPP;
	private JRadioButton rbtnP;
	private JRadioButton rbtnB;

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
				if (changedClient == null) {
					frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.PROFILE, frame, client));
				} else {
					frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.PROFILE, frame, changedClient));
				}
				frame.revalidate();
				frame.repaint();
			}
		});
		panelBackIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JLabel lblBackIcon = new JLabel("");
		panelBackIcon.add(lblBackIcon, BorderLayout.CENTER);

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

		rbtnPP = new JRadioButton("");
		rbtnPP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSubscription = "Premium Plus";
			}
		});
		rbtnPP.setBounds(100, 475, 23, 23);
		add(rbtnPP);

		rbtnP = new JRadioButton("");
		rbtnP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSubscription = "Premium";
			}
		});
		rbtnP.setBounds(400, 475, 23, 23);
		add(rbtnP);

		rbtnB = new JRadioButton("");
		rbtnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSubscription = "Basic";
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
				doCheckSubscription(client, panelPremiumPlusIcon, lblPremiumPlusIcon, panelPremiumIcon, lblPremiumIcon,
						panelBasicIcon, lblBasicIcon);
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

		addSubscriptionImages(client, panelPremiumPlusIcon, lblPremiumPlusIcon, panelPremiumIcon, lblPremiumIcon,
				panelBasicIcon, lblBasicIcon);
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
			actualSubscription = "premium plus";
			rbtnPP.setSelected(true);
			newSubscription = "premium plus";
		} else {
			addImage(panel, lbl, "img/icon/sbpp_grey.png");
		}
	}

	private void addImagePremium(Client client, JPanel panel, JLabel lbl) {
		if (client instanceof ClientP) {
			addImage(panel, lbl, "img/icon/sbp.png");
			actualSubscription = "premium";
			newSubscription = "premium";
			rbtnP.setSelected(true);
		} else {
			addImage(panel, lbl, "img/icon/sbp_grey.png");
		}
	}

	private void addImageBasic(Client client, JPanel panel, JLabel lbl) {
		if (!(client instanceof ClientPP) && !(client instanceof ClientP)) {
			addImage(panel, lbl, "img/icon/sbbasic.png");
			actualSubscription = "basic";
			newSubscription = "basic";
			rbtnB.setSelected(true);
		} else {
			addImage(panel, lbl, "img/icon/sbbasic_grey.png");
		}
	}

	private void addSubscriptionImages(Client client, JPanel panelPremiumPlusIcon, JLabel lblPremiumPlusIcon,
			JPanel panelPremiumIcon, JLabel lblPremiumIcon, JPanel panelBasicIcon, JLabel lblBasicIcon) {
		addImagePremiumPlus(client, panelPremiumPlusIcon, lblPremiumPlusIcon);
		addImagePremium(client, panelPremiumIcon, lblPremiumIcon);
		addImageBasic(client, panelBasicIcon, lblBasicIcon);
	}

	public int askToConfirmChange() {
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

	private void checkNewSubscription(Client client, JPanel panelPremiumPlusIcon, JLabel lblPremiumPlusIcon,
			JPanel panelPremiumIcon, JLabel lblPremiumIcon, JPanel panelBasicIcon, JLabel lblBasicIcon)
			throws SQLException, Exception {
		ClientManager clientManager = new ClientManager();
		ClientPManager clientPManager = new ClientPManager();
		ClientPPManager clientPPManager = new ClientPPManager();

		if (actualSubscription.equalsIgnoreCase(newSubscription)) {
			JOptionPane.showMessageDialog(null, "Ya posee ese nivel de suscripción.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			int confirm = askToConfirmChange();
			if (confirm == 0) {
				if (actualSubscription.equalsIgnoreCase("basic")) {
					String bankNumber = null;
					JTextField textFildBankAccount = new JTextField();
					Object[] message = { "Cuenta bancaria: ", textFildBankAccount };
					JOptionPane.showMessageDialog(null, message, "Has elegido un plan de pago",
							JOptionPane.PLAIN_MESSAGE);

					bankNumber = textFildBankAccount.getText();

					clientManager.changeSubscription(client.getId(), bankNumber, actualSubscription, newSubscription);
				} else if (actualSubscription.equalsIgnoreCase("premium")) {
					ClientP clientP = clientPManager.getClientPById(client.getId());
					clientManager.changeSubscription(client.getId(), clientP.getBankAccount(), actualSubscription,
							newSubscription);
				} else if (actualSubscription.equalsIgnoreCase("premium plus")) {
					ClientPP clientPP = clientPPManager.getClientPPById(client.getId());
					clientManager.changeSubscription(client.getId(), clientPP.getBankAccount(), actualSubscription,
							newSubscription);
				}

				JOptionPane.showMessageDialog(null, "Su suscripción a cambiado a " + newSubscription, "Actualización",
						JOptionPane.INFORMATION_MESSAGE);

				changedClient = clientManager.getClientByUsername(client.getUsername());

				addSubscriptionImages(changedClient, panelPremiumPlusIcon, lblPremiumPlusIcon, panelPremiumIcon,
						lblPremiumIcon, panelBasicIcon, lblBasicIcon);
			}
		}
	}

	private void doCheckSubscription(Client client, JPanel panelPremiumPlusIcon, JLabel lblPremiumPlusIcon,
			JPanel panelPremiumIcon, JLabel lblPremiumIcon, JPanel panelBasicIcon, JLabel lblBasicIcon) {
		try {
			checkNewSubscription(client, panelPremiumPlusIcon, lblPremiumPlusIcon, panelPremiumIcon, lblPremiumIcon,
					panelBasicIcon, lblBasicIcon);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error general.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
