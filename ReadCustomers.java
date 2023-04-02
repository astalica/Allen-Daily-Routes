package AllenGUI;

import java.util.*;


import com.opencsv.CSVReader;

import java.io.*;

public class ReadCustomers {
	
	String X;
	String address;
	String name;
	String alias;
	String customerData;
	String latitude;
	String longitude;
	String serviceTime;
	String customerNum;
	
	ArrayList<Customer> customers = new ArrayList<Customer>();

	public ReadCustomers(String fileName) throws FileNotFoundException {
		
		CSVReader reader = new CSVReader(new FileReader(fileName));
		String[] line;
		
		try { 
			  
			
			
			while((line = reader.readNext()) != null) {
				
				
				
				X = line[0];
				address = line[1];
				name = line[2];
				alias = line[3];
				customerData = line[4];
				latitude = line[5];
				longitude = line[6];
				serviceTime = line[7];
				customerNum = line[8];
				
				Customer newCust = new Customer(X,address,name,alias,customerData,latitude,longitude,serviceTime,customerNum);
				customers.add(newCust);
				
			}
		}  
		catch (Exception e)   
		{  
		e.printStackTrace();  
		}  
		
		
			
	}
		
	
	
	public void printCustomers() {
		
		for(int i = 0; i < customers.size(); i++) {
			System.out.println((customers.get(i)).getX());
			System.out.println((customers.get(i)).getAddress());
			System.out.println((customers.get(i)).getName());
			System.out.println((customers.get(i)).getCustomerData());
			System.out.println((customers.get(i)).getLatitude());
			System.out.println((customers.get(i)).getLongitude());
			System.out.println((customers.get(i)).getServiceTime());
			System.out.println((customers.get(i)).getCustomerNum());
		}
		
	}
	
	public static Customer findCustomer(ArrayList<Customer> customerList, String customerNumber) {
		
		for(Customer customer : customerList) {
			
			if(customer.getCustomerNum().equals(customerNumber)) {
				
				return customer;
				
			}
				
			
			
		}
		
		return customerList.get(0);
		
	}
	
	public ArrayList<Customer> getCustomerList(){
		
		return customers;
		
	}
	
}
