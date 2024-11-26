package org.APIImplementationFramework.payload;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.APIImplementationFramework.pojo.CreateUserPojo;
import org.APIImplementationFramework.pojo.CreateUserResponsePojo;

public class UpdateCreatedUserPayload {

    static Gson gson = new Gson();

    static Faker faker = new Faker();
    public static String name = faker.name().firstName();
    public static String email = faker.internet().emailAddress();

    public static String getUpdateUserPayload(){

        CreateUserPojo createUserPojo = new CreateUserPojo();
        createUserPojo.setName(name);
        createUserPojo.setEmail(email);
        createUserPojo.setGender("Female");
        createUserPojo.setStatus("Inactive");

//        Convert payload to JSON Payload
        String jsonPayload = gson.toJson(createUserPojo);
        return jsonPayload;
    }

    public static CreateUserResponsePojo getDeSerializedUpdatedResponse(String response){
        CreateUserResponsePojo createUserResponsePojo = gson.fromJson(response, CreateUserResponsePojo.class);
        return createUserResponsePojo;
    }
}
