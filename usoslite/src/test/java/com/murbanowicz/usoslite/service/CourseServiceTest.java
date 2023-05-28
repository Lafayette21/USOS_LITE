package com.murbanowicz.usoslite.service;

import com.murbanowicz.usoslite.model.Course;
import com.murbanowicz.usoslite.model.Field;
import com.murbanowicz.usoslite.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepositoryMock;
    @Mock
    private FieldService fieldServiceMock;
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        courseService = new CourseService(courseRepositoryMock, fieldServiceMock);
    }

    @Test
    void shouldGetAllFields() {
        // when
        courseService.getAllCourses();
        // then
        verify(courseRepositoryMock).findAll();
    }

    @Test
    void shouldGetCourseById() {
        //given
        long courseId = 1L;
        Course expectedCourse = new Course("Matematyka", 7);
        //when
        when(courseRepositoryMock.findById(courseId)).thenReturn(Optional.of(expectedCourse));
        //then
        assertThat(courseService.getCourseById(courseId)).isEqualTo(expectedCourse);
    }

    @Test
    void shouldAddNewCourse() {
        // given
        Course expectedCourse = new Course("Matematyka", 7);
        // when
        courseService.createCourse(expectedCourse);
        // then
        ArgumentCaptor<Course> courseArgumentCaptor = ArgumentCaptor.forClass(Course.class);
        verify(courseRepositoryMock).save(courseArgumentCaptor.capture());

        Course actualCourse = courseArgumentCaptor.getValue();
        assertThat(actualCourse).isEqualTo(expectedCourse);
    }

    @Test
    void shouldDeleteCourseById() {
        //given
        long courseId = 1L;
        Course expectedCourse = new Course("Matematyka", 7);
        //when
        when(courseRepositoryMock.findById(courseId)).thenReturn(Optional.of(expectedCourse));
        courseService.deleteCourseById(courseId);
        //Then
        verify(courseRepositoryMock).delete(expectedCourse);
    }

    @Test
    void shouldUpdateCourseById() {
        //given
        long courseId = 1L;
        Course courseToUpdate = new Course("Matematyka", 7);
        //when
        when(courseRepositoryMock.findById(courseId)).thenReturn(Optional.of(courseToUpdate));
        Course expectedUpdatedCourse = new Course("Fizyka", 8);
        Course courseUpdatedField = courseService.updateById(courseId, expectedUpdatedCourse);
        //Then
        assertThat(courseUpdatedField).isEqualTo(expectedUpdatedCourse);
    }

    @Test
    void shouldGetCoursesByField() {
        // given
        Long fieldId = 1L;
        Field field = new Field("Computer Science");
        Course maths = new Course("Maths", 7, field);
        Course programming = new Course("C++", 5, field);
        Course algebra = new Course("Algebra", 5, mock(Field.class));
        //when
        when(fieldServiceMock.getFieldById(fieldId)).thenReturn(field);
        when(courseRepositoryMock.findAll()).thenReturn(List.of(maths, programming, algebra));
        List<Course> coursesByField = courseService.getCoursesByField(fieldId);
        //then
        assertThat(coursesByField).isEqualTo(List.of(maths, programming));
    }

    @Test
    void shouldAssignCourseToField(){
        // given
        Long fieldId = 1L;
        Field field = new Field("Computer Science");
        Long courseId = 1L;
        Course maths = new Course("Maths", 7, field);
        //when
        when(fieldServiceMock.getFieldById(fieldId)).thenReturn(field);
        when(courseRepositoryMock.findById(courseId)).thenReturn(Optional.of(maths));
        Course course = courseService.assignCourseToField(courseId, fieldId);
        //then
        assertThat(course.getField()).isEqualTo(field);
    }
}
