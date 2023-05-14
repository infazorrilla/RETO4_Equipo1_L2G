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

import soundbridge.controller.Controller;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;

/**
 * Panel that enables a client to change his subscription.
 */
public class ChangeSubscription extends JPanel {

	private static final long serialVersionUID = -8063689617197980268L;
	private String actualSubscription = null;
	private String newSubscription = null;
	private Client changedClient = null;
	private JRadioButton rbtnPP;
	private JRadioButton rbtnP;
	private JRadioButton rbtnB;

	/**
	 * Initializes the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
	public ChangeSubscription(JFrame frame, Client client) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);

		initialize(frame, client);
		addBackgroundGif();
	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
	private void initialize(JFrame frame, Client client) {
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
				goToClientProfile(frame, client);
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
		rbtnPP.setOpaque(false);
		add(rbtnPP);

		rbtnP = new JRadioButton("");
		rbtnP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSubscription = "Premium";
			}
		});
		rbtnP.setBounds(400, 515, 23, 23);
		rbtnP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rbtnP.setOpaque(false);
		add(rbtnP);

		rbtnB = new JRadioButton("");
		rbtnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSubscription = "Basic";
			}
		});
		rbtnB.setBounds(725, 515, 23, 23);
		rbtnB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rbtnB.setOpaque(false);
		add(rbtnB);

		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rbtnPP);
		btnGroup.add(rbtnP);
		btnGroup.add(rbtnB);

		JButton btnConfirm = new JButton("Confirmar selección");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doChangeClientSubscription(client, panelPremiumPlusIcon, lblPremiumPlusIcon, panelPremiumIcon,
						lblPremiumIcon, panelBasicIcon, lblBasicIcon);
			}
		});
		btnConfirm.setBounds(400, 600, 200, 50);
		add(btnConfirm);
		btnConfirm.setForeground(Color.white);
		btnConfirm.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		btnConfirm.setBackground(Color.black);
		btnConfirm.setOpaque(true);
		btnConfirm.setBorder(new LineBorder(new Color(244, 135, 244), 2));
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

		JPanel panelBackground = new JPanel();
		panelBackground.setBounds(0, 0, 1000, 672);
		add(panelBackground);
		panelBackground.setLayout(new BorderLayout(0, 0));
		panelBackground.setOpaque(false);

		JLabel lblBackground = new JLabel("");
		panelBackground.add(lblBackground, BorderLayout.CENTER);

		WindowUtils.addImage(panelIconCross, lblIconCross, "img/icon/cross.png");
		WindowUtils.addImage(panelIconTick1, lblIconTick1, "img/icon/tick.png");
		WindowUtils.addImage(panelIconTick2, lblIconTick2, "img/icon/tick.png");
		WindowUtils.addImage(panelIconTick3, lblIconTick3, "img/icon/tick.png");
		WindowUtils.addImage(panelIconTick4, lblIconTick4, "img/icon/tick.png");

		addSubscriptionImages(client, panelPremiumPlusIcon, lblPremiumPlusIcon, panelPremiumIcon, lblPremiumIcon,
				panelBasicIcon, lblBasicIcon);
		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");
		WindowUtils.addImage(panelBackground, lblBackground, "img/panel/sub_bg.png");
	}

	/**
	 * Takes the client to his profile panel.
	 * 
	 * @param frame  frame where the panel is added
	 * @param client logged client
	 */
	private void goToClientProfile(JFrame frame, Client client) {
		frame.getContentPane().removeAll();
		if (changedClient == null) {
			frame.getContentPane().add(
					PanelFactory.getJPanel(PanelFactory.PROFILE, frame, client, null, null, null, null, null, null));
		} else {
			frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.PROFILE, frame, changedClient, null, null,
					null, null, null, null));
		}
		frame.revalidate();
		frame.repaint();
	}

	/**
	 * Adds a .GIF image as background of the main panel.
	 */
	private void addBackgroundGif() {
		setLayout(new BorderLayout(0, 0));

		JLabel lblBackground = new JLabel("");
		add(lblBackground, BorderLayout.CENTER);

		WindowUtils.addGif(lblBackground, "img/panel/sub_bg.gif");
	}

	/**
	 * Adds a colored image related to the premium plus subscription when the client
	 * is premium plus. On the contrary, a grey scaled image is added.
	 * 
	 * @param client logged client
	 * @param panel  panel where the image is added
	 * @param lbl    label where the image is placed
	 */
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

	/**
	 * Adds a colored image related to the premium subscription when the client is
	 * premium. On the contrary, a grey scaled image is added.
	 * 
	 * @param client logged client
	 * @param panel  panel where the image is added
	 * @param lbl    label where the image is placed
	 */
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

	/**
	 * Adds a colored image related to the basic subscription when the client is
	 * basic. On the contrary, a grey scaled image is added.
	 * 
	 * @param client logged client
	 * @param panel  panel where the image is added
	 * @param lbl    label where the image is placed
	 */
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

	/**
	 * Adds every subscription image.
	 * 
	 * @param client               logged client
	 * @param panelPremiumPlusIcon panel corresponding to the premium plus
	 *                             subscription
	 * @param lblPremiumPlusIcon   label corresponding to the premium plus
	 *                             subscription
	 * @param panelPremiumIcon     panel corresponding to the premium subscription
	 * @param lblPremiumIcon       label corresponding to the premium subscription
	 * @param panelBasicIcon       panel corresponding to the basic subscription
	 * @param lblBasicIcon         label corresponding to the basic subscription
	 */
	private void addSubscriptionImages(Client client, JPanel panelPremiumPlusIcon, JLabel lblPremiumPlusIcon,
			JPanel panelPremiumIcon, JLabel lblPremiumIcon, JPanel panelBasicIcon, JLabel lblBasicIcon) {
		addImagePremiumPlus(client, panelPremiumPlusIcon, lblPremiumPlusIcon);
		addImagePremium(client, panelPremiumIcon, lblPremiumIcon);
		addImageBasic(client, panelBasicIcon, lblBasicIcon);
	}

	/**
	 * Asks the client to confirm the subscription change.
	 * 
	 * @return reply value
	 */
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

	/**
	 * Changes the client's subscription if the selected one is different.
	 * 
	 * @param client               logged client
	 * @param panelPremiumPlusIcon panel corresponding to the premium plus
	 *                             subscription
	 * @param lblPremiumPlusIcon   label corresponding to the premium plus
	 *                             subscription
	 * @param panelPremiumIcon     panel corresponding to the premium subscription
	 * @param lblPremiumIcon       label corresponding to the premium subscription
	 * @param panelBasicIcon       panel corresponding to the basic subscription
	 * @param lblBasicIcon         label corresponding to the basic subscription
	 * @throws SQLException
	 * @throws Exception
	 */
	private void changeClientSubscription(Client client, JPanel panelPremiumPlusIcon, JLabel lblPremiumPlusIcon,
			JPanel panelPremiumIcon, JLabel lblPremiumIcon, JPanel panelBasicIcon, JLabel lblBasicIcon)
			throws SQLException, Exception {
		Controller controller = new Controller();

		if (actualSubscription.equalsIgnoreCase(newSubscription)) {
			WindowUtils.errorPane("Ya posee ese nivel de suscripción.", "Error");
		} else {
			int confirm = askToConfirmChange();
			if (confirm == 0) {
				if (actualSubscription.equalsIgnoreCase("basic")) {
					String bankNumber = WindowUtils.inputPaneWithIcon("Introduzca su número de cuenta:", "Plan de pago",
							"img/icon/money.png");

					controller.changeClientSubscription(client, bankNumber, newSubscription);
				} else if (actualSubscription.equalsIgnoreCase("premium")) {
					ClientP clientP = controller.getPremiumClient(client);
					controller.changeClientSubscription(client, clientP.getBankAccount(), newSubscription);
				} else if (actualSubscription.equalsIgnoreCase("premium plus")) {
					ClientPP clientPP = controller.getPremiumPlusClient(client);
					controller.changeClientSubscription(client, clientPP.getBankAccount(), newSubscription);
				}

				WindowUtils.confirmationPane("Su suscripción a cambiado a " + newSubscription + ".", "Actualización");

				changedClient = controller.clientByUsername(client.getUsername());

				addSubscriptionImages(changedClient, panelPremiumPlusIcon, lblPremiumPlusIcon, panelPremiumIcon,
						lblPremiumIcon, panelBasicIcon, lblBasicIcon);
			}
		}
	}

	/**
	 * Changes the client's subscription, controlling the exceptions.
	 * 
	 * @param client               logged client
	 * @param panelPremiumPlusIcon panel corresponding to the premium plus
	 *                             subscription
	 * @param lblPremiumPlusIcon   label corresponding to the premium plus
	 *                             subscription
	 * @param panelPremiumIcon     panel corresponding to the premium subscription
	 * @param lblPremiumIcon       label corresponding to the premium subscription
	 * @param panelBasicIcon       panel corresponding to the basic subscription
	 * @param lblBasicIcon         label corresponding to the basic subscription
	 */
	private void doChangeClientSubscription(Client client, JPanel panelPremiumPlusIcon, JLabel lblPremiumPlusIcon,
			JPanel panelPremiumIcon, JLabel lblPremiumIcon, JPanel panelBasicIcon, JLabel lblBasicIcon) {
		try {
			changeClientSubscription(client, panelPremiumPlusIcon, lblPremiumPlusIcon, panelPremiumIcon, lblPremiumIcon,
					panelBasicIcon, lblBasicIcon);
		} catch (SQLException e) {
			WindowUtils.errorPane("No se ha podido realizar el cambio de suscripción.", "Error");
		} catch (Exception e) {
			WindowUtils.errorPane("No se ha podido realizar el cambio de suscripción.", "Error");
		}
	}
}
