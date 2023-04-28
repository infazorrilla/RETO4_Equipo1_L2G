package soundbridge.view.panels;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import soundbridge.view.components.TextPrompt;
import soundbridge.view.factory.PanelFactory;

import java.awt.BorderLayout;

public class SignUp extends JPanel{

	private static final long serialVersionUID = -2586474039198890631L;
	

	public SignUp(JFrame frame) {
	
	setBounds(0, 0, 1000, 672);
	setBackground(Color.black);
	setLayout(null);
	
	JPanel panelProfileIcon = new JPanel();
	panelProfileIcon.setBounds(30, 30, 150, 150);
	panelProfileIcon.setOpaque(false);
	add(panelProfileIcon);
	panelProfileIcon.setLayout(new BorderLayout(0, 0));
	
	JLabel lblProfileIcon = new JLabel("");
	panelProfileIcon.add(lblProfileIcon, BorderLayout.CENTER);
	
	JTextField textFildNameSignUp = new JTextField();
	textFildNameSignUp.setBounds(190, 49, 200, 40);
	add(textFildNameSignUp);
	textFildNameSignUp.setColumns(10);
	textFildNameSignUp.setForeground(Color.white);
	textFildNameSignUp.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	textFildNameSignUp.setBorder(new LineBorder(Color.WHITE, 2));
	textFildNameSignUp.setCaretColor(Color.WHITE);
	textFildNameSignUp.setOpaque(false);
	textFildNameSignUp.setHorizontalAlignment(SwingConstants.CENTER);
	textFildNameSignUp.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			textFildNameSignUp.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		}

		@Override
		public void focusLost(FocusEvent e) {
			textFildNameSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		}
	});
	
	JTextField textFildLastNameSignUp = new JTextField();
	textFildLastNameSignUp.setBounds(190, 112, 200, 40);
	add(textFildLastNameSignUp);
	textFildLastNameSignUp.setColumns(10);
	textFildLastNameSignUp.setForeground(Color.white);
	textFildLastNameSignUp.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	textFildLastNameSignUp.setBorder(new LineBorder(Color.WHITE, 2));
	textFildLastNameSignUp.setCaretColor(Color.WHITE);
	textFildLastNameSignUp.setOpaque(false);
	textFildLastNameSignUp.setHorizontalAlignment(SwingConstants.CENTER);
	textFildLastNameSignUp.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			textFildLastNameSignUp.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		}

		@Override
		public void focusLost(FocusEvent e) {
			textFildLastNameSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		}
	});
	
	TextPrompt placeholderName = new TextPrompt("Nombre", textFildNameSignUp);
	placeholderName.changeAlpha(0.8f);
	placeholderName.changeStyle(Font.ITALIC);
	placeholderName.setHorizontalAlignment(SwingConstants.CENTER);
	
	TextPrompt placeholderLastName = new TextPrompt("Apellido", textFildLastNameSignUp);
	placeholderLastName.changeAlpha(0.8f);
	placeholderLastName.changeStyle(Font.ITALIC);
	placeholderLastName.setHorizontalAlignment(SwingConstants.CENTER);
	
	
	
	JLabel lblPersonalInformation = new JLabel("Datos personales:");
	lblPersonalInformation.setFont(new Font("Lucida Grande", Font.BOLD, 16));
	lblPersonalInformation.setForeground(new Color(255, 255, 255));
	lblPersonalInformation.setBounds(88, 220, 143, 23);
	add(lblPersonalInformation);
	
	JLabel lblUser = new JLabel("Usuario:");
	lblUser.setForeground(Color.WHITE);
	lblUser.setFont(new Font("Dialog", Font.PLAIN, 15));
	lblUser.setBounds(190, 270, 143, 23);
	add(lblUser);
	
	JLabel lblPasswd = new JLabel("Contraseña:");
	lblPasswd.setForeground(Color.WHITE);
	lblPasswd.setFont(new Font("Dialog", Font.PLAIN, 15));
	lblPasswd.setBounds(165, 310, 99, 23);
	add(lblPasswd);
	
	JLabel lblPersonalId = new JLabel("DNI:");
	lblPersonalId.setForeground(Color.WHITE);
	lblPersonalId.setFont(new Font("Dialog", Font.PLAIN, 15));
	lblPersonalId.setBounds(216, 350, 66, 23);
	add(lblPersonalId);
	
	JLabel lblGender = new JLabel("Género:");
	lblGender.setForeground(Color.WHITE);
	lblGender.setFont(new Font("Dialog", Font.PLAIN, 15));
	lblGender.setBounds(198, 390, 59, 23);
	add(lblGender);
	
	JLabel lblNacionalidad = new JLabel("Nacionalidad:");
	lblNacionalidad.setForeground(Color.WHITE);
	lblNacionalidad.setFont(new Font("Dialog", Font.PLAIN, 15));
	lblNacionalidad.setBounds(165, 430, 92, 23);
	add(lblNacionalidad);
	
	JLabel lblBirthDate = new JLabel("Fecha de nacimiento:");
	lblBirthDate.setForeground(Color.WHITE);
	lblBirthDate.setFont(new Font("Dialog", Font.PLAIN, 15));
	lblBirthDate.setBounds(110, 470, 143, 23);
	add(lblBirthDate);
	
	JLabel lblAdress = new JLabel("Dirección:");
	lblAdress.setForeground(Color.WHITE);
	lblAdress.setFont(new Font("Dialog", Font.PLAIN, 15));
	lblAdress.setBounds(190, 510, 66, 23);
	add(lblAdress);
	
	JLabel lblPhoneNumber = new JLabel("Teléfono:");
	lblPhoneNumber.setForeground(Color.WHITE);
	lblPhoneNumber.setFont(new Font("Dialog", Font.PLAIN, 15));
	lblPhoneNumber.setBounds(190, 550, 92, 23);
	add(lblPhoneNumber);
	
	JLabel lblMail = new JLabel("E-mail:");
	lblMail.setForeground(Color.WHITE);
	lblMail.setFont(new Font("Dialog", Font.PLAIN, 15));
	lblMail.setBounds(203, 590, 66, 23);
	add(lblMail);
	
	JTextField textFildUsernameSignUp = new JTextField();
	textFildUsernameSignUp.setBounds(256, 264, 200, 35);
	add(textFildUsernameSignUp);
	textFildUsernameSignUp.setColumns(10);
	textFildUsernameSignUp.setForeground(Color.white);
	textFildUsernameSignUp.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	textFildUsernameSignUp.setBorder(new LineBorder(Color.WHITE, 2));
	textFildUsernameSignUp.setCaretColor(Color.WHITE);
	textFildUsernameSignUp.setOpaque(false);
	textFildUsernameSignUp.setHorizontalAlignment(SwingConstants.CENTER);
	textFildUsernameSignUp.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			textFildUsernameSignUp.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		}

		@Override
		public void focusLost(FocusEvent e) {
			textFildUsernameSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		}
	});
	
	JTextField textFieldPasswdSignUp = new JTextField();
	textFieldPasswdSignUp.setOpaque(false);
	textFieldPasswdSignUp.setHorizontalAlignment(SwingConstants.CENTER);
	textFieldPasswdSignUp.setForeground(Color.WHITE);
	textFieldPasswdSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
	textFieldPasswdSignUp.setColumns(10);
	textFieldPasswdSignUp.setCaretColor(Color.WHITE);
	textFieldPasswdSignUp.setBorder(new LineBorder(Color.WHITE, 2));
	textFieldPasswdSignUp.setBounds(256, 304, 200, 35);
	add(textFieldPasswdSignUp);
	textFieldPasswdSignUp.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			textFieldPasswdSignUp.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		}

		@Override
		public void focusLost(FocusEvent e) {
			textFieldPasswdSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		}
	});
	
	JTextField textFieldPersonalIdSignUp = new JTextField();
	textFieldPersonalIdSignUp.setOpaque(false);
	textFieldPersonalIdSignUp.setHorizontalAlignment(SwingConstants.CENTER);
	textFieldPersonalIdSignUp.setForeground(Color.WHITE);
	textFieldPersonalIdSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
	textFieldPersonalIdSignUp.setColumns(10);
	textFieldPersonalIdSignUp.setCaretColor(Color.WHITE);
	textFieldPersonalIdSignUp.setBorder(new LineBorder(Color.WHITE, 2));
	textFieldPersonalIdSignUp.setBounds(256, 344, 200, 35);
	add(textFieldPersonalIdSignUp);
	textFieldPersonalIdSignUp.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			textFieldPersonalIdSignUp.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		}

		@Override
		public void focusLost(FocusEvent e) {
			textFieldPersonalIdSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		}
	});
	
	JTextField textFildGenderSignUp = new JTextField();
	textFildGenderSignUp.setOpaque(false);
	textFildGenderSignUp.setHorizontalAlignment(SwingConstants.CENTER);
	textFildGenderSignUp.setForeground(Color.WHITE);
	textFildGenderSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
	textFildGenderSignUp.setColumns(10);
	textFildGenderSignUp.setCaretColor(Color.WHITE);
	textFildGenderSignUp.setBorder(new LineBorder(Color.WHITE, 2));
	textFildGenderSignUp.setBounds(256, 384, 200, 35);
	add(textFildGenderSignUp);
	textFildGenderSignUp.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			textFildGenderSignUp.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		}

		@Override
		public void focusLost(FocusEvent e) {
			textFildGenderSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		}
	});
	
	JTextField textFieldNationalitySignUp = new JTextField();
	textFieldNationalitySignUp.setOpaque(false);
	textFieldNationalitySignUp.setHorizontalAlignment(SwingConstants.CENTER);
	textFieldNationalitySignUp.setForeground(Color.WHITE);
	textFieldNationalitySignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
	textFieldNationalitySignUp.setColumns(10);
	textFieldNationalitySignUp.setCaretColor(Color.WHITE);
	textFieldNationalitySignUp.setBorder(new LineBorder(Color.WHITE, 2));
	textFieldNationalitySignUp.setBounds(256, 424, 200, 35);
	add(textFieldNationalitySignUp);
	textFieldNationalitySignUp.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			textFieldNationalitySignUp.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		}

		@Override
		public void focusLost(FocusEvent e) {
			textFieldNationalitySignUp.setBorder(new LineBorder(Color.WHITE, 2));
		}
	});
	JTextField textFieldBirthDateSignUp = new JTextField();
	textFieldBirthDateSignUp.setOpaque(false);
	textFieldBirthDateSignUp.setHorizontalAlignment(SwingConstants.CENTER);
	textFieldBirthDateSignUp.setForeground(Color.WHITE);
	textFieldBirthDateSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
	textFieldBirthDateSignUp.setColumns(10);
	textFieldBirthDateSignUp.setCaretColor(Color.WHITE);
	textFieldBirthDateSignUp.setBorder(new LineBorder(Color.WHITE, 2));
	textFieldBirthDateSignUp.setBounds(256, 464, 200, 35);
	add(textFieldBirthDateSignUp);
	textFieldBirthDateSignUp.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			textFieldBirthDateSignUp.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		}

		@Override
		public void focusLost(FocusEvent e) {
			textFieldBirthDateSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		}
	});
	JTextField textFieldAdressSignUp = new JTextField();
	textFieldAdressSignUp.setOpaque(false);
	textFieldAdressSignUp.setHorizontalAlignment(SwingConstants.CENTER);
	textFieldAdressSignUp.setForeground(Color.WHITE);
	textFieldAdressSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
	textFieldAdressSignUp.setColumns(10);
	textFieldAdressSignUp.setCaretColor(Color.WHITE);
	textFieldAdressSignUp.setBorder(new LineBorder(Color.WHITE, 2));
	textFieldAdressSignUp.setBounds(256, 504, 200, 35);
	add(textFieldAdressSignUp);
	textFieldAdressSignUp.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			textFieldAdressSignUp.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		}

		@Override
		public void focusLost(FocusEvent e) {
			textFieldAdressSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		}
	});
	JTextField textFieldPhoneNumberSignUp = new JTextField();
	textFieldPhoneNumberSignUp.setOpaque(false);
	textFieldPhoneNumberSignUp.setHorizontalAlignment(SwingConstants.CENTER);
	textFieldPhoneNumberSignUp.setForeground(Color.WHITE);
	textFieldPhoneNumberSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
	textFieldPhoneNumberSignUp.setColumns(10);
	textFieldPhoneNumberSignUp.setCaretColor(Color.WHITE);
	textFieldPhoneNumberSignUp.setBorder(new LineBorder(Color.WHITE, 2));
	textFieldPhoneNumberSignUp.setBounds(256, 544, 200, 35);
	add(textFieldPhoneNumberSignUp);
	textFieldPhoneNumberSignUp.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			textFieldPhoneNumberSignUp.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		}

		@Override
		public void focusLost(FocusEvent e) {
			textFieldPhoneNumberSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		}
	});
	JTextField textFieldEmailSignUp = new JTextField();
	textFieldEmailSignUp.setOpaque(false);
	textFieldEmailSignUp.setHorizontalAlignment(SwingConstants.CENTER);
	textFieldEmailSignUp.setForeground(Color.WHITE);
	textFieldEmailSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
	textFieldEmailSignUp.setColumns(10);
	textFieldEmailSignUp.setCaretColor(Color.WHITE);
	textFieldEmailSignUp.setBorder(new LineBorder(Color.WHITE, 2));
	textFieldEmailSignUp.setBounds(256, 584, 200, 35);
	add(textFieldEmailSignUp);
	textFieldEmailSignUp.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			textFieldEmailSignUp.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		}

		@Override
		public void focusLost(FocusEvent e) {
			textFieldEmailSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		}
	});
	
	JPanel panelHomeIcon = new JPanel();
	panelHomeIcon.setBounds(900, 45, 50, 50);
	add(panelHomeIcon);
	panelHomeIcon.setLayout(new BorderLayout(0, 0));
	panelHomeIcon.setOpaque(false);
	panelHomeIcon.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.LIBRARY, frame, null));
			frame.revalidate();
			frame.repaint();
		}
	});
	
	JLabel lblHomeIcon = new JLabel("");
	panelHomeIcon.add(lblHomeIcon, BorderLayout.CENTER);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	addImage(panelHomeIcon, lblHomeIcon, "img/icon/home.png");
	addImage(panelProfileIcon, lblProfileIcon, "img/icon/profile.png");
	
	JLabel lblTiposDeSu = new JLabel("Tipos de suscripción:");
	lblTiposDeSu.setForeground(Color.WHITE);
	lblTiposDeSu.setFont(new Font("Dialog", Font.BOLD, 16));
	lblTiposDeSu.setBounds(595, 227, 178, 23);
	add(lblTiposDeSu);
}
	private void addImage(JPanel panel, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);
		label.setIcon(icon);
	}
}
