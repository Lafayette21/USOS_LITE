package com.murbanowicz.usoslite.controller;

import com.murbanowicz.usoslite.model.Field;
import com.murbanowicz.usoslite.service.FieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/v1/field")
public class FieldController {
    private final FieldService service;

    public FieldController(FieldService fieldService) {
        this.service = fieldService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Field>> getAllFields() {
        return ResponseEntity.ok(service.getAllFields());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Field> getFieldById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFieldById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Field> createNewField(@RequestBody Field field) {
        return new ResponseEntity<>(service.createNewField(field), HttpStatus.CREATED);
    }
}
