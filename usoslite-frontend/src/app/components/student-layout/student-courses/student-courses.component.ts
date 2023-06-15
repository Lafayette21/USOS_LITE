import {Component, OnInit} from '@angular/core';
import {Student} from "../../../model/student.model";
import {ActivatedRoute} from "@angular/router";
import {UserLocalStorageService} from "../../../service/user-local-storage.service";
import {Course} from "../../../model/course.model";
import {HttpErrorResponse} from "@angular/common/http";
import {CourseService} from "../../../service/course.service";

@Component({
    selector: 'app-student-courses',
    templateUrl: './student-courses.component.html',
    styleUrls: ['./student-courses.component.css']
})
export class StudentCoursesComponent implements OnInit {
    student!: Student;
    courses!: Course[];

    constructor(private route: ActivatedRoute, private userLocalStorageService: UserLocalStorageService<Student>,
                private courseService: CourseService) {
    }

    ngOnInit(): void {
        this.student = this.userLocalStorageService.getUser();
        this.courseService.getCoursesByField(this.student.field.id).subscribe({
            next: (courses: Course[]) => {
                this.courses = courses;
                console.log("Successfully fetched courses for field with id: " + this.student.field.id)
            },
            error: (error: HttpErrorResponse) => {
                console.error("Could not get courses for field with id:" + this.student.field.id, error)
            }
        })
    }
}
