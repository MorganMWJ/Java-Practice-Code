package accounts.model;

import util.Model;

/**
 * @author Chris Loftus
 */
public class Account extends Model implements AccountI {

	
	private double balance = 0.0;

	public Account(double initialBalance) {
		setBalance(initialBalance);
	}

	private void setBalance(double anAmount) {
		balance = 0.0;
		deposit(anAmount);
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public void deposit(double anAmount) {
		balance += anAmount;
		System.out.println("Increased balance to " + balance);
		notifyChanged(new Double(balance));
	}

	@Override
	public void withdraw(double anAmount) {
		balance -= anAmount;
		notifyChanged(new Double(balance));
	}
}
