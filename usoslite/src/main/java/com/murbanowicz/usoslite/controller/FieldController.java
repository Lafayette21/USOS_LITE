package com.murbanowicz.usoslite.controller;

import com.murbanowicz.usoslite.model.Field;
import com.murbanowicz.usoslite.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/v1/field")
@RequiredArgsConstructor
public class FieldController {
    private final FieldService service;

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

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Field> deleteFieldById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteFieldById(id));
    }

    @PutMapping("/update-by-id/{id}")
    public ResponseEntity<Field> updateFieldById(@PathVariable Long id, @RequestBody Field updatedField){
        return ResponseEntity.ok(service.updateFieldById(id, updatedField));
    }
}
