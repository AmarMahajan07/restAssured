package test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.common_method_patch_api;
import common_method.common_method_utilitise;
import io.restassured.path.json.JsonPath;
import request_repository.Patch_request_repository;

public class Patch_tc1 
{
	@Test
  public static void orchestrator() throws IOException
  {
	  String response_body = "";
	  int response_statuscode = 0;
	  String baseURI=Patch_request_repository.baseURI();
	  String resource=Patch_request_repository.resource();
	  String requestbody=Patch_request_repository.Patch_request_tc1();
	  
	  for(int i=0; i<5;i++)
	  {
		  response_statuscode=common_method_patch_api.responsestatuscode_extractor(baseURI, resource, requestbody);
		  if(response_statuscode==200)
		  {
			  response_body=common_method_patch_api.response_body_extractor(baseURI, resource, requestbody);
			  response_bodyvalidator(response_body);
			  break;
		  }
		  else
		  {
			  System.out.println("correct statuscode is not found in the iteration " + i);
		  }
	  }
	  common_method_utilitise.EvidenceFileCreator("Patch_tc1", requestbody, response_body);
	  Assert.assertEquals(response_statuscode,200);
	  
  }

 public static void response_bodyvalidator(String response_body) 
 {
	//create json path object to extract response body parameter
     JsonPath jsp=new JsonPath(response_body);
     
     //extract response body parameter
     String res_name=jsp.getString("name");
     String res_job=jsp.getString("job");
     String res_date=jsp.getString("updatedAt");
     
//     System.out.println(res_name);
//     System.out.println(res_job);
//     System.out.println(res_date);
     
     //validate response body parameter
     Assert.assertEquals(res_name,"morpheus");
     Assert.assertEquals(res_job,"zion resident");
     
     String date = res_date.substring(0,10);
     //System.out.println(date);
     String Sysdate =LocalDate.now().toString();
     //System.out.println(Sysdate);
     
     Assert.assertEquals(date,Sysdate);
     
  }
 
}
