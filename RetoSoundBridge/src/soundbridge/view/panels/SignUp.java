package soundbridge.view.panels;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import soundbridge.controller.Controller;
import soundbridge.database.managers.ClientManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.utils.WindowUtils;
import soundbridge.view.components.TextPrompt;
import soundbridge.view.factory.PanelFactory;

import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignUp extends JPanel {
	private JTextField textFieldNameSignUp = null;
	private JTextField textFieldLastNameSignUp = null;
	private JTextField textFieldUsernameSignUp = null;
	private JTextField textFieldPasswdSignUp = null;
	private JTextField textFieldPersonalIdSignUp = null;
	private JTextField textFieldGenderSignUp = null;
	private JTextField textFieldNationalitySignUp = null;
	private JTextField textFieldBirthDateSignUp = null;
	private JTextField textFieldAddressSignUp = null;
	private JTextField textFieldPhoneNumberSignUp = null;
	private JTextField textFieldEmailSignUp = null;
	private Controller controller = null;
	private String bankNumber = null;
	private int suscription = 0;
	private static final long serialVersionUID = -2586474039198890631L;

	public SignUp(JFrame frame) {

		initialize(frame);

	}

	private void initialize(JFrame frame) {
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

		textFieldNameSignUp = new JTextField();
		textFieldNameSignUp.setBounds(190, 49, 200, 40);
		add(textFieldNameSignUp);
		textFieldNameSignUp.setColumns(10);
		textFieldNameSignUp.setForeground(Color.white);
		textFieldNameSignUp.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textFieldNameSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldNameSignUp.setCaretColor(Color.WHITE);
		textFieldNameSignUp.setOpaque(true);
		textFieldNameSignUp.setBackground(Color.BLACK);
		textFieldNameSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNameSignUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateNameField();
			}
		});

		textFieldLastNameSignUp = new JTextField();
		textFieldLastNameSignUp.setBounds(190, 112, 200, 40);
		add(textFieldLastNameSignUp);
		textFieldLastNameSignUp.setColumns(10);
		textFieldLastNameSignUp.setForeground(Color.white);
		textFieldLastNameSignUp.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textFieldLastNameSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldLastNameSignUp.setCaretColor(Color.WHITE);
		textFieldLastNameSignUp.setOpaque(true);
		textFieldLastNameSignUp.setBackground(Color.BLACK);
		textFieldLastNameSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldLastNameSignUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateLastNameField();
			}
		});

		TextPrompt placeholderName = new TextPrompt("Nombre", textFieldNameSignUp);
		placeholderName.changeAlpha(0.8f);
		placeholderName.changeStyle(Font.BOLD + Font.ITALIC);
		placeholderName.setHorizontalAlignment(SwingConstants.CENTER);

		TextPrompt placeholderLastName = new TextPrompt("Apellido", textFieldLastNameSignUp);
		placeholderLastName.changeAlpha(0.8f);
		placeholderLastName.changeStyle(Font.BOLD + Font.ITALIC);
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

		textFieldUsernameSignUp = new JTextField();
		textFieldUsernameSignUp.setBounds(256, 264, 200, 35);
		add(textFieldUsernameSignUp);
		textFieldUsernameSignUp.setColumns(10);
		textFieldUsernameSignUp.setForeground(Color.white);
		textFieldUsernameSignUp.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textFieldUsernameSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldUsernameSignUp.setCaretColor(Color.WHITE);
		textFieldUsernameSignUp.setOpaque(true);
		textFieldUsernameSignUp.setBackground(Color.BLACK);
		textFieldUsernameSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUsernameSignUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateUserField();
			}
		});

		TextPrompt placeholderUsername = new TextPrompt("Min 5 caracteres", textFieldUsernameSignUp);
		placeholderUsername.changeAlpha(0.8f);
		placeholderUsername.changeStyle(Font.BOLD + Font.ITALIC);
		placeholderUsername.setHorizontalAlignment(SwingConstants.CENTER);

		textFieldPasswdSignUp = new JTextField();
		textFieldPasswdSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPasswdSignUp.setForeground(Color.WHITE);
		textFieldPasswdSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldPasswdSignUp.setColumns(10);
		textFieldPasswdSignUp.setCaretColor(Color.WHITE);
		textFieldPasswdSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldPasswdSignUp.setBounds(256, 304, 200, 35);
		textFieldPasswdSignUp.setOpaque(true);
		textFieldPasswdSignUp.setBackground(Color.BLACK);
		add(textFieldPasswdSignUp);
		textFieldPasswdSignUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validatePasswdField();
			}
		});

		TextPrompt placeholderPasswdSignUp = new TextPrompt("Min 10 caracteres", textFieldPasswdSignUp);
		placeholderPasswdSignUp.changeAlpha(0.8f);
		placeholderPasswdSignUp.changeStyle(Font.BOLD + Font.ITALIC);
		placeholderPasswdSignUp.setHorizontalAlignment(SwingConstants.CENTER);

		textFieldPersonalIdSignUp = new JTextField();
		textFieldPersonalIdSignUp.setForeground(Color.WHITE);
		textFieldPersonalIdSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldPersonalIdSignUp.setColumns(10);
		textFieldPersonalIdSignUp.setCaretColor(Color.WHITE);
		textFieldPersonalIdSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldPersonalIdSignUp.setBounds(256, 344, 200, 35);
		textFieldPersonalIdSignUp.setOpaque(true);
		textFieldPersonalIdSignUp.setBackground(Color.BLACK);
		textFieldPersonalIdSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		add(textFieldPersonalIdSignUp);
		textFieldPersonalIdSignUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validatePersonalIdField();
			}
		});

		textFieldGenderSignUp = new JTextField();
		textFieldGenderSignUp.setForeground(Color.WHITE);
		textFieldGenderSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldGenderSignUp.setColumns(10);
		textFieldGenderSignUp.setCaretColor(Color.WHITE);
		textFieldGenderSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldGenderSignUp.setBounds(256, 384, 200, 35);
		textFieldGenderSignUp.setOpaque(true);
		textFieldGenderSignUp.setBackground(Color.BLACK);
		textFieldGenderSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		add(textFieldGenderSignUp);
		textFieldGenderSignUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateGenderField();
			}
		});

		TextPrompt placeholderGenderSignUp = new TextPrompt("Hombre   Mujer   Otro", textFieldGenderSignUp);
		placeholderGenderSignUp.changeAlpha(0.8f);
		placeholderGenderSignUp.changeStyle(Font.BOLD + Font.ITALIC);
		placeholderGenderSignUp.setHorizontalAlignment(SwingConstants.CENTER);

		textFieldNationalitySignUp = new JTextField();
		textFieldNationalitySignUp.setForeground(Color.WHITE);
		textFieldNationalitySignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldNationalitySignUp.setColumns(10);
		textFieldNationalitySignUp.setCaretColor(Color.WHITE);
		textFieldNationalitySignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldNationalitySignUp.setBounds(256, 424, 200, 35);
		textFieldNationalitySignUp.setOpaque(true);
		textFieldNationalitySignUp.setBackground(Color.BLACK);
		textFieldNationalitySignUp.setHorizontalAlignment(SwingConstants.CENTER);
		add(textFieldNationalitySignUp);
		textFieldNationalitySignUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateNationalityField();
			}
		});

		textFieldBirthDateSignUp = new JTextField();
		textFieldBirthDateSignUp.setOpaque(true);
		textFieldBirthDateSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldBirthDateSignUp.setForeground(Color.WHITE);
		textFieldBirthDateSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldBirthDateSignUp.setColumns(10);
		textFieldBirthDateSignUp.setCaretColor(Color.WHITE);
		textFieldBirthDateSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldBirthDateSignUp.setBounds(256, 464, 200, 35);
		textFieldBirthDateSignUp.setBackground(Color.BLACK);
		add(textFieldBirthDateSignUp);
		textFieldBirthDateSignUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateBirthDateField();
			}
		});

		TextPrompt placeholderBirthDateSignUp = new TextPrompt("dd/MM/yyyy", textFieldBirthDateSignUp);
		placeholderBirthDateSignUp.changeAlpha(0.8f);
		placeholderBirthDateSignUp.changeStyle(Font.BOLD + Font.ITALIC);
		placeholderBirthDateSignUp.setHorizontalAlignment(SwingConstants.CENTER);

		textFieldAddressSignUp = new JTextField();
		textFieldAddressSignUp.setOpaque(true);
		textFieldAddressSignUp.setBackground(Color.BLACK);
		textFieldAddressSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAddressSignUp.setForeground(Color.WHITE);
		textFieldAddressSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldAddressSignUp.setColumns(10);
		textFieldAddressSignUp.setCaretColor(Color.WHITE);
		textFieldAddressSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldAddressSignUp.setBounds(256, 504, 200, 35);
		add(textFieldAddressSignUp);
		textFieldAddressSignUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateAddressField();
			}
		});

		textFieldPhoneNumberSignUp = new JTextField();
		textFieldPhoneNumberSignUp.setOpaque(true);
		textFieldPhoneNumberSignUp.setBackground(Color.BLACK);
		textFieldPhoneNumberSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPhoneNumberSignUp.setForeground(Color.WHITE);
		textFieldPhoneNumberSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldPhoneNumberSignUp.setColumns(10);
		textFieldPhoneNumberSignUp.setCaretColor(Color.WHITE);
		textFieldPhoneNumberSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldPhoneNumberSignUp.setBounds(256, 544, 200, 35);
		add(textFieldPhoneNumberSignUp);
		textFieldPhoneNumberSignUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validatePhoneField();
			}
		});

		TextPrompt placeholderPhoneeSignUp = new TextPrompt("+34000000000", textFieldPhoneNumberSignUp);
		placeholderPhoneeSignUp.changeAlpha(0.8f);
		placeholderPhoneeSignUp.changeStyle(Font.BOLD + Font.ITALIC);
		placeholderPhoneeSignUp.setHorizontalAlignment(SwingConstants.CENTER);

		textFieldEmailSignUp = new JTextField();
		textFieldEmailSignUp.setOpaque(true);
		textFieldEmailSignUp.setBackground(Color.BLACK);
		textFieldEmailSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEmailSignUp.setForeground(Color.WHITE);
		textFieldEmailSignUp.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldEmailSignUp.setColumns(10);
		textFieldEmailSignUp.setCaretColor(Color.WHITE);
		textFieldEmailSignUp.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldEmailSignUp.setBounds(256, 584, 200, 35);
		add(textFieldEmailSignUp);
		textFieldEmailSignUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateEmailField();
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
				frame.getContentPane()
						.add(PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null, null, null));
				frame.revalidate();
				frame.repaint();
			}
		});

		JLabel lblHomeIcon = new JLabel("");
		panelHomeIcon.add(lblHomeIcon, BorderLayout.CENTER);

		JLabel lblTiposDeSu = new JLabel("Tipos de suscripción:");
		lblTiposDeSu.setForeground(Color.WHITE);
		lblTiposDeSu.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTiposDeSu.setBounds(550, 120, 178, 23);
		add(lblTiposDeSu);

		JPanel panelSuscriptionP = new JPanel();
		panelSuscriptionP.setOpaque(false);

		panelSuscriptionP.setBounds(550, 169, 150, 150);
		add(panelSuscriptionP);
		panelSuscriptionP.setLayout(new BorderLayout(0, 0));

		JLabel lblSuscription = new JLabel("");
		panelSuscriptionP.add(lblSuscription);

		JPanel panelSuscriptionPP = new JPanel();

		panelSuscriptionPP.setOpaque(false);
		panelSuscriptionPP.setBounds(745, 169, 150, 150);
		add(panelSuscriptionPP);
		panelSuscriptionPP.setLayout(new BorderLayout(0, 0));

		JLabel lblSuscriptionPP = new JLabel("");
		panelSuscriptionPP.add(lblSuscriptionPP, BorderLayout.NORTH);

		JLabel lblPriceSuscriptionP = new JLabel("1.99€/mes");
		lblPriceSuscriptionP.setForeground(Color.WHITE);
		lblPriceSuscriptionP.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPriceSuscriptionP.setBounds(589, 330, 83, 23);
		add(lblPriceSuscriptionP);

		JLabel lblPriceSuscriptionPP = new JLabel("9.99€/mes");
		lblPriceSuscriptionPP.setForeground(Color.WHITE);
		lblPriceSuscriptionPP.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblPriceSuscriptionPP.setBounds(786, 330, 83, 23);
		add(lblPriceSuscriptionPP);

		JPanel panelSuscriptionBasic = new JPanel();

		panelSuscriptionBasic.setOpaque(false);
		panelSuscriptionBasic.setBounds(655, 364, 150, 150);
		add(panelSuscriptionBasic);
		panelSuscriptionBasic.setLayout(new BorderLayout(0, 0));

		JLabel lblSuscriptionBasic = new JLabel("");
		panelSuscriptionBasic.add(lblSuscriptionBasic, BorderLayout.NORTH);

		JLabel lblFree = new JLabel("Gratis");
		lblFree.setForeground(Color.WHITE);
		lblFree.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblFree.setBounds(709, 525, 83, 23);
		add(lblFree);

		JButton btnRegister = new JButton("REGISTRARME");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAllFields(frame);
			}

		});
		btnRegister.setBounds(639, 575, 200, 50);
		add(btnRegister);
		btnRegister.setForeground(Color.black);
		btnRegister.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		btnRegister.setBackground(new Color(244, 135, 244));
		btnRegister.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		btnRegister.setOpaque(true);
		btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		WindowUtils.addImage(panelSuscriptionBasic, lblSuscriptionBasic, "img/icon/sbbasic.png");
		WindowUtils.addImage(panelSuscriptionPP, lblSuscriptionPP, "img/icon/sbpp.png");
		WindowUtils.addImage(panelSuscriptionP, lblSuscription, "img/icon/sbp.png");
		WindowUtils.addImage(panelHomeIcon, lblHomeIcon, "img/icon/arrow.png");
		WindowUtils.addImage(panelProfileIcon, lblProfileIcon, "img/icon/profile.png");

		JRadioButton rdbtnPremium = new JRadioButton("");
		rdbtnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suscription = 1;
			}
		});
		rdbtnPremium.setBounds(560, 332, 20, 23);
		rdbtnPremium.setOpaque(false);
		add(rdbtnPremium);

		JRadioButton rdbtnPremiumPlus = new JRadioButton("");
		rdbtnPremiumPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suscription = 2;
			}
		});
		rdbtnPremiumPlus.setBounds(760, 332, 20, 23);
		rdbtnPremiumPlus.setOpaque(false);
		add(rdbtnPremiumPlus);

		JRadioButton rdbtnBasic = new JRadioButton("");
		rdbtnBasic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suscription = 3;
			}
		});
		rdbtnBasic.setBounds(685, 527, 20, 23);
		rdbtnBasic.setOpaque(false);
		rdbtnBasic.setSelected(true);
		add(rdbtnBasic);

		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnPremium);
		btnGroup.add(rdbtnPremiumPlus);
		btnGroup.add(rdbtnBasic);
	}

	private void registerUser(JFrame frame) {
		Client client = null;
		if (null == controller)
			controller = new Controller();

		if (suscription == 1)
			client = new ClientP();
		else if (suscription == 2)
			client = new ClientPP();
		else

			client = new Client();

		client.setName(textFieldNameSignUp.getText());
		client.setLastName(textFieldLastNameSignUp.getText());
		client.setUsername(textFieldUsernameSignUp.getText());
		client.setPasswd(textFieldPasswdSignUp.getText());
		client.setPersonalId(textFieldPersonalIdSignUp.getText());
		client.setGender(textFieldGenderSignUp.getText());
		client.setNationality(textFieldNationalitySignUp.getText());
		client.setAddress(textFieldAddressSignUp.getText());
		client.setTelephone(textFieldPhoneNumberSignUp.getText());
		client.setEmail(textFieldEmailSignUp.getText());

		try {
			if (!textFieldBirthDateSignUp.getText().isBlank())
				client.setBirthDate(stringToDate(textFieldBirthDateSignUp.getText()));
			controller.insertClient(client);
			if (suscription == 1) {

				bankNumber = WindowUtils.inputPaneWithIcon("Introduzca su número de cuenta:", "Plan de pago",
						"img/icon/money.png");
				registerUserP(frame);
			} else if (suscription == 2) {

				bankNumber = WindowUtils.inputPaneWithIcon("Introduzca su número de cuenta:", "Plan de pago",
						"img/icon/money.png");
				registerUserPP(frame);
			} else if (suscription == 3) {

				WindowUtils.confirmationPane("El registro ha ocurrido de forma exitosa.", "Registrado");
				frame.getContentPane().removeAll();
				frame.getContentPane()
						.add(PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null, null, null));
				frame.revalidate();
				frame.repaint();
			} else {
				WindowUtils.errorPane("Debe seleccionar un método de suscripción.", "Error");
			}
		} catch (ParseException e) {
			WindowUtils.errorPane("Formato de fecha incorrecto.", "Error");

		} catch (SQLException e) {
			WindowUtils.errorPane("Debe rellenar todos los datos correctamente y ser mayor de 18 años.", "Error");

		} catch (Exception e) {
			WindowUtils.errorPane("Error en el registro.", "Error");
		}

	}

	private void registerUserP(JFrame frame) {
		if (null == controller)
			controller = new Controller();
		ClientManager clientManager = new ClientManager();
		try {
			Client client = clientManager.getClientByUsername(textFieldUsernameSignUp.getText());
			ClientP clientp = new ClientP();
			clientp.setId(client.getId());
			clientp.setBankAccount(bankNumber);
			controller.insertClientP(clientp);
			WindowUtils.confirmationPane("El registro ha ocurrido de forma exitosa.", "Registrado");
			frame.getContentPane().removeAll();
			frame.getContentPane()
					.add(PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null, null, null));
			frame.revalidate();
			frame.repaint();
		} catch (SQLException e) {
			WindowUtils.errorPane("Error en el registro.", "Error en la base de datos");
		} catch (Exception e1) {

			WindowUtils.errorPane("Error en el registro.", "Error general");
		}
	}

	private void registerUserPP(JFrame frame) {
		ClientPP clientpp = new ClientPP();
		if (null == controller)
			controller = new Controller();

		try {
			Client client = controller.getClientByUsername(textFieldUsernameSignUp.getText());
			clientpp.setId(client.getId());
			clientpp.setBankAccount(bankNumber);
			controller.insertClientPP(clientpp);
			WindowUtils.confirmationPane("El registro ha ocurrido de forma exitosa.", "Registrado");
			frame.getContentPane().removeAll();
			frame.getContentPane()
					.add(PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null, null, null));
			frame.revalidate();
			frame.repaint();
		} catch (Exception e) {
			System.out.println(e);
			WindowUtils.errorPane("Error en el registro.", "Error");
		}
	}

	private Date stringToDate(String fecha) throws ParseException {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
		return formatter.parse(fecha);
	}

	private boolean isLetterStringCorrect(JTextField textField) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			char[] chars = str.toCharArray();

			for (char c : chars) {
				if (!Character.isLetter(c)) {
					return false;
				}
			}
		}

		return ret;
	}

	private boolean isEmptyText(JTextField textField) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		}

		return ret;
	}

	private boolean isLengthCorrect(JTextField textField, int length) {
		boolean ret = true;
		String str = textField.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if (str.length() < length) {
				ret = false;
			}
		}

		return ret;
	}

	private boolean isPersonalIdCorrect() {
		boolean ret = true;
		String str = textFieldPersonalIdSignUp.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if (str.length() != 9) {
				ret = false;
			} else {
				if (!str.matches("[0-9]{8}[a-zA-Z]"))
					ret = false;
			}
		}

		return ret;
	}

	private boolean isGenderCorrect() {
		boolean ret = true;
		String str = textFieldGenderSignUp.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if (!str.equalsIgnoreCase("Hombre") && !str.equalsIgnoreCase("Mujer") && !str.equalsIgnoreCase("Otro"))
				ret = false;
		}

		return ret;

	}

	private boolean isDateCorrect() {
		boolean ret = true;
		String str = textFieldBirthDateSignUp.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if (!str.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}"))
				ret = false;
		}

		return ret;
	}

	private boolean isPhoneCorrect() {
		boolean ret = true;
		String str = textFieldPhoneNumberSignUp.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if (!str.matches(".[0-9]{11}"))
				ret = false;
		}

		return ret;
	}

	private boolean isEmailCorrect() {
		boolean ret = true;
		String str = textFieldEmailSignUp.getText();

		if (str.isBlank()) {
			ret = false;
		} else {
			if (!str.matches("(.+)@(.+)"))
				ret = false;
		}

		return ret;
	}

	private void validateNameField() {
		if (isLetterStringCorrect(textFieldNameSignUp))
			textFieldNameSignUp.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldNameSignUp.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	private void validateLastNameField() {
		if (isLetterStringCorrect(textFieldLastNameSignUp))
			textFieldLastNameSignUp.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldLastNameSignUp.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	private void validateUserField() {
		if (isLengthCorrect(textFieldUsernameSignUp, 5))
			textFieldUsernameSignUp.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldUsernameSignUp.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	private void validatePasswdField() {
		if (isLengthCorrect(textFieldPasswdSignUp, 10))
			textFieldPasswdSignUp.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldPasswdSignUp.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	private void validatePersonalIdField() {
		if (isPersonalIdCorrect())
			textFieldPersonalIdSignUp.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldPersonalIdSignUp.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	private void validateGenderField() {
		if (isGenderCorrect())
			textFieldGenderSignUp.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldGenderSignUp.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	private void validateNationalityField() {
		if (isLetterStringCorrect(textFieldNationalitySignUp))
			textFieldNationalitySignUp.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldNationalitySignUp.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	private void validateBirthDateField() {
		if (isDateCorrect())
			textFieldBirthDateSignUp.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldBirthDateSignUp.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	private void validateAddressField() {
		if (isEmptyText(textFieldAddressSignUp))
			textFieldAddressSignUp.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldAddressSignUp.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	private void validatePhoneField() {
		if (isPhoneCorrect())
			textFieldPhoneNumberSignUp.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldPhoneNumberSignUp.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	private void validateEmailField() {
		if (isEmailCorrect())
			textFieldEmailSignUp.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldEmailSignUp.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	private void checkAllFields(JFrame frame) {
		if (isLetterStringCorrect(textFieldNameSignUp) && isLetterStringCorrect(textFieldLastNameSignUp)
				&& isLengthCorrect(textFieldUsernameSignUp, 5) && isLengthCorrect(textFieldPasswdSignUp, 10)
				&& isPersonalIdCorrect() && isGenderCorrect() && isLetterStringCorrect(textFieldNationalitySignUp)
				&& isDateCorrect() && isEmptyText(textFieldAddressSignUp) && isPhoneCorrect() && isEmailCorrect()) {
			registerUser(frame);
		} else {
			validateNameField();
			validateLastNameField();
			validateUserField();
			validatePasswdField();
			validatePersonalIdField();
			validateGenderField();
			validateNationalityField();
			validateBirthDateField();
			validateAddressField();
			validatePhoneField();
			validateEmailField();
			
			WindowUtils.errorPane("Revisa los campos incorrectos marcados de color rojo.", "Error");
		}
	}
}
