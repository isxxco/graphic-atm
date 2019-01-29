package controller;

import java.awt.CardLayout;
import java.awt.Container;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import data.Database;
import model.BankAccount;
import view.ATM;
import view.HomeView;
import view.InformationView;
import view.LoginView;

public class ViewManager {
	
	private Container views;				// the collection of all views in the application
	private Database db;					// a reference to the database
	private BankAccount account;			// the user's bank account
	private BankAccount destination;		// an account to which the user can transfer funds
	
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
	
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	 * Routes a login request from the LoginView to the Database.
	 * 
	 * @param accountNumber
	 * @param pin
	 */

	public boolean deposit(double amount) {
		if (account.deposit(amount) == ATM.SUCCESS) {
			this.updateAccount(account);
			HomeView hv = ((HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX]);
			hv.setAccount(account);
			return true;
	    } else {
	    	return false;
	    	}
	}
	
	public boolean withdraw(double amount) {
		if (account.withdraw(amount) == ATM.SUCCESS) {
			this.updateAccount(account);
			HomeView hv = ((HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX]);
			hv.setAccount(account);
			return true;
	    } else {
	    	return false;
	    	}
	}

	public boolean transfer(long accountNum, double amount) {
		destination = db.getAccount(accountNum);
		if (destination == null) {
			return false;
		}
		
		if (account.transfer(destination, amount)==ATM.SUCCESS && db.updateAccount(account) && db.updateAccount(destination)) {
			HomeView hv = ((HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX]);
			hv.setAccount(account);
			return true;
		}
		return false;
	}

	public void login(String accountNumber, char[] pin) {
		try {
			account = db.getAccount(Long.valueOf(accountNumber), Integer.valueOf(new String(pin)));
			
			if (account == null) {
				LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
				lv.updateErrorMessage("Invalid account number and/or PIN.");
			} else {
				if (account.getStatus() == 'N') {
					LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
					lv.updateErrorMessage("Account has been closed, sorry!");
				} else {
					HomeView hv = ((HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX]);
					hv.setAccount(account);
					switchTo(ATM.HOME_VIEW);
					
					LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
					lv.updateErrorMessage("");
				}
			}
		} catch (NumberFormatException e) {
			// ignore
		}
	}
	
	public void logOut() {
		account = null;
		switchTo(ATM.LOGIN_VIEW);
		
	}
	
	/**
	 * Switches the active (or visible) view upon request.
	 * 
	 * @param view
	 */
	
	public void switchTo(String view) {
		((CardLayout) views.getLayout()).show(views, view);
		
		if (view.equals(ATM.INFORMATION_VIEW)) {
			InformationView Iv = ((InformationView) views.getComponents()[ATM.INFORMATION_VIEW_INDEX]);
			Iv.switchingToInformationView(account);

		}
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
	public long maxAccountNumber() throws SQLException {
		return db.maxAccountNumber();
	}
	public void insertAccount_wrapp(BankAccount acc) {
		db.insertAccount(acc);
	}
	public boolean updateAccount(BankAccount account) { 
		if (account.getAccountNumber() == this.account.getAccountNumber()) {
			this.account = account;
			HomeView hv = ((HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX]);
			hv.setAccount(account);
		}
		return db.updateAccount(account);
	}
	public boolean closeAccount() {
		if (db.closeAccount(this.account)) {
			this.logOut();
			return true;
		} else {
			return false;
		}		
	}
}
