import java.util.Scanner;
import java.util.HashMap;


public class main {
	//
	// Functions
	//
	
	static void printMenu() {
		System.out.println("Customer and Sales System \n "
					+ "1. Enter Customer Information \n "
					+ "2. Generate Customer data file \n "
					+ "3. Report on total Sales \n "
					+ "4. Check for fraud in sales data \n "
					+ "9. Quit\n "
					+ "Enter menu option (1-9)");
	}
	
	static void enterCustomerInfo() {
		Scanner input = new Scanner(System.in);

		System.out.println("Enter your first Name: ");
		String firstName = input.nextLine();

		System.out.println("Enter your Last Name: ");
		String lastName = input.nextLine();

		System.out.println(" Enter the city you are from: ");
		String city = input.nextLine();
	
		while (true) {
			System.out.println("Enter Postal Code: ");
			String postalCode = input.nextLine();
			
			if (postalCode.length() == 6 && validatePostalCode(postalCode)) {
				break;
			}
			else if (postalCode.length() < 6) {
				System.out.println("Invalid Postal Code, please try again.");
			}

		}
		while (true) {
			System.out.println("Enter Credit Card Number: ");
			String creditCard = input.nextLine(); 

			String space = creditCard.replaceAll("\\s+ " , "");

			if (creditCard.length() == 12 && validateCreditCard(creditCard)) {
				break;
			}
			else if (creditCard.length() < 12 || creditCard.length() > 12 ) {
				System.out.println("Invalid Credit Card, Please try again");
			
			}
			
		}
		information[0] = firstName;
        information[1] = lastName;
        information[2] = city;
        information[3] = postalCode;
        information[4] = creditCard;

	}
	
		

	
	
	
	static boolean validatePostalCode(String enterCustomerInfo) {
		return true;
	}

	static boolean validateCreditCard( String creditCard) {
		return true;
	}

	
	static void generateCustomerDataFile() {
		
	}
	
	static void reportSalesData() {
		
	}
	
	static void calculatePercentage() {
		
	}
	
	static void generateGraph() {
		
	}
	
	static void fraudCheck() {
		
	}
	
	static void generateReport() {
		
	}
	
	//####################################################################
	//#       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         #
	//####################################################################

	static void initiateHashMaps(HashMap<String, Integer> amount) {
		amount.put("1", 0);
		amount.put("2", 0);
		amount.put("3", 0);
		amount.put("4", 0);
		amount.put("5", 0);
		amount.put("6", 0);
		amount.put("7", 0);
		amount.put("8", 0);
		amount.put("9", 0);
	}
	
	
	
	//####################################################################
	//#                            MAIN PROGRAM                          #
	//#           DO NOT EDIT ANY CODE EXCEPT WHERE INDICATED            #
	//####################################################################
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		HashMap<String, Integer> firstDigitAmount = new HashMap<String, Integer>();
		HashMap<String, Integer> firstDigitPercentage = new HashMap<String, Integer>();
		
		String[] customerInfo = new String[5];
		
		String userInput = "";
		String enterCustomerOption = "1";
		String generateCustomerOption = "2";
		String reportSalesData = "3";
		String checkForFraud = "4";
		String exitCondition = "9";
		
		//Set up the 'dictionaries'
		initiateHashMaps(firstDigitAmount);
		initiateHashMaps(firstDigitPercentage);
		
		while (!userInput.equals(exitCondition)) {
			printMenu();
			userInput = scanner.nextLine();

			if (userInput.equals(enterCustomerOption)) {
				enterCustomerInfo();
			}
			
			else if (userInput.equals(generateCustomerOption)) {
				generateCustomerDataFile();
			}
			
			else if (userInput.equals(reportSalesData)) {
				reportSalesData();
			}
			
			else if (userInput.equals(checkForFraud)) {
				fraudCheck();
			}
			
			else if (userInput.equals(exitCondition)) {
				scanner.close();
				break;
			}
			
			else {
				System.out.println("Please type in a valid option (A number from 1-9)");
			}
		}
		System.out.println("Program Terminated");
	}
}
