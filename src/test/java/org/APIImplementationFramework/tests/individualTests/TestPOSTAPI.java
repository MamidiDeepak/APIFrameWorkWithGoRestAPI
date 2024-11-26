package org.APIImplementationFramework.tests.individualTests;

import static org.assertj.core.api.Assertions.assertThat;

import io.opentelemetry.api.trace.StatusCode;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.APIImplementationFramework.asserts.AssertActions;
import org.APIImplementationFramework.base.BaseClass;
import org.APIImplementationFramework.endPoints.APIEndpoints;
import org.APIImplementationFramework.listeners.ReTryAnalyzer;
import org.APIImplementationFramework.payload.CreateUserPayload;
import org.APIImplementationFramework.pojo.CreateUserResponsePojo;
import org.APIImplementationFramework.utils.ReadKey;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Map;

public class TestPOSTAPI extends BaseClass {

    String token = "46364650434b1d3a11beb1bfd6a6a94565717ef64de8e676ba1e5dc2ea2b5bbd";

    @Test (priority = 1, retryAnalyzer = ReTryAnalyzer.class)
    public void testPOSTAPI(ITestContext context){
        requestSpecification.basePath(APIEndpoints.CREATE_USER)
                .headers(("Authorization"), "Bearer " + token);

        response = RestAssured.given(requestSpecification)
                .when().body(CreateUserPayload.getCreateUserPayload()).post();

        System.out.printf(response.getBody().asString());

        CreateUserResponsePojo curp = CreateUserPayload.getDeSerializedResponse(response.asString());

        Long UserId = curp.getId();

        String name = curp.getName();
        System.out.printf("Created Name : "+name);

        String expectedName = CreateUserPayload.name;
        String expectedEmail = CreateUserPayload.email;

        AssertActions.validateTheResponseData(curp.getName(), expectedName);

        assertThat(curp.getName()).isNotNull();
        assertThat(curp.getName()).isEqualTo(ReadKey.readKey("create.post.user"));
        assertThat(response.getStatusCode()).isEqualTo(201);
        assertThat(response.getStatusLine()).isEqualTo("HTTP/1.1 201 Created");
        assertThat(response.getTime()).isLessThan(2000);
        assertThat(response.getContentType()).isEqualTo("application/json; charset=utf-8");
        assertThat(curp.getName()).isInstanceOf(String.class);
        assertThat(curp.getName()).isEqualTo(expectedName);

        Map<String,String> cookies = response.getCookies();
        System.out.printf("Cookies : "+cookies);

        String expectedJsonSchema = "{\n" +
                "  \"$schema\": \"http://json-schema.org/draft-04/schema#\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"id\": {\n" +
                "      \"type\": \"integer\"\n" +
                "    },\n" +
                "    \"name\": {\n" +
                "      \"type\": \"string\"\n" +
                "    },\n" +
                "    \"email\": {\n" +
                "      \"type\": \"string\"\n" +
                "    },\n" +
                "    \"gender\": {\n" +
                "      \"type\": \"string\"\n" +
                "    },\n" +
                "    \"status\": {\n" +
                "      \"type\": \"string\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"required\": [\n" +
                "    \"id\",\n" +
                "    \"name\",\n" +
                "    \"email\",\n" +
                "    \"gender\",\n" +
                "    \"status\"\n" +
                "  ]\n" +
                "}";

        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(expectedJsonSchema));

        context.setAttribute("createdUserId",UserId);
    }

//    @Test (priority = 2)
//    public void testNonAuthorizedUserTryingToCreateUser(){
//        requestSpecification.basePath(APIEndpoints.CREATE_USER);
//
//        response = RestAssured.given(requestSpecification)
//                .when().body(CreateUserPayload.getCreateUserPayload()).post();
//
//        System.out.printf(response.getBody().asString());
//
//        CreateUserResponsePojo ccrp = CreateUserPayload.getDeSerializedResponse(response.asString());
//
////        String actualMessage = response.getBody().asString();
//
//        assertThat(ccrp.getMessage()).isEqualTo("Authentication failed");
//
//    }
}
