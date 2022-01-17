package BankingApp;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {
	
	public static boolean containsUser(ArrayList<Users> userDatabase, String nameSearch) {
		for(int i=0;i<userDatabase.size();i++) {
			if(nameSearch.equalsIgnoreCase(userDatabase.get(i).getUserLogin())) {
				return true;
			}
			
		}
		return false;
	}
	public static Users findUser(ArrayList<Users> database, String findName) {
		for(int i=0;i<database.size();i++) {
			if(findName.equalsIgnoreCase(database.get(i).getUserLogin())) {
				return database.get(i);
			}
		}
		System.out.println("User was not found. Please try again...");
		return null;
	}

	public static void main(String[] args) {
		//list of taken usernames
		ArrayList<Users> userDatabase = new ArrayList<Users>();
		Users currentUser = new Users();
		boolean running = true;
		Scanner input = new Scanner(System.in);
		
		System.out.println(" - Bank of Darek -\n");
		
		try {
		//Menu loop for account login/creation
		while(running) {
			
			System.out.println("1. Login");
			System.out.println("2. Create an account");
			System.out.println("3. Exit application");
			
			String response = input.nextLine();
			int x = Integer.parseInt(response);
			
			switch(x) {
			
			case 1:
				System.out.println("Username: ");
				String userNameInput = input.nextLine();
				System.out.println("Password: ");
				String userPassInput = input.nextLine();
				
				if(containsUser(userDatabase, userNameInput)) {
					Users two = findUser(userDatabase, userNameInput);
				if(two.getPassword().equals(userPassInput)) {
					System.out.println("Welcome " + two.getUserName() + ",\n ");
					currentUser = two;
					running = false;
				} else {
					System.out.println("Incorrect password. Please try again...");
				}
					
				} else {
					System.out.println("User not found. Please try again...");
				}
				
				break;
			case 2:
				boolean creatingName = true;
				boolean creatingLogin = true;
				boolean creatingPassword = true;
				Users one = new Users();
				
				while(creatingName) {
					
					System.out.println("Please enter your first name: ");
					String nameInput = input.nextLine();
				
				if(nameInput.matches("[a-zA-Z]+")) {
					one.setUserName(nameInput);
					creatingName = false;
				} else {
					System.out.println("That is not a valid name. Name's should only contains letters.");
					}
				}
				while(creatingLogin) {
					System.out.println("Please enter a user login between 4-16 characters: ");
					String loginInput = input.nextLine();
					
					if(loginInput.length() >= 4 && loginInput.length() <= 16 && !containsUser(userDatabase, loginInput)) {
						one.setUserLogin(loginInput);
						creatingLogin = false;
					} else if(loginInput.length() < 4 || loginInput.length() > 16) {
						System.out.println("User login is not between 4-16 characters. Please try again...");
					} else {
						System.out.println("User login is not unique. Please try again...");
					}
				}
				while(creatingPassword) {
					System.out.println("Please enter a password between 6-18 characters: ");
					String passwordInput = input.nextLine();
					System.out.println("Please re-enter your password: ");
					String passwordInput2 = input.nextLine();
					
					if(passwordInput.length() >= 6 && passwordInput.length() <= 18 && passwordInput.equals(passwordInput2)) {
						one.setUserPassword(passwordInput);
						creatingPassword = false;
					} else if(passwordInput.length() < 6 || passwordInput.length() > 18) {
						System.out.println("User login is not between 6-18 characters. Please try again...");
					} else {
						System.out.println("Passwords don't match. Please try again...");
					}
				}
				userDatabase.add(one);
				System.out.println("User has been created.\n");
				break;
			case 3:
				System.out.println("Thank you and have a great day!");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option. Enter either '1', '2', or '3'");
				break;
			}
			
		}
		
			boolean loggedIn = true;
			//Menu loop for account
			while(loggedIn) {
				System.out.println(" - " + currentUser.getUserName() + "'s account - \n");
				System.out.println("1. Account balance");
				System.out.println("2. Make a deposit");
				System.out.println("3. Make a withdrawal");
				System.out.println("4. Exit application");
				
				String menuInput = input.nextLine();
				int y = Integer.parseInt(menuInput);
				
				switch(y) {
				
				case 1:
					System.out.println("Your current balance is " + currentUser.getBalance());
					System.out.println();
					break;
				case 2:
					System.out.println("Please enter an amount you'd like to deposit: ");
					String depositInput = input.nextLine();
					if(depositInput.matches("[0-9]{1,13}(\\.[0-9]*)?")) {
						double depositAmount = Double.parseDouble(depositInput);
						currentUser.deposit(depositAmount);
						System.out.println("You've made a deposit of $" + depositAmount);
						System.out.println("Your new balance is $" + currentUser.getBalance());
					} else {
						System.out.println("That is not a valid amount to deposit. Please try again...");
					}
					break;
				case 3:
					System.out.println("Please enter an amount you'd like to withdraw: ");
					String withdrawInput = input.nextLine();
					if(withdrawInput.matches("[0-9]{1,13}(\\.[0-9]*)?")) {
						double withdrawAmount = Double.parseDouble(withdrawInput);
						if(withdrawAmount <= currentUser.getBalance()) {
							currentUser.withdraw(withdrawAmount);
							System.out.println("You've made a withdrawal of $" + withdrawAmount);
							System.out.println("Your new balance is $" + currentUser.getBalance());
						} else {
							System.out.println("Insufficient funds...");
						}
						
					} else {
						System.out.println("Invalid option. Returning to main menu...");
					}
					break;
				case 4:
					System.out.println("Thank you and have a great day!");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid option. Enter either '1', '2', '3', or '4'");
					break;
				}
			}
		
		} catch(NumberFormatException e) {
	System.out.println("That's not an integer value. Please try again...");
		}
	}
}