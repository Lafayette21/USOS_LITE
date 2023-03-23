package com.murbanowicz.usoslite.controller;

import com.murbanowicz.usoslite.model.Field;
import com.murbanowicz.usoslite.service.FieldService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
