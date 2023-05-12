import java.util.Scanner;
import java.util.HashMap;
import java.io.File;  
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class main extends Application {

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
	
	static void calculatePercentage() {
		
	}
	
	static void generateGraph() {
		launch(); // Launch calls the start function, showing the graph
	}
	
	static void fraudCheck() {
		
	}
	
	static void generateReport() {
		
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
		HashMap<Integer, Integer> firstDigitAmount = salesData();
		HashMap<Integer, Integer> firstDigitPercentage = salesDataPercentages();
		
		String userInput = "";
		String enterCustomerOption = "1";
		String generateCustomerOption = "2";
		String reportSalesData = "3";
		String checkForFraud = "4";
		String exitCondition = "9";
		
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
				generateGraph();
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
