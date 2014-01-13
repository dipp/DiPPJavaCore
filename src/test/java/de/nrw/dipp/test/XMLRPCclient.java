package de.nrw.dipp.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class XMLRPCclient {
	
	public static void main(String[] args){
//		String serverURL = "http://193.30.112.98/developer/remoteUpload/";
		String serverURL = "http://www.dipp.nrw.de/demo/portal_skins/custom/";
		try{
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		    config.setServerURL(new URL(serverURL));
		    config.setBasicUserName("dippadm");
		    config.setBasicPassword("z23t5ng");
		    XmlRpcClient client = new XmlRpcClient();
		    client.setConfig(config);
		    
//		    String[] request_article_args = {"test:132", "test 132", "Jochen Schirrwagen",
//		    		"descr123", "2006-12-06 07:23", "subject123;erstes Schlachwort; zweites Schlachwort", "DPPL"};
		    String[] request_article_args = {"temp:844", "temp 844", "Jochen Schirrwagen",
		    		"descr123", "2006-12-06 07:23", "subject123;erstes Schlachwort; zweites Schlachwort", "DPPL"};
		    // doc_type, parent_id, pid, dsid, label
		    String[] request_content_args = {"FedoraDocument",
		    		"temp_844", "content:10167", "DS1", "index_html"};
		    String[] request_multimedia_args = {"FedoraMultimedia",
		    		"temp_724", "content:10477", "DS3", "dippArticle-3.png"};
		    // state: visible, hide, submit, publish
// aPID, aIsChildOf, aIsParentOf, aCModel, aCreator, aTitle, aURL		    
            String[] request_workflow_args = {"temp:844", 
            		"test:1", "", "dipp:article", "Jochen Schirrwagen", "Kurzanleitung", "http://www.dipp.nrw.de/demo/tmp/temp_844"};
		    
		    String resultArticle = (String) client.execute ("createArticle", request_article_args);
		    String resultContent = (String) client.execute ("createContent", request_content_args);
		    String resultWorkflow = (String) client.execute ("createWorkflowInstance", request_workflow_args);
		    System.out.println(resultArticle + "\n" + resultContent + "\n" + resultWorkflow);
		}catch(MalformedURLException url_exc){
			url_exc.printStackTrace();
		}catch(XmlRpcException xmlrpc_exc){			
//			StackTraceElement[] ste = xmlrpc_exc.getStackTrace();
			System.out.println("locMsg: " + xmlrpc_exc.getLocalizedMessage()); 
			System.out.println("error in xmlrpc: " + xmlrpc_exc.code);
		}
//	"http://193.30.112.98/testjournal/");
		
	}

}
