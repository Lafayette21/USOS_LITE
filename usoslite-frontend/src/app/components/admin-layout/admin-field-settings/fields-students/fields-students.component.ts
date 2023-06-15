import {Component, Input, OnInit} from '@angular/core';
import {StudentService} from "../../../../service/student.service";
import {Student} from "../../../../model/student.model";
import {HttpErrorResponse} from "@angular/common/http";
import {reload} from "../../../../routing/redirection.provider";
import {Router} from "@angular/router";

@Component({
    selector: 'app-fields-students',
    templateUrl: './fields-students.component.html',
    styleUrls: ['./fields-students.component.css']
})
export class FieldsStudentsComponent implements OnInit {
    @Input() fieldId!: number;
    fieldStudents!: Student[];

    constructor(private router: Router, private studentService: StudentService) {
    }

    ngOnInit(): void {
        this.studentService.getStudentsByField(this.fieldId).subscribe({
            next: (value: Student[]) => {
                this.fieldStudents = value;
                console.log("Successfully fetched students for field with id: " + this.fieldId)
            },
            error: (error: HttpErrorResponse) => {
                console.error("Could not get students for field with id:" + this.fieldId, error)
            }
        })
    }

    deleteStudent(studentId: number) {
        this.studentService.deleteStudentById(studentId).subscribe({
            next: (student: Student) => {
                console.log("Deleted student: " + student.firstName + " " + student.lastName);
                reload(this.router.url);
            },
            error: (error: HttpErrorResponse) => {
                console.error("Failed deleting student", error);
            }
        });
    }
}
