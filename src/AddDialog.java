import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AddDialog extends JDialog implements ItemListener {

	JTextField[] textFields = new JTextField[5];
	JLabel label;
	JComboBox c1, c2, c3;
	String[] tmp = new String[5];
	JTextField nameField, idField, emailField;

	public JTextField[] getTextFields() {
		return textFields;
	}

	public String[] getTmp() {
		return tmp;
	}

	AddDialog(JFrame frame, String str) {

		super(frame, str, true);

		EmptyBorder border = new EmptyBorder(15, 20, 25, 20);

		JPanel panel = new JPanel(new GridBagLayout());

		panel.setBorder(border);
		add(panel);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new java.awt.Insets(10, 10, 10, 10);

		JPanel innerPanel;
		innerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints innerC = new GridBagConstraints();

//		String[] labels = {"Status", "Department", "Name", "ID", "Email"};
//		
//		for (int i = 0; i < labels.length; i++) {
//			label = new JLabel(labels[i]);
//
//			c.gridx = 0;
//			c.gridy = 0;
//
//			panel.add(label, c);
//			c.gridx = 1;
//			textFields[i] = new JTextField(10);
//			panel.add(textFields[i], c);
//		}
		c.anchor = GridBagConstraints.WEST;

		label = new JLabel("Status");

		c.gridx = 0;
		c.gridy = 0;

		panel.add(label, c);

		c.gridx = 1;
//		textFields[0] = new JTextField(10);
//		panel.add(textFields[0], c);
		String[] stats = { "Not selected yet", "Grad", "Under", "Faculty", "Staff" };
		c1 = new JComboBox(stats);
		c1.setSelectedItem("Not selected yet");
		panel.add(c1, c);
		c1.addItemListener(this);

		;
		label = new JLabel("Department");
		c.gridx = 0;
		c.gridy = 1;

		panel.add(label, c);

//		textFields[1] = new JTextField(10);
		c.gridx = 1;

//		panel.add(textFields[1], c);
		String[] depts = { "Not selected yet", "GLS", "CSEE", "ICT", "MCE" };
		c2 = new JComboBox(depts);
		panel.add(c2, c);
		c2.addItemListener(this);
		c2.setSelectedItem("Not selected yet");

		label = new JLabel("Name");
		c.gridx = 0;
		c.gridy = 2;

		panel.add(label, c);

		c.gridx = 1;
		nameField = new JTextField(10);
		panel.add(nameField, c);

		label = new JLabel("ID");
		c.gridx = 0;
		c.gridy = 3;

		panel.add(label, c);

		c.gridx = 1;
		idField = new JTextField(10);
		panel.add(idField, c);

		label = new JLabel("Email Address");
		c.gridx = 0;
		c.gridy = 4;

		panel.add(label, c);

		innerC.gridx = 0;
		innerC.gridy = 0;

		emailField = new JTextField(5);
		innerPanel.add(emailField, innerC);

//		innerC.gridx = 1;
//		label = new JLabel("@");
//		innerPanel.add(label, innerC);

		String[] domains = { "Not selected yet", "@gmail.com", "@handong.ac.kr", "@handong.edu", "@naver.com",
				"@kakao.com" };
		c3 = new JComboBox(domains);
		c3.addItemListener(this);
		c3.setSelectedItem("Not selected yet");
		innerC.gridx = 1;
		innerPanel.add(c3, innerC);

		c.gridx = 1;
		panel.add(innerPanel, c);

		JButton addButton = new JButton(new AbstractAction("Add to List") {
			public void actionPerformed(ActionEvent e) {

				tmp[0] = (String) c1.getSelectedItem();
				tmp[1] = (String) c2.getSelectedItem();
				tmp[2] = nameField.getText();
				tmp[3] = idField.getText();
				tmp[4] = (String) c3.getSelectedItem();

				try {
					if (tmp[0].equals("Not selected yet") || tmp[1].equals("Not selected yet") || tmp[2].equals("") || tmp[3].equals("")
							|| tmp[4].equals("Not selected yet") || emailField.getText().equals("")) {
						throw new Exception();
					}
					tmp[4] = emailField.getText() + tmp[4];

					GUI.submitDialog();
					dispose();

					c1.setSelectedItem("Not selected yet");
					c2.setSelectedItem("Not selected yet");
					nameField.setText("");
					idField.setText("");
					emailField.setText("");
					c3.setSelectedItem("Not selected yet");
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Not Filled out!", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

				}

			}
		});

		c.gridx = 0;
		c.gridy = 5;

		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;

		panel.add(addButton, c);

	}

	public void itemStateChanged(ItemEvent e) {
		// if the state combobox is changed
		if (e.getSource() == c1) {
			tmp[0] = (String) c1.getSelectedItem();

		} else if (e.getSource() == c2) {
			tmp[1] = (String) c2.getSelectedItem();
		} else if (e.getSource() == c3) {
			tmp[4] = (String) c3.getSelectedItem();
		}
	}
}
