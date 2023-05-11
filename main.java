import java.util.Scanner;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList; 

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
		
	}
	
	static void validatePostalCode() {
		
	}
	
	static void validateCreditCard() {
		
	}
	
	
	/*
	 * This function takes in the information from 
	 * the user input and outputs and .csv file with 
	 * the name and path of the users choice
	 */
	static void generateCustomerDataFile(String[] information) {
		FileWriter writer = null;
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter file location: ");
			String location = scanner.nextLine();
			System.out.println("Enter file name: ");
			String fileName = scanner.nextLine();
			String path = location + fileName;
			
		    writer = new FileWriter(path);
		    writer.append(information[0]);
		    writer.append(", ");
		    writer.append(information[1]);
		    writer.append(", ");
		    writer.append(information[2]);
		    writer.append(", ");
		    writer.append(information[3]);
		    writer.append(", ");
		    writer.append(information[4]);
		    writer.append("\n");
		    
		    System.out.println("CSV file is created...");
		    scanner.close();
		  } catch (IOException e) {
		     e.printStackTrace();
		  } finally {
		  try {
		      writer.flush();
		      writer.close();
		        } catch (IOException e) {
		      e.printStackTrace();
		        }
		  }
	}
	
	static void reportSalesData() {
		
	}
	
	static void calculatePercentage() {
		
	}
	
	static void generateGraph() {
		
	}
	
	/*
	 * Checks the percentage in the first digit percentage 
	 * hashmap to see if it looks robotly generated 
	 * (percentage of digit 1 is between 29% and 32%)
	 */
	static void fraudCheck(HashMap<String, Integer> amount) {
		if (amount.get("1") >= 29 && amount.get("1") <= 32) {
			System.out.println("No Fraud Detected! \n");
		}
		else {
			System.out.println("Fraud Detected! \n");
			
		}
	}
	
	static void generateReport() {
		
	}
	
	//####################################################################
	//#       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         #
	//####################################################################

	/*
	 * This function just sets up the hashmap so it can be edited later on in the code. 
	 */
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
				generateCustomerDataFile(customerInfo);
			}
			
			else if (userInput.equals(reportSalesData)) {
				reportSalesData();
			}
			
			else if (userInput.equals(checkForFraud)) {
				fraudCheck(firstDigitPercentage);
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
