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
	
	static void enterCustomerInfo(ArrayList<ArrayList<String>> customerInfo) {
		ArrayList<String> test = new ArrayList<String>();
		test.add("A1");
		test.add("A2");
		test.add("markham");
		test.add("L6B");
		test.add("1234567654");
		customerInfo.add(test);
		System.out.println(customerInfo);
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
	static void generateCustomerDataFile(ArrayList<ArrayList<String>> information) {
		FileWriter writer = null;
		Scanner scanner = new Scanner(System.in);
		try {
			//Get user preferred location and name for file
			System.out.println("Enter file location: ");
			String location = scanner.nextLine();
			System.out.println("Enter file name: ");
			String fileName = scanner.nextLine();
			fileName = fileName + ".csv";
			String path = location + fileName;
			
		    writer = new FileWriter(path);
		    //get the information from the get customer information function to add to .csv file
		    for (int i = 0; i < information.size(); i++) {
		    	for (int j = 0; j < information.get(i).size(); j++) {
		    		if (j == 0) {
		    			System.out.println(information.get(i).get(j));

		    			writer.append(information.get(i).get(j));
		    		}
		    		else {
		    			System.out.println(information.get(i).get(j));
		    			writer.append(", ");
					    writer.append(information.get(i).get(j));
		    		}
		    	}
		    }
		    System.out.println("CSV file is created...");
		//Catch any issues  
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
	static void fraudCheck(HashMap<Integer, Integer> amount) {
		if (amount.get(1) >= 29 && amount.get(1) <= 32) {
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

	
	
	
	//####################################################################
	//#                            MAIN PROGRAM                          #
	//#           DO NOT EDIT ANY CODE EXCEPT WHERE INDICATED            #
	//####################################################################
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		HashMap<Integer, Integer> firstDigitAmount = null;
		HashMap<Integer, Integer> firstDigitPercentage = null;
		ArrayList<ArrayList<String>> customerInfo = new ArrayList<ArrayList<String>>();
		
		
		String userInput = "";
		final String enterCustomerOption = "1";
		final String generateCustomerOption = "2";
		final String reportSalesData = "3";
		final String checkForFraud = "4";
		final String exitCondition = "9";
		
		while (!userInput.equals(exitCondition)) {
			printMenu();
			userInput = scanner.nextLine();
			switch (userInput) {
				case enterCustomerOption:
					enterCustomerInfo(customerInfo);
					break;
				case generateCustomerOption:
					generateCustomerDataFile(customerInfo);
					break;
				case reportSalesData:
					reportSalesData();
					break;
				case checkForFraud:
					fraudCheck(firstDigitPercentage);
					break;
				case exitCondition:
					scanner.close();
					break;
				default:
					System.out.println("Please type in a valid option (A number from 1-9)");
					
			}
		}
		System.out.println("Program Terminated");
	}
}
