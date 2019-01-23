package controller;

import java.awt.CardLayout;
import java.awt.Container;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import data.Database;
import model.BankAccount;
import view.ATM;
import view.LoginView;

public class ViewManager {
	
	private Container views;				// the collection of all views in the application
	private Database db;					// a reference to the database
	private BankAccount account;			// the user's bank account
	//private BankAccount destination;		// an account to which the user can transfer funds

	/**
	 * Constructs an instance (or object) of the ViewManager class.
	 * 
	 * @param layout
	 * @param container
	 */
	
	public ViewManager(Container views) {
		this.views = views;
		this.db = new Database();
	}
	
	public void sendBankAccount(BankAccount account, String view) {
		switch (view) {
		case "home":
			view.HomeView hv = ((view.HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX]);
			hv.setMessage(account);
			break;
		case "deposit":
			view.DepositView dv = ((view.DepositView) views.getComponents()[ATM.DEPOSIT_VIEW_INDEX]);
			dv.setMessage(account);
			break;
		case "withdraw":
			view.WithdrawalView wv = ((view.WithdrawalView) views.getComponents()[ATM.WITHDRAWL_VIEW_INDEX]);
			wv.setMessage(account);
			break;
		case "transfer":
			view.TransferView tv = ((view.TransferView) views.getComponents()[ATM.TRANSFER_VIEW_INDEX]);
			tv.setMessage(account);
			break;
		case "info":
			view.InformationView iv = ((view.InformationView) views.getComponents()[ATM.INFORMATION_VIEW_INDEX]);
			iv.setView(account);
			break;
		case "manager":
			this.account = account;
			break;
	}
	}
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	 * Routes a login request from the LoginView to the Database.
	 * 
	 * @param accountNumber
	 * @param pin
	 */
	
	public void login(String accountNumber, char[] pin) {
		try {
			account = (db.getAccount(Long.valueOf(accountNumber), Integer.valueOf(new String(pin))));
			
			if (account == null || account.getStatus() == 'N') {
				LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
				lv.updateErrorMessage("Invalid account number and/or PIN.");
			} 
			else {
				sendBankAccount(account, "home");
				sendBankAccount(account, "withdraw");
				sendBankAccount(account, "transfer");
				sendBankAccount(account, "info");
				switchTo(ATM.HOME_VIEW);
				
				LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
				lv.updateErrorMessage("");
			}
		} catch (NumberFormatException e) {
			// ignore
		}
	}
	
	/**
	 * Switches the active (or visible) view upon request.
	 * 
	 * @param view
	 */
	
	public void switchTo(String view) {
		((CardLayout) views.getLayout()).show(views, view);
	}
	
	/**
	 * Routes a shutdown request to the database before exiting the application. This
	 * allows the database to clean up any open resources it used.
	 */
	
	public void shutdown() {
		try {			
			int choice = JOptionPane.showConfirmDialog(
				views,
				"Are you sure?",
				"Shutdown ATM",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			
			if (choice == 0) {
				db.shutdown();
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeAccount() {
		try {			
			int choice = JOptionPane.showConfirmDialog(
				views,
				"Are you sure?",
				"Close your account",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			
			if (choice == 0) {
				db.closeAccount(account);
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public long maxAccountNumber() throws SQLException {
		return db.getMaxAccountNumber();
	}
	public void insertAccountFR(BankAccount acc) {
		db.insertAccount(acc);
	}
	public BankAccount getAccount() {
		return account;
	}
	public int deposit(double amount) {
		return account.deposit(amount);
	}
	public int withdraw(double amount) {
		return account.withdraw(amount);
	}
	public void updateAcc(BankAccount destination) {
		db.updateAccount(account);
		if (destination != null) db.updateAccount(destination);
	}
	public void updateTransAcc() {
		db.updateAccount(account);
	}
	public BankAccount getTransferAccount(long accountNumber) {
		return db.getAccount(accountNumber);
	}
	public int transfer(BankAccount destination, double amount) {
		return account.transfer(destination, amount);
	}
}