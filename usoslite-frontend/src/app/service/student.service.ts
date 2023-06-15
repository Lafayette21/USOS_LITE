import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Student} from "../model/student.model";

@Injectable({
    providedIn: 'root'
})
export class StudentService {
    apiBaseUrl = "http://localhost:8080/app/v1/student"

    constructor(private http: HttpClient) {
    }

    getStudentByMail(email: string): Observable<Student> {
        return this.http.get<Student>(this.apiBaseUrl + "/get-by-email/" + email);
    }

    getAllStudents(): Observable<Student[]> {
        return this.http.get<Student[]>(this.apiBaseUrl + "/get-all");
    }

    getStudentsByField(fieldId: number): Observable<Student[]> {
        return this.http.get<Student[]>(this.apiBaseUrl + "/get-students-by-field/" + fieldId);
    }

    deleteStudentById(id: number): Observable<Student> {
        return this.http.delete<Student>(this.apiBaseUrl + "/delete-by-id/" + id);
    }

    transferStudentToField(student: Student, fieldId: number): Observable<Student> {
        return this.http.put<Student>(this.apiBaseUrl + "/" + student.id + "/set-field/" + fieldId, student);
    }

    createStudent(student: Student, fieldId: number): Observable<Student> {
        return this.http.post<Student>(this.apiBaseUrl + "/create/with-field-id/" + fieldId, student);
    }
}