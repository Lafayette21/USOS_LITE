import {Component, OnInit} from '@angular/core';
import {Admin} from "../../../model/admin.model";
import {UserLocalStorageService} from "../../../service/user-local-storage.service";

@Component({
    selector: 'app-admin-home',
    templateUrl: './admin-home.component.html',
    styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {
    admin!: Admin;

    constructor(private userLocalStorageService: UserLocalStorageService<Admin>) {
    }

    ngOnInit(): void {
        this.admin = this.userLocalStorageService.getUser();
    }
}
