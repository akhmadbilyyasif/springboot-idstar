package com.example.springbootidstar.model.dao;

import lombok.Data;

@Data
public class ForgotPasswordModel {
    private String email;
    private String otp;
    private String newPassword;
    private String confirmNewPassword;
}
