package com.example.springbootidstar.controller;

import com.example.springbootidstar.model.KaryawanTraining;
import com.example.springbootidstar.service.KaryawanTrainingService;
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
@RequestMapping("/v1/idstar/karyawan-training")
public class KaryawanTrainingController {
    @Autowired
    private KaryawanTrainingService karyawanTrainingService;

    @PostMapping("/save")
    public ResponseEntity<Map> save(@RequestBody KaryawanTraining karyawanTraining) {
        Map obj = karyawanTrainingService.insert(karyawanTraining);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }

    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody KaryawanTraining karyawanTraining) {
        Map obj = karyawanTrainingService.update(karyawanTraining);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map obj = karyawanTrainingService.delete(id);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }

    @GetMapping("/list")
    public ResponseEntity<Map> list(@RequestParam() Integer page, @RequestParam Integer size) {
        Map list = karyawanTrainingService.getAll(size, page);
        return new ResponseEntity<Map>(list, new HttpHeaders(), HttpStatus.valueOf(list.get("status").toString()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getById(@PathVariable(value = "id") Long id) {
        Map obj = karyawanTrainingService.getById(id);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }
}
