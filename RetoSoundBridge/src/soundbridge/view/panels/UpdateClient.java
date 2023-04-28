package soundbridge.view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import soundbridge.database.pojos.Client;

public class UpdateClient extends JPanel {

	private static final long serialVersionUID = 2091925243705072798L;
	
	public UpdateClient(JFrame frame, Client client) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);
		
		JLabel lblTitle = new JLabel("Actualice sus datos:");
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTitle.setBounds(90, 89, 510, 38);
		lblTitle.setForeground(Color.white);
		add(lblTitle);
		
		JLabel lblPasswd = new JLabel("Contraseña:");
		lblPasswd.setForeground(Color.WHITE);
		lblPasswd.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPasswd.setBounds(50, 310, 200, 23);
		lblPasswd.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPasswd);

		JLabel lblPersonalId = new JLabel("DNI:");
		lblPersonalId.setForeground(Color.WHITE);
		lblPersonalId.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPersonalId.setBounds(50, 350, 200, 23);
		lblPersonalId.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPersonalId);

		JLabel lblGender = new JLabel("Género:");
		lblGender.setForeground(Color.WHITE);
		lblGender.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblGender.setBounds(50, 390, 200, 23);
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblGender);

		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setForeground(Color.WHITE);
		lblNacionalidad.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNacionalidad.setBounds(50, 430, 200, 23);
		lblNacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNacionalidad);

		JLabel lblBirthDate = new JLabel("Fecha de nacimiento:");
		lblBirthDate.setForeground(Color.WHITE);
		lblBirthDate.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblBirthDate.setBounds(50, 470, 200, 23);
		lblBirthDate.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblBirthDate);

		JLabel lblAdress = new JLabel("Dirección:");
		lblAdress.setForeground(Color.WHITE);
		lblAdress.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblAdress.setBounds(50, 510, 200, 23);
		lblAdress.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblAdress);

		JLabel lblPhoneNumber = new JLabel("Teléfono:");
		lblPhoneNumber.setForeground(Color.WHITE);
		lblPhoneNumber.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPhoneNumber.setBounds(50, 550, 200, 23);
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPhoneNumber);

		JLabel lblMail = new JLabel("E-mail:");
		lblMail.setForeground(Color.WHITE);
		lblMail.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblMail.setBounds(50, 590, 200, 23);
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblMail);

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
	}

}
