package com.example.springbootidstar.controller;

import com.example.springbootidstar.model.Karyawan;
import com.example.springbootidstar.service.KaryawanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author asief
 * @project springboot-idstar, 10/04/2023
 */
@RestController
@RequestMapping("/v1/idstar/karyawan")
public class KaryawanController {

    @Autowired
    private KaryawanService karyawanService;

    @PostMapping("/save")
    public ResponseEntity<Map> addKaryawan(@Valid @RequestBody Karyawan karyawan) {
        Map obj = karyawanService.insert(karyawan);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }

    @PutMapping("/update")
    public ResponseEntity<Map> updateKaryawan(@Valid @RequestBody Karyawan karyawan) {
        Map obj = karyawanService.update(karyawan);
        return new ResponseEntity<Map>(obj,HttpStatus.valueOf(obj.get("status").toString()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getById(@PathVariable(value = "id") Long id) {
        Map obj = karyawanService.getById(id);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listKaryawan(@RequestParam() Integer page,
                                            @RequestParam() Integer size) {

        Map list = karyawanService.getAll(size, page);
        return new ResponseEntity<Map>(list, HttpStatus.valueOf(list.get("status").toString()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> deleteKaryawan(@PathVariable(value = "id") Long id) {
        Map obj = karyawanService.delete(id);
        return new ResponseEntity<Map>(obj, HttpStatus.valueOf(obj.get("status").toString()));
    }
}
