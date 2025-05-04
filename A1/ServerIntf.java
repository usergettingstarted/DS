import java.rmi.*;

public interface ServerIntf extends Remote{
	boolean isLeapYear(int year) throws RemoteException;
}
