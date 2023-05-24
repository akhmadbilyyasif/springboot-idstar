package com.example.springbootidstar.utility;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author asief
 * @project springboot-idstar, 27/04/2023
 */
@Data
public class Config {
    String code = "status", message = "message";
    public String code_notFound ="404";
    public String codeRequired ="403";
    public String isRequired =" is Required";
    public String code_sukses = "200";

    public String code_server = "500";
    public String code_null = "1";
    public String message_sukses = "sukses";
    public String convertDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = dateFormat.format(date);
        return strDate;
    }
}
