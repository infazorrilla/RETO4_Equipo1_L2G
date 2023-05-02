package soundbridge.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import soundbridge.database.pojos.Album;
import soundbridge.database.pojos.Client;
import soundbridge.utils.WindowUtils;
import soundbridge.view.factory.PanelFactory;

public class AlbumView extends JPanel {

	private static final long serialVersionUID = 7113753129445115841L;
	
	public AlbumView(JFrame frame, Client client, Album album) {
		initialize(frame, client, album);
	}
	
	public void initialize(JFrame frame, Client client, Album album) {
		setBounds(0, 0, 1000, 672);
		setLayout(null);
		setBackground(Color.black);
		
		JPanel panelAlbumCover = new JPanel();
		panelAlbumCover.setBounds(40, 40, 250, 250);
		add(panelAlbumCover);
		panelAlbumCover.setLayout(new BorderLayout(0, 0));
		panelAlbumCover.setOpaque(false);

		JLabel lblAlbumCover = new JLabel("");
		panelAlbumCover.add(lblAlbumCover, BorderLayout.CENTER);
		
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
		panelHomeIcon.setToolTipText("Ir a mi biblioteca.");

		JLabel lblHomeIcon = new JLabel("");
		panelHomeIcon.add(lblHomeIcon, BorderLayout.CENTER);
		
		JLabel lblName = new JLabel(album.getName());
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblName.setBounds(324, 103, 400, 35);
		lblName.setForeground(Color.white);
		add(lblName);
		
		JLabel lblReleaseYear = new JLabel("(" + (new SimpleDateFormat("yyyy")).format(album.getReleaseYear()) + ")");
		lblReleaseYear.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblReleaseYear.setBounds(324, 130, 400, 35);
		lblReleaseYear.setForeground(Color.white);
		add(lblReleaseYear);
		
		JScrollPane scrollPaneSongs = new JScrollPane();
		scrollPaneSongs.setBounds(44, 179, 904, 383);
		add(scrollPaneSongs);

		JTable tableClients = new JTable();
		tableClients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableClients.setDefaultEditor(Object.class, null);
		scrollPaneSongs.setViewportView(tableClients);
		tableClients.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		scrollPaneSongs.setViewportView(tableClients);
		Object[] columnsManagesClients = { "Título", "Género", "Género", "DNI", "Username", "Contraseña", "Bloqueado"  };

		DefaultTableModel modelClients= new DefaultTableModel();
		modelClients.setColumnIdentifiers(columnsManagesClients);
		tableClients.setModel(modelClients);
		
		WindowUtils.addImage(panelHomeIcon, lblHomeIcon, "img/icon/home.png");
		WindowUtils.addImage(panelAlbumCover, lblAlbumCover, album.getCover());
	}

}
