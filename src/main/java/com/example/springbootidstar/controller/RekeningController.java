package com.example.springbootidstar.controller;

import com.example.springbootidstar.model.Rekening;
import com.example.springbootidstar.service.RekeningService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author asief
 * @project springboot-idstar, 24/05/2023
 */
@RestController
@RequestMapping("/v1/idstar/rekening")
public class RekeningController {

    @Autowired
    private RekeningService rekeningService;

    @PostMapping("/save")
    public ResponseEntity<Map> add(@Valid @RequestBody Rekening rekening) {
        Map obj = rekeningService.insert(rekening);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }

    @PutMapping("/update")
    public ResponseEntity<Map> updateRekening(@Valid @RequestBody Rekening rekening) {
        Map obj = rekeningService.update(rekening);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getById(@PathVariable(value = "id") Long id) {
        Map obj = rekeningService.getById(id);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listRekening(@RequestParam() Integer page,
                                            @RequestParam() Integer size) {

        Map list = rekeningService.getAll(size, page);
        return new ResponseEntity<Map>(list, HttpStatus.valueOf(list.get("status").toString()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> deleteRekening(@PathVariable(value = "id") Long id) {
        Map obj = rekeningService.delete(id);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }
}
