package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import soundbridge.database.managers.ClientManager;
import soundbridge.database.managers.ClientPManager;
import soundbridge.database.managers.ClientPPManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;

public class ChangeSubscription extends JPanel {

	private static final long serialVersionUID = -8063689617197980268L;
	private String actualSubscription = null;
	private String newSubscription = null;
	private Client changedClient = null;
	private JRadioButton rbtnPP;
	private JRadioButton rbtnP;
	private JRadioButton rbtnB;
	private JLabel lblBackground;
	
	public ChangeSubscription(JFrame frame, Client client) {
		initialize(frame, client);
	}
	
	private void initialize(JFrame frame, Client client) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

		JLabel lblTitle = new JLabel("Seleccione la suscripción que desee:");
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTitle.setBounds(75, 40, 510, 38);
		lblTitle.setForeground(Color.white);
		add(lblTitle);

		JLabel lblPremiumPlus = new JLabel("PREMIUM PLUS");
		lblPremiumPlus.setHorizontalAlignment(SwingConstants.CENTER);
		lblPremiumPlus.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblPremiumPlus.setBounds(75, 135, 200, 27);
		lblPremiumPlus.setForeground(Color.white);
		add(lblPremiumPlus);

		JPanel panelPremiumPlusIcon = new JPanel();
		panelPremiumPlusIcon.setBounds(75, 300, 200, 200);
		add(panelPremiumPlusIcon);
		panelPremiumPlusIcon.setLayout(new BorderLayout(0, 0));
		panelPremiumPlusIcon.setOpaque(false);

		JLabel lblPremiumPlusIcon = new JLabel("");
		panelPremiumPlusIcon.add(lblPremiumPlusIcon, BorderLayout.CENTER);

		JLabel lblPremium = new JLabel("PREMIUM");
		lblPremium.setHorizontalAlignment(SwingConstants.CENTER);
		lblPremium.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblPremium.setBounds(400, 135, 200, 27);
		lblPremium.setForeground(Color.white);
		add(lblPremium);

		JPanel panelPremiumIcon = new JPanel();
		panelPremiumIcon.setBounds(400, 300, 200, 200);
		add(panelPremiumIcon);
		panelPremiumIcon.setLayout(new BorderLayout(0, 0));
		panelPremiumIcon.setOpaque(false);

		JLabel lblPremiumIcon = new JLabel("");
		panelPremiumIcon.add(lblPremiumIcon, BorderLayout.CENTER);

		JLabel lblBasic = new JLabel("BASIC");
		lblBasic.setHorizontalAlignment(SwingConstants.CENTER);
		lblBasic.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblBasic.setBounds(725, 135, 200, 27);
		lblBasic.setForeground(Color.white);
		add(lblBasic);

		JPanel panelBasicIcon = new JPanel();
		panelBasicIcon.setBounds(725, 300, 200, 200);
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
		panelBackIcon.setToolTipText("Volver a mi perfil.");

		JLabel lblBackIcon = new JLabel("");
		panelBackIcon.add(lblBackIcon, BorderLayout.CENTER);

		JLabel lblPricePP = new JLabel("9.99 €/mes");
		lblPricePP.setHorizontalAlignment(SwingConstants.CENTER);
		lblPricePP.setForeground(Color.WHITE);
		lblPricePP.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblPricePP.setBounds(75, 515, 200, 27);
		add(lblPricePP);

		JLabel lblPriceP = new JLabel("1.99 €/mes");
		lblPriceP.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriceP.setForeground(Color.WHITE);
		lblPriceP.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblPriceP.setBounds(400, 515, 200, 27);
		add(lblPriceP);

		JLabel lblPriceB = new JLabel("Gratis");
		lblPriceB.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriceB.setForeground(Color.WHITE);
		lblPriceB.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblPriceB.setBounds(725, 515, 200, 27);
		add(lblPriceB);

		rbtnPP = new JRadioButton("");
		rbtnPP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSubscription = "Premium Plus";
			}
		});
		rbtnPP.setBounds(75, 515, 23, 23);
		rbtnPP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(rbtnPP);

		rbtnP = new JRadioButton("");
		rbtnP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSubscription = "Premium";
			}
		});
		rbtnP.setBounds(400, 515, 23, 23);
		rbtnP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(rbtnP);

		rbtnB = new JRadioButton("");
		rbtnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSubscription = "Basic";
			}
		});
		rbtnB.setBounds(725, 515, 23, 23);
		rbtnB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		btnConfirm.setBounds(400, 600, 200, 50);
		add(btnConfirm);
		btnConfirm.setForeground(Color.white);
		btnConfirm.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		btnConfirm.setBackground(new Color(244, 135, 244, 20));
		btnConfirm.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		btnConfirm.setOpaque(false);
		btnConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JPanel panelIconTick1 = new JPanel();
		panelIconTick1.setBounds(75, 175, 25, 25);
		add(panelIconTick1);
		panelIconTick1.setLayout(new BorderLayout(0, 0));
		panelIconTick1.setOpaque(false);

		JLabel lblIconTick1 = new JLabel("");
		panelIconTick1.add(lblIconTick1, BorderLayout.CENTER);

		JPanel panelIconTick2 = new JPanel();
		panelIconTick2.setBounds(75, 210, 25, 25);
		add(panelIconTick2);
		panelIconTick2.setLayout(new BorderLayout(0, 0));
		panelIconTick2.setOpaque(false);

		JLabel lblIconTick2 = new JLabel("");
		panelIconTick2.add(lblIconTick2, BorderLayout.CENTER);

		JPanel panelIconTick3 = new JPanel();
		panelIconTick3.setBounds(75, 245, 25, 25);
		add(panelIconTick3);
		panelIconTick3.setLayout(new BorderLayout(0, 0));
		panelIconTick3.setOpaque(false);

		JLabel lblIconTick3 = new JLabel("");
		panelIconTick3.add(lblIconTick3, BorderLayout.CENTER);

		JLabel lblPremiumPlus1 = new JLabel("Listas personalizadas");
		lblPremiumPlus1.setForeground(Color.white);
		lblPremiumPlus1.setBounds(112, 175, 163, 25);
		add(lblPremiumPlus1);

		JLabel lblPremiumPlus2 = new JLabel("Lista de Favoritos");
		lblPremiumPlus2.setForeground(Color.WHITE);
		lblPremiumPlus2.setBounds(112, 210, 163, 25);
		add(lblPremiumPlus2);

		JLabel lblPremiumPlus3 = new JLabel("Valoración de álbumes");
		lblPremiumPlus3.setForeground(Color.WHITE);
		lblPremiumPlus3.setBounds(112, 245, 163, 25);
		add(lblPremiumPlus3);

		JPanel panelIconTick4 = new JPanel();
		panelIconTick4.setOpaque(false);
		panelIconTick4.setBounds(400, 175, 25, 25);
		add(panelIconTick4);
		panelIconTick4.setLayout(new BorderLayout(0, 0));

		JLabel lblIconTick4 = new JLabel("");
		panelIconTick4.add(lblIconTick4, BorderLayout.CENTER);

		JLabel lblPremium1 = new JLabel("Lista de Favoritos");
		lblPremium1.setForeground(Color.WHITE);
		lblPremium1.setBounds(437, 175, 163, 25);
		add(lblPremium1);

		JPanel panelIconCross = new JPanel();
		panelIconCross.setOpaque(false);
		panelIconCross.setBounds(725, 175, 25, 25);
		add(panelIconCross);
		panelIconCross.setLayout(new BorderLayout(0, 0));

		JLabel lblIconCross = new JLabel("");
		panelIconCross.add(lblIconCross, BorderLayout.CENTER);

		JLabel lblBasic1 = new JLabel("Sin privilegios");
		lblBasic1.setForeground(Color.WHITE);
		lblBasic1.setBounds(762, 175, 163, 25);
		add(lblBasic1);

		WindowUtils.addImage(panelIconCross, lblIconCross, "img/icon/cross.png");
		WindowUtils.addImage(panelIconTick1, lblIconTick1, "img/icon/tick.png");
		WindowUtils.addImage(panelIconTick2, lblIconTick2, "img/icon/tick.png");
		WindowUtils.addImage(panelIconTick3, lblIconTick3, "img/icon/tick.png");
		WindowUtils.addImage(panelIconTick4, lblIconTick4, "img/icon/tick.png");

		addSubscriptionImages(client, panelPremiumPlusIcon, lblPremiumPlusIcon, panelPremiumIcon, lblPremiumIcon,
				panelBasicIcon, lblBasicIcon);
		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");
		
		setLayout(new BorderLayout(0, 0));

		lblBackground = new JLabel("");
		add(lblBackground, BorderLayout.CENTER);
		
		WindowUtils.addImage(this, lblBackground, "img/panel/change_sub_bg.jpeg");
	}

	private void addImagePremiumPlus(Client client, JPanel panel, JLabel lbl) {
		if (client instanceof ClientPP) {
			WindowUtils.addImage(panel, lbl, "img/icon/sbpp.png");
			actualSubscription = "premium plus";
			rbtnPP.setSelected(true);
			newSubscription = "premium plus";
		} else {
			WindowUtils.addImage(panel, lbl, "img/icon/sbpp_grey.png");
		}
	}

	private void addImagePremium(Client client, JPanel panel, JLabel lbl) {
		if (client instanceof ClientP) {
			WindowUtils.addImage(panel, lbl, "img/icon/sbp.png");
			actualSubscription = "premium";
			newSubscription = "premium";
			rbtnP.setSelected(true);
		} else {
			WindowUtils.addImage(panel, lbl, "img/icon/sbp_grey.png");
		}
	}

	private void addImageBasic(Client client, JPanel panel, JLabel lbl) {
		if (!(client instanceof ClientPP) && !(client instanceof ClientP)) {
			WindowUtils.addImage(panel, lbl, "img/icon/sbbasic.png");
			actualSubscription = "basic";
			newSubscription = "basic";
			rbtnB.setSelected(true);
		} else {
			WindowUtils.addImage(panel, lbl, "img/icon/sbbasic_grey.png");
		}
	}

	private void addSubscriptionImages(Client client, JPanel panelPremiumPlusIcon, JLabel lblPremiumPlusIcon,
			JPanel panelPremiumIcon, JLabel lblPremiumIcon, JPanel panelBasicIcon, JLabel lblBasicIcon) {
		addImagePremiumPlus(client, panelPremiumPlusIcon, lblPremiumPlusIcon);
		addImagePremium(client, panelPremiumIcon, lblPremiumIcon);
		addImageBasic(client, panelBasicIcon, lblBasicIcon);
	}

	public int askToConfirmChange() {
		String iconPath = null;

		if (newSubscription.equalsIgnoreCase("basic")) {
			iconPath = "img/icon/sbbasic.png";
		} else if (newSubscription.equalsIgnoreCase("premium")) {
			iconPath = "img/icon/sbp.png";
		} else {
			iconPath = "img/icon/sbpp.png";
		}

		int ret = WindowUtils.yesOrNoPaneWithIcon("¿Desea cambiar su suscripción a " + newSubscription + "?",
				"Cambio de Suscripción", iconPath);

		return ret;
	}

	private void checkNewSubscription(Client client, JPanel panelPremiumPlusIcon, JLabel lblPremiumPlusIcon,
			JPanel panelPremiumIcon, JLabel lblPremiumIcon, JPanel panelBasicIcon, JLabel lblBasicIcon)
			throws SQLException, Exception {
		ClientManager clientManager = new ClientManager();
		ClientPManager clientPManager = new ClientPManager();
		ClientPPManager clientPPManager = new ClientPPManager();

		if (actualSubscription.equalsIgnoreCase(newSubscription)) {
			WindowUtils.errorPane("Ya posee ese nivel de suscripción.", "Error");
		} else {
			int confirm = askToConfirmChange();
			if (confirm == 0) {
				if (actualSubscription.equalsIgnoreCase("basic")) {
					String bankNumber = WindowUtils.inputPaneWithIcon("Introduzca su número de cuenta:", "Plan de pago",
							"img/icon/money.png");

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

				WindowUtils.confirmationPane("Su suscripción a cambiado a " + newSubscription + ".", "Actualización");

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
			WindowUtils.errorPane("No se ha podido realizar el cambio de suscripción.", "Error");
		} catch (Exception e) {
			WindowUtils.errorPane("No se ha podido realizar el cambio de suscripción.", "Error");
		}
	}
}
