import java.util.Scanner;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList; 
import java.io.File;  
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
 
public class main extends Application {

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
	
	/*
	 * Generates a salesdata.csv file where the use would like. The file contains the distribution of the numbers 
	 */
	static void reportSalesData()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the path to save the file");
		String path = scanner.nextLine();
		
		HashMap<Integer, Integer> toUse = salesDataPercentages();
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path + "\\salesdata.csv"));
					
			for (int i = 1; i < toUse.size(); i++)
			{
				writer.write(i + ": " + toUse.get(i) + "%\n");
			}
			
			writer.close();
			
        } catch (IOException e) {
            System.err.println("Invalid path");
        }
	}
	
	static void generateGraph() {
		launch(); // Launch calls the start function, showing the graph
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
	
	//####################################################################
	//#       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         #
	//####################################################################
	
	/**
	 * 
	 * @return Creates a graph based on the data in salesDataPercentages and shows the window
	 */
	@Override public void start(Stage stage) {
		
		// Setting up the graph
        stage.setTitle("Distribution between numbers in dataset");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Distribution of numbers");
        xAxis.setLabel("Number");       
        yAxis.setLabel("Percentage of appearance");
 
        XYChart.Series series1 = new XYChart.Series();
        HashMap<Integer, Integer> toUse = salesDataPercentages(); // Creating a copy of the data set to use        
        for (int i = 1; i <= toUse.size(); i++) // Loop over the dictionary, adding a bar for each number there is (total 9 bars)
        {
        	double toAdd = toUse.get(i);
            series1.getData().add(new XYChart.Data(String.valueOf(i), toAdd));
        }
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show(); // Show the actual graph
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
			}
			
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}	
	/**
	 * 
	 * @return Returns a HashMap where the key is a number and the value is the percentage of times the number appears in the data set
	 */
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
	
	//####################################################################
	//#                            MAIN PROGRAM                          #
	//#           DO NOT EDIT ANY CODE EXCEPT WHERE INDICATED            #
	//####################################################################
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		ArrayList<ArrayList<String>> customerInfo = new ArrayList<ArrayList<String>>();
		HashMap<Integer, Integer> firstDigitAmount = salesData();
		HashMap<Integer, Integer> firstDigitPercentage = salesDataPercentages();
		
		
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
					System.out.println("Close the graph to write a sales data report file.");

					generateGraph();
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
