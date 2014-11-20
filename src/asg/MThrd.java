import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MThrd implements Runnable {
	private JEditorPane editorPane;
	private JTextField txtUrl;
	private JTextArea txtrHeader;
	private Thread thrd;
	private String threadName;
	private JTextArea amt;
	
	public MThrd
        (String threadName,JEditorPane editorPane,JTextField txtUrl,JTextArea txtrHeader,JTextArea amt){
		this.threadName = threadName;
		this.editorPane = editorPane;
		this.txtUrl = txtUrl;
		this.txtrHeader = txtrHeader;
		this.amt = amt;
	}
	
	public void run() {
		getLink();
		getHeader();
	}
	
	public void start ()
	   {
		  amt.append(threadName+" builting Thread" +  "\n" );
	      if (thrd == null)
	      {
	         thrd = new Thread (this, threadName);
	         thrd.start ();
	         amt.append(thrd.getName()+" Starting Thread" +  "\n" );
	      }
	   }
	public void getLink(){
		try{
 		 	thrd.sleep(600);
 		 }
 		 catch(InterruptedException e){}
		 amt.append(thrd.getName()+" Get Web" +"\n" );
		 try {
			 editorPane.setPage(txtUrl.getText());
		 }
		 catch (IOException e) {
			 editorPane.setContentType("text/html");
		   	 editorPane.setText("<html>Cann't load "+txtUrl.getText()+" </html>");
		 }
		 
		
	}
	public void getHeader(){
		amt.append(thrd.getName()+" Get Header" +"\n" );
		try{
 		 	thrd.sleep(600);
 		 }
 		 catch(InterruptedException e){}
		 try {
	            URLConnection conn = new URL(txtUrl.getText()).openConnection();
	            Map<String, List<String>> map = conn.getHeaderFields();
	            txtrHeader.append("Header for URL: "+txtUrl.getText()+ "\n");

	            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
	            	txtrHeader.append(entry.getKey() + " : " + entry.getValue()+"\n");
	            }
	            List<String> contentLength = map.get("Content-Length");
	 
	            if (contentLength == null) {
	            	txtrHeader.append("'Content-Length' doesn't present in Header!");
	            } else {
	                for (String header : contentLength) {
	                	txtrHeader.append("Content-Lenght: " + header);
	                }
	            }
	 
	        } catch (Exception e) {
	        	txtrHeader.append("Can't load header from "+txtUrl.getText());
	        }	
	}
}
