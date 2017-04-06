import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.google.gson.Gson;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;


public class Controller {
	private Text dataField;
	ServerSocket serverSocket;
	JavaServerThread serverThread = new JavaServerThread(serverSocket);
	

    public static void main(String[] args) throws Exception {
    	Controller c = new Controller();
    	c.initDisplay();
    }
    
    public void initDisplay(){
    	Display display = new Display();
        Shell shlController = new Shell(display);

        shlController.setText("Controller");
        shlController.setMinimumSize(new Point(250, 250));
        shlController.setLayout(null);
        // Create the children of the composite.
        initButtons(shlController);
        shlController.pack();
        shlController.open();
        
        while (!shlController.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
    }
    
    public void initButtons(Shell shlController){
        Button btnStartServer = new Button(shlController, SWT.PUSH);
        btnStartServer.setBounds(10, 10, 87, 25);
        btnStartServer.setText("Start Server");
      //action listener for start button
        btnStartServer.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		try{
        			//TODO start server
        			serverThread.start();
        		}
        		catch(Exception ex){
        			System.out.println("Could not connect\r\n");
        		    ex.printStackTrace();   
        		}
        	}
        });
        
        Button btnStopServer = new Button(shlController, SWT.PUSH);
        btnStopServer.setBounds(137, 10, 87, 25);
        btnStopServer.setText("Stop Server");
        //action listener for stop button
        btnStopServer.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		try{
        			
        			//stop client Thread (IO streams)
        			/*if(!serverThread.getClientList().isEmpty()){
	        			for (ClientRequest c : serverThread.getClientList()) {
							c.terminate();
							c.join();
						}
        			}
        			
        			//stop server Thread
        			serverThread.terminate();
        			serverThread.join();*/

        		}
        		catch(Exception ex){
        			System.out.println("Could not disconnect\r\n");
        			ex.printStackTrace();
        		}
        	}
        });
        
        Button btnSendData = new Button(shlController, SWT.NONE);
        btnSendData.setBounds(138, 117, 75, 25);
        btnSendData.setText("Send Data");
        //action listener for send data button
        btnSendData.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		try{
        			//TODO send data from to client
        	    	List<Stoplicht> stoplichten = new ArrayList<Stoplicht>();
        	    	stoplichten.add(new Stoplicht("XWO", 1));
        	    	stoplichten.add(new Stoplicht("FN", 2));

        	    	String json = new Gson().toJson(stoplichten);
        	    	String data =  "{\"stoplichten\":" + json + "}";
        			//serverThread.getClient().sendData(dataField.getText());
        			serverThread.getClient().sendData(data);
        		}
        		
        		catch (Exception ex) {
        			
					System.out.println("Could not send data");
					
				}
        	}
        });
        
        dataField = new Text(shlController, SWT.BORDER);
        dataField.setBounds(137, 90, 76, 21);
        
    }
   }