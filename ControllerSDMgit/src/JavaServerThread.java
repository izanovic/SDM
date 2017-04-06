import java.util.List;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaServerThread extends Thread {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private ClientRequest clientThread;
	private volatile boolean running = true;
	private List<ClientRequest> clientList;
	
	//constructor
	public JavaServerThread(ServerSocket s){
		try{
			s = new ServerSocket(1337, 100, InetAddress.getByName("141.252.207.138"));
		}
		catch (Exception e) {
			System.out.println("Could not create serverSocket");
			e.printStackTrace();
		}
		this.serverSocket = s;
	}
	
	public void run() {
	    while (running) {
	    	System.out.println("Server started  at:  " + serverSocket);
	    	System.out.println("Waiting for a  connection...");
	    	try {
	    		clientSocket = serverSocket.accept();
	    	} 
	    	catch (IOException e) {
	    		System.out.println("Could not accept client connection");
	    		e.printStackTrace();
	    	}

	      System.out.println("Received a  connection from " + clientSocket.getInetAddress() + " : " + clientSocket.getPort());
	      clientThread = new ClientRequest(clientSocket);
	      
	      clientThread.start();
	      //clientList.add(clientThread);
	    }
	}
	
	public ClientRequest getClient(){
		return clientThread;
	}
	

    public void terminate() throws IOException {
        running = false;
        try {
        	clientSocket.close();
            serverSocket.close();
		} 
        catch (Exception e) {
        	System.out.println("Could not close client/server socket");
		}
        
    }
    
    public List<ClientRequest> getClientList(){
    	return this.clientList;
    }

}