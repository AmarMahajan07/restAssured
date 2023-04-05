package common_method;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class common_method_api {
	public static int responsestatuscode_extractor(String baseURI,String resource,String req_body)
	{
		RestAssured.baseURI=baseURI;
		int response_statuscode = given().header("Content-Type","application/json").body(req_body).when().post(resource).then().
				extract().statusCode();
		
		return response_statuscode;
		
		
	}
	public static String responsesbody_extractor(String baseURI,String resource,String req_body)
	{
		RestAssured.baseURI=baseURI;
		String response_body = given().header("Content-Type","application/json").body(req_body).when().post(resource).then().
				extract().response().asString();
		
		return response_body;
		
	}

}
