package com.example.demo.bo;

public class UserResponse {

    private Long id;
    private String username;
    private String phoneNumber;
    private String address;

    public UserResponse(Long id, String username) {
        this.id = id;
        this.username = username;
//        this.phoneNumber = phoneNumber;
//        this.address = address;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
