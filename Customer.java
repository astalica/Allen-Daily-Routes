package AllenGUI;

public class Customer {

	String X;
	String address;
	String name;
	String alias;
	String customerData;
	String latitude;
	String longitude;
	String serviceTime;
	String customerNum;
	
	public Customer(String X, String address, String name, String alias, String customerData, String latitude, 
			String longitude, String serviceTime, String custNum) {
		
		this.X = X;
		this.address = address;
		this.name = name;
		this.alias = alias;
		this.customerData = customerData;
		this.latitude = latitude;
		this.longitude = longitude;
		this.serviceTime = serviceTime;
		this.customerNum = custNum;
		
	}
	
	public String getX() {
		return X;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public String getCustomerData() {
		return customerData;
	}

	public String getLatitude() {
		return latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public String getServiceTime() {
		return serviceTime;
	}
	
	public String getCustomerNum() {
		return customerNum;
	}
	
}
