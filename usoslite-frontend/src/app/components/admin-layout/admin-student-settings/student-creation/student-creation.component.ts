import {Component, OnInit} from '@angular/core';
import {Student} from "../../../../model/student.model";
import {Field} from "../../../../model/field.model";
import {HttpErrorResponse} from "@angular/common/http";
import {FieldService} from "../../../../service/field.service";
import {Router} from "@angular/router";
import {StudentService} from "../../../../service/student.service";
import {generateStudentEmail, generateStudentNumber} from "../../../../util/student.utils";
import {UserRole} from "../../../../model/userRole.enum";
import {reload} from "../../../../routing/redirection.provider";

@Component({
    selector: 'app-student-creation',
    templateUrl: './student-creation.component.html',
    styleUrls: ['./student-creation.component.css']
})
export class StudentCreationComponent implements OnInit {
    student!: Student;
    fieldsToChooseFrom!: Field[];
    chosenField!: Field;

    constructor(private router: Router, private fieldService: FieldService, private studentService: StudentService) {
    }

    ngOnInit(): void {
        this.student = new Student();
        this.fieldService.getAllFields().subscribe({
            next: (fields: Field[]) => {
                console.log("Fetched all fields");
                this.fieldsToChooseFrom = fields;
            },
            error: (error: HttpErrorResponse) => {
                console.error("Failed fetching fields", error);
            }
        })
    }

    submit(): void {
        this.student.studentNumber = String(generateStudentNumber());
        this.student.email = generateStudentEmail(this.student.firstName, this.student.lastName);
        this.student.password = this.student.studentNumber;
        this.student.userRole = UserRole.STUDENT;

        this.studentService.createStudent(this.student, this.chosenField.id).subscribe({
            next: (student: Student) => {
                console.log("Created new Student: " + student.firstName + " " + student.lastName);
                reload(this.router.url);
            },
            error: (error: HttpErrorResponse) => {
                console.error("Failed creating student", error)
            }
        })
    }


}
