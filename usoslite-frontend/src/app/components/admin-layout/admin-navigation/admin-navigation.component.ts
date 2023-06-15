import {Component} from '@angular/core';
import {getAdminRouteNames} from "../../../routing/admin-routing.provider";
import {Router} from "@angular/router";
import {logout, redirectTo} from "../../../routing/redirection.provider";
import {UserLocalStorageService} from "../../../service/user-local-storage.service";
import {Admin} from "../../../model/admin.model";

@Component({
    selector: 'app-admin-navigation',
    templateUrl: './admin-navigation.component.html',
    styleUrls: ['./admin-navigation.component.css']
})
export class AdminNavigationComponent {
    routeNames: string[] = getAdminRouteNames()

    constructor(private router: Router, private localStorageService: UserLocalStorageService<Admin>) {
    }

    redirectAdminTo(routeName: string) {
        redirectTo(routeName, this.router);
    }

    logoutAdmin(){
        logout(this.router, this.localStorageService);
    }
}
