package org.APIImplementationFramework.tests.individualTests;

import io.restassured.RestAssured;
import org.APIImplementationFramework.base.BaseClass;
import org.APIImplementationFramework.endPoints.APIEndpoints;
import org.APIImplementationFramework.payload.UpdateCreatedUserPayload;
import org.APIImplementationFramework.pojo.CreateUserResponsePojo;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestPUTAPI extends BaseClass {

    @Test
    public void testUpdatingCreatedUser(ITestContext context){

        Long id = (Long) context.getAttribute("createdUserId");

        requestSpecification.basePath(APIEndpoints.UPDATE_USER+id);

        response = RestAssured.given(requestSpecification)
                .when().body(UpdateCreatedUserPayload.getUpdateUserPayload()).put();

        response.then().log().all();

//        CreateUserResponsePojo cuurp = UpdateCreatedUserPayload.getDeSerializedUpdatedResponse(response.asString());
//
//        assertThat(response.getStatusCode()).isEqualTo(200);
////        assertThat(response.getStatusLine()).isEqualTo("");
//        assertThat(response.getTime()).isLessThan(2000L);
////        assertThat(response.getContentType()).isEqualTo("");
//
//        assertThat(cuurp.getName()).isNotNull();
////        assertThat(cuurp.getName()).isEqualTo("");
//        assertThat(cuurp.getName()).isInstanceOf(String.class);
//        assertThat(cuurp.getStatus()).containsAnyOf("inactive");
    }

//    @Test
//    public void testNonAuthorizedUserTryingToCreateUser(){
//
//    }
}
