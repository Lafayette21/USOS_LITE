import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Field} from "../model/field.model";

@Injectable({
    providedIn: 'root'
})
export class FieldService {
    apiBaseUrl = "http://localhost:8080/app/v1/field";

    constructor(private http: HttpClient) {
    }

    getAllFields(): Observable<Field[]> {
        return this.http.get<Field[]>(this.apiBaseUrl + "/get-all");
    }

    getAllFieldsExceptFor(fieldId: number): Observable<Field[]> {
        return this.http.get<Field[]>(this.apiBaseUrl + "/get-all/except/" + fieldId);
    }

    deleteFieldById(fieldId: number): Observable<Field> {
        return this.http.delete<Field>(this.apiBaseUrl + "/delete-by-id/" + fieldId);
    }

    addNewField(field: Field): Observable<Field> {
        return this.http.post<Field>(this.apiBaseUrl + "/create", field);
    }
}