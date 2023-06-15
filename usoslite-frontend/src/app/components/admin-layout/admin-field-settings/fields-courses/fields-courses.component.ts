import {Component, Input, OnInit} from '@angular/core';
import {Course} from "../../../../model/course.model";
import {CourseService} from "../../../../service/course.service";
import {HttpErrorResponse} from "@angular/common/http";
import {reload} from "../../../../routing/redirection.provider";
import {Router} from "@angular/router";

@Component({
    selector: 'app-fields-courses',
    templateUrl: './fields-courses.component.html',
    styleUrls: ['./fields-courses.component.css']
})
export class FieldsCoursesComponent implements OnInit {
    @Input() fieldId!: number;
    fieldCourses!: Course[];
    courseFormVisible: boolean = false;

    courseCreationButtonText: string = "Add Course";
    newCourse: Course = new Course();

    constructor(private router: Router, private courseService: CourseService) {
    }

    deleteCourse(courseId: number): void {
        this.courseService.deleteCourseById(courseId).subscribe({
            next: (course: Course) => {
                console.log("Deleted course by name: " + course.courseName);
                reload(this.router.url);
            },
            error: (error: HttpErrorResponse) => {
                console.error("Failed deleting course", error);
            }

        })
    }

    ngOnInit(): void {
        this.courseService.getCoursesByField(this.fieldId).subscribe({
            next: (value: Course[]) => {
                this.fieldCourses = value;
                console.log("Successfully fetched courses for field with id: " + this.fieldId)
            },
            error: (error: HttpErrorResponse) => {
                console.error("Could not get courses for field with id:" + this.fieldId, error)
            }
        })
    }

    showCourseForm(): void {
        this.courseFormVisible = !this.courseFormVisible;
        if (this.courseFormVisible) {
            this.courseCreationButtonText = "Hide";
        } else {
            this.courseCreationButtonText = "Add Course";
        }
    }

    addNewCourse(): void {
        if (this.newCourse.ectsPoints < 1){
            alert("At least 1 ECTS point for subject required")
            return;
        }
        this.courseService.createCourseWithField(this.newCourse, this.fieldId).subscribe({
            next: (course: Course) => {
                console.log("Successfully created new course: "+ course.courseName);
                reload(this.router.url);
            },
            error: (error: HttpErrorResponse) => {
                console.error("Failed creating new Course", error);
            }
        })
    }
}
