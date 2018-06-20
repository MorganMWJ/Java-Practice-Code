package accounts.model;

public interface AccountI {
	public double getBalance();
	public void deposit(double anAmount);
	public void withdraw(double anAmount);
}
