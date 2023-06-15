import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {LoginComponent} from './components/login/login.component';
import {AppRoutingModule} from "./app-routing.module";
import {AdminLayoutComponent} from './components/admin-layout/admin-layout.component';
import {StudentLayoutComponent} from './components/student-layout/student-layout.component';
import {HttpClientModule} from "@angular/common/http";
import {StudentNavigationComponent} from './components/student-layout/student-navigation/student-navigation.component';
import {StudentHomeComponent} from './components/student-layout/student-home/student-home.component';
import {StudentCoursesComponent} from './components/student-layout/student-courses/student-courses.component';
import {AuthenticationService} from "./service/authentication.service";
import {UserLocalStorageService} from "./service/user-local-storage.service";
import {AdminNavigationComponent} from './components/admin-layout/admin-navigation/admin-navigation.component';
import {AdminHomeComponent} from './components/admin-layout/admin-home/admin-home.component';
import {
    AdminStudentSettingsComponent
} from './components/admin-layout/admin-student-settings/admin-student-settings.component';
import {
    AdminFieldSettingsComponent
} from './components/admin-layout/admin-field-settings/admin-field-settings.component';
import {CommonModule, Location} from "@angular/common";
import {FieldService} from "./service/field.service";
import {RouterModule} from "@angular/router";
import {
    FieldsCoursesComponent
} from './components/admin-layout/admin-field-settings/fields-courses/fields-courses.component';
import {CourseService} from "./service/course.service";
import {
    FieldsStudentsComponent
} from './components/admin-layout/admin-field-settings/fields-students/fields-students.component';
import {
    StudentTransferComponent
} from './components/admin-layout/admin-student-settings/student-transfer/student-transfer.component';
import {
    StudentCreationComponent
} from './components/admin-layout/admin-student-settings/student-creation/student-creation.component';

@NgModule({
    declarations: [
        AppComponent,
        AdminFieldSettingsComponent,
        AdminHomeComponent,
        AdminLayoutComponent,
        AdminNavigationComponent,
        AdminStudentSettingsComponent,
        LoginComponent,
        StudentCoursesComponent,
        StudentHomeComponent,
        StudentLayoutComponent,
        StudentNavigationComponent,
        FieldsCoursesComponent,
        FieldsStudentsComponent,
        StudentTransferComponent,
        StudentCreationComponent,
    ],
    imports: [
        AppRoutingModule,
        BrowserModule,
        CommonModule,
        FormsModule,
        HttpClientModule,
        RouterModule
    ],
    providers: [
        AuthenticationService,
        CourseService,
        FieldService,
        Location,
        UserLocalStorageService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
