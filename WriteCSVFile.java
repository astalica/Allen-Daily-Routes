package AllenGUI;

import com.opencsv.CSVWriter;

import java.util.*;
import java.io.*;

public class WriteCSVFile {
	
	static CSVWriter file;
	

	public static void makeCSVFile(String filePath, ArrayList<String> customerNumbers, ReadCustomers customerList ) {
			
		ArrayList<Customer> customersEntered = new ArrayList<Customer>();
		
		for(String custNum : customerNumbers) {
			
			customersEntered.add(ReadCustomers.findCustomer(customerList.getCustomerList(),custNum));
			
		}
		
		try {
			
			
			file =new CSVWriter(new FileWriter(new File(filePath)));
			
			String[] colName = {"X","Address","name","Alias","Customer Data","latitude","longitude","Service Time","Customer Number"};
			String[] allenAddress = {"X","60 Saginaw Dr, Rochester, NY, 14623", "Allen Associates", " ", " ", " "," "," "," "};
			file.writeNext(colName);
			file.writeNext(allenAddress);
			
			for(int i = 0; i < customersEntered.size();i++) {
				
				String[] line = new String[] {"X",(customersEntered.get(i)).getAddress(),(customersEntered.get(i)).getName(),
						(customersEntered.get(i)).getAlias(),(customersEntered.get(i)).getCustomerData(),(customersEntered.get(i)).getLatitude(),
						(customersEntered.get(i)).getLongitude(),(customersEntered.get(i)).getServiceTime(),(customersEntered.get(i)).getCustomerNum()};
				file.writeNext(line);
				
			}
			
			file.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
