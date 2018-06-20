package accounts.controller;

import accounts.model.AccountI;
import accounts.view.AccountGui;
import util.Model;

/**
 * @author Chris Loftus
 */
public class AccountController implements ControllerI {
	
	private AccountI model;
	private AccountGui view = null;

	public AccountController(Model model) {
		this.model = (AccountI) model;

		// Create the GUI
		this.view = new AccountGui(this, model);
	}

	@Override
	public void processUserAction(String action) {
		double amount = Double.valueOf(view.getAmountFieldValue());
		if (action.equals("Withdraw")) {
			System.out.println("Withdraw an amount : " + amount);
			model.withdraw(amount);
		} else if (action.equals("Deposit")) {
			System.out.println("Deposit an amount : " + amount);
			model.deposit(amount);
		}
	}

}
