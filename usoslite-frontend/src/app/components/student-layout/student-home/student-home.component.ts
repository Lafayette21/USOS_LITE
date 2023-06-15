import {Component, OnInit} from '@angular/core';
import {Student} from "../../../model/student.model";
import {UserLocalStorageService} from "../../../service/user-local-storage.service";

@Component({
    selector: 'app-student-home',
    templateUrl: './student-home.component.html',
    styleUrls: ['./student-home.component.css']
})
export class StudentHomeComponent implements OnInit{
    student!: Student;

    constructor(private userLocalStorageService: UserLocalStorageService<Student>) {
    }

    ngOnInit(): void {
        this.student = this.userLocalStorageService.getUser();
    }

}
