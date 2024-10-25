package project_bankApplication;

import java.util.* ;

import java.sql.* ;

public class BankApplication extends Exception {

	private static final Scanner scanner=new Scanner(System.in) ;
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Bank");
		boolean running=true ;
		while(running) {
			System.out.println("\nPlease choose an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            
            int choice=scanner.nextInt() ;
            try {
            	switch(choice) {
            		case 1:
            			registerUser() ;
            			break ;
            		case 2:
            			userLogin() ;
            			break ;
            		case 3:
            			adminLogin();
            			break ;
            		case 4:
            			running=false ;
            			System.out.println("Thank You for using the Bank Application");
            			break ;
            		default:
            			System.out.println("Invalid option, Please try again.");
            	}
            }
            catch (Exception e) {
            	System.out.println("An error occurred: "+e.getMessage());
            }
		}
	}
	
	private static void registerUser() throws Exception{
		System.out.println("Enter your UserName:");
		String username=scanner.nextLine();
		System.out.println("Enter your password:");
		String password=scanner.nextLine();
		System.out.println("Enter your email:");
		String email=scanner.nextLine();
		System.out.println("Enter your Phone Number:");
		String phone=scanner.nextLine();
		
		boolean success=UserRegistration.registerUser(username,password,email,phone);
		if(success) {
			System.out.println("User registered Successfully");
		}else {
			System.out.println("Registration failed!\nUsername or email already exists");
		}
	}
	
	private static void userLogin() throws Exception{
		System.out.println("Enter your Username:");
		String username=scanner.nextLine();
		System.out.println("Enter your password:");
		String password=scanner.nextLine();
		
		boolean success=UserLogin.login(username,password);
		if(success) {
			System.out.println("Login Successful");
			userOperations(username);
		}else {
			System.out.println("Invalid Username or Password.\n Try again.");
		}
	}
	
	private static void adminLogin() throws Exception{
		System.out.println("Enter admin unsername:");
		String adminUsername=scanner.nextLine();
		System.out.println("Enter admin password:");
		String adminPassword=scanner.nextLine();
		
		if(adminUsername.equals("admin") && adminPassword.equals("adminPass")) {
			System.out.println("Admin login Successful");
			adminOperations();
		}else {
			System.out.println("Invalid admin Credentials.");
		}
	}
	
	private static void userOperations(String username) throws Exception{
		boolean loggedIn=true ;
		while(loggedIn) {
			System.out.println("Please choose and Option:");
			System.out.println("1. Deposit");
			System.out.println("2. Withdrwal");
			System.out.println("3. Check Balance");
			System.out.println("4. Logout");
			
			int choice=scanner.nextInt();
			scanner.nextLine() ;
			
			try {
				switch(choice) {
				case 1:
					System.out.println("Enter deposit Amount:");
					double depositAmount=scanner.nextDouble();
					scanner.nextLine() ;
					TransactionService.deposit(username,depositAmount) ;
					break ;
				case 2:
					System.out.println("Enter withdrwal amount:");
					double withdrwalAmount=scanner.nextDouble();
					scanner.nextLine() ;
					TransactionService.withdraw(username,withdrwalAmount);
					break ;
				case 3:
					double balance=UserOperations.checkBalance(username);
					System.out.println("Your current balance: "+balance+" rupees.");
					break ;
				case 4: 
					loggedIn=false ;
					System.out.println("Logging Out...");
					break ;
				default:
					System.out.println("Invalid Option.\nPlease try again");
				}
			}catch(Exception e) {
				System.out.println("An error occured. "+ e.getMessage()) ;
			}
		}
	}
	private static void adminOperations() throws Exception{
		boolean adminLoggedIn=true ;
		while(adminLoggedIn) {
			System.out.println("Please select the operation type:");
			System.out.println("1. View all users.");
			System.out.println("2. View Transactions.");
			System.out.println("3. Logout");
			
			int choice=scanner.nextInt();
			scanner.nextLine();
			
			try {
				switch(choice) {
					case 1:
						AdminOperations.viewAllUsers();
						break ;
					case 2:
						AdminOperations.viewAllTransactions();
						break ;
					case 3:
						adminLoggedIn=false ;
						System.out.println("Admin Logging out...");
						break ;
					default:
						System.out.println("Invalid Option.\nPlease try again.");
				}		
			}catch(Exception e) {
				System.out.println("An error occurred: "+e.getMessage());
			}
		}
	}
}
