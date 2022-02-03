package com.vmotors.domain.request;

public class LoginResponseModel {
    /**
     * email : abc@example.com
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiNzAzOWQ2OGQ3Z TVlZGRiM2ZmZDA4ODA3OGZkMGNiNWMiLCJleHAiOjE1NzE2NDQ2NjV9.Ny2 3sfrV_Mt0wWACsiB54LavQYalU0SruT0L5lNymb4
     * responseMessage : Successfully login.
     * responseCode : 200
     *
     *{
     * "Response": "Success",
     *   "user_id": "1",
     *   "group_id": "1",
     *   "role_id": "17",
     *   "login":"Invalid Username and Password"}
     *
     *   {
     *     "id": 32,
     *     "social_id": null,
     *     "social_id_type": null,
     *     "socialLoginToken": null,
     *     "firstName": "testadmin",
     *     "lastName": "abc",
     *     "email": "axe@y.com",
     *     "contact": null,
     *     "profileImage": null,
     *     "dob": null,
     *     "role": 3,
     *     "isSubscribed": 0,
     *     "isActive": 1,
     *     "isDeleted": 0,
     *     "created": "2021-11-21T09:53:46.000Z",
     *     "updated": null
     * }
     */


    private int id;
    private String email;
    private String firstName;
    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
