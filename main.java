import java.util.Scanner;
import java.util.HashMap;
import java.io.File;  
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	
	static void generateCustomerDataFile() {
		
	}
	
	static void reportSalesData() {
		
		/*
		for(int i = 1; i < salesData().size(); i++)
		{
			System.out.println(i + ": " + salesData().get(i));
		}
		*/
		
		for(int i = 1; i <= salesDataPercentages().size(); i++)
		{
			System.out.println(salesDataPercentages().get(i));
		}
		
		//salesData();
		
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
	
	
	/**
	 * 
	 * @return Returns a HashMap where the key is a number and the value is the amount of times that number appears in the data set
	 */
	static HashMap<Integer, Integer> salesData()
	{
		// Declare a dictionary for us to add to in the function
		HashMap<Integer, Integer> toReturn = new HashMap<Integer, Integer>();
		
		// Create the file object
		File salesDataFile = new File("sales.csv");
		
		// Create the reader
		Scanner fileReader;
		
		try {
			fileReader = new Scanner(salesDataFile);
			
			// Read through the file, getting the first character of the sales information and adding it to the dictionary
			while(fileReader.hasNextLine())
			{
				String readLine = fileReader.nextLine();
				Integer firstChar = readLine.split(",")[1].toCharArray()[0] - '0'; // First character of the Sales Data column
				
				toReturn.merge(firstChar, 1, Integer::sum);	

				
				/*
				// If the dictionary does not contain the key, create a new entry and set the value to 1
				if (!toReturn.containsKey(firstChar))
				{
					toReturn.put(firstChar, 1);
				}
				else // If it DOES exist, add 1 for the value 
				{
					System.out.println(toReturn.get(firstChar));
					toReturn.put(firstChar, toReturn.get(firstChar) + 1);
				}
				*/
			}
			
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}
	
	static HashMap<Integer, Integer> salesDataPercentages()
	{
		HashMap<Integer, Integer> toReturn = new HashMap<Integer, Integer>();
		for(int i = 1; i < salesData().size(); i++)
		{
			//System.out.println(getTotalValues());
			
			double percentage = (double) salesData().get(i) / getTotalValues() * 100.0;
			toReturn.put(i, (int)Math.round(percentage));
		}
		
		return toReturn;
	}
	
	/**
	 * 
	 * @return The total amount of numbers inside salesData()
	 */
	static int getTotalValues()
	{
		int total = 0;
		
		// Get the value of each key inside the dictionary and add to a variable
		for(int i = 1; i < salesData().size(); i++)
		{
			total += salesData().get(i);
		}
		
		// Return the total
		return total;
	}
	
	/**
	 * 
	 * @param str
	 * @return A boolean based on whether the String is numeric
	 */
	public static boolean isNumeric(String str)
	{
		  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
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
