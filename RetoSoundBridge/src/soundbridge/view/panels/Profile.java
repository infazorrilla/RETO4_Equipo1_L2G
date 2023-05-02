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
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import soundbridge.controller.Controller;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

public class Profile extends JPanel {

	private static final long serialVersionUID = -6645561962016339329L;

	public Profile(JFrame frame, Client client) {
		initialize(frame, client);
	}

	private void initialize(JFrame frame, Client client) {
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
				frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame, client, null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});
		panelHomeIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelHomeIcon.setToolTipText("Volver a mi biblioteca.");

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
		btnLogOut.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logOut(frame);
			}
		});
		btnLogOut.setBounds(200, 130, 113, 30);
		add(btnLogOut);
		btnLogOut.setForeground(Color.white);
		btnLogOut.setFont(new Font("Lucida Grande", Font.ITALIC, 15));
		btnLogOut.setBorder(new LineBorder(Color.black, 0));
		btnLogOut.setOpaque(false);
		btnLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JPanel panelEditSubscriptionIcon = new JPanel();
		panelEditSubscriptionIcon.setBounds(50, 200, 25, 25);
		add(panelEditSubscriptionIcon);
		panelEditSubscriptionIcon.setLayout(new BorderLayout(0, 0));
		panelEditSubscriptionIcon.setOpaque(false);
		panelEditSubscriptionIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane()
						.add(PanelFactory.getJPanel(PanelFactory.CHANGE_SUBSCRIPTION, frame, client, null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});
		panelEditSubscriptionIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelEditSubscriptionIcon.setToolTipText("Cambiar mi suscripción.");

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

		JButton btnAcceptLogIn = new JButton("Eliminar cuenta");
		btnAcceptLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDeleteAccount(frame, client);
			}
		});
		btnAcceptLogIn.setBounds(50, 520, 200, 50);
		add(btnAcceptLogIn);
		btnAcceptLogIn.setForeground(Color.white);
		btnAcceptLogIn.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		btnAcceptLogIn.setBackground(new Color(244, 135, 244, 20));
		btnAcceptLogIn.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		btnAcceptLogIn.setOpaque(false);
		btnAcceptLogIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JPanel panelEditInfoIcon = new JPanel();
		panelEditInfoIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelEditInfoIcon.setBounds(310, 200, 25, 25);
		panelEditInfoIcon.setLayout(new BorderLayout(0, 0));
		add(panelEditInfoIcon);
		panelEditInfoIcon.setOpaque(false);
		panelEditInfoIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane()
						.add(PanelFactory.getJPanel(PanelFactory.UPDATE_CLIENT, frame, client, null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});
		panelEditInfoIcon.setToolTipText("Editar datos de mi cuenta.");

		JLabel lblEditInfoIcon = new JLabel("");
		panelEditInfoIcon.add(lblEditInfoIcon, BorderLayout.CENTER);

		JLabel lblInfo = new JLabel("Datos de la cuenta:");
		lblInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblInfo.setBounds(350, 200, 301, 27);
		lblInfo.setForeground(Color.white);
		add(lblInfo);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblDni.setBounds(350, 250, 120, 20);
		lblDni.setForeground(new Color(244, 135, 244));
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDni);

		JLabel lblDniValue = new JLabel(client.getPersonalId());
		lblDniValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblDniValue.setBounds(480, 250, 301, 20);
		lblDniValue.setForeground(Color.white);
		add(lblDniValue);

		JLabel lblGender = new JLabel("Género:");
		lblGender.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblGender.setBounds(350, 300, 120, 20);
		lblGender.setForeground(new Color(244, 135, 244));
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblGender);

		JLabel lblGenderValue = new JLabel(client.getGender());
		lblGenderValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblGenderValue.setBounds(480, 300, 301, 20);
		lblGenderValue.setForeground(Color.white);
		add(lblGenderValue);

		JLabel lblNationality = new JLabel("Nacionalidad:");
		lblNationality.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNationality.setBounds(350, 350, 120, 20);
		lblNationality.setForeground(new Color(244, 135, 244));
		lblNationality.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNationality);

		JLabel lblNationalityValue = new JLabel(client.getNationality());
		lblNationalityValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNationalityValue.setBounds(480, 350, 301, 20);
		lblNationalityValue.setForeground(Color.white);
		add(lblNationalityValue);

		JLabel lblBirthDate = new JLabel("Nacimiento:");
		lblBirthDate.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblBirthDate.setBounds(350, 400, 120, 20);
		lblBirthDate.setForeground(new Color(244, 135, 244));
		lblBirthDate.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblBirthDate);

		JLabel lblBirthDateValue = new JLabel((new SimpleDateFormat("dd-MM-yyyy")).format(client.getBirthDate()));
		lblBirthDateValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblBirthDateValue.setBounds(480, 400, 301, 20);
		lblBirthDateValue.setForeground(Color.white);
		add(lblBirthDateValue);

		JLabel lblAddress = new JLabel("Dirección:");
		lblAddress.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblAddress.setBounds(350, 450, 120, 20);
		lblAddress.setForeground(new Color(244, 135, 244));
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblAddress);

		JLabel lblAddressValue = new JLabel(client.getAddress());
		lblAddressValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblAddressValue.setBounds(480, 450, 400, 20);
		lblAddressValue.setForeground(Color.white);
		add(lblAddressValue);

		JLabel lblTelephone = new JLabel("Teléfono:");
		lblTelephone.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblTelephone.setBounds(350, 500, 120, 20);
		lblTelephone.setForeground(new Color(244, 135, 244));
		lblTelephone.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTelephone);

		JLabel lblTelephoneValue = new JLabel(client.getTelephone());
		lblTelephoneValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblTelephoneValue.setBounds(480, 500, 301, 20);
		lblTelephoneValue.setForeground(Color.white);
		add(lblTelephoneValue);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblEmail.setBounds(350, 550, 120, 20);
		lblEmail.setForeground(new Color(244, 135, 244));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEmail);

		JLabel lblEmailValue = new JLabel(client.getEmail());
		lblEmailValue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblEmailValue.setBounds(480, 550, 301, 20);
		lblEmailValue.setForeground(Color.white);
		add(lblEmailValue);

		WindowUtils.addImage(panelProfileIcon, lblProfileIcon, "img/icon/profile.png");
		WindowUtils.addImage(panelHomeIcon, lblHomeIcon, "img/icon/home.png");
		WindowUtils.addImage(panelEditSubscriptionIcon, lblEditSubscriptionIcon, "img/icon/pen.png");
		WindowUtils.addImage(panelEditInfoIcon, lblEditInfoIcon, "img/icon/pen.png");
		addSubscriptionImage(client, panelSubscriptionIcon, lblSubscriptionIcon);
	}

	private void logOut(JFrame frame) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}

	private void addSubscriptionImage(Client client, JPanel panel, JLabel lbl) {
		if (client instanceof ClientPP) {
			WindowUtils.addImage(panel, lbl, "img/icon/sbpp.png");
		} else if (client instanceof ClientP) {
			WindowUtils.addImage(panel, lbl, "img/icon/sbp.png");
		} else {
			WindowUtils.addImage(panel, lbl, "img/icon/sbbasic.png");
		}
	}

	private int askToConfirmDeletion() {
		int reply = WindowUtils.yesOrNoPaneWithIcon("¿Desea eliminar la cuenta?", "Eliminar Cuenta",
				"img/icon/alert.png");
		return reply;
	}

	private void doDeleteAccount(JFrame frame, Client client) {
		Controller controller = new Controller();
		int reply = askToConfirmDeletion();
		if (reply == 0) {
			try {
				controller.deleteAccount(client);
				WindowUtils.messagePaneWithIcon("Su cuenta ha sido eliminada.", "Confirmación", "img/icon/bye.png");

				goToLogin(frame);

			} catch (SQLException e) {
				WindowUtils.errorPane("No se ha podido eliminar su cuenta.", "Error en la base de datos");
			} catch (Exception e) {
				WindowUtils.errorPane("No se ha podido eliminar su cuenta.", "Error general");
			}
		}
	}

	private void goToLogin(JFrame frame) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null));
		frame.revalidate();
		frame.repaint();
	}

}
