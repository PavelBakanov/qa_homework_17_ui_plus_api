package models;


import lombok.Data;

@Data
public class LoginResponseModel {
    String userId, username, password, token, expires, created_date;
    Boolean isActive;
}