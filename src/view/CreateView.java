package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import controller.ViewManager;
import model.BankAccount;
import model.User;


@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton logoutButton;
	private JTextField fName;
	private JTextField lName;
	private JTextField address;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
	private JFormattedTextField dob;
	private JTextField phone;
	private JPasswordField pinField;
	private JButton submitButton;
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public CreateView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the CreateView components.
	 */
	
	private void initialize() {
		
		// TODO
		//
		// this is a placeholder for this view and should be removed once you start
		// building the CreateView.
		this.setLayout(null);
		
		this.initFnameField();
		this.initLnameField();
		this.initAddressField();
		this.initCityField();
		this.initStateField();
		this.initZipField();
		this.initDobField();
		this.initPhoneField();
		this.initPinField();
		this.initLogoutButton();
		this.initSubmitButton();
		
		// TODO
		//
		// this is where you should build the CreateView (i.e., all the components that
		// allow the user to enter his or her information and create a new account).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
	}
	
	private void initFnameField() {
		JLabel label = new JLabel("FirstName", SwingConstants.LEFT);
		label.setBounds(100, 60, 95, 35);
		label.setLabelFor(fName);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		fName = new JTextField(20);
		fName.setBounds(205, 60, 200, 35);
		
		this.add(label);
		this.add(fName);
	}
	
	private void initLnameField() {
		JLabel label = new JLabel("LastName", SwingConstants.LEFT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(lName);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		lName = new JTextField(20);
		lName.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(lName);
	}
	
	private void initAddressField() {
		JLabel label = new JLabel("Address", SwingConstants.LEFT);
		label.setBounds(100, 140, 95, 35);
		label.setLabelFor(address);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		address = new JTextField(20);
		address.setBounds(205, 140, 200, 35);
		
		this.add(label);
		this.add(address);
	}
	
	private void initCityField() {
		JLabel label = new JLabel("City", SwingConstants.LEFT);
		label.setBounds(100, 180, 95, 35);
		label.setLabelFor(city);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		city = new JTextField(20);
		city.setBounds(205, 180, 200, 35);
		
		this.add(label);
		this.add(city);
	}
	
	private void initStateField() {
		JLabel label = new JLabel("State", SwingConstants.LEFT);
		label.setBounds(100, 220, 95, 35);
		label.setLabelFor(state);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		state = new JTextField(20);
		state.setBounds(205, 220, 200, 35);
		
		this.add(label);
		this.add(state);
	}
	
	private void initZipField() {
		JLabel label = new JLabel("ZIP", SwingConstants.LEFT);
		label.setBounds(100, 260, 95, 35);
		label.setLabelFor(zip);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		zip = new JTextField(20);
		zip.setBounds(205, 260, 200, 35);
		
		this.add(label);
		this.add(zip);
	}
	
	private void initDobField() {
		JLabel label = new JLabel("Date of Birth", SwingConstants.RIGHT);
		label.setBounds(100, 300, 95, 35);

	    try {
			MaskFormatter dateFormat = new MaskFormatter("##/##/####");
			dateFormat.setPlaceholderCharacter('_');
			dob = new JFormattedTextField(dateFormat);
		} catch (ParseException e) {
			dob.setText("");
		}
		dob.setBounds(205, 300, 200, 35);

		this.add(label);
	    this.add(dob);
		/**JLabel label = new JLabel("DOB", SwingConstants.LEFT);
		label.setBounds(100, 340, 95, 35);
		label.setLabelFor(dob);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		dob = new JTextField(20);
		dob.setBounds(205, 340, 200, 35);
		
		this.add(label);
		this.add(dob);**/
	}
	
	private void initPhoneField() {
		JLabel label = new JLabel("PHONE", SwingConstants.LEFT);
		label.setBounds(100, 340, 95, 35);
		label.setLabelFor(phone);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		phone = new JTextField(20);
		phone.setBounds(205, 340, 200, 35);
		
		this.add(label);
		this.add(phone);
	}
	
	private void initPinField() {
		JLabel label = new JLabel("PIN", SwingConstants.LEFT);
		label.setBounds(100, 380, 95, 35);
		label.setLabelFor(pinField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		pinField = new JPasswordField(20);
		pinField.setBounds(205, 380, 200, 35);
		addKeyListener(pinField, 4);
		
		this.add(label);
		this.add(pinField);
	}
	
	private void initLogoutButton() {
        logoutButton = new JButton("Cancel");
        logoutButton.setBounds(50, 420, 100, 50);
        logoutButton.addActionListener(this);
        
        this.add(logoutButton);
    }
	
	private void initSubmitButton() {	
		submitButton = new JButton("Login");
		submitButton.setBounds(160, 420, 100, 50);
		submitButton.addActionListener(this);
		
		this.add(submitButton);
	}
	
	private void addKeyListener(JTextField field, int length) {
		field.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        if (field.getText().length() >= length) 
		            e.consume();
		    }
		});
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
		
		if (source.equals(logoutButton)) {
			manager.logout();
		}
		else if (source.equals(submitButton)) {
			String strDob = dob.getText();
			String strPhone = phone.getText();
			int dob = Integer.parseInt(strDob.substring(0,2) + strDob.substring(3,5) + strDob.substring(6,10));
			int phone = Integer.parseInt(strPhone.substring(1,4) + strPhone.substring(6,9) + strPhone.substring(10, 14));
			User tempUser = new User(Integer.parseInt(String.valueOf(pinField.getPassword())), dob, phone, fName.getText(), lName.getText(), address.getText(), city.getText(), state.getText(), zip.getText());
			BankAccount tempAccount = new BankAccount('Y', manager.getMaxAccountNumber() + 1, 0.0, tempUser);
			manager.addAccount(tempAccount);
		}
		// TODO
		//
		// this is where you'll setup your action listener, which is responsible for
		// responding to actions the user might take in this view (an action can be a
		// user clicking a button, typing in a textfield, etc.).
		//
		// feel free to use my action listener in LoginView.java as an example.
	}
}