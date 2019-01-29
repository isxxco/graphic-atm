package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import controller.ViewManager;
import model.BankAccount;

@SuppressWarnings("serial")
public class HomeView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private BankAccount account;
	
	private JButton logOutButton;
	private JButton DepositButton;
	private JButton WithdrawButton;
	private JButton TransferButton;
	private JButton InfoButton;
	private JButton CloseButton;


	
	private JLabel welcomeText;
	private JLabel InfoText;

	/**
	 * Constructs an instance (or objects) of the HomeView class.
	 * 
	 * @param manager
	 */
	
	public HomeView(ViewManager manager) {
		super();
		this.manager = manager;
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the HomeView components.
	 */
	
	private void initialize() {
		Box firstRow = new Box(BoxLayout.X_AXIS);
		welcomeText = new JLabel("");
		firstRow.add(welcomeText);
		firstRow.add(Box.createHorizontalStrut(40));
		logOutButton = new JButton("Log Out");
		logOutButton.addActionListener(this);
		firstRow.add(logOutButton);
		this.add(firstRow);
		
		Box secondRow = new Box(BoxLayout.X_AXIS);
		InfoText = new JLabel("");
		secondRow.add(InfoText);
		this.add(secondRow);
		this.add(new Box(BoxLayout.X_AXIS));
		
		this.add(Box.createVerticalStrut(50));
		Box thirdRow = new Box(BoxLayout.X_AXIS);
		DepositButton = new JButton("Deposit");
		DepositButton.addActionListener(this);
		thirdRow.add(DepositButton);
		WithdrawButton = new JButton("Withdraw");
		WithdrawButton.addActionListener(this);
		thirdRow.add(WithdrawButton);
		TransferButton = new JButton("Transfer");
		TransferButton.addActionListener(this);
		thirdRow.add(TransferButton);
		this.add(thirdRow);
		
		this.add(Box.createVerticalStrut(50));
		Box lastRow = new Box(BoxLayout.X_AXIS);
		InfoButton = new JButton("View/Edit Personal Information");
		InfoButton.addActionListener(this);
		lastRow.add(InfoButton);
		this.add(lastRow);
		
		this.add(Box.createVerticalStrut(50));
		Box CloseRow = new Box(BoxLayout.X_AXIS);
		CloseButton = new JButton("Close Account");
		CloseButton.addActionListener(this);
		CloseRow.add(CloseButton);
		this.add(CloseRow);
		this.add(Box.createVerticalGlue());
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
		if(source.equals(logOutButton)) {
			this.account = null;
			manager.logOut();	
		}
		
		else if (source.equals(DepositButton)) {
			manager.switchTo(ATM.DEPOSIT_VIEW);
		}
		else if (source.equals(WithdrawButton)) {
			manager.switchTo(ATM.WITHDRAW_VIEW);
		}
		
		else if (source.equals(TransferButton)) {
			manager.switchTo(ATM.TRANSFER_VIEW);		
		}
		else if (source.equals(InfoButton)) {
			manager.switchTo(ATM.INFORMATION_VIEW);
		}
		else if (source.equals(CloseButton)) {
			int choice = JOptionPane.showConfirmDialog(
					this, "Are you sure?",
					"Close Account",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE
				);

				if (choice == 0) {
					manager.closeAccount();
				}

		}

	}
		
	public void setAccount(BankAccount account) {
		this.account = account;
		if (this.account != null) {
			String welcome = String.format("Welcome %s %s!", account.getUser().getFirstName(), account.getUser().getLastName());
			welcomeText.setText(welcome);
			
			String info = String.format("Account Number: %d, Balance: $%.2f", account.getAccountNumber(), account.getBalance());
			InfoText.setText(info);
		} 
	}
}