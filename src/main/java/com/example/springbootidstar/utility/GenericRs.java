package com.example.springbootidstar.utility;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author asief
 * @project springboot-idstar, 13/04/2023
 */
@Component
public class GenericRs {

    public Map templateSuccess(Object o){
        Map map = new HashMap();
        map.put("data", o);
        map.put("message", "Success");
        map.put("status", "200");
        return map;
    }
    public Map templateError(Object o, String status){
        Map map = new HashMap();
        map.put("message", o);
        map.put("status", status);
        return map;
    }
    public Map notFound(Object o){
        Map map = new HashMap();
        map.put("message", o);
        map.put("status", "404");
        return map;
    }
    public boolean chekNull(Object obj) {
        if (obj == null) {
            return true;
        }
        return false;
    }
}
