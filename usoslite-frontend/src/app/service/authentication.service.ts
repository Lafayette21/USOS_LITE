import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user.model";
import {AuthenticationRequest} from "../model/authentication-request.model";

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {
    apiBaseUrl = "http://localhost:8080/app/v1/authenticate"

    constructor(private http: HttpClient) {}

    authenticate(request: AuthenticationRequest): Observable<User> {
        return this.http.post<User>(this.apiBaseUrl, request);
    }
}