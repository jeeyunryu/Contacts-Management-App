
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;

public class GUI {
	private int clicks = 0;
	private JLabel label;
	private JFrame frame = new JFrame();
	String[] header = {"Status", "Department", "Name", "ID", "Email Address" };;
	String[][] contents;
	static DefaultTableModel tableModel;
	String status, dept, name, email;
	int id;
	static AddDialog addDialog;

	static ArrayList<Person> people = new ArrayList<Person>();
	String fileName = "Contacts.dat";
	String[] tmp = new String[5];
	static JTable table;

	public GUI() {

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
		panel.setLayout(new GridLayout(2, 2));

		JButton button;
		JLabel label;

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		EmptyBorder border = new EmptyBorder(5, 20, 25, 20);
		c.insets = new Insets(10, 0, 0, 0);

		GridBagConstraints innerC = new GridBagConstraints();

		button = new JButton("Undo");
		button.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				table.getRowSorter().setSortKeys(null);

			}
		});
		c.anchor = GridBagConstraints.EAST;

		panel.add(button, c);

		tableModel = new DefaultTableModel(header, 0);

		try {

			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
			for (Person person : (ArrayList<Person>) inputStream.readObject()) {
				people.add(person);
				String status = null;

				if (person instanceof Under) {
					status = "Under";
				}
				if (person instanceof Grad) {
					status = "Grad";
				}
				if (person instanceof Faculty) {
					status = "Faculty";
				}
				if (person instanceof Staff) {
					status = "Staff";
				}
				Object[] temp_arr = new Object[] {status, person.getDept(), person.getName(), person.getId(),
						person.getEmail() };
				tableModel.addRow(temp_arr);
			}
			inputStream.close();

		} catch (EOFException exc) {
			System.out.println("End of reading from file!");

		} catch (FileNotFoundException exc) {
			
		} catch (IOException exc) {
			System.out.println("IOException occured!");

		} catch (ClassNotFoundException exc) {
			System.out.println("ClassNotFoundException occured!");
		}

		table = new JTable(tableModel);
		resizeColumnWidth(table);
		table.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
//		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
//		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
//		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//		sorter.setSortKeys(sortKeys);
		
//		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
//			public Component getTableCellRendererComponent(JTable table, Object value, Boolean hashFocus, int row, int column) {
//				JCheckBox checkBox = new JCheckBox();
//				checkBox.setSelected(((Boolean)value).booleanValue());
//				checkBox.setHorizontalAlignment(JLabel.CENTER);
//				return checkBox;
//			}
//		};
//		JCheckBox checkBox = new JCheckBox();
//		
//		table.getColumn("").setCellRenderer(renderer);
//		
//		table.getColumn("").setCellEditor(new DefaultCellEditor(checkBox));

		
		class IntComparator implements Comparator {
            public int compare(Object o1, Object o2) {
                Integer int1 = (Integer)o1;
                Integer int2 = (Integer)o2;
                return int1.compareTo(int2);
            }

            public boolean equals(Object o2) {
                return this.equals(o2);
            }
        }

        sorter.setComparator(3, new IntComparator());

		JScrollPane scrollPane = new JScrollPane(table);

		c.gridx = 0;
		c.gridy = 1;
		panel.add(scrollPane, c);

		addDialog = new AddDialog(frame, "Add a person");

		JPanel innerPanel2 = new JPanel(new GridBagLayout());

		button = new JButton(new AbstractAction("Add") {
			public void actionPerformed(ActionEvent e) {
				addDialog.pack();
				addDialog.setLocationRelativeTo(null);
				addDialog.setVisible(true);

			}
		});

		c.insets = new Insets(10, 0, 0, 0);
		innerC.gridx = 0;

		innerC.gridy = 0;

		innerC.insets = new Insets(0, 0, 0, 5);
		innerPanel2.add(button, innerC);

//        innerC.gridx = 0;  
		innerC.gridx = 1;

		button = new JButton(new AbstractAction("Save") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));

					outputStream.writeObject(people);
					outputStream.flush();
					outputStream.close();
					JOptionPane.showMessageDialog(null, "Successfully saved!");
				} catch (FileNotFoundException exc) {
					System.out.println("Error opening the file " + fileName);
					System.exit(0);
				} catch (IOException exc) {
					System.out.println("Problem with output to file " + fileName);
				}
			}
		});
		innerC.insets = new Insets(0, 5, 0, 0);
		innerPanel2.add(button, innerC);

		c.gridx = 0;
		c.gridy = 2;
		panel.add(innerPanel2, c);

		// set up the frame and display it
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("People in HGU");
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	// process the button clicks
	public static void submitDialog() {
//        clicks++;
//        label.setText("Number of clicks:  " + clicks);
		String[] input = addDialog.getTmp();
		String status = input[0];
		String dept = input[1];
		String name = input[2];
		int id = Integer.parseInt(input[3]);
		String email = input[4];
		Object[] objs = {status, dept, name, id, email };

		tableModel.addRow(objs);
		resizeColumnWidth(table);

		addPeople(status, dept, name, id, email);

	}

	public static void addPeople(String status, String dept, String name, int id, String email) {
		if (status.equalsIgnoreCase("Under")) {
			Person person = new Under(dept, name, id, email);
			people.add(person);
		} else if (status.equalsIgnoreCase("Grad")) {
			Person person = new Grad(dept, name, id, email);
			people.add(person);
		} else if (status.equalsIgnoreCase("Faculty")) {
			Person person = new Faculty(dept, name, id, email);
			people.add(person);
		} else if (status.equalsIgnoreCase("Staff")) {
			Person person = new Staff(dept, name, id, email);
			people.add(person);
		}
	}

	public static void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
//		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 15; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
//			if (width > 300)
//				width = 300;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	public static void main(String[] args) {
		new GUI();
	}
}
