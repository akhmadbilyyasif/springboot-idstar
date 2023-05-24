package com.example.springbootidstar.controller;

import com.example.springbootidstar.model.Training;
import com.example.springbootidstar.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author asief
 * @project springboot-idstar, 24/05/2023
 */
@RestController
@RequestMapping("/v1/idstar/training")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @PostMapping("/save")
    public ResponseEntity<Map> save(@RequestBody Training objModel) {
        Map obj = trainingService.insert(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }

    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody Training objModel) {
        Map obj = trainingService.update(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map obj = trainingService.delete(id);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }

    @GetMapping("/list")
    public ResponseEntity<Map> list(@RequestParam() Integer page, @RequestParam Integer size) {
        Map list = trainingService.getAll(size, page);
        return new ResponseEntity<Map>(list, new HttpHeaders(), HttpStatus.valueOf(list.get("status").toString()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getById(@PathVariable(value = "id") Long id) {
        Map obj = trainingService.getById(id);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }

}
