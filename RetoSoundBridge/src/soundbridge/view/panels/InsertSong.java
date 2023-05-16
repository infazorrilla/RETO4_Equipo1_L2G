package soundbridge.view.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import soundbridge.controller.Controller;
import soundbridge.database.pojos.Album;
import soundbridge.database.pojos.ArtGroup;
import soundbridge.database.pojos.Artist;
import soundbridge.database.pojos.Employee;
import soundbridge.database.pojos.Song;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JButton;

/**
 * Panel that enables an employee to add new songs belonging to the already
 * existing artists and albums.
 */
public class InsertSong extends JPanel {

	private static final long serialVersionUID = -7791300318343389694L;
	private ArrayList<Object> allArtists = null;
	private Controller controller = null;
	private Object selectedArt = null;
	private ArrayList<Album> allAlbums = null;
	private Album selectedAlbum = null;
	private JTextField textTitle;
	private JTextField textDuration;
	private JTextField textFile;
	private JTextField textLanguage;
	private JTextField textGenre;
	private JTextField textYear;
	private JComboBox<String> comboAlbum;
	private JComboBox<String> comboArtist;

	/**
	 * Initializes the panel.
	 * 
	 * @param frame    frame where the panel is added
	 * @param employee logged employee
	 */
	public InsertSong(JFrame frame, Employee employee) {
		setBounds(0, 0, 1000, 672);
		setBackground(Color.black);
		setLayout(null);

		initialize(frame, employee);
	}

	/**
	 * Initializes the components of the panel.
	 * 
	 * @param frame    frame where the panel is added
	 * @param employee logged employee
	 */
	private void initialize(JFrame frame, Employee employee) {
		JLabel lblTitle = new JLabel("Nueva canción");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblTitle.setBounds(380, 44, 250, 36);
		add(lblTitle);

		JPanel panelBackIcon = new JPanel();
		panelBackIcon.setBounds(900, 45, 50, 50);
		add(panelBackIcon);
		panelBackIcon.setLayout(new BorderLayout(0, 0));
		panelBackIcon.setOpaque(false);
		panelBackIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goBack(frame, employee);
			}
		});
		panelBackIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBackIcon.setToolTipText("Volver");

		JLabel lblBackIcon = new JLabel("");
		panelBackIcon.add(lblBackIcon, BorderLayout.CENTER);

		JPanel panelArtistImage = new JPanel();
		panelArtistImage.setOpaque(false);
		panelArtistImage.setBounds(638, 117, 230, 230);
		add(panelArtistImage);
		panelArtistImage.setLayout(new BorderLayout(0, 0));

		JLabel lblArtistImage = new JLabel("");
		panelArtistImage.add(lblArtistImage, BorderLayout.CENTER);

		JPanel panelAlbumImage = new JPanel();
		panelAlbumImage.setOpaque(false);
		panelAlbumImage.setBounds(638, 400, 230, 230);
		add(panelAlbumImage);
		panelAlbumImage.setLayout(new BorderLayout(0, 0));

		JLabel lblAlbumImage = new JLabel("");
		panelAlbumImage.add(lblAlbumImage, BorderLayout.CENTER);

		comboAlbum = new JComboBox<String>();
		comboAlbum.setBounds(234, 179, 200, 35);
		comboAlbum.setForeground(Color.WHITE);
		comboAlbum.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboAlbum.setBorder(new LineBorder(Color.WHITE, 2));
		comboAlbum.setBackground(Color.black);
		comboAlbum.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (null != comboAlbum.getSelectedItem()) {
					selectedAlbum();
					WindowUtils.addImage(panelAlbumImage, lblAlbumImage, selectedAlbum.getCover());
				}
			}
		});
		add(comboAlbum);

		comboArtist = new JComboBox<String>();
		comboArtist.setBounds(234, 117, 200, 35);
		comboArtist.setForeground(Color.WHITE);
		comboArtist.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboArtist.setBorder(new LineBorder(Color.WHITE, 2));
		comboArtist.setBackground(Color.black);
		comboArtist.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (null != comboArtist.getSelectedItem()) {
					selectedArtist();
					addArtistImageAndAlbums(panelArtistImage, lblArtistImage);
				}
			}
		});
		add(comboArtist);

		JLabel lblArtName = new JLabel("Artista:");
		lblArtName.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblArtName.setForeground(Color.WHITE);
		lblArtName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArtName.setBounds(61, 117, 161, 35);
		add(lblArtName);

		JLabel lblAlbum = new JLabel("Álbum:");
		lblAlbum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAlbum.setForeground(Color.WHITE);
		lblAlbum.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblAlbum.setBounds(61, 179, 161, 35);
		add(lblAlbum);

		textTitle = new JTextField();
		textTitle.setOpaque(false);
		textTitle.setHorizontalAlignment(SwingConstants.CENTER);
		textTitle.setForeground(Color.WHITE);
		textTitle.setFont(new Font("Dialog", Font.PLAIN, 15));
		textTitle.setColumns(10);
		textTitle.setCaretColor(Color.WHITE);
		textTitle.setBorder(new LineBorder(Color.WHITE, 2));
		textTitle.setBounds(234, 240, 200, 35);
		add(textTitle);
		textTitle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateTitleField();
			}
		});

		JLabel lblName = new JLabel("Título");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblName.setBounds(61, 240, 161, 35);
		add(lblName);

		textDuration = new JTextField();
		textDuration.setOpaque(false);
		textDuration.setHorizontalAlignment(SwingConstants.CENTER);
		textDuration.setForeground(Color.WHITE);
		textDuration.setFont(new Font("Dialog", Font.PLAIN, 15));
		textDuration.setColumns(10);
		textDuration.setCaretColor(Color.WHITE);
		textDuration.setBorder(new LineBorder(Color.WHITE, 2));
		textDuration.setBounds(234, 304, 200, 35);
		add(textDuration);
		textDuration.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateDurationField();
			}
		});

		JLabel lblDuration = new JLabel("Duración (s):");
		lblDuration.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuration.setForeground(Color.WHITE);
		lblDuration.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblDuration.setBounds(61, 304, 161, 35);
		add(lblDuration);

		textFile = new JTextField();
		textFile.setOpaque(false);
		textFile.setHorizontalAlignment(SwingConstants.CENTER);
		textFile.setForeground(Color.WHITE);
		textFile.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFile.setColumns(10);
		textFile.setCaretColor(Color.WHITE);
		textFile.setBorder(new LineBorder(Color.WHITE, 2));
		textFile.setBounds(234, 367, 200, 35);
		add(textFile);
		textFile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateFileField();
			}
		});

		JLabel lblFile = new JLabel("Ruta del archivo:");
		lblFile.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFile.setForeground(Color.WHITE);
		lblFile.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblFile.setBounds(61, 367, 161, 35);
		add(lblFile);

		textLanguage = new JTextField();
		textLanguage.setOpaque(false);
		textLanguage.setHorizontalAlignment(SwingConstants.CENTER);
		textLanguage.setForeground(Color.WHITE);
		textLanguage.setFont(new Font("Dialog", Font.PLAIN, 15));
		textLanguage.setColumns(10);
		textLanguage.setCaretColor(Color.WHITE);
		textLanguage.setBorder(new LineBorder(Color.WHITE, 2));
		textLanguage.setBounds(234, 429, 200, 35);
		add(textLanguage);
		textLanguage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateLanguageField();
			}
		});

		JLabel lblLanguage = new JLabel("Idioma:");
		lblLanguage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLanguage.setForeground(Color.WHITE);
		lblLanguage.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblLanguage.setBounds(61, 429, 161, 35);
		add(lblLanguage);

		textGenre = new JTextField();
		textGenre.setOpaque(false);
		textGenre.setHorizontalAlignment(SwingConstants.CENTER);
		textGenre.setForeground(Color.WHITE);
		textGenre.setFont(new Font("Dialog", Font.PLAIN, 15));
		textGenre.setColumns(10);
		textGenre.setCaretColor(Color.WHITE);
		textGenre.setBorder(new LineBorder(Color.WHITE, 2));
		textGenre.setBounds(234, 487, 200, 35);
		add(textGenre);
		textGenre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateGenreField();
			}
		});

		JLabel lblGender = new JLabel("Género:");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setForeground(Color.WHITE);
		lblGender.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblGender.setBounds(61, 487, 161, 35);
		add(lblGender);

		JLabel lblYear = new JLabel("Año:");
		lblYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYear.setForeground(Color.WHITE);
		lblYear.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblYear.setBounds(61, 545, 161, 35);
		add(lblYear);

		textYear = new JTextField();
		textYear.setOpaque(false);
		textYear.setHorizontalAlignment(SwingConstants.CENTER);
		textYear.setForeground(Color.WHITE);
		textYear.setFont(new Font("Dialog", Font.PLAIN, 15));
		textYear.setColumns(10);
		textYear.setCaretColor(Color.WHITE);
		textYear.setBorder(new LineBorder(Color.WHITE, 2));
		textYear.setBounds(234, 545, 200, 35);
		add(textYear);
		textYear.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validateYearField();
			}
		});

		JButton btnInsert = new JButton("Añadir");
		btnInsert.setOpaque(true);
		btnInsert.setForeground(Color.BLACK);
		btnInsert.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		btnInsert.setBorder(new LineBorder(new Color(244, 135, 244), 2));
		btnInsert.setBackground(new Color(244, 135, 244));
		btnInsert.setBounds(234, 607, 200, 40);
		add(btnInsert);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doInsertNewSong();
			}
		});

		addArtistsNamesToCombo();

		selectedArtist();

		WindowUtils.addImage(panelBackIcon, lblBackIcon, "img/icon/arrow.png");
	}

	/**
	 * Gets all artists and groups from database.
	 * 
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	private void getAllArtists() throws SQLException, Exception {
		if (null == controller)
			controller = new Controller();

		allArtists = controller.artistsAndGroups();
	}

	/**
	 * Gets all albums of the selected artist.
	 * 
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	private void getAlbumsByArtist() throws SQLException, Exception {
		if (null == controller)
			controller = new Controller();

		allAlbums = controller.getAlbumsByArtist((Artist) selectedArt);
	}

	/**
	 * Gets all albums of the selected art group.
	 * 
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	private void getAlbumsByGroup() throws SQLException, Exception {
		if (null == controller)
			controller = new Controller();

		allAlbums = controller.getAlbumsByGroup((ArtGroup) selectedArt);
	}

	/**
	 * Adds artist's albums to a ComboBox.
	 */
	private void addArtistAlbumsToCombo() {
		try {
			getAlbumsByArtist();
		} catch (SQLException e) {
			WindowUtils.errorPane("No se han podido cargar los álbumes.", "Error en la base de datos");
		} catch (Exception e) {
			WindowUtils.errorPane("No se han podido cargar los álbumes.", "Error general");
		}

		if (allAlbums != null) {
			for (Album album : allAlbums) {
				comboAlbum.addItem(album.getName());
			}
		}
	}

	/**
	 * Adds group's albums to a ComboBox.
	 */
	private void addGroupAlbumsToCombo() {
		try {
			getAlbumsByGroup();
		} catch (SQLException e) {
			WindowUtils.errorPane("No se han podido cargar los álbumes.", "Error en la base de datos");
		} catch (Exception e) {
			WindowUtils.errorPane("No se han podido cargar los álbumes.", "Error general");
		}

		if (allAlbums != null) {
			for (Album album : allAlbums) {
				comboAlbum.addItem(album.getName());
			}
		}
	}

	/**
	 * Adds artists' and groups' names to a ComboBox.
	 */
	private void addArtistsNamesToCombo() {
		try {
			getAllArtists();
		} catch (SQLException e) {
			WindowUtils.errorPane("No se han podido cargar los artistas y grupos.", "Error en la base de datos");
		} catch (Exception e) {
			WindowUtils.errorPane("No se han podido cargar los artistas y grupos.", "Error general");
		}

		if (allArtists != null) {
			for (Object object : allArtists) {
				if (object instanceof Artist)
					comboArtist.addItem(((Artist) object).getName());
				else if (object instanceof ArtGroup)
					comboArtist.addItem(((ArtGroup) object).getName());
			}
		}
	}

	/**
	 * Stores the selected artist or group.
	 */
	private void selectedArtist() {
		int index = comboArtist.getSelectedIndex();
		selectedArt = allArtists.get(index);
	}

	/**
	 * Stores the selected album.
	 */
	private void selectedAlbum() {
		int index = comboAlbum.getSelectedIndex();
		selectedAlbum = allAlbums.get(index);
	}

	/**
	 * Prints artists' and groups' images.
	 * 
	 * @param panel panel containing a label
	 * @param lbl   label where the image is printed
	 */
	private void addArtistImageAndAlbums(JPanel panel, JLabel lbl) {
		comboAlbum.removeAllItems();
		if (selectedArt instanceof Artist) {
			WindowUtils.addImage(panel, lbl, ((Artist) selectedArt).getImage());
			addArtistAlbumsToCombo();
		} else if (selectedArt instanceof ArtGroup) {
			WindowUtils.addImage(panel, lbl, ((ArtGroup) selectedArt).getImage());
			addGroupAlbumsToCombo();
		}
	}

	/**
	 * Validates the entered text for the song's title, which must not be empty.
	 */
	private void validateTitleField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isNotEmptyText(textTitle))
			textTitle.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textTitle.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the entered text for the song's duration in seconds.
	 */
	private void validateDurationField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isLengthCorrectNumericSeconds(textDuration))
			textDuration.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textDuration.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the entered text for the song's file path.
	 */
	private void validateFileField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isNotEmptyText(textFile))
			textFile.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textFile.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the entered text for the song's language.
	 */
	private void validateLanguageField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isLetterStringCorrect(textLanguage))
			textLanguage.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textLanguage.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the entered text for the song's genre.
	 */
	private void validateGenreField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isLetterStringCorrect(textGenre))
			textGenre.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textGenre.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Validates the entered text for the song's release year.
	 */
	private void validateYearField() {
		if (controller == null)
			controller = new Controller();

		if (controller.isLengthCorrectNumeric(textYear, 4))
			textYear.setBorder(new LineBorder(new Color(0, 205, 20), 2));
		else
			textYear.setBorder(new LineBorder(new Color(255, 40, 40), 2));
	}

	/**
	 * Inserts a new song into the database.
	 * 
	 * @throws SQLException if there is an error on database
	 * @throws Exception    if there is a generic error
	 */
	private void insertNewSong() throws SQLException, Exception {
		Song song = new Song();
		song.setAlbum(selectedAlbum);

		if (selectedArt instanceof Artist) {
			song.setArtist((Artist) selectedArt);
		} else if (selectedArt instanceof ArtGroup) {
			song.setArtGroup((ArtGroup) selectedArt);
		}

		song.setDuration(Integer.parseInt(textDuration.getText()));
		song.setGenre(textGenre.getText());
		song.setLang(textLanguage.getText());
		song.setName(textTitle.getText());
		song.setReleaseYear(new SimpleDateFormat("yyyy").parse(textYear.getText()));
		song.setSource(textFile.getText());

		if (controller == null)
			controller = new Controller();

		controller.insertSong(song);
	}

	/**
	 * Inserts a song into the database, controlling the exceptions.
	 */
	private void doInsertSong() {
		try {
			insertNewSong();
			WindowUtils.confirmationPane("La canción se ha añadido correctamente.", "Confirmación");
		} catch (SQLException e) {
			WindowUtils.errorPane("No se ha podido añadir la canción", "Error en la base de datos");
		} catch (Exception e) {
			WindowUtils.errorPane("No se ha podido añadir la canción", "Error general");
		}
	}

	/**
	 * Validates every text field.
	 */
	private void validateAllFields() {
		validateTitleField();
		validateDurationField();
		validateFileField();
		validateLanguageField();
		validateGenreField();
		validateYearField();
	}

	/**
	 * Checks if every field is correct.
	 * 
	 * @return true if all fields are correct
	 */
	private boolean areAllFieldsCorrect() {
		boolean ret = false;
		if (controller == null)
			controller = new Controller();

		if (controller.isNotEmptyText(textTitle) && controller.isLengthCorrectNumericSeconds(textDuration)
				&& controller.isNotEmptyText(textFile) && controller.isLetterStringCorrect(textLanguage)
				&& controller.isLetterStringCorrect(textGenre) && controller.isLengthCorrectNumeric(textYear, 4))
			ret = true;

		return ret;
	}

	/**
	 * Resets every text field.
	 */
	private void resetFields() {
		textTitle.setText("");
		textTitle.setBorder(new LineBorder(Color.WHITE, 2));
		textDuration.setText("");
		textDuration.setBorder(new LineBorder(Color.WHITE, 2));
		textFile.setText("");
		textFile.setBorder(new LineBorder(Color.WHITE, 2));
		textLanguage.setText("");
		textLanguage.setBorder(new LineBorder(Color.WHITE, 2));
		textGenre.setText("");
		textGenre.setBorder(new LineBorder(Color.WHITE, 2));
		textYear.setText("");
		textYear.setBorder(new LineBorder(Color.WHITE, 2));
	}

	/**
	 * Inserts a new song into the database if all text fields are valid.
	 */
	private void doInsertNewSong() {
		if (controller == null)
			controller = new Controller();

		if (areAllFieldsCorrect()) {
			doInsertSong();
			resetFields();
		} else {
			validateAllFields();
			WindowUtils.errorPane("Revisa los campos incorrectos marcados de color rojo.", "Error");
		}
	}

	/**
	 * Takes the employee back to the edition menu.
	 * 
	 * @param frame    frame where the panel is added
	 * @param employee logged employee
	 */
	private void goBack(JFrame frame, Employee employee) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(PanelFactory.getJPanel(PanelFactory.EMPLOYEE_MENU, frame, null, employee, null, null,
				null, null, null));
		frame.revalidate();
		frame.repaint();
	}
}
