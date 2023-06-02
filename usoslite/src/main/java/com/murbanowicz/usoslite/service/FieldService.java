package com.murbanowicz.usoslite.service;

import com.murbanowicz.usoslite.exception.FieldNotFoundException;
import com.murbanowicz.usoslite.model.Field;
import com.murbanowicz.usoslite.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FieldService {
    private final FieldRepository repository;

    public List<Field> getAllFields() {
        return repository.findAll();
    }

    public Field getFieldById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException(id));
    }

    public Field createNewField(Field field) {
        return repository.save(field);
    }

    public Field deleteFieldById(Long id) {
        Field fieldToDelete = getFieldById(id);
        repository.delete(fieldToDelete);
        return fieldToDelete;
    }

    @Transactional
    public Field updateFieldById(long fieldId, Field updatedField) {
        Field fieldToUpdate = getFieldById(fieldId);
        fieldToUpdate.setName(updatedField.getName());
        return fieldToUpdate;
    }

    public List<Field> getAllFieldsExceptFor(Long fieldId) {
        Field fieldToExclude = getFieldById(fieldId);
        return getAllFields().stream()
                .filter(field -> !field.equals(fieldToExclude))
                .collect(Collectors.toList());
    }
}
