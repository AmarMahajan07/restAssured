package test_class;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.common_method_get_api;
import common_method.common_method_utilitise;
import io.restassured.path.json.JsonPath;
import request_repository.Get_request_repository;

public class Get_tc1 {
	
	@Test
	public static void orchestrator() throws IOException
	{
		String response_body= "";
		int response_statuscode= 0;
		String baseURI=Get_request_repository.baseURI();
		String resource=Get_request_repository.resource();
		String requestbody=Get_request_repository.Get_tc_1();
		
		for(int i=0;i<5;i++)
		{
			response_statuscode=common_method_get_api.responsestatuscode_extractor(baseURI,resource,requestbody);
			if(response_statuscode==200)
			{
				response_body=common_method_get_api.responsebody_extractor(baseURI,resource,requestbody);
				response_bodyvalidator(response_body);
				break;
			}
			else
			{
				System.out.println("correct status code is not found in the iteration " + i);
			}
		}
		common_method_utilitise.EvidenceFileCreator("Get_tc1",requestbody,response_body);
		Assert.assertEquals(response_statuscode,200);
	}


	public static void response_bodyvalidator(String response_body) 
	{
		//create json path object to extract response body parameter
		JsonPath jsp=new JsonPath(response_body);
		
		//calculate data array length or count
		int count=jsp.getInt("data.size()");
		//System.out.println(count);
		
		//declare expected results
		int ID[]= {7,8,9,10,11,12};
		String EMAIL[]= {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in","byron.fields@reqres.in","george.edwards@reqres.in","rachel.howell@reqres.in"};
		String FNAME[]= {"Michael","Lindsay","Tobias","Byron","George","Rachel"};
		String LNAME[]= {"Lawson","Ferguson","Funke","Fields","Edwards","Howell"};
		String AVATAR[]= {"https://reqres.in/img/faces/7-image.jpg","https://reqres.in/img/faces/8-image.jpg","https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg","https://reqres.in/img/faces/11-image.jpg","https://reqres.in/img/faces/12-image.jpg"};
		
		for(int i=0;i<count;i++)
		{
			int dec_id=ID[i];
			String dec_email=EMAIL[i];
			String dec_fname=FNAME[i];
			String dec_lname=LNAME[i];
			String dec_avatar=AVATAR[i];
			
			int res_id=jsp.getInt("data["+i+"].id");
			String res_email=jsp.getString("data["+i+"].email");
			String res_fname=jsp.getString("data["+i+"].first_name");
			String res_lname=jsp.getString("data["+i+"].last_name");
			String res_avatar=jsp.getString("data["+i+"].avatar");
			
			System.out.println(res_id);
			System.out.println(res_email);
			System.out.println(res_fname);
			System.out.println(res_lname);
			System.out.println(res_avatar);
		
		    Assert.assertEquals(res_id,dec_id);
		    Assert.assertEquals(res_email,dec_email);
		    Assert.assertEquals(res_fname,dec_fname);
		    Assert.assertEquals(res_lname,dec_lname);
		    Assert.assertEquals(res_avatar,dec_avatar);
	   }
   }
	
}


