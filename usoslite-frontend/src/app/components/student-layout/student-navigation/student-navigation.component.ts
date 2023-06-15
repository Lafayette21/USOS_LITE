import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {getStudentRouteNames} from "../../../routing/student-routing.provider";
import {logout, redirectTo} from "../../../routing/redirection.provider";
import {UserLocalStorageService} from "../../../service/user-local-storage.service";
import {Student} from "../../../model/student.model";

@Component({
    selector: 'app-student-navigation',
    templateUrl: './student-navigation.component.html',
    styleUrls: ['./student-navigation.component.css']
})
export class StudentNavigationComponent {
    elements: string[] = getStudentRouteNames();


    constructor(private router: Router, private localStorageService: UserLocalStorageService<Student>) {
    }

    redirectStudentTo(routeName: string): void {
        redirectTo(routeName, this.router);
    }

    logoutStudent(): void {
        logout(this.router, this.localStorageService);
    }
}
