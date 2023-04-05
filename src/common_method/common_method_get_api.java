package common_method;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class common_method_get_api {
	public static int responsestatuscode_extractor(String baseURI,String resource,String req_body)
	{
		RestAssured.baseURI=baseURI;
		int response_statuscode = given().header("Content-Type","application/json").body(req_body)
				.when().get(resource).then().extract().statusCode();
		return response_statuscode;
	}
	
public static String responsebody_extractor(String baseURI,String resource,String req_body)
    {
	   String response_body = given().header("Content-Type","application/json").body(req_body)
			   .when().get(resource).then().extract().response().asString();
	   return response_body;
    }

}
