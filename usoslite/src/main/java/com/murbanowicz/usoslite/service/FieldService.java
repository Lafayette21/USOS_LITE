package com.murbanowicz.usoslite.service;

import com.murbanowicz.usoslite.exception.FieldNotFoundException;
import com.murbanowicz.usoslite.model.Field;
import com.murbanowicz.usoslite.repository.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {
    private final FieldRepository repository;

    public FieldService(FieldRepository repository) {
        this.repository = repository;
    }

    public List<Field> getAllFields() {
        return repository.findAll();
    }

    public Field getFieldById(Long id) {
        return repository.findById(id)
                .orElseThrow(()->new FieldNotFoundException(id));
    }
}
