import {Component, Input, OnInit} from '@angular/core';
import {Field} from "../../../../model/field.model";
import {FieldService} from "../../../../service/field.service";
import {Student} from "../../../../model/student.model";
import {HttpErrorResponse} from "@angular/common/http";
import {StudentService} from "../../../../service/student.service";
import {Router} from "@angular/router";
import {reload} from "../../../../routing/redirection.provider";

@Component({
    selector: 'app-student-transfer',
    templateUrl: './student-transfer.component.html',
    styleUrls: ['./student-transfer.component.css']
})
export class StudentTransferComponent implements OnInit {
    @Input() student!: Student;
    fields!: Field[];
    selectedField!: Field;

    constructor(private router: Router, private fieldService: FieldService,
                private studentService: StudentService) {
    }

    ngOnInit(): void {
        this.fieldService.getAllFieldsExceptFor(this.student.field.id).subscribe({
            next: (fields: Field[]) => {
                console.log("Fetched all fields except for: " + this.student.field.id);
                this.fields = fields;
            },
            error: (error: HttpErrorResponse) => {
                console.error("Failed fetching fields", error);
            }
        })
    }

    transferStudent(): void {
        this.studentService.transferStudentToField(this.student, this.selectedField.id).subscribe({
            next: (student: Student) => {
                console.log("Successfully transferred student: " + student.firstName + " " + student.lastName);
                reload(this.router.url);
            },
            error: (error: HttpErrorResponse) => {
                console.error("Failed transferring student: ", error);
            }
        })
    }
}
