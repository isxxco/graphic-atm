package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import controller.ViewManager;
import model.BankAccount;
import model.User;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JTextField FnameField;
	private JTextField LnameField;
	private JTextField PhoneField;
	private JTextField StreetField;
	private JComboBox<String> first;
	private JComboBox<String> second;
	private JComboBox<String> third;
	private JTextField CityField;
	private JComboBox<String> initial;
	private JTextField ZipField;
	private JTextField APhoneField;
	private JTextField BPhoneField;
	private JPasswordField PINField;
	private JButton CancelButton;
	private JButton CreateButton;
	private JLabel errorLabel;	
	
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public CreateView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorLabel = new JLabel("", SwingConstants.CENTER);
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the CreateView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		
		initFnameField();
		initLnameField();
		initPhoneField();
		initAPhoneField();
		initBPhoneField();
		initStreetField();
		initBirthdayField();
		initCityField();
		initStateField();
		initZipField();
		initPINField();
		initCancelButton();
		initCreateButton();
		initErrorLabel();
		
		errorLabel = new JLabel();
		errorLabel.setText("You are missing a required field!");
		this.add(errorLabel);
	}
	
	private void initFnameField() {
		JLabel label = new JLabel("First Name", SwingConstants.RIGHT);
		label.setBounds(100, 10, 95, 35);
		label.setLabelFor(FnameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		FnameField = new JTextField(25);
		FnameField.setBounds(205, 10, 200, 35);
		
		this.add(label);
		this.add(FnameField);
	}
	
	//x, y, width, height starts at 0,0 top left

	private void initLnameField() {
		JLabel label = new JLabel("Last Name", SwingConstants.RIGHT);
		label.setBounds(100, 50, 95, 35);
		label.setLabelFor(LnameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		LnameField = new JTextField(25);
		LnameField.setBounds(205, 50, 200, 35);
		
		this.add(label);
		this.add(LnameField);
	}
	
	private void initBirthdayField() {
		JLabel label = new JLabel("Birthdate", SwingConstants.RIGHT);
		label.setBounds(100, 90, 95, 35);
		label.setLabelFor(first);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		this.add(label);
		
		String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		first = new JComboBox<String>(months);
		first.setBounds(205, 90, 95, 35);
		this.add(first);
		first.setVisible(true);
		
		String[] days = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		second = new JComboBox<String>(days);
		second.setBounds(300, 90, 50, 35);
		this.add(second);
		second.setVisible(true);
		
		int[] years = IntStream.range(1900, 2019).toArray();
		String[] stringYears = Arrays.toString(years).split("[\\[\\]]")[1].split(",");
		third = new JComboBox<String>(stringYears);
		third.setBounds(350, 90, 95, 35);
		this.add(third);
		third.setVisible(true);
	}
	
	private void initPhoneField() {
		JLabel label = new JLabel("Phone", SwingConstants.RIGHT);
		label.setBounds(100, 130, 95, 35);
		label.setLabelFor(PhoneField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		PhoneField = new JTextField(20);
		PhoneField.setBounds(205, 130, 30, 35);
		
		this.add(label);
		this.add(PhoneField);
	}
	
	private void initAPhoneField() {
		APhoneField = new JTextField(20);
		APhoneField.setBounds(245, 130, 30, 35);
		
		this.add(APhoneField);
	}
	
	private void initBPhoneField() {
		BPhoneField = new JTextField(20);
		BPhoneField.setBounds(285, 130, 40, 35);
		
		this.add(BPhoneField);
	}
	
	private void initStreetField() {
		JLabel label = new JLabel("Address", SwingConstants.RIGHT);
		label.setBounds(100, 170, 95, 35);
		label.setLabelFor(StreetField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		StreetField = new JTextField(30);
		StreetField.setBounds(205, 170, 200, 35);
		
		this.add(label);
		this.add(StreetField);
	}
	
	private void initCityField() {
		JLabel label = new JLabel("City", SwingConstants.RIGHT);
		label.setBounds(100, 210, 95, 35);
		label.setLabelFor(CityField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		CityField = new JTextField(30);
		CityField.setBounds(205, 210, 200, 35);
		
		this.add(label);
		this.add(CityField);
	}
	
	private void initStateField() {
		JLabel label = new JLabel("State", SwingConstants.RIGHT);
		label.setBounds(100, 250, 95, 35);
		label.setLabelFor(initial);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		this.add(label);
		
		String[] states = { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY" };
		initial = new JComboBox<String>(states);
		initial.setBounds(205, 250, 200, 35);
		this.add(initial);
		initial.setVisible(true);
	}
	
	private void initZipField() {
		JLabel label = new JLabel("ZIP", SwingConstants.RIGHT);
		label.setBounds(100, 290, 95, 35);
		label.setLabelFor(ZipField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		ZipField = new JTextField(30);
		ZipField.setBounds(205, 290, 200, 35);
		
		this.add(label);
		this.add(ZipField);
	}
	
	private void initPINField() {
		JLabel label = new JLabel("PIN", SwingConstants.RIGHT);
		label.setBounds(100, 330, 95, 35);
		label.setLabelFor(PINField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		PINField = new JPasswordField();
	    PINField.setEchoChar('*');
		PINField.setBounds(205, 330, 50, 35);
		
		this.add(label);
		this.add(PINField);
	}
	
	private void initCancelButton() {	
		CancelButton = new JButton("Cancel");
		CancelButton.setBounds(305, 370, 100, 35);
		CancelButton.addActionListener(this);
		
		this.add(CancelButton);
	}
	
	private void initCreateButton() {	
		CreateButton = new JButton("Confirm");
		CreateButton.setBounds(205, 370, 100, 35);
		CreateButton.addActionListener(this);
		
		this.add(CreateButton);
	}
	
	private void initErrorLabel() {
		errorLabel.setBounds(250, 400, 100, 35);
		errorLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorLabel.setForeground(Color.RED);
		
		this.add(errorLabel);
	}
	
	/*
	 * CreateView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The CreateView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the CreateView.
	 * 
	 * @param e
	 */
	//private Database db;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(CreateButton)) {
			String firstName = FnameField.getText();
			String lastName = LnameField.getText();
			String phone = PhoneField.getText() + APhoneField.getText() + BPhoneField.getText();
			String street = StreetField.getText();
			String month = first.getSelectedItem().toString();
			String date = second.getSelectedItem().toString();
			String year = third.getSelectedItem().toString();
			String dob = year.substring(1) + month + date;
			String city = CityField.getText();
			String zipcode = ZipField.getText();
			String state = initial.getSelectedItem().toString();
			String pinNumber = new String(PINField.getPassword());
			boolean create = true;

			if (firstName.equals("") || lastName.equals("") || firstName.length() > 15 || lastName.length() > 20) {
				errorLabel.setText("Incorrect name input. First Name: 1 character MIN - 15 character MAX. Last Name: 1 character MIN - 20 character MAX.");
				create = false;
			}
			if (month.equals("") || date.equals("") || year.equals("")) {
				errorLabel.setText("Please select a birthdate.");
				create = false;
			}
			
			if(phone.length() != 10 || phone.matches("[a-zA-Z]+")) {
				errorLabel.setText("Please enter a 10 digit phone number.");
				create = false;
			}
			if (street.equals("") || city.equals("") || state.equals("") || zipcode.equals("") || zipcode.matches("[a-zA-Z]+")) {
				errorLabel.setText("Please enter all components of your address.");
				create = false;
			}
			
			if (pinNumber.equals("") || pinNumber.length() > 4 || pinNumber.matches("[a-zA-Z]+")) {
				errorLabel.setText("Please enter a 4 digit numerical PIN.");
				create = false;
			}
			
			long num = 0;
			try {
				num = manager.maxAccountNumber() + 1;
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			if (create) {
				User user = new User(Integer.parseInt(pinNumber), Integer.parseInt(dob), Long.parseLong(phone), firstName, lastName, street, city, state, zipcode);
				BankAccount acc = new BankAccount('Y', num, 0, user);
				manager.insertAccountFR(acc);
				JOptionPane.showMessageDialog(null, "Welcome to the Bank! Your account number is " + num + " and your PIN is " + pinNumber + ". Please record in a secure location.");
				this.removeAll();
				initialize();
			}
			
			manager.switchTo(ATM.LOGIN_VIEW);
		}
		else if (e.getActionCommand().equals("Cancel")) {
			this.removeAll();
			initialize();
			manager.switchTo(ATM.LOGIN_VIEW);
		} else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
	}
}