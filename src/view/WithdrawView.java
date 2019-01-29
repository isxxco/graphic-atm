package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ViewManager;

@SuppressWarnings("serial")
public class WithdrawView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	
	private JButton BackButton;
	private JButton WithdrawButton;

	private JLabel errorMessageLabel;
	private JLabel welcomeText;
	private JTextField InputText;
	private JLabel statusText;

	/**
	 * Constructs an instance (or objects) of the HomeView class.
	 * 
	 * @param manager
	 */
	
	public WithdrawView(ViewManager manager) {
		super();
		this.manager = manager;
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		initialize();
	}

	private void initialize() {
		this.add(Box.createVerticalStrut(80));
		Box firstRow = new Box(BoxLayout.X_AXIS);
		welcomeText = new JLabel("How much would you like to withdraw?");
		firstRow.add(welcomeText);
		this.add(firstRow);
		
		this.add(Box.createVerticalStrut(100));
		Box secondRow = new Box(BoxLayout.X_AXIS);
		InputText = new JTextField(75);
		secondRow.add(InputText);
		this.add(secondRow);
		
		this.add(Box.createVerticalStrut(30));
		Box thirdRow = new Box(BoxLayout.X_AXIS);
		WithdrawButton = new JButton("Withdraw");
		WithdrawButton.addActionListener(this);
		thirdRow.add(WithdrawButton);
		this.add(Box.createHorizontalStrut(0));
		BackButton = new JButton("Return Home");
		BackButton.addActionListener(this);
		thirdRow.add(BackButton);
		this.add(thirdRow);
		
		Box fourthRow = new Box(BoxLayout.X_AXIS);
		statusText = new JLabel("");
		fourthRow.add(statusText);
		this.add(fourthRow);
		
		Box fithRow = new Box(BoxLayout.X_AXIS);
		this.errorMessageLabel = new JLabel();
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		fithRow.add(errorMessageLabel);
		this.add(fithRow);
		this.add(Box.createVerticalStrut(300));


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
		if(source.equals(BackButton)) {
			manager.switchTo(ATM.HOME_VIEW);
			this.removeAll();
			this.initialize();
		}
		else if (source.equals(WithdrawButton)) {
			String amountString = this.InputText.getText();
			try  {  
			    double amount = Double.parseDouble(amountString);
			    if (manager.withdraw(amount)) {
			    	manager.switchTo(ATM.HOME_VIEW);
					this.removeAll();
					this.initialize();
			    }
			    else {
					errorMessageLabel.setText("Invalid withdraw amount");  
			    }
		    }
			  catch(NumberFormatException nfe)  
			  {
				this.errorMessageLabel.setText("Please input a signle number ONLY");  
			  }  
		}
	}
}