package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ViewManager;
import model.BankAccount;

@SuppressWarnings("serial")
public class HomeView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton logoutButton;
	private JButton DepositButton;
	private JButton WithdrawlButton;
	private JButton TransferButton;
	private JButton CloseButton;
	private JButton InfoButton;
	private JLabel infoLabel;
	private BankAccount account;
	private JLabel welcomeMessage;

	/**
	 * Constructs an instance (or objects) of the HomeView class.
	 * 
	 * @param manager
	 */
	
	public HomeView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	public void setMessage (BankAccount account) {
		this.account = account;
		if (this.account != null) {
			welcomeMessage.setText("Hello " + account.getUser().getFirstName() + " "  + account.getUser().getLastName() + "! What can I help you with?");
			infoLabel.setText("Account Number: " + account.getAccountNumber() + " | " + "Balance: " + account.getFBalance());
		} 
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the HomeView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		
		initDepositButton();
		initWithdrawlButton();
		initTransferButton();
		initInfoButton();
		initCloseButton();
		initLogoutButton();
		initInfoLabel();
		initwelcomeMessage();
	}
	
	private void initDepositButton() {	
		DepositButton = new JButton("Deposit");
		DepositButton.setBounds(20, 10, 120, 35);
		DepositButton.addActionListener(this);
		
		this.add(DepositButton);
	}
	
	private void initWithdrawlButton() {	
		WithdrawlButton = new JButton("Withdrawl");
		WithdrawlButton.setBounds(20, 60, 120, 35);
		WithdrawlButton.addActionListener(this);
		
		this.add(WithdrawlButton);
	}
	
	private void initTransferButton() {	
		TransferButton = new JButton("Transfer");
		TransferButton.setBounds(20, 110, 120, 35);
		TransferButton.addActionListener(this);
		
		this.add(TransferButton);
	}
	
	private void initInfoButton() {	
		InfoButton = new JButton("Personal Info");
		InfoButton.setBounds(20, 160, 120, 35);
		InfoButton.addActionListener(this);
		
		this.add(InfoButton);
	}
	
	private void initCloseButton() {	
		CloseButton = new JButton("Close Account");
		CloseButton.setBounds(20, 210, 120, 35);
		CloseButton.addActionListener(this);
		
		this.add(CloseButton);
	}
	
	private void initLogoutButton() {	
		logoutButton = new JButton("Log Out");
		logoutButton.setBounds(20, 260, 120, 35);
		logoutButton.addActionListener(this);
		
		this.add(logoutButton);
	}
	
	private void initwelcomeMessage() {
		welcomeMessage  = new JLabel("");
		welcomeMessage.setBounds(150, 10, 300, 35);
		
		this.add(welcomeMessage);
	}
	
	private void initInfoLabel() {
		infoLabel  = new JLabel("");
		infoLabel.setBounds(150, 50, 300, 35);
		
		this.add(infoLabel);
	}
	
	/*
	 * HomeView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The HomeView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the HomeView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if (source.equals(DepositButton)) {
			manager.sendBankAccount(account, "deposit");
			manager.switchTo(ATM.DEPOSIT_VIEW);
		}
		else if (source.equals(WithdrawlButton)) {
			manager.sendBankAccount(account, "withdraw");
			manager.switchTo(ATM.WITHDRAWL_VIEW);
		}
		else if (source.equals(TransferButton)) {
			manager.sendBankAccount(account, "transfer");
			manager.switchTo(ATM.TRANSFER_VIEW);
		}
		else if (source.equals(InfoButton)) {
			manager.sendBankAccount(account, "info");
			manager.switchTo(ATM.INFORMATION_VIEW);
		}
		else if (source.equals(CloseButton)) {
			manager.closeAccount();
			manager.switchTo(ATM.LOGIN_VIEW);
		}
		else if (source.equals(logoutButton)) {
			int choice = JOptionPane.showConfirmDialog(null,"Are you sure you would like to logout?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (choice == 0) {
				manager.switchTo(ATM.LOGIN_VIEW);
			}
			else {
				manager.switchTo(ATM.HOME_VIEW);
			}
		}
		else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
	}
}