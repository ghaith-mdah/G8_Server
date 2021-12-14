// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package server;

import java.io.*;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.mysql.cj.MysqlConnection;

import MySQLconnection.SQLconnection;
import gui.ServerPortFrameController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.clients;
import logic.Item;
import logic.Order;
import logic.Request;
import logic.User;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class EchoServer extends AbstractServer {
	// Class variables *************************************************
	SQLconnection c = new SQLconnection();
	ArrayList<Item>items=new ArrayList<Item>();
	// final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 * 
	 */

	public EchoServer(int port) {
		super(port);
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 * @param
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {

		String message;// the message contains the request from client
		String type1 = ((Request) msg).getRequest();
		String[] arr = type1.split("\t");
		message = arr[0];
		/**
		 * @author gethe
		 * @param String message
		 * @Functionality according to message in class Request the server Decides what
		 *                action to do
		 */
		switch (message) {
		case "connect":
			ServerController.ConnectClient(client);
			try {
				client.sendToClient(new Request("connected", null));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "disconnect":
			ServerController.DisonnectClient(client);
			try {
				System.out.print("after send to client");
				client.sendToClient(new Request("disconnected", null));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "Login":
			try {
				String str = SQLconnection.GetUserWithPassword((User) ((Request) msg).getObj());
				client.sendToClient(new Request("Role	"+str, (User) ((Request) msg).getObj()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "Log out":
			try {
				SQLconnection.GetLogOut((User) ((Request) msg).getObj());
				client.sendToClient(new Request("Logged out", null));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "get user":
			try {
				String str = SQLconnection.GetUser((User) ((Request) msg).getObj());
				client.sendToClient(new Request("email	"+str, (User) ((Request) msg).getObj()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case "reset password":
			SQLconnection.ResetPassword((User) ((Request) msg).getObj());
			try {
				client.sendToClient(new Request("password saved",null));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case "get resturant name":
			String []NameAndID=SQLconnection.GetResturantNameForSupplierId((User) ((Request) msg).getObj());
			try {
				client.sendToClient(new Request("resturant name sent",NameAndID));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case"edit item":
			int id=Integer.parseInt(arr[1]);
			String status=SQLconnection.UpdateItemInMenu((Item)((Request) msg).getObj(), id);
			try {
				client.sendToClient(new Request("after update item	"+status,null));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "show items in restaurant":
			items=SQLconnection.GetItemsForRestaurantId((int)((Request) msg).getObj());
			try {
				client.sendToClient(new Request("res menu sent",items));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case"add item to menu":
			int id1=Integer.parseInt(arr[1]);
			String status1=SQLconnection.AddItemToMenu((Item)((Request) msg).getObj(), id1);
			try {
				client.sendToClient(new Request("after add item	"+status1,null));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case"remove item from menu":
			int id2=Integer.parseInt(arr[1]);
			String status2=SQLconnection.RemoveItemFromMenu((Item)((Request) msg).getObj(), id2);
			try {
				client.sendToClient(new Request("after add item	"+status2,null));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("Illegal Commaned");
			System.exit(0);
			break;
		}

	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}
}
//End of EchoServer class
