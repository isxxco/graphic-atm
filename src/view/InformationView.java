package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import model.User;
import model.BankAccount;

@SuppressWarnings("serial")
public class InformationView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	
	private JLabel errorMessageLabel;
	private JButton BackButton;
	private JButton EditButton;

	private JTextField firstName;
	private JTextField lastName;
	
	private JComboBox<String> day;
	private JComboBox<String> month;
	private JComboBox<String> year;
	
	private JTextField phone1;
	private JTextField phone2;
	private JTextField phone3;
	private JTextField address;
	private JTextField city;
	private JComboBox<String> state;
	private JTextField postal;
	private JPasswordField pinField;
	
	private long accountNum;
	private double accountBal;
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public InformationView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the CreateView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		
		initFirstNameField();
		firstName.setEnabled(false);
		initLastNameField();
		lastName.setEnabled(false);
		initBirthdayFields();
		day.setEnabled(false);
		month.setEnabled(false);
		year.setEnabled(false);
		initPhoneField();
		phone1.setEnabled(false);
		phone2.setEnabled(false);
		phone3.setEnabled(false);
		initAddressField();
		address.setEnabled(false);
		initCityField();
		city.setEnabled(false);
		initStateField();
		state.setEnabled(false);
		initPostalField();
		postal.setEnabled(false);
		initPinField();
		pinField.setEnabled(false);
		initButtons();
		
		errorMessageLabel = new JLabel("Welcome to the Information View!", SwingConstants.CENTER);
		errorMessageLabel.setBounds(36, 400, 338, 35);
		this.add(errorMessageLabel);
	}
	
	public void switchingToInformationView(BankAccount account) {
		accountNum = account.getAccountNumber();
		accountBal = account.getBalance();
		
		firstName.setText(account.getUser().getFirstName());
		lastName.setText(account.getUser().getLastName());
		
		int dobInt = account.getUser().getDob();
		String dob = String.valueOf(dobInt);
		day.setSelectedIndex(Integer.parseInt(dob.substring(6)));
		month.setSelectedIndex(Integer.parseInt(dob.substring(4, 6)));
		year.setSelectedIndex(Integer.parseInt(dob.substring(0, 4))-1899);

		long phoneLong = account.getUser().getPhone();
		String phone = String.valueOf(phoneLong);
		phone1.setText(phone.substring(0, 3));
		phone2.setText(phone.substring(3, 6));
		phone3.setText(phone.substring(6));

		address.setText(account.getUser().getStreetAddress());
		city.setText(account.getUser().getCity());
		state.setSelectedItem(account.getUser().getState());
		postal.setText(account.getUser().getZip());
		pinField.setText(String.valueOf(account.getUser().getPin()));
	}
	private void initFirstNameField() {
		JLabel label = new JLabel("First Name", SwingConstants.RIGHT);
		label.setBounds(5, 5, 100, 35);
		label.setLabelFor(firstName);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
	
		firstName = new JTextField(30);
		firstName.setBounds(110, 5, 270, 35);
		this.add(label);
		this.add(firstName);
		
	}
	
	private void initLastNameField() {
		JLabel label = new JLabel("Last Name", SwingConstants.RIGHT);
		label.setBounds(5, 45, 100, 35);
		label.setLabelFor(lastName);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		lastName = new JTextField(30);
		lastName.setBounds(110, 45, 270, 35);
		
		this.add(label);
		this.add(lastName);
	}
	
	private void initBirthdayFields() {
		int[] days = IntStream.range(1,32).toArray();
		String[] stringdays = Arrays.toString(days).replace("[", "[,").split("[\\[\\]]")[1].split(",");
		
		int[] years = IntStream.range(1900,2019).toArray();
		String[] stringyears = Arrays.toString(years).replace("[", "[,").split("[\\[\\]]")[1].split(",");
		
		String[] stringmonths = {"", "January", "Febuary", "March", "April", "May", "June", "July",
		                          "August", "September", "October", "November", "December"};
		
		
		JLabel label = new JLabel("Birthday", SwingConstants.CENTER);
		label.setLabelFor(day);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		label.setBounds(5, 85, 100, 35);
		day = new JComboBox<String>(stringdays);
		day.setBounds(110, 85, 50, 35);
		month = new JComboBox<String>(stringmonths);
		month.setBounds(165, 85, 100, 35);
		year = new JComboBox<String>(stringyears);
		year.setBounds(270, 85, 100, 35);

		
		this.add(label);
		this.add(day);
		this.add(month);
		this.add(year);
	}
	
	private void initPhoneField() {
		JLabel label = new JLabel("Phone Num.", SwingConstants.CENTER);
		label.setBounds(5, 125, 100, 35);
		label.setLabelFor(phone1);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		phone1 = new JTextField(3);
		phone1.setBounds(110, 125, 85, 35);
		phone2 = new JTextField(3);
		phone2.setBounds(110+85+5, 125, 85, 35);
		phone3 = new JTextField(4);
		phone3.setBounds(110+85+85+5+5, 125, 90, 35);

		this.add(label);
		this.add(phone1);
		this.add(phone2);
		this.add(phone3);
	}
	
	private void initAddressField() {
		JLabel label = new JLabel("Address", SwingConstants.RIGHT);
		label.setBounds(5, 165, 100, 35);
		label.setLabelFor(address);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		address = new JTextField(30);
		address.setBounds(110, 165, 270, 35);

		this.add(label);
		this.add(address);
	}

	private void initCityField() {
		JLabel label = new JLabel("City", SwingConstants.RIGHT);
		label.setLabelFor(city);
		label.setBounds(5, 205, 100, 35);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		city = new JTextField(30);
		city.setBounds(110, 205, 270, 35);

		this.add(label);
		this.add(city);
	}
	
	private void initStateField() {
		JLabel label = new JLabel("State", SwingConstants.RIGHT);
		label.setBounds(5, 245, 100, 35);
		label.setLabelFor(state);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		String[] abbrivs = {"", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		state = new JComboBox<String>(abbrivs);
		state.setBounds(110, 245, 85, 35);

		this.add(label);
		this.add(state);
	}
	
	private void initPostalField() {
		JLabel label = new JLabel("Postal Code", SwingConstants.RIGHT);
		label.setBounds(200, 245, 90, 35);
		label.setLabelFor(postal);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		postal = new JTextField(5);
		postal.setBounds(295, 245, 85, 35);
		
		this.add(label);
		this.add(postal);
	}
	private void initPinField() {
		JLabel label = new JLabel("PIN", SwingConstants.RIGHT);
		label.setBounds(5, 285, 100, 35);
		label.setLabelFor(pinField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		pinField = new JPasswordField(4);
		pinField.setBounds(110, 285, 270, 35);

		this.add(label);
		this.add(pinField);
	}
	
	private void initButtons() {	
		EditButton = new JButton("Edit");
		EditButton.setBounds(106, 360, 140, 35);
		EditButton.addActionListener(this);
		
		BackButton = new JButton("Return");
		BackButton.setBounds(254, 360, 120, 35);
		BackButton.addActionListener(this);

		this.add(EditButton);
		this.add(BackButton);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(BackButton)) {
			manager.switchTo(ATM.HOME_VIEW);
			this.removeAll();
			this.initialize();
		} else if(source.equals(EditButton)) {
			if(EditButton.getText().equals("Edit")) {
				address.setEnabled(true);
				city.setEnabled(true);
				state.setEnabled(true);
				postal.setEnabled(true);
				
				phone1.setEnabled(true);
				phone2.setEnabled(true);
				phone3.setEnabled(true);
				
				pinField.setEnabled(true);
				
				EditButton.setText("Save");
			} else {
				boolean create = true;

				String first = firstName.getText();
				String last = firstName.getText();
				if (first.equals(null) || last.equals(null) || first.length() > 15 || last.length() > 20) {
					errorMessageLabel.setText("Name Formatted Incorrectly");
					create = false;
				} 
				
				int dob = 0;
				if (year.getSelectedIndex() != 0 && day.getSelectedIndex() != 0 && month.getSelectedIndex() != 0) {
					String yr = String.valueOf(year.getSelectedIndex()+1899);
					String mon = String.valueOf(month.getSelectedIndex());
					if (mon.length() != 2) {
						mon = 0 + mon;
					}
					String dy = String.valueOf(day.getSelectedIndex());
					if (dy.length() != 2) {
						dy = 0 + dy;
					}
					dob = Integer.parseInt(yr+mon+dy);
				} else {
					errorMessageLabel.setText("Fill in all fields (Birthday missing)");
					create = false;

				}
				long phone = 0;
				if( !phone1.getText().matches("\\d{3}") || !phone2.getText().matches("\\d{3}") || !phone3.getText().matches("\\d{4}")) {
					errorMessageLabel.setText("Phone Number Formatted Incorrectly");
					create = false;
				} else {
					phone = Long.parseLong((phone1.getText()+phone2.getText()+phone3.getText()));
				}
				
				
				String a = address.getText();
				String c = city.getText();
				String s = state.getItemAt(state.getSelectedIndex());
				String zip = postal.getText();
				if(a.equals(null) || c.equals(null) || s.equals("") || !zip.matches("\\d{5}") || a.length() > 30 || c.length() > 30 || zip.length() != 5) {
					errorMessageLabel.setText("Residency Formatted Incorrectly (City, State, Street, Zip)");
					create = false;
				}
				
				char[] pinChars = null;
				if(!pinField.getPassword().equals(null)) {
					pinChars= pinField.getPassword();
					for( char digit : pinChars) {
						if (!Character.isDigit(digit)) {
							errorMessageLabel.setText("PIN Formatted Incorrectly");
							create = false;
						}
					}
					if (pinChars.length != 4) {
						errorMessageLabel.setText("PIN must be exactly 4 numbers long");
						create = false;
					}
				}
								
				if(create) {
					String pinString = "";
					for (char ch : pinChars) {
						pinString += ch;
					}
					int pin = Integer.parseInt(pinString); 
					User user = new User(pin, dob, phone, first, last, a, c, s, zip);
					BankAccount acc = new BankAccount('Y',accountNum,accountBal,user);
					manager.updateAccount(acc);
					
					
					EditButton.setText("Edit");
					address.setEnabled(false);
					city.setEnabled(false);
					state.setEnabled(false);
					postal.setEnabled(false);
					
					phone1.setEnabled(false);
					phone2.setEnabled(false);
					phone3.setEnabled(false);
					
					pinField.setEnabled(false);
				}
			}
			
		}
	}
}