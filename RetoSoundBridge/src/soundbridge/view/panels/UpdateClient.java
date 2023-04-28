package soundbridge.view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;

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
		
		JLabel lblPasswd1 = new JLabel("Contraseña:");
		lblPasswd1.setForeground(Color.WHITE);
		lblPasswd1.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPasswd1.setBounds(90, 188, 200, 23);
		lblPasswd1.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPasswd1);
		
		JLabel lblPasswd2 = new JLabel("Repite la contraseña:");
		lblPasswd2.setForeground(Color.WHITE);
		lblPasswd2.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPasswd2.setBounds(90, 228, 200, 23);
		lblPasswd2.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPasswd2);

		JLabel lblPersonalId = new JLabel("DNI:");
		lblPersonalId.setForeground(Color.WHITE);
		lblPersonalId.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPersonalId.setBounds(90, 268, 200, 23);
		lblPersonalId.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPersonalId);

		JLabel lblGender = new JLabel("Género:");
		lblGender.setForeground(Color.WHITE);
		lblGender.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblGender.setBounds(90, 308, 200, 23);
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblGender);

		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setForeground(Color.WHITE);
		lblNacionalidad.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNacionalidad.setBounds(90, 348, 200, 23);
		lblNacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNacionalidad);

		JLabel lblBirthDate = new JLabel("Fecha de nacimiento:");
		lblBirthDate.setForeground(Color.WHITE);
		lblBirthDate.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblBirthDate.setBounds(90, 388, 200, 23);
		lblBirthDate.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblBirthDate);

		JLabel lblAdress = new JLabel("Dirección:");
		lblAdress.setForeground(Color.WHITE);
		lblAdress.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblAdress.setBounds(90, 428, 200, 23);
		lblAdress.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblAdress);

		JLabel lblPhoneNumber = new JLabel("Teléfono:");
		lblPhoneNumber.setForeground(Color.WHITE);
		lblPhoneNumber.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPhoneNumber.setBounds(90, 468, 200, 23);
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPhoneNumber);

		JLabel lblMail = new JLabel("E-mail:");
		lblMail.setForeground(Color.WHITE);
		lblMail.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblMail.setBounds(90, 508, 200, 23);
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblMail);
		
		JTextField textFieldPasswd1SignUp = new JTextField();
		textFieldPasswd1SignUp.setOpaque(false);
		textFieldPasswd1SignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPasswd1SignUp.setForeground(Color.WHITE);
		textFieldPasswd1SignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldPasswd1SignUp.setColumns(10);
		textFieldPasswd1SignUp.setCaretColor(Color.WHITE);
		textFieldPasswd1SignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldPasswd1SignUp.setBounds(296, 182, 200, 35);
		add(textFieldPasswd1SignUp);
		textFieldPasswd1SignUp.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldPasswd1SignUp.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textFieldPasswd1SignUp.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});

		JTextField textFieldPasswd2SignUp = new JTextField();
		textFieldPasswd2SignUp.setOpaque(false);
		textFieldPasswd2SignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPasswd2SignUp.setForeground(Color.WHITE);
		textFieldPasswd2SignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldPasswd2SignUp.setColumns(10);
		textFieldPasswd2SignUp.setCaretColor(Color.WHITE);
		textFieldPasswd2SignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldPasswd2SignUp.setBounds(296, 222, 200, 35);
		add(textFieldPasswd2SignUp);
		textFieldPasswd2SignUp.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldPasswd2SignUp.setBorder(new LineBorder(new Color(244, 135, 244), 2));
			}

			@Override
			public void focusLost(FocusEvent e) {
				textFieldPasswd2SignUp.setBorder(new LineBorder(Color.WHITE, 2));
			}
		});

		JTextField textFieldPersonalIdSignUp = new JTextField(client.getPersonalId());
		textFieldPersonalIdSignUp.setOpaque(false);
		textFieldPersonalIdSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPersonalIdSignUp.setForeground(Color.WHITE);
		textFieldPersonalIdSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldPersonalIdSignUp.setColumns(10);
		textFieldPersonalIdSignUp.setCaretColor(Color.WHITE);
		textFieldPersonalIdSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldPersonalIdSignUp.setBounds(296, 262, 200, 35);
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

		JTextField textFildGenderSignUp = new JTextField(client.getGender());
		textFildGenderSignUp.setOpaque(false);
		textFildGenderSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFildGenderSignUp.setForeground(Color.WHITE);
		textFildGenderSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFildGenderSignUp.setColumns(10);
		textFildGenderSignUp.setCaretColor(Color.WHITE);
		textFildGenderSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFildGenderSignUp.setBounds(296, 302, 200, 35);
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

		JTextField textFieldNationalitySignUp = new JTextField(client.getNationality());
		textFieldNationalitySignUp.setOpaque(false);
		textFieldNationalitySignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNationalitySignUp.setForeground(Color.WHITE);
		textFieldNationalitySignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldNationalitySignUp.setColumns(10);
		textFieldNationalitySignUp.setCaretColor(Color.WHITE);
		textFieldNationalitySignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldNationalitySignUp.setBounds(296, 342, 200, 35);
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
		JTextField textFieldBirthDateSignUp = new JTextField((new SimpleDateFormat("dd-MM-yyyy")).format(client.getBirthDate()));
		textFieldBirthDateSignUp.setOpaque(false);
		textFieldBirthDateSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldBirthDateSignUp.setForeground(Color.WHITE);
		textFieldBirthDateSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldBirthDateSignUp.setColumns(10);
		textFieldBirthDateSignUp.setCaretColor(Color.WHITE);
		textFieldBirthDateSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldBirthDateSignUp.setBounds(296, 382, 200, 35);
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
		JTextField textFieldAdressSignUp = new JTextField(client.getAddress());
		textFieldAdressSignUp.setOpaque(false);
		textFieldAdressSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAdressSignUp.setForeground(Color.WHITE);
		textFieldAdressSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldAdressSignUp.setColumns(10);
		textFieldAdressSignUp.setCaretColor(Color.WHITE);
		textFieldAdressSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldAdressSignUp.setBounds(296, 422, 200, 35);
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
		JTextField textFieldPhoneNumberSignUp = new JTextField(client.getTelephone());
		textFieldPhoneNumberSignUp.setOpaque(false);
		textFieldPhoneNumberSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPhoneNumberSignUp.setForeground(Color.WHITE);
		textFieldPhoneNumberSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldPhoneNumberSignUp.setColumns(10);
		textFieldPhoneNumberSignUp.setCaretColor(Color.WHITE);
		textFieldPhoneNumberSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldPhoneNumberSignUp.setBounds(296, 462, 200, 35);
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
		JTextField textFieldEmailSignUp = new JTextField(client.getEmail());
		textFieldEmailSignUp.setOpaque(false);
		textFieldEmailSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEmailSignUp.setForeground(Color.WHITE);
		textFieldEmailSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldEmailSignUp.setColumns(10);
		textFieldEmailSignUp.setCaretColor(Color.WHITE);
		textFieldEmailSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldEmailSignUp.setBounds(296, 502, 200, 35);
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
