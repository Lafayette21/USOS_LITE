import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {LoginComponent} from "./components/login/login.component";
import {AdminLayoutComponent} from "./components/admin-layout/admin-layout.component";
import {StudentLayoutComponent} from "./components/student-layout/student-layout.component";
import {StudentHomeComponent} from "./components/student-layout/student-home/student-home.component";
import {StudentCoursesComponent} from "./components/student-layout/student-courses/student-courses.component";
import {AdminHomeComponent} from "./components/admin-layout/admin-home/admin-home.component";
import {
    AdminStudentSettingsComponent
} from "./components/admin-layout/admin-student-settings/admin-student-settings.component";
import {
    AdminFieldSettingsComponent
} from "./components/admin-layout/admin-field-settings/admin-field-settings.component";


const routes: Routes = [
    {
        path: '',
        component: LoginComponent
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: "admin",
        component: AdminLayoutComponent,
        children: [
            {
                path: "home",
                component: AdminHomeComponent
            },
            {
                path: "student-settings",
                component: AdminStudentSettingsComponent
            },
            {
                path: "field-settings",
                component: AdminFieldSettingsComponent
            }
        ]
    },
    {
        path: "student",
        component: StudentLayoutComponent,
        children: [
            {
                path: "home",
                component: StudentHomeComponent
            },
            {
                path: "courses",
                component: StudentCoursesComponent
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {

}