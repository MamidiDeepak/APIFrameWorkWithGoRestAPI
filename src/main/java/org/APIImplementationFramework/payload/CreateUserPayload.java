package org.APIImplementationFramework.payload;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.APIImplementationFramework.pojo.CreateUserPojo;
import org.APIImplementationFramework.pojo.CreateUserResponsePojo;

public class CreateUserPayload {

    static Gson gson = new Gson();

    static Faker faker = new Faker();
    public static String name = faker.name().firstName();
    public static String email = faker.internet().emailAddress();

    public static String getCreateUserPayload(){

        CreateUserPojo createUserPojo = new CreateUserPojo();
        createUserPojo.setName(name);
        createUserPojo.setEmail(email);
        createUserPojo.setGender("Male");
        createUserPojo.setStatus("active");

//        Convert payload to JSON Payload
        String jsonPayload = gson.toJson(createUserPojo);
        return jsonPayload;
    }

    public static CreateUserResponsePojo getDeSerializedResponse(String response){
        CreateUserResponsePojo createUserResponsePojo = gson.fromJson(response, CreateUserResponsePojo.class);
        return createUserResponsePojo;
    }
}
