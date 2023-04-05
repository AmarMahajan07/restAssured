package common_method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class common_method_utilitise {
	public static void EvidenceFileCreator(String filename,String request,String response) throws IOException
	{
		File newTextFile = new File("C:\\restassuredEvidence\\" + filename + ".txt");
		if(newTextFile.createNewFile())
		{		
		  FileWriter dataWriter = new FileWriter(newTextFile);
		  dataWriter.write("request body is :\n" +request+ "\n\n");
		  dataWriter.write("response body is :\n" +response);
		  dataWriter.close();
		  System.out.println("request and response body data save in :" +newTextFile.getName());
	    }
		else
		{
			System.out.println(newTextFile.getName()+ " Already exit take a back of it ");
		}
  }

}
