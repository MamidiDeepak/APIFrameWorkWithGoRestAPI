package org.APIImplementationFramework.base;

import static org.assertj.core.api.Assertions.assertThat;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.APIImplementationFramework.asserts.AssertActions;
import org.APIImplementationFramework.endPoints.APIEndpoints;
import org.APIImplementationFramework.listeners.ReTryAnalyzer;
import org.APIImplementationFramework.listeners.ReTryListener;
import org.APIImplementationFramework.payload.CreateUserPayload;
import org.testng.annotations.BeforeClass;


public class BaseClass {
    public APIEndpoints apiEndpoints;
    public CreateUserPayload createUserPayload;
    public AssertActions assertActions;
    public ReTryAnalyzer reTryAnalyzer;
    public ReTryListener reTryListener;
    public RequestSpecification requestSpecification;
    public Response response;

//    String token = "46364650434b1d3a11beb1bfd6a6a94565717ef64de8e676ba1e5dc2ea2b5bbd";

    @BeforeClass
    public void InitialBaseUrl(){
       requestSpecification = RestAssured.given()
                .baseUri(APIEndpoints.BASE_URL)
//                .headers(("Authorization"), "Bearer " + token)
                .contentType("application/json");
    }

}
