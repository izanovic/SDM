import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class ClientRequest extends Thread{
	private Socket socket;
	private final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	private volatile boolean running = true;
	
	public ClientRequest(Socket socket) {
    	this.socket = socket;
	}
	
	//While clientSocket is running keep reading from it whenever there is data
	public void run() {
		while (running) {
		    try{
		    	BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    	OutputStreamWriter  socketWriter = new OutputStreamWriter(socket.getOutputStream()); 

			    Gson g = new Gson();
		    	String inputMsg = "";
		    	while(!socket.isClosed()) {
		    		//if data stream from socket is ready to be read
		    		if(socketReader.ready()){
		    			inputMsg = socketReader.readLine();
		    			//Trigger tri =  g.fromJson(inputMsg, Trigger.class);
		    			System.out.println("Received from  client: " + inputMsg);
		    			String subInput = "";
		    			if(inputMsg.contains("[")){
			    			int startIndex = inputMsg.indexOf("[");
			    			int endIndex = inputMsg.indexOf("]");
			    			subInput = inputMsg.substring(startIndex + 1, endIndex);
		    			}
		    			else{
		    				subInput = inputMsg;
		    			}
		    			String input = "[" + subInput + "]";
		    			System.out.println("Coverted input to: " + input);
		    			
		    			List<Trigger> triggerPoints = g.fromJson(input, new TypeToken<List<Trigger>>(){}.getType());
		    			triggerPoints.forEach(x -> System.out.println(x.toString()));
		    			
		    		}
		    		
		    		//check if there are messages on queue
		    		if(!queue.isEmpty()){
		    			String msg = queue.take();
		    			socketWriter.write(msg + "\r\n");
		    			socketWriter.flush();
		    			System.out.println("Send message to client: " + msg);
		    		}
		    	}
			}
		    catch(Exception e){
		    	System.out.println("Could not read from client");
		    	e.printStackTrace();
		    }
		}
	}
	
	//Method which puts messages on the queue
	public void sendData(String s){
		queue.offer(s);
	}
	
	 public void terminate() throws IOException {
	        running = false;
	        socket.close();
    }
}
