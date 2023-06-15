import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Course} from "../model/course.model";
import {Field} from "../model/field.model";

@Injectable({
    providedIn: 'root'
})
export class CourseService {
    apiBaseUrl = "http://localhost:8080/app/v1/course";

    constructor(private http: HttpClient) {
    }

    deleteCourseById(courseId: number): Observable<Course>{
        return this.http.delete<Course>(this.apiBaseUrl + "/delete-by-id/" + courseId);
    }

    getCoursesByField(fieldId: number): Observable<Course[]>{
        return this.http.get<Course[]>(this.apiBaseUrl + "/get-by-field/" + fieldId)
    }

    createCourseWithField(course: Course, fieldId: number): Observable<Course> {
        return this.http.post<Course>(this.apiBaseUrl  + "/create/assign-to-field/" + fieldId, course);
    }
}