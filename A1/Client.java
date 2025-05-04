import java.rmi.*;
import java.util.*;

public class Client {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			String serverURL = "rmi://localhost/Server";
			ServerIntf serverIntf = (ServerIntf) Naming.lookup(serverURL);
      System.out.print("Enter a year to check if it’s a leap year: ");
      int year = sc.nextInt();
      boolean isLeap = serverIntf.isLeapYear(year);
      if (isLeap) {
        System.out.println(year + " is a leap year.");
      } else {
        System.out.println(year + " is not a leap year.");
      }
		}
		catch(Exception e) {
			System.out.println("Exception Occured at Client "+e.getMessage());
		}
		sc.close();
	}
}
