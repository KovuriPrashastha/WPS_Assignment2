package in.ac.vce;
import java.sql.Connection;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.util.Scanner;

public class JDBCCachedRowSet {

	public static void main(String[] args) throws Exception
	{
		  Connection con = new MySqlConnection().MySqlConnec();
		  con.setAutoCommit(false);
		  String url = "jdbc:mysql://localhost:3306/test";
		  String username = "root";
		  String password = "";
		  RowSetFactory factory = RowSetProvider.newFactory();
	      CachedRowSet rowSet = factory.createCachedRowSet();
		  rowSet.setUrl(url);
		  rowSet.setUsername(username);
		  rowSet.setPassword(password);
		  rowSet.setCommand("select * from details");
	      rowSet.execute();
	    
	      int option;
	      String[] array ;
	      String[] attribute = {"Name", "emailId", "dob in dd-mm-yy"};
	      Scanner sc = new Scanner(System.in);
	      System.out.println("Enter the option : ");
	      System.out.println("1. Insert data");
	      System.out.println("2. Display data");
	      System.out.println("3. Update data");
	      option = sc.nextInt();
	      
	      
	      switch(option) {
	      case 1:
	    	array = new String[10];
	    	for(int i = 0; i< 3; i++) {
	    		System.out.println("Enter "+attribute[i]+" : ");
	    		array[i] = sc.next();
	    	}
	    	System.out.println("Enter mobile number");
	    	int mobile = sc.nextInt();
	    	rowSet.moveToInsertRow();
	  		rowSet.updateString(1, array[0]);
	  		rowSet.updateString(2, array[1]);
	  		rowSet.updateInt(3, mobile);
	  		rowSet.updateString(4, array[2]);
	  	    rowSet.insertRow();
	  	    rowSet.moveToCurrentRow();
	  	    rowSet.acceptChanges(con);
	  	    System.out.println("Row Inserted Successfully");
	  	    break;
	      case 2:
	    	int mobileno;
	    	System.out.println("Enter mobile number :");
	    	mobileno = sc.nextInt();
	    	rowSet.setCommand("select * from details where mobile="+mobileno);
	  	    rowSet.execute();
	  	    System.out.println("Details with given phone number : ");
	  	    while(rowSet.next()) {
		         System.out.print("User Name: "+rowSet.getString("name")+", ");
		         System.out.print("EmailId: "+rowSet.getString("email")+", ");
		         System.out.print("MobileNo: "+rowSet.getInt("mobile")+", ");
		         System.out.print("DOB: "+rowSet.getString("dob"));
		         System.out.println("");
	  	    }
	  	    break;
	      case 3:
	    	  String name, email;
	    	  System.out.println("Enter the name whose name should be changed");
	    	  name = sc.next();
	    	  int flag = 0;
	    	  System.out.println("Enter the changed email id");
	    	  email = sc.next();
	    	  while(rowSet.next()) {
		          if(rowSet.getString("name").equals(name)) {
		             rowSet.updateString("email", email);
		             rowSet.updateRow();
		             flag = 1;
		          }
		       } 
		      rowSet.acceptChanges(con);
		      if(flag == 1)
		    	  System.out.println("Email id changed to "+email+" successfully");
		      else
		    	  System.out.println("Couldn't change the email id");
		      break;
	  	  default :
	  		  break; 
	      }
	}
}
