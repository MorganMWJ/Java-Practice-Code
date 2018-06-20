package accounts.view;

import accounts.controller.ControllerI;
import accounts.model.AccountI;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;

import javax.swing.*;

import util.Model;
import util.View;

/**
 * @author Chris Loftus
 */
public class AccountGui extends View implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TextPanel textPanel;

	private ButtonPanel buttonPanel;
	private ControllerI controller;

	private Model account;

	public AccountGui(ControllerI controller, Model account) {
		this.account = account;
		this.controller = controller;

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				buildAccountGui();
			}
		});
	}

	private void buildAccountGui() {
		setTitle("Account GUI");

		buttonPanel = new ButtonPanel();
		add(buttonPanel, BorderLayout.NORTH);

		textPanel = new TextPanel();
		add(textPanel, BorderLayout.CENTER);

		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Register the view with the model.
		// Remember this method was defined in View
		// and is basically addObserver
		registerWithModel(account);
	}

	/**
	 * Our update is simply refreshing the balance by calling the model's
	 * getBalance method
	 */
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Updating interface");
		if (o instanceof AccountI) {
			double balance = ((AccountI) o).getBalance();
			textPanel.balanceField.setText("" + balance);
		}
	}

	public String getAmountFieldValue() {
		return textPanel.inputField.getText();
	}

	// ActionListener methods

	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		controller.processUserAction(action);
	}

	// ////////////////////////////////////////////////////////////
	// ///////////INNER CLASSES
	// Give GUI inner classes package access modifier (instead of private) to
	// allow
	// access by AccountController
	class ButtonPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ButtonPanel() {
			JButton button = new JButton("Deposit");
			button.addActionListener(AccountGui.this);
			add(button);

			button = new JButton("Withdraw");
			button.addActionListener(AccountGui.this);
			add(button);
		}
	}

	class TextPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JTextField inputField;
		JTextField balanceField;

		public TextPanel() {
			setLayout(new GridLayout(2, 2, 5, 5));
			add(new JLabel("Amount: "));
			inputField = new JTextField(8);
			add(inputField);

			add(new JLabel("Balance: "));
			balanceField = new JTextField(8);
			add(balanceField);
		}
	}

}
