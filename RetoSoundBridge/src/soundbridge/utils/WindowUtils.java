package soundbridge.utils;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class WindowUtils {

	public static void messagePaneWithIcon(String message, String title, String path) {
		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("OptionPane.messagebackground", Color.BLACK);
		UIManager.put("Panel.background", Color.BLACK);
		UIManager.put("Button.background", Color.WHITE);
		UIManager.put("Button.foreground", Color.BLACK);

		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);

		JOptionPane.showMessageDialog(null, "<html><font color='white'>" + message + "</font></html>", title,
				JOptionPane.INFORMATION_MESSAGE, icon);
	}

	public static void errorPane(String message, String title) {
		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("OptionPane.messagebackground", Color.BLACK);
		UIManager.put("Panel.background", Color.BLACK);
		UIManager.put("Button.background", Color.WHITE);
		UIManager.put("Button.foreground", Color.BLACK);

		ImageIcon icon = new ImageIcon("img/icon/cross.png");
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);

		JOptionPane.showMessageDialog(null, "<html><font color='white'>" + message + "</font></html>", title,
				JOptionPane.INFORMATION_MESSAGE, icon);
	}

	public static void confirmationPane(String message, String title) {
		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("OptionPane.messagebackground", Color.BLACK);
		UIManager.put("Panel.background", Color.BLACK);
		UIManager.put("Button.background", Color.WHITE);
		UIManager.put("Button.foreground", Color.BLACK);

		ImageIcon icon = new ImageIcon("img/icon/tick.png");
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);

		JOptionPane.showMessageDialog(null, "<html><font color='white'>" + message + "</font></html>", title,
				JOptionPane.INFORMATION_MESSAGE, icon);
	}

	public static int yesOrNoPaneWithIcon(String message, String title, String path) {
		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("OptionPane.messagebackground", Color.BLACK);
		UIManager.put("Panel.background", Color.BLACK);
		UIManager.put("Button.background", Color.WHITE);
		UIManager.put("Button.foreground", Color.BLACK);

		JFrame frame = new JFrame();
		String[] options = new String[2];
		options[0] = "Sí";
		options[1] = "No";

		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);

		int ret = JOptionPane.showOptionDialog(frame.getContentPane(),
				"<html><font color='white'>" + message + "</font></html>", title, JOptionPane.YES_NO_OPTION,
				JOptionPane.YES_NO_OPTION, icon, options, null);

		return ret;
	}

	public static String inputPaneWithIcon(String message, String title, String path) {
		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("OptionPane.messagebackground", Color.BLACK);
		UIManager.put("Panel.background", Color.BLACK);
		UIManager.put("Button.background", Color.WHITE);
		UIManager.put("Button.foreground", Color.BLACK);

		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);

		JTextField textField = new JTextField();

		Object[] components = { "<html><font color='white'>" + message + "</font></html>", textField };
		JOptionPane.showMessageDialog(null, components, title, JOptionPane.PLAIN_MESSAGE, icon);

		return textField.getText();
	}

	public static void addImage(JPanel panel, JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
		icon.setImage(resizedImg);
		label.setIcon(icon);
	}

	public static void addGif(JLabel label, String path) {
		ImageIcon icon = new ImageIcon(path);
		label.setIcon(icon);
	}

}
