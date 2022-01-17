package BankingApp;

public class Users extends Application{

	private String userLogin;
	private String userPassword;
	private double balance;
	private String userName;
	
public Users() {
	
	userLogin = null;
	userPassword = null;
	balance = 0.00;
	userName = null;
	
}
public Users(String userLogin, String userPassword, int balance, String userName) {
	
	this.userLogin = userLogin;
	this.userPassword = userPassword;
	this.balance = balance;
	this.userName = userName;
	
}
public void setUserLogin(String userLogin) {
	this.userLogin = userLogin;
}
public void setUserPassword(String userPassword) {
	this.userPassword = userPassword;
}
public void setInitialBalance(double balance) {
	if(balance > 0) {
	this.balance = balance;
	}
}
public void setUserName(String userName) {
	this.userName = userName;
}
public void deposit(double addMoney) {
	if(addMoney > 0) {
		balance += addMoney;
	}
}
public void withdraw(double removeMoney) {
	if(removeMoney > 0) {
		balance -= removeMoney;
	}
}
public String getUserLogin() {
	return userLogin;
}
public double getBalance() {
	return balance;
}
public String getUserName() {
	return userName;
}
public String getPassword() {
	return userPassword;
}
public String toString() {
	return ("user login: " + userLogin + " - userPassword: " + userPassword + " - balance: $" + balance + " - Name: " + userName);
}

}

