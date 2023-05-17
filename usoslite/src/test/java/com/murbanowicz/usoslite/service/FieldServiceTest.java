package com.murbanowicz.usoslite.service;

import com.murbanowicz.usoslite.model.Field;
import com.murbanowicz.usoslite.repository.FieldRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FieldServiceTest {
    @Mock
    private FieldRepository fieldRepositoryMock;
    private FieldService fieldService;

    @BeforeEach
    void setUp() {
        fieldService = new FieldService(fieldRepositoryMock);
    }

    @Test
    void shouldGetAllFields() {
        // when
        fieldService.getAllFields();
        // then
        verify(fieldRepositoryMock).findAll();
    }

    @Test
    void shouldGetStudentById() {
        //given
        long fieldId = 1L;
        Field expectedField = new Field("Informatyka");
        //when
        when(fieldRepositoryMock.findById(fieldId)).thenReturn(Optional.of(expectedField));
        //then
        assertThat(fieldService.getFieldById(fieldId)).isEqualTo(expectedField);
    }

    @Test
    void shouldAddNewField() {
        // given
        Field expectedField = new Field("Informatyka");
        // when
        fieldService.createNewField(expectedField);
        // then
        ArgumentCaptor<Field> fieldArgumentCaptor = ArgumentCaptor.forClass(Field.class);
        verify(fieldRepositoryMock).save(fieldArgumentCaptor.capture());

        Field actualField = fieldArgumentCaptor.getValue();
        assertThat(actualField).isEqualTo(expectedField);
    }

    @Test
    void shouldDeleteFieldById() {
        //given
        long fieldId = 1L;
        Field expectedField = new Field("Informatyka");
        //when
        when(fieldRepositoryMock.findById(fieldId)).thenReturn(Optional.of(expectedField));
        fieldService.deleteFieldById(fieldId);
        //Then
        verify(fieldRepositoryMock).delete(expectedField);
    }

    @Test
    void shouldUpdateFieldById() {
        //given
        long fieldId = 1L;
        Field fieldToUpdate = new Field("Informatyka");
        //when
        when(fieldRepositoryMock.findById(fieldId)).thenReturn(Optional.of(fieldToUpdate));
        Field expectedUpdatedField = new Field("Fizyka");
        Field actualUpdatedField = fieldService.updateFieldById(fieldId, expectedUpdatedField);
        //Then
        assertThat(actualUpdatedField).isEqualTo(expectedUpdatedField);
    }
}