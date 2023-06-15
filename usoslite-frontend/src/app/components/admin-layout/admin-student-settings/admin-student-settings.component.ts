import {Component, OnInit} from '@angular/core';
import {Student} from "../../../model/student.model";
import {StudentService} from "../../../service/student.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {reload} from "../../../routing/redirection.provider";

@Component({
    selector: 'app-admin-student-settings',
    templateUrl: './admin-student-settings.component.html',
    styleUrls: ['./admin-student-settings.component.css']
})
export class AdminStudentSettingsComponent implements OnInit {
    students!: Student[];
    studentTransferVisible: boolean[] = [];
    studentCreationVisible: boolean = false;
    studentCreationButtonText: string = "Add new Student";

    constructor(private router: Router, private studentService: StudentService) {
    }

    ngOnInit(): void {
        this.studentService.getAllStudents().subscribe({
            next: (response: Student[]) => {
                console.log("Fetched all students");
                this.students = response;
            },
            error: (error: HttpErrorResponse) => {
                console.error("Failed fetching all students", error);
            }
        })
    }

    deleteStudent(studentId: number): void {
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

    showStudentTransferField(index: number): void {
        this.studentTransferVisible[index] = !this.studentTransferVisible[index];
    }

    showStudentCreationForm() {
        this.studentCreationVisible = !this.studentCreationVisible;
        if (this.studentCreationVisible) {
            this.studentCreationButtonText = "Hide form"
        } else {
            this.studentCreationButtonText = "Add new Student";
        }
    }
}
