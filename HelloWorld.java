/*
 * Allen Daily Routes
 * 
 * Program made for Allen Associates
 * This produces a GUI with various data input options to make daily route files
 * 
 * Customer Number: A text field in which the user can input a customers number,
 * 					when the enter key or "add customer" button is pressed, the
 * 					text from the field is added to the customerNumbers string array
 * 					list in AllenDailyRoutes.java. At the same time, it invokes the findCustomer method from
 * 					ReadCustomers.java using the data in the field. The name of the 
 * 					customer that is returned from findCustomer is then concatenated
 * 					into the "Customers Added:" text at the bottom of the GUI
 * 
 * Remove Customer: Another text field in which the user can enter a customer number.
 * 					When the "remove customer" button is pressed, the customer number
 * 					in the text field is removed from the customerNumber array list in
 * 					AllenDailyRoutes.java. The "Customers Added:" text is adjusted
 * 					accordingly.
 * 
 * Route Number:    This is a text field for the user to enter the route number. When the 
 * 					"Make File" button is pressed, the 'route' string in AllenDailyRoutes
 * 					is set to the value in this text field
 * 
 * Customers Added: A text object that is updated with customer names as they are added or
 * 					removed
 * 
 * Make File:       When this button is pressed, the routes method from AllenDailyRoutes.java
 * 					is invoked. It uses values from the customerNumber arrayList and route number
 * 					text field to make the daily stops csv file.
 * 
 * Reset: 			When this button is pressed, all values are reset, and the program is
 * 					ready to make another route.
 * 
 * Database Path:   This text field displays the file path of the customer database used.
 * 
 * File Output Path:This text field displays the output path of files made.
 * 
 * @Version October-19-2022
 * @Author Alex Stalica
*/
package AllenGUI;
 
//imports for program
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.apache.poi.sl.usermodel.TextBox;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class HelloWorld extends Application {
	
	//establish program variables
	//													        S:\\Route4Me Database
	static final TextField fileOutputPathField = new TextField("C:\\Users\\brand\\Desktop");
	//													S:\\Route4Me Database\\Allen Daily Stops Database.csv
	static TextField databasePathField = new TextField("D:\\Allen Daily Stops Database.csv");
	static TextField routeNumberField = new TextField();
	static int numCusts = 0;
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
    	
    	//set application window size and title
        primaryStage.setHeight(750);
        primaryStage.setWidth(750);
    	primaryStage.setTitle("Allen Associates Daily Routes");
    	
//-------------------------------------------------------------------------------
    	VBox window = new VBox(10);
    	//establish gridpane for application window
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(100);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        
//-------------------------------------------------------------------------------
       
        //Add Allen Assoc. logo to grid
        //												 S:\\Route4Me Database\\Allen-Assoc-75th-LOGO.png
        /*
        Image allenLogo = new Image(new FileInputStream("C:\\Users\\Morgan\\Pictures\\Allen-Assoc-75th-LOGO.png"));
        ImageView viewAllenLogo = new ImageView();
        viewAllenLogo.setImage(allenLogo);
        viewAllenLogo.setFitHeight(200);
        viewAllenLogo.setFitWidth(200);
        viewAllenLogo.setPreserveRatio(true);
        Group root = new Group(viewAllenLogo);
        grid.add(root, 2, 9);
        */
//-------------------------------------------------------------------------------        
        
        //Establish customer number label and text field
        Label customerNumberLabel = new Label ("Customer Number:");
        customerNumberLabel.setAlignment(Pos.BOTTOM_CENTER);
        grid.add(customerNumberLabel, 0, 1);
        
        TextField customerNumberField = new TextField();
        grid.add(customerNumberField, 0, 2);
        
//-------------------------------------------------------------------------------
        
        //establish remove customer label and text field
        Label removeCustomerLabel = new Label("Remove Customer:");
        grid.add(removeCustomerLabel, 0, 4);
        
        TextField removeCustomerField = new TextField();
        grid.add(removeCustomerField, 0, 5);
        
//-------------------------------------------------------------------------------
        
        //establish customers added text
        Text customersAdded = new Text("Customers Added: \n");
        //grid.add(customersAdded, 0, 11);
        
//-------------------------------------------------------------------------------
        
        //establish actionTarget text for error messages
        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);
        
//-------------------------------------------------------------------------------
        
        //establish Add Customer button
        Button addCustBtn = new Button("Add Customer");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(addCustBtn);
        grid.add(hbBtn1, 0, 3);
        
        //set action for Add Customer Button
        addCustBtn.setOnAction(new EventHandler<ActionEvent>() {
        	
        	@Override
        	public void handle(ActionEvent e) {
        		
        		//make a list of customer from database
        		ReadCustomers customerList = null;
        		try {
                	customerList = new ReadCustomers(databasePathField.getText());
                }catch(Exception e2) {
                	e2.printStackTrace();
                }
        		
        		//if a customer exists with the entered customer number, add to arraylist, add 1 to numCusts, update "Customers Added:", and reset textField
        		if(ReadCustomers.findCustomer(customerList.getCustomerList(),customerNumberField.getText()) != customerList.getCustomerList().get(0)) {
	        		
        			AllenDailyRoutes.customerNumbers.add(customerNumberField.getText());
        			numCusts++;
        			customersAdded.setText(customersAdded.getText().concat(numCusts + ". " + ReadCustomers.findCustomer(customerList.getCustomerList(),customerNumberField.getText()).getName()) + "\n");
	        		customerNumberField.setText("");
	        		actionTarget.setText("");
	        		
	        	//if no customer found, present an error
        		}else {
        			actionTarget.setFill(Color.FIREBRICK);
        			actionTarget.setText("Customer number not valid");
        		}
        		
        		//for debugging
        		for(String custNum : AllenDailyRoutes.customerNumbers) {
        			
        			System.out.println(custNum);
        			
        		}
        		
        		System.out.println();
        		
        	}
        	
        });
        
     //use same logic with button but on enter key press   
	 customerNumberField.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        	
	        	@Override
	        	public void handle(KeyEvent e) {
	        		
	        		if(e.getCode() == KeyCode.ENTER) {
		        		ReadCustomers customerList = null;
		        		try {
		                	customerList = new ReadCustomers(databasePathField.getText());
		                }catch(Exception e2) {
		                	e2.printStackTrace();
		                }
		        		
		        		if(ReadCustomers.findCustomer(customerList.getCustomerList(),customerNumberField.getText()) != customerList.getCustomerList().get(0)) {
			        		
		        			AllenDailyRoutes.customerNumbers.add(customerNumberField.getText());
		        			numCusts++;
		        			customersAdded.setText(customersAdded.getText().concat(numCusts + ". " + ReadCustomers.findCustomer(customerList.getCustomerList(),customerNumberField.getText()).getName()) + "\n");
			        		customerNumberField.setText("");
			        		actionTarget.setText("");
			        		
		        		}else {
		        			
		        			actionTarget.setFill(Color.FIREBRICK);
		        			actionTarget.setText("Customer number not valid");
		        			
		        		}
		        		for(String custNum : AllenDailyRoutes.customerNumbers) {
		        			
		        			System.out.println(custNum);
		        			
		        		}
		        		
		        		System.out.println();
		        		
		        	}
	        	}
	        });
	 
//-------------------------------------------------------------------------------
        
	    //establish remove customer button
        Button removeCustBtn = new Button("Remove Customer");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(removeCustBtn);
        grid.add(hbBtn2, 0, 6);
        
        //set remove customer button action
        removeCustBtn.setOnAction(new EventHandler<ActionEvent>() {
        	
        	@Override
        	public void handle(ActionEvent e) {
        		
        		//loop through arrayList, if number entered is in the list, remove it
        		for(int i = 0; i < AllenDailyRoutes.customerNumbers.size(); i++) {
        			
        			if(AllenDailyRoutes.customerNumbers.get(i).equals(removeCustomerField.getText())) {
        				
        				AllenDailyRoutes.customerNumbers.remove(i);
        				
        			}
        			
        		}
        		
        		//create a customer list
        		ReadCustomers customerList = null;
        		try {
        			
        			customerList = new ReadCustomers(databasePathField.getText());
        			
        		}catch(Exception e2) {
        			
        			e2.printStackTrace();
        			
        		}
        		
        		//establish temporary "Customers Added:" string
        		String string = "Customers Added: \n";
        		
        		//concatenate string with customer names in updated list
        		for(int j = 0; j < AllenDailyRoutes.customerNumbers.size(); j++) {
        			
        			string = string.concat((j+1) + ". " + (ReadCustomers.findCustomer(customerList.getCustomerList(),AllenDailyRoutes.customerNumbers.get(j))).getName() + "\n");
        			
        		}
        		
        		//set customers added text to string
        		customersAdded.setText(string);
        		
        		//reset text field
        		removeCustomerField.setText("");
        		
        		//for debugging
        		for(String num : AllenDailyRoutes.customerNumbers) {
        			
        			System.out.println(num);
        			
        		}
        		
        		System.out.println();
        		
        	}
        	
        });
        
//-------------------------------------------------------------------------------
        
        //establish route number label and text field
        Label routeNumber = new Label("Route number:");
        grid.add(routeNumber, 0, 7);
  
        grid.add(routeNumberField, 0, 8);
        
//-------------------------------------------------------------------------------
        
        //establish database path label and text field
        Label databasePath = new Label("Database Path:");
        grid.add(databasePath, 2, 1);
        
        grid.add(databasePathField, 2, 2);
        
//-------------------------------------------------------------------------------
        
        //establish file output path label and textField
        Label fileOutputPath = new Label("File output:");
        grid.add(fileOutputPath, 2, 4);
        
        grid.add(fileOutputPathField, 2, 5);
        
//-------------------------------------------------------------------------------
        
        //establish submit button
        Button submitButton = new Button ("Make File");
        HBox hbBtn3 = new HBox(10);
        hbBtn3.setAlignment(Pos.CENTER);
        hbBtn3.getChildren().add(submitButton);
        grid.add(hbBtn3, 1, 2);
        
        //set submit button action
        submitButton.setOnAction(new EventHandler<ActionEvent>(){
        	
        	public void handle(ActionEvent e) {
        		
        		//if there is a route number, execute AllenDailyRoutes.routes
        		if(routeNumberField.getText().equals("") == false) {
	        		
        			try {
        				AllenDailyRoutes.date = LocalDate.now();
						AllenDailyRoutes.routes();
						actionTarget.setText("File made!");
						actionTarget.setFill(Color.LIME);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						actionTarget.setText("Error, file not made");
						actionTarget.setFill(Color.FIREBRICK);
						e1.printStackTrace();
					}
        		
        		//present an error if no route number	
        		}else {
        			
        			actionTarget.setFill(Color.FIREBRICK);
        			actionTarget.setText("Error: No route number");
        			
        		}
        	}
        	
        });
        
//-------------------------------------------------------------------------------
        
        //establish reset button
        Button resetButton = new Button ("Reset");
        HBox hbBtn4 = new HBox(10);
        hbBtn4.setAlignment(Pos.CENTER);
        hbBtn4.getChildren().add(resetButton);
        grid.add(hbBtn4, 1, 3);
        
        
        //set reset button action
        resetButton.setOnAction(new EventHandler<ActionEvent>(){
        	
        	public void handle(ActionEvent e) {
        		
        		//reset all values
        		AllenDailyRoutes.customerNumbers.clear();
        		customersAdded.setText("Customers Added: \n");
        		routeNumberField.setText("");
        		numCusts = 0; 
        		AllenDailyRoutes.date = LocalDate.now();
        		actionTarget.setText("");
        		
        	}
        	
        });
        
//-------------------------------------------------------------------------------
        
        //show application window
        HBox custs = new HBox(100);
        custs.setAlignment(Pos.CENTER_LEFT);
        Text space = new Text("             ");
        custs.getChildren().add(space);
        custs.getChildren().add(customersAdded);
        
   
        
        window.getChildren().add(grid);
        window.getChildren().add(custs);
        Scene scene = new Scene(window,100,100);
        
        
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
}
