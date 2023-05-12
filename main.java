import java.util.Scanner;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class main {
	//
	// Functions
	//
	
	private static String creditCard;
	private static String postalCode;



	static void printMenu() {
		System.out.println("Customer and Sales System \n "
					+ "1. Enter Customer Information \n "
					+ "2. Generate Customer data file \n "
					+ "3. Report on total Sales \n "
					+ "4. Check for fraud in sales data \n "
					+ "9. Quit\n "
					+ "Enter menu option (1-9)");
	}
	
	static void enterCustomerInfo(ArrayList<ArrayList<String>> customerInfomation) throws FileNotFoundException {
		ArrayList<String> storage = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your first Name: ");
		String firstName = scanner.nextLine();

		System.out.println("Enter your last name: ");
		String lastName = scanner.nextLine();

		System.out.println("Enter the city you are from: ");
		String city = scanner.nextLine();

			while (true) {
				System.out.println("Enter Postal Code: ");
				String postalCode = scanner.nextLine();
				
				if (postalCode.length() >= 3 && main.validatePostalCode(postalCode, postalCode)) {
					
					break;
				}
				else if (postalCode.length() < 3 || postalCode.length() > 6) {
					System.out.println("Invalid amount of digits");
				}
			}

			while (true) {
				System.out.println("Enter Credit Card Number: ");
				String creditCard = scanner.nextLine(); 
				String card = "";
				for (int i = 0; i < creditCard.length(); i++) {
					boolean flag = Character.isDigit(creditCard.charAt(i));
					if (flag) {
						card = card + creditCard.charAt(i);
					}
				}
				creditCard = card;

				if (creditCard.length() == 16 && main.validateCreditCard(creditCard)) {
					System.out.println("Credit Card Validated");
					break;
				}
				else if (creditCard.length() < 16 || creditCard.length() > 16 ) {
					System.out.println("Please enter the correct amount of digits for your Credit Card");
				}

				else {
					System.out.println("Invalid Credit Card Number");
				}
			}
				
			
	
			
		storage.add(firstName);
		storage.add(lastName);
		storage.add(city);
		storage.add(postalCode);
		storage.add(creditCard);

		customerInfomation.add(storage);
		}


	
	
	static boolean validatePostalCode(String enterCustomerInfo, String postalCode) throws FileNotFoundException {
		
		try (BufferedReader br = new BufferedReader(new FileReader("postal_codes.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(postalCode.substring(0, 3))) {
                    System.out.println("Postal Code Validated");
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.out.println("Enter a valid postal code");
            return false;
        }
    }


	static boolean validateCreditCard( String creditCard) {		
		int sum = 0;
		boolean alternate = false;
		for (int i = creditCard.length() - 1; i >= 0; i--) {
		int n = Integer.parseInt(creditCard.substring(i, i + 1));
		if (alternate) {
			n *= 2;
			if (n > 9) {
			n = (n % 10) + 1;
			}
		}
		sum += n;
		alternate = !alternate;
		}
		return (sum % 10 == 0);
	
		

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
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		HashMap<String, Integer> firstDigitAmount = new HashMap<String, Integer>();
		HashMap<String, Integer> firstDigitPercentage = new HashMap<String, Integer>();
		ArrayList<ArrayList<String>> customerInfo= new ArrayList<ArrayList<String>>();

		
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
				
				enterCustomerInfo(customerInfo);
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
