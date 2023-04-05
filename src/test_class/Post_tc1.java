package test_class;

import java.io.IOException;
import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.common_method_api;
import common_method.common_method_utilitise;
import io.restassured.path.json.JsonPath;
import request_repository.Post_request_repository;

public class Post_tc1 
{
	@Test
	public static void orchestrator() throws IOException
	{
		String response_body = "";
		int response_statuscode = 0;
		String baseURI=Post_request_repository.baseURI();
		String resource=Post_request_repository.resource();
		String requestbody=Post_request_repository.Post_request_tc1();
		for(int i=0;i<5;i++)
		{
			response_statuscode=common_method_api.responsestatuscode_extractor(baseURI, resource, requestbody);
			if(response_statuscode==201)
			{
				response_body=common_method_api.responsesbody_extractor(baseURI, resource, requestbody);
				response_bodyValidator(response_body);
				break;
			}
			else
			{
				System.out.println("xyz" +i );
			}
		}
		common_method_utilitise.EvidenceFileCreator("Post_tc1", requestbody, response_body);
		Assert.assertEquals(response_statuscode, 201);
		
}
	
    public static void response_bodyValidator(String response_body) 
	    {
	    	
	    //create json path object to extract response body parameter
	    JsonPath jsp = new JsonPath(response_body);	
	
        //extract response body parameter
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_id = jsp.getString("id");
		String res_date = jsp.getString("createdAt");
		
//		System.out.println(res_name);
//		System.out.println(res_job);
//		System.out.println(res_id);
//		System.out.println(res_date);

		//validate response body parameter
        Assert.assertEquals(res_name,"morpheus");
        Assert.assertEquals(res_job,"leader");
        Assert.assertNotNull(res_id);
        
        String date = res_date.substring(0,10);
        //System.out.println(date);
        String Sysdate =LocalDate.now().toString();
        //System.out.println(Sysdate);
        
        Assert.assertEquals(date,Sysdate);
        

	}
	    
}

	