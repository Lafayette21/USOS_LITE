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

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
}
