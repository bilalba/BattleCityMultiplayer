import java.net.*;
import java.io.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;

public class ClientChat {
	public static void main(String[] args) throws Exception{

       	System.out.println("Welcome to Bilal's chatroom.");
        frt = new ArrayList<String>();
		Registry registry = LocateRegistry.getRegistry();
		chat=(Chat)registry.lookup("chat");
		chat.estabconn();
	}
}