package org.APIImplementationFramework.tests.individualTests;

import io.restassured.RestAssured;
import org.APIImplementationFramework.base.BaseClass;
import org.APIImplementationFramework.endPoints.APIEndpoints;
import org.APIImplementationFramework.listeners.ReTryAnalyzer;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestGETAPI extends BaseClass {

    @Test (retryAnalyzer = ReTryAnalyzer.class)
    public void VerifyCreatedUserDetails(ITestContext context){

        Long id = (Long) context.getAttribute("createdUserId");

        requestSpecification.basePath(APIEndpoints.GET_USER+id);

        response = RestAssured.given(requestSpecification)
                .when().get();

        response.then().log().all();

        assertThat(response.getStatusCode()).isEqualTo(200);
    }
}
