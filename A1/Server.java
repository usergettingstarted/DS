import java.rmi.*;
import java.rmi.server.*;

public class Server extends UnicastRemoteObject implements ServerIntf{
	public Server() throws RemoteException{	
		super();
	}
	public boolean isLeapYear(int year) throws RemoteException {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}
	public static void main(String[] args) {
		try {
			Server serverImpl = new Server();
			Naming.rebind("Server", serverImpl);
			System.out.println("Server started...");
		}
		catch(Exception e) {
			System.out.println("Exception Occured at Server "+e.getMessage());
		}
	}
}
