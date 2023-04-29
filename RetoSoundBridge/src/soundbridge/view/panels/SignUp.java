package soundbridge.view.panels;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import soundbridge.database.managers.ClientManager;
import soundbridge.database.managers.ClientPManager;
import soundbridge.database.managers.ClientPPManager;
import soundbridge.database.pojos.Client;
import soundbridge.database.pojos.ClientP;
import soundbridge.database.pojos.ClientPP;
import soundbridge.utils.WindowUtils;
import soundbridge.view.components.TextPrompt;
import soundbridge.view.factory.PanelFactory;

import java.awt.BorderLayout;

public class SignUp extends JPanel {
	JTextField textFildNameSignUp = null;
	JTextField textFildLastNameSignUp = null;
	JTextField textFildUsernameSignUp = null;
	JTextField textFieldPasswdSignUp = null;
	JTextField textFieldPersonalIdSignUp = null;
	JTextField textFildGenderSignUp = null;
	JTextField textFieldNationalitySignUp = null;
	JTextField textFieldBirthDateSignUp = null;
	JTextField textFieldAdressSignUp = null;
	JTextField textFieldPhoneNumberSignUp = null;
	JTextField textFieldEmailSignUp = null;
	JTextField textFildBankAccount = null;
	int suscription = 0;
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

		textFildNameSignUp = new JTextField();
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

		textFildLastNameSignUp = new JTextField();
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

		textFildUsernameSignUp = new JTextField();
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

		textFieldPasswdSignUp = new JTextField();
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

		textFieldPersonalIdSignUp = new JTextField();
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

		textFildGenderSignUp = new JTextField();
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

		textFieldNationalitySignUp = new JTextField();
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
		textFieldBirthDateSignUp = new JTextField();
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
		textFieldAdressSignUp = new JTextField();
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
		textFieldPhoneNumberSignUp = new JTextField();
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
		textFieldEmailSignUp = new JTextField();
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

		JLabel lblTiposDeSu = new JLabel("Tipos de suscripción:");
		lblTiposDeSu.setForeground(Color.WHITE);
		lblTiposDeSu.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTiposDeSu.setBounds(550, 120, 178, 23);
		add(lblTiposDeSu);

		JPanel panelSuscriptionP = new JPanel();
		panelSuscriptionP.setOpaque(false);
		panelSuscriptionP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				suscription = 1;
			}
		});
		panelSuscriptionP.setBounds(550, 169, 150, 150);
		add(panelSuscriptionP);
		panelSuscriptionP.setLayout(new BorderLayout(0, 0));

		JLabel lblSuscription = new JLabel("");
		panelSuscriptionP.add(lblSuscription);

		JPanel panelSuscriptionPP = new JPanel();
		panelSuscriptionPP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				suscription = 2;
			}
		});
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
		panelSuscriptionBasic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				suscription = 3;
			}
		});
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
				registerUser();
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

	}

	private void registerUser() {
		Client client = new Client();
		ClientManager clientManager = new ClientManager();

		client.setName(textFildNameSignUp.getText());
		client.setLastName(textFildLastNameSignUp.getText());
		client.setUsername(textFildUsernameSignUp.getText());
		client.setPasswd(textFieldPasswdSignUp.getText());
		client.setPersonalId(textFieldPersonalIdSignUp.getText());
		client.setGender(textFildGenderSignUp.getText());
		client.setNationality(textFieldNationalitySignUp.getText());
		String birthDate = textFieldBirthDateSignUp.getText();
		client.setBirthDate(stringToDate(birthDate));
		client.setAddress(textFieldAdressSignUp.getText());
		client.setTelephone(textFieldPhoneNumberSignUp.getText());
		client.setEmail(textFieldEmailSignUp.getText());

		try {
			
			if (suscription == 1) {
				textFildBankAccount = new JTextField();
				clientManager.insert(client);
				Object[] message = { "Cuenta bancaria: ", textFildBankAccount };
				JOptionPane.showMessageDialog(null, message, "Has elegido un plan de pago", JOptionPane.PLAIN_MESSAGE);
				registerUserP();
			} else if (suscription == 2) {
				clientManager.insert(client);
				JTextField textFildBankAccount = new JTextField();

				Object[] message = { "Cuenta bancaria: ", textFildBankAccount };
				JOptionPane.showMessageDialog(null, message, "Has elegido un plan de pago", JOptionPane.PLAIN_MESSAGE);
				registerUserPP();
			} else if (suscription == 3) {
				clientManager.insert(client);
				JFrame jFrame = new JFrame();
				JOptionPane.showMessageDialog(jFrame, "El registro ha ocurrido de forma exitosa");
			}
		} catch (Exception e) {
			JFrame jFrame = new JFrame();
			JOptionPane.showMessageDialog(jFrame, "ERROR EN EL REGISTRO");
			e.printStackTrace();
		}

	}

	private void registerUserP() {
		
		ClientPManager clientpmanager = new ClientPManager();
		ClientManager clientManager = new ClientManager();
		try {
			Client client = clientManager.getClientByUsername(textFildUsernameSignUp.getText());
			System.out.println(client.toString());
			ClientP clientp = new ClientP();
			clientp.setId(client.getId());
			clientp.setBankAccount(textFildBankAccount.getText());
			clientp.setAddress(client.getAddress());
			clientp.setBirthDate(client.getBirthDate());
			clientp.setEmail(client.getEmail());
			clientp.setGender(client.getEmail());
			clientp.setLastName(client.getLastName());
			clientp.setName(client.getName());
			clientp.setNationality(client.getNationality());
			clientp.setPasswd(client.getPasswd());
			clientp.setPersonalId(client.getPersonalId());
			clientp.setUsername(client.getUsername());
			clientp.setTelephone(client.getTelephone());
			System.out.println(clientp.toString());
			clientpmanager.insert(clientp);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
		

	private void registerUserPP() {
		ClientPP clientpp = new ClientPP();
		ClientPPManager clientppmanager = new ClientPPManager();
		clientpp.setBankAccount(textFildBankAccount.getText());
		clientpp.setSuscriptionDate(new Date());
		try {
			clientppmanager.insert(clientpp);
		} catch (Exception e) {
			JFrame jFrame = new JFrame();
			JOptionPane.showMessageDialog(jFrame, "ERROR EN EL REGISTRO");
			e.printStackTrace();
		}
	}

	private Date stringToDate(String fecha) {
		Date ret = null;
		try {
			String pattern = "yyyy/MM/dd";
			SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
			ret = formatter.parse(fecha);
		} catch (Exception e) {
			// Algo ha ido mal, devolvemos la fecha del sistema
			ret = new Date();
		}
		return ret;
	}
}
