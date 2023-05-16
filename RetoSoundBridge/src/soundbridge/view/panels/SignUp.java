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

/**
 * This is a panel that allows the client to sign up in the application. It
 * validates all the fields and there are different types of subscriptions.
 */
public class SignUp extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldLastName;
	private JTextField textFieldUsername;
	private JTextField textFieldPasswd;
	private JTextField textFieldPersonalId;
	private JTextField textFieldGender;
	private JTextField textFieldNationality;
	private JTextField textFieldBirthDate;
	private JTextField textFieldAddress;
	private JTextField textFieldPhoneNumber;
	private JTextField textFieldEmail;
	private Controller controller = null;
	private String bankNumber = null;
	private int suscription = 0;
	private static final long serialVersionUID = -2586474039198890631L;

	/**
	 * Initializes the panel.
	 * 
	 * @param frame frame where the panel is added
	 */
	public SignUp(JFrame frame) {

		initialize(frame);

	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame frame where the panel is added
	 */

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

		textFieldName = new JTextField();
		textFieldName.setBounds(190, 49, 200, 40);
		add(textFieldName);
		textFieldName.setColumns(10);
		textFieldName.setForeground(Color.white);
		textFieldName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textFieldName.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldName.setCaretColor(Color.WHITE);
		textFieldName.setOpaque(true);
		textFieldName.setBackground(Color.BLACK);
		textFieldName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateNameField();
			}
		});

		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(190, 112, 200, 40);
		add(textFieldLastName);
		textFieldLastName.setColumns(10);
		textFieldLastName.setForeground(Color.white);
		textFieldLastName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textFieldLastName.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldLastName.setCaretColor(Color.WHITE);
		textFieldLastName.setOpaque(true);
		textFieldLastName.setBackground(Color.BLACK);
		textFieldLastName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldLastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateLastNameField();
			}
		});

		TextPrompt placeholderName = new TextPrompt("Nombre", textFieldName);
		placeholderName.changeAlpha(0.8f);
		placeholderName.changeStyle(Font.BOLD + Font.ITALIC);
		placeholderName.setHorizontalAlignment(SwingConstants.CENTER);

		TextPrompt placeholderLastName = new TextPrompt("Apellido", textFieldLastName);
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

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(256, 264, 200, 35);
		add(textFieldUsername);
		textFieldUsername.setColumns(10);
		textFieldUsername.setForeground(Color.white);
		textFieldUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		textFieldUsername.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldUsername.setCaretColor(Color.WHITE);
		textFieldUsername.setOpaque(true);
		textFieldUsername.setBackground(Color.BLACK);
		textFieldUsername.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateUserField();
			}
		});

		TextPrompt placeholderUsername = new TextPrompt("Min 5 caracteres", textFieldUsername);
		placeholderUsername.changeAlpha(0.8f);
		placeholderUsername.changeStyle(Font.BOLD + Font.ITALIC);
		placeholderUsername.setHorizontalAlignment(SwingConstants.CENTER);

		textFieldPasswd = new JTextField();
		textFieldPasswd.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPasswd.setForeground(Color.WHITE);
		textFieldPasswd.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldPasswd.setColumns(10);
		textFieldPasswd.setCaretColor(Color.WHITE);
		textFieldPasswd.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldPasswd.setBounds(256, 304, 200, 35);
		textFieldPasswd.setOpaque(true);
		textFieldPasswd.setBackground(Color.BLACK);
		add(textFieldPasswd);
		textFieldPasswd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validatePasswdField();
			}
		});

		TextPrompt placeholderPasswdSignUp = new TextPrompt("Min 10 caracteres", textFieldPasswd);
		placeholderPasswdSignUp.changeAlpha(0.8f);
		placeholderPasswdSignUp.changeStyle(Font.BOLD + Font.ITALIC);
		placeholderPasswdSignUp.setHorizontalAlignment(SwingConstants.CENTER);

		textFieldPersonalId = new JTextField();
		textFieldPersonalId.setForeground(Color.WHITE);
		textFieldPersonalId.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldPersonalId.setColumns(10);
		textFieldPersonalId.setCaretColor(Color.WHITE);
		textFieldPersonalId.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldPersonalId.setBounds(256, 344, 200, 35);
		textFieldPersonalId.setOpaque(true);
		textFieldPersonalId.setBackground(Color.BLACK);
		textFieldPersonalId.setHorizontalAlignment(SwingConstants.CENTER);
		add(textFieldPersonalId);
		textFieldPersonalId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validatePersonalIdField();
			}
		});

		textFieldGender = new JTextField();
		textFieldGender.setForeground(Color.WHITE);
		textFieldGender.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldGender.setColumns(10);
		textFieldGender.setCaretColor(Color.WHITE);
		textFieldGender.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldGender.setBounds(256, 384, 200, 35);
		textFieldGender.setOpaque(true);
		textFieldGender.setBackground(Color.BLACK);
		textFieldGender.setHorizontalAlignment(SwingConstants.CENTER);
		add(textFieldGender);
		textFieldGender.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateGenderField();
			}
		});

		TextPrompt placeholderGenderSignUp = new TextPrompt("Hombre   Mujer   Otro", textFieldGender);
		placeholderGenderSignUp.changeAlpha(0.8f);
		placeholderGenderSignUp.changeStyle(Font.BOLD + Font.ITALIC);
		placeholderGenderSignUp.setHorizontalAlignment(SwingConstants.CENTER);

		textFieldNationality = new JTextField();
		textFieldNationality.setForeground(Color.WHITE);
		textFieldNationality.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldNationality.setColumns(10);
		textFieldNationality.setCaretColor(Color.WHITE);
		textFieldNationality.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldNationality.setBounds(256, 424, 200, 35);
		textFieldNationality.setOpaque(true);
		textFieldNationality.setBackground(Color.BLACK);
		textFieldNationality.setHorizontalAlignment(SwingConstants.CENTER);
		add(textFieldNationality);
		textFieldNationality.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateNationalityField();
			}
		});

		textFieldBirthDate = new JTextField();
		textFieldBirthDate.setOpaque(true);
		textFieldBirthDate.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldBirthDate.setForeground(Color.WHITE);
		textFieldBirthDate.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldBirthDate.setColumns(10);
		textFieldBirthDate.setCaretColor(Color.WHITE);
		textFieldBirthDate.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldBirthDate.setBounds(256, 464, 200, 35);
		textFieldBirthDate.setBackground(Color.BLACK);
		add(textFieldBirthDate);
		textFieldBirthDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateBirthDateField();
			}
		});

		TextPrompt placeholderBirthDateSignUp = new TextPrompt("dd/MM/yyyy", textFieldBirthDate);
		placeholderBirthDateSignUp.changeAlpha(0.8f);
		placeholderBirthDateSignUp.changeStyle(Font.BOLD + Font.ITALIC);
		placeholderBirthDateSignUp.setHorizontalAlignment(SwingConstants.CENTER);

		textFieldAddress = new JTextField();
		textFieldAddress.setOpaque(true);
		textFieldAddress.setBackground(Color.BLACK);
		textFieldAddress.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAddress.setForeground(Color.WHITE);
		textFieldAddress.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldAddress.setColumns(10);
		textFieldAddress.setCaretColor(Color.WHITE);
		textFieldAddress.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldAddress.setBounds(256, 504, 200, 35);
		add(textFieldAddress);
		textFieldAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateAddressField();
			}
		});

		textFieldPhoneNumber = new JTextField();
		textFieldPhoneNumber.setOpaque(true);
		textFieldPhoneNumber.setBackground(Color.BLACK);
		textFieldPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPhoneNumber.setForeground(Color.WHITE);
		textFieldPhoneNumber.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldPhoneNumber.setColumns(10);
		textFieldPhoneNumber.setCaretColor(Color.WHITE);
		textFieldPhoneNumber.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldPhoneNumber.setBounds(256, 544, 200, 35);
		add(textFieldPhoneNumber);
		textFieldPhoneNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validatePhoneField();
			}
		});

		TextPrompt placeholderPhoneeSignUp = new TextPrompt("+34000000000", textFieldPhoneNumber);
		placeholderPhoneeSignUp.changeAlpha(0.8f);
		placeholderPhoneeSignUp.changeStyle(Font.BOLD + Font.ITALIC);
		placeholderPhoneeSignUp.setHorizontalAlignment(SwingConstants.CENTER);

		textFieldEmail = new JTextField();
		textFieldEmail.setOpaque(true);
		textFieldEmail.setBackground(Color.BLACK);
		textFieldEmail.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEmail.setForeground(Color.WHITE);
		textFieldEmail.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldEmail.setColumns(10);
		textFieldEmail.setCaretColor(Color.WHITE);
		textFieldEmail.setBorder(new LineBorder(Color.WHITE, 2));
		textFieldEmail.setBounds(256, 584, 200, 35);
		add(textFieldEmail);
		textFieldEmail.addKeyListener(new KeyAdapter() {
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
				frame.getContentPane().add(
						PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null, null, null, null));
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

	/**
	 * Sign up the client asking him for the bank account if it is necessary.
	 * 
	 * @param frame frame where the panel is added
	 */
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

		client.setName(textFieldName.getText());
		client.setLastName(textFieldLastName.getText());
		client.setUsername(textFieldUsername.getText());
		client.setPasswd(textFieldPasswd.getText());
		client.setPersonalId(textFieldPersonalId.getText());
		client.setGender(textFieldGender.getText());
		client.setNationality(textFieldNationality.getText());
		client.setAddress(textFieldAddress.getText());
		client.setTelephone(textFieldPhoneNumber.getText());
		client.setEmail(textFieldEmail.getText());

		try {
			if (!textFieldBirthDate.getText().isBlank())
				client.setBirthDate(stringToDate(textFieldBirthDate.getText()));
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
				frame.getContentPane().add(
						PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null, null, null, null));
				frame.revalidate();
				frame.repaint();
			} else {
				WindowUtils.errorPane("Debe seleccionar un método de suscripción.", "Error");
			}
		} catch (ParseException e) {
			WindowUtils.errorPane("Formato de fecha incorrecto.", "Error");

		} catch (SQLException e) {
			WindowUtils.errorPane("Debe rellenar todos los datos correctamente.", "Error");

		} catch (Exception e) {
			WindowUtils.errorPane("Error en el registro.", "Error");
		}

	}

	/**
	 * Signs up the premium clients.
	 * 
	 * @param frame frame where the panel is added
	 */
	private void registerUserP(JFrame frame) {
		if (null == controller)
			controller = new Controller();
		ClientManager clientManager = new ClientManager();
		try {
			Client client = clientManager.getClientByUsername(textFieldUsername.getText());
			ClientP clientp = new ClientP();
			clientp.setId(client.getId());
			clientp.setBankAccount(bankNumber);
			controller.insertClientP(clientp);
			WindowUtils.confirmationPane("El registro ha ocurrido de forma exitosa.", "Registrado");
			frame.getContentPane().removeAll();
			frame.getContentPane()
					.add(PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null, null, null, null));
			frame.revalidate();
			frame.repaint();
		} catch (SQLException e) {
			WindowUtils.errorPane("Error en el registro.", "Error en la base de datos");
		} catch (Exception e1) {

			WindowUtils.errorPane("Error en el registro.", "Error general");
		}
	}

	/**
	 * Signs up the premium plus clients.
	 * 
	 * @param frame frame where the panel is added
	 */
	private void registerUserPP(JFrame frame) {
		ClientPP clientpp = new ClientPP();
		if (null == controller)
			controller = new Controller();

		try {
			Client client = controller.getClientByUsername(textFieldUsername.getText());
			clientpp.setId(client.getId());
			clientpp.setBankAccount(bankNumber);
			controller.insertClientPP(clientpp);
			WindowUtils.confirmationPane("El registro ha ocurrido de forma exitosa.", "Registrado");
			frame.getContentPane().removeAll();
			frame.getContentPane()
					.add(PanelFactory.getJPanel(PanelFactory.LOGIN, frame, null, null, null, null, null, null, null));
			frame.revalidate();
			frame.repaint();
		} catch (Exception e) {
			System.out.println(e);
			WindowUtils.errorPane("Error en el registro.", "Error");
		}
	}

	/**
	 * Changes a String into Date type.
	 * 
	 * @param date date that you want to retype.
	 * @return the date in Date type.
	 * @throws ParseException if unexpected error while parsing
	 */
	private Date stringToDate(String date) throws ParseException {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
		return formatter.parse(date);
	}

	/**
	 * Validates the name field setting green if it is correct and red if it is not.
	 */
	private void validateNameField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isLetterStringCorrect(textFieldName))
			textFieldName.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldName.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the last name field setting green if it is correct and red if it is
	 * not.
	 */
	private void validateLastNameField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isLetterStringCorrect(textFieldLastName))
			textFieldLastName.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldLastName.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the username field setting green if it is correct and red if it is
	 * not.
	 */
	private void validateUserField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isLengthCorrect(textFieldUsername, 5))
			textFieldUsername.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldUsername.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the password field setting green if it is correct and red if it is
	 * not.
	 */
	private void validatePasswdField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isLengthCorrect(textFieldPasswd, 10))
			textFieldPasswd.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldPasswd.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the personalId field setting green if it is correct and red if it
	 * is not.
	 */
	private void validatePersonalIdField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isPersonalIdCorrect(textFieldPersonalId))
			textFieldPersonalId.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldPersonalId.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the gender field setting green if it is correct and red if it is
	 * not.
	 */
	private void validateGenderField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isGenderCorrect(textFieldGender))
			textFieldGender.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldGender.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the nationality field setting green if it is correct and red if it
	 * is not.
	 */
	private void validateNationalityField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isLetterStringCorrect(textFieldNationality))
			textFieldNationality.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldNationality.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the birth date field setting green if it is correct and red if it
	 * is not.
	 */
	private void validateBirthDateField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isDateCorrect(textFieldBirthDate))
			textFieldBirthDate.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldBirthDate.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the address field setting green if it is correct and red if it is
	 * not.
	 */
	private void validateAddressField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isNotEmptyText(textFieldAddress))
			textFieldAddress.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldAddress.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the telephone field setting green if it is correct and red if it is
	 * not.
	 */
	private void validatePhoneField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isPhoneCorrect(textFieldPhoneNumber))
			textFieldPhoneNumber.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldPhoneNumber.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the email field setting green if it is correct and red if it is
	 * not.
	 */
	private void validateEmailField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isEmailCorrect(textFieldEmail))
			textFieldEmail.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFieldEmail.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates all fields setting green if it is correct and red if it is not.
	 */
	private void validateAllFields() {
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
	}

	/**
	 * Validates all the fields.
	 * 
	 * @return true if all are correct and false if they are not.
	 */
	private boolean areAllFieldsCorrect() {
		boolean ret = false;
		if (controller == null)
			controller = new Controller();

		if (controller.isLetterStringCorrect(textFieldName) && controller.isLetterStringCorrect(textFieldLastName)
				&& controller.isLengthCorrect(textFieldUsername, 5) && controller.isLengthCorrect(textFieldPasswd, 10)
				&& controller.isPersonalIdCorrect(textFieldPersonalId) && controller.isGenderCorrect(textFieldGender)
				&& controller.isLetterStringCorrect(textFieldNationality)
				&& controller.isDateCorrect(textFieldBirthDate) && controller.isNotEmptyText(textFieldAddress)
				&& controller.isPhoneCorrect(textFieldPhoneNumber) && controller.isEmailCorrect(textFieldEmail)) {
			ret = true;
		}

		return ret;
	}

	/**
	 * Takes the information of "areAllFieldsCorrect" and if it is true sign up the
	 * user, if it is not, show an error message.
	 * 
	 * @param frame the frame where the panel is added
	 */
	private void checkAllFields(JFrame frame) {
		if (areAllFieldsCorrect()) {
			registerUser(frame);
		} else {
			validateAllFields();
			WindowUtils.errorPane("Revisa los campos incorrectos marcados de color rojo.", "Error");
		}
	}
}
