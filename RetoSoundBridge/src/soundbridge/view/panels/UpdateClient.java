package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import soundbridge.database.pojos.Client;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

public class UpdateClient extends JPanel {

	private static final long serialVersionUID = 2091925243705072798L;
	private Client changedClient = null;
	
	public UpdateClient(JFrame frame, Client client) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);
		
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
		
		JLabel lblTitle = new JLabel("Actualice sus datos:");
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTitle.setBounds(87, 75, 349, 38);
		lblTitle.setForeground(Color.white);
		add(lblTitle);
		
		JLabel lblTitle2 = new JLabel("Cambie su contraseña:");
		lblTitle2.setForeground(Color.WHITE);
		lblTitle2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTitle2.setBounds(509, 75, 349, 38);
		add(lblTitle2);
		
		JLabel lblPasswd1 = new JLabel("Contraseña:");
		lblPasswd1.setForeground(Color.WHITE);
		lblPasswd1.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPasswd1.setBounds(517, 142, 200, 35);
		lblPasswd1.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPasswd1);
		
		JLabel lblPasswd2 = new JLabel("Repita la contraseña:");
		lblPasswd2.setForeground(Color.WHITE);
		lblPasswd2.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPasswd2.setBounds(517, 202, 200, 35);
		lblPasswd2.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPasswd2);

		JLabel lblPersonalId = new JLabel("DNI:");
		lblPersonalId.setForeground(Color.WHITE);
		lblPersonalId.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPersonalId.setBounds(29, 142, 200, 35);
		lblPersonalId.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPersonalId);

		JLabel lblGender = new JLabel("Género:");
		lblGender.setForeground(Color.WHITE);
		lblGender.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblGender.setBounds(29, 202, 200, 35);
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblGender);

		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setForeground(Color.WHITE);
		lblNacionalidad.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNacionalidad.setBounds(29, 262, 200, 35);
		lblNacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNacionalidad);

		JLabel lblBirthDate = new JLabel("Fecha de nacimiento:");
		lblBirthDate.setForeground(Color.WHITE);
		lblBirthDate.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblBirthDate.setBounds(29, 322, 200, 35);
		lblBirthDate.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblBirthDate);

		JLabel lblAdress = new JLabel("Dirección:");
		lblAdress.setForeground(Color.WHITE);
		lblAdress.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblAdress.setBounds(29, 382, 200, 35);
		lblAdress.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblAdress);

		JLabel lblPhoneNumber = new JLabel("Teléfono:");
		lblPhoneNumber.setForeground(Color.WHITE);
		lblPhoneNumber.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPhoneNumber.setBounds(29, 477, 200, 35);
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPhoneNumber);

		JLabel lblMail = new JLabel("E-mail:");
		lblMail.setForeground(Color.WHITE);
		lblMail.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblMail.setBounds(29, 537, 200, 35);
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblMail);
		
		JPasswordField passwdField1 = new JPasswordField();
		passwdField1.setOpaque(false);
		passwdField1.setHorizontalAlignment(SwingConstants.CENTER);
		passwdField1.setForeground(Color.WHITE);
		passwdField1.setFont(new Font("Dialog", Font.PLAIN, 15));
		passwdField1.setColumns(10);
		passwdField1.setCaretColor(Color.WHITE);
		passwdField1.setBorder(new LineBorder(Color.WHITE, 2));
		passwdField1.setBounds(723, 142, 200, 35);
		add(passwdField1);
		passwdField1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passwdField1.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				passwdField1.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});

		JPasswordField passwdField2 = new JPasswordField();
		passwdField2.setOpaque(false);
		passwdField2.setHorizontalAlignment(SwingConstants.CENTER);
		passwdField2.setForeground(Color.WHITE);
		passwdField2.setFont(new Font("Dialog", Font.PLAIN, 15));
		passwdField2.setColumns(10);
		passwdField2.setCaretColor(Color.WHITE);
		passwdField2.setBorder(new LineBorder(Color.WHITE, 2));
		passwdField2.setBounds(723, 202, 200, 35);
		add(passwdField2);
		passwdField2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passwdField2.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				passwdField2.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});

		JTextField textPersonalId = new JTextField(client.getPersonalId());
		textPersonalId.setOpaque(false);
		textPersonalId.setHorizontalAlignment(SwingConstants.CENTER);
		textPersonalId.setForeground(Color.WHITE);
		textPersonalId.setFont(new Font("Dialog", Font.PLAIN, 15));
		textPersonalId.setColumns(10);
		textPersonalId.setCaretColor(Color.WHITE);
		textPersonalId.setBorder(new LineBorder(Color.WHITE, 2));
		textPersonalId.setBounds(235, 142, 200, 35);
		add(textPersonalId);
		textPersonalId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textPersonalId.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textPersonalId.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});

		JTextField textGender = new JTextField(client.getGender());
		textGender.setOpaque(false);
		textGender.setHorizontalAlignment(SwingConstants.CENTER);
		textGender.setForeground(Color.WHITE);
		textGender.setFont(new Font("Dialog", Font.PLAIN, 15));
		textGender.setColumns(10);
		textGender.setCaretColor(Color.WHITE);
		textGender.setBorder(new LineBorder(Color.WHITE, 2));
		textGender.setBounds(235, 202, 200, 35);
		add(textGender);
		textGender.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textGender.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textGender.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});

		JTextField textNationality = new JTextField(client.getNationality());
		textNationality.setOpaque(false);
		textNationality.setHorizontalAlignment(SwingConstants.CENTER);
		textNationality.setForeground(Color.WHITE);
		textNationality.setFont(new Font("Dialog", Font.PLAIN, 15));
		textNationality.setColumns(10);
		textNationality.setCaretColor(Color.WHITE);
		textNationality.setBorder(new LineBorder(Color.WHITE, 2));
		textNationality.setBounds(235, 262, 200, 35);
		add(textNationality);
		textNationality.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textNationality.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textNationality.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});
		JTextField textBirthDate = new JTextField((new SimpleDateFormat("dd-MM-yyyy")).format(client.getBirthDate()));
		textBirthDate.setOpaque(false);
		textBirthDate.setHorizontalAlignment(SwingConstants.CENTER);
		textBirthDate.setForeground(Color.WHITE);
		textBirthDate.setFont(new Font("Dialog", Font.PLAIN, 15));
		textBirthDate.setColumns(10);
		textBirthDate.setCaretColor(Color.WHITE);
		textBirthDate.setBorder(new LineBorder(Color.WHITE, 2));
		textBirthDate.setBounds(235, 322, 200, 35);
		add(textBirthDate);
		textBirthDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textBirthDate.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textBirthDate.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});
		
		JTextArea textAreaAddress = new JTextArea(client.getAddress());
		textAreaAddress.setOpaque(false);
		textAreaAddress.setForeground(Color.WHITE);
		textAreaAddress.setFont(new Font("Dialog", Font.PLAIN, 15));
		textAreaAddress.setColumns(10);
		textAreaAddress.setCaretColor(Color.WHITE);
		textAreaAddress.setBorder(new LineBorder(Color.WHITE, 2));
		textAreaAddress.setBounds(235, 382, 200, 70);
		add(textAreaAddress);
		textAreaAddress.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textAreaAddress.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textAreaAddress.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});
		textAreaAddress.setLineWrap(true);
		textAreaAddress.setWrapStyleWord(true);
		
		JTextField textPhone = new JTextField(client.getTelephone());
		textPhone.setOpaque(false);
		textPhone.setHorizontalAlignment(SwingConstants.CENTER);
		textPhone.setForeground(Color.WHITE);
		textPhone.setFont(new Font("Dialog", Font.PLAIN, 15));
		textPhone.setColumns(10);
		textPhone.setCaretColor(Color.WHITE);
		textPhone.setBorder(new LineBorder(Color.WHITE, 2));
		textPhone.setBounds(235, 477, 200, 35);
		add(textPhone);
		textPhone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textPhone.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textPhone.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});
		JTextField textEmail = new JTextField(client.getEmail());
		textEmail.setOpaque(false);
		textEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textEmail.setForeground(Color.WHITE);
		textEmail.setFont(new Font("Dialog", Font.PLAIN, 15));
		textEmail.setColumns(10);
		textEmail.setCaretColor(Color.WHITE);
		textEmail.setBorder(new LineBorder(Color.WHITE, 2));
		textEmail.setBounds(235, 537, 200, 35);
		add(textEmail);
		textEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textEmail.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textEmail.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});
		
		JButton btnUpdateInfo = new JButton("Actualizar");
		btnUpdateInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnUpdateInfo.setBounds(235, 597, 200, 40);
		add(btnUpdateInfo);
		btnUpdateInfo.setForeground(Color.black);
		btnUpdateInfo.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		btnUpdateInfo.setBackground(new Color(244, 135, 244));
		btnUpdateInfo.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		btnUpdateInfo.setOpaque(true);
		btnUpdateInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton btnChangePasswd = new JButton("Confirmar");
		btnChangePasswd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnChangePasswd.setBounds(723, 262, 200, 40);
		add(btnChangePasswd);
		btnChangePasswd.setForeground(Color.black);
		btnChangePasswd.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		btnChangePasswd.setBackground(new Color(244, 135, 244));
		btnChangePasswd.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		btnChangePasswd.setOpaque(true);
		btnChangePasswd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JPanel panelBackground = new JPanel();
		panelBackground.setBounds(0, 0, 1000, 672);
		add(panelBackground);
		panelBackground.setLayout(new BorderLayout(0, 0));

		JLabel lblBackground = new JLabel("");
		panelBackground.add(lblBackground, BorderLayout.CENTER);

		WindowUtils.addImage(panelBackground, lblBackground, "img/panel/update_client_bg.jpeg");
		
		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");
		
	}
}
