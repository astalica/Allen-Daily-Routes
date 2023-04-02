package AllenGUI;

import java.util.*;
import java.time.*;
import com.opencsv.CSVWriter;
import java.io.*;

public class AllenDailyRoutes {
	
	static ArrayList<String> customerNumbers = new ArrayList<String>();
	static String route;
	static String fileName = HelloWorld.databasePathField.getText();
	static LocalDate date = LocalDate.now();;
	static String filePath = HelloWorld.fileOutputPathField.getText();
		
		
		public static void routes() throws FileNotFoundException {
	
		
			route = HelloWorld.routeNumberField.getText();
			ReadCustomers customerList = new ReadCustomers(fileName);
		
	
		
		try {
			
			if(date.getDayOfWeek().getValue() == 5) {
				
				date = date.plusDays(3);
				
			}else {
				
				date = date.plusDays(1);
				
			}
			
			filePath = HelloWorld.fileOutputPathField.getText();
			filePath = filePath + "\\" + date.getMonthValue() + "-" + date.getDayOfMonth() + "-" + (date.getYear()-2000) + " RTE " + route + ".csv";
			
			

			WriteCSVFile.makeCSVFile(filePath,customerNumbers,customerList);

			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
