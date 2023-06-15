import {Component} from '@angular/core';
import {AuthenticationService} from "../../service/authentication.service";
import {User} from "../../model/user.model";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthenticationRequest} from "../../model/authentication-request.model";
import {getRoutingForRole} from "../../routing/role-routing.provider";
import {UserLocalStorageService} from "../../service/user-local-storage.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent {
    authenticationRequest: AuthenticationRequest = new AuthenticationRequest("", "");

    constructor(private authenticationService: AuthenticationService,
                private userLocalStorageService: UserLocalStorageService<User>,
                private router: Router) {
    }

    submitLogin() {
        this.authenticationService.authenticate(this.authenticationRequest).subscribe({
            next: (user: User) => {
                console.log("Successfully logged in " + user.email)
                this.redirectUser(user)
            },
            error: (error: HttpErrorResponse) => {
                console.log("Log in failed", error)
                alert("Incorrect email or password")
            }
        })
    }

    private redirectUser(user: User) {
        this.userLocalStorageService.persistUser(user);

        this.router.navigate([getRoutingForRole(user.userRole)])
            .then(() => {
                console.log("Successfully redirected logged user")
            })
            .catch((error: HttpErrorResponse) => {
                console.error("Error navigating to page", error)
            });
    }
}
