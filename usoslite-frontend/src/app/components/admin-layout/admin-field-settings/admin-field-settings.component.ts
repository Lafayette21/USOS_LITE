import {Component, OnInit} from '@angular/core';
import {Field} from "../../../model/field.model";
import {Router} from "@angular/router";
import {FieldService} from "../../../service/field.service";
import {HttpErrorResponse} from "@angular/common/http";
import {reload} from "../../../routing/redirection.provider";
import {Course} from "../../../model/course.model";
import {CourseService} from "../../../service/course.service";

@Component({
    selector: 'app-admin-field-settings',
    templateUrl: './admin-field-settings.component.html',
    styleUrls: ['./admin-field-settings.component.css']
})
export class AdminFieldSettingsComponent implements OnInit {
    fields!: Field[];
    courses!: Course[];

    coursesVisible: boolean[] = [];
    studentsVisible: boolean[] = [];

    newField: Field = new Field();
    fieldCreationFormVisible: boolean = false;
    createFieldButtonText: string = "Create new Field";

    constructor(private router: Router, private fieldService: FieldService, private courseService: CourseService) {
    }

    ngOnInit(): void {
        this.fieldService.getAllFields().subscribe({
            next: (response: Field[]) => {
                console.log("Fetched all fields");
                this.fields = response;
            },
            error: (error: HttpErrorResponse) => {
                console.error("Failed fetching all fields", error);
            }
        })
    }

    deleteField(fieldId: number): void {
        this.fieldService.deleteFieldById(fieldId).subscribe({
            next: (field: Field) => {
                console.log("Deleted field: " + field.name);
                reload(this.router.url);
            },
            error: (error: HttpErrorResponse) => {
                console.error("Failed deleting field", error);
            }
        });
    }

    showStudents(index: number) {
        this.coursesVisible[index] = false;
        this.studentsVisible[index] = !this.studentsVisible[index];
    }

    showFieldCourses(index: number) {
        this.studentsVisible[index] = false;
        this.coursesVisible[index] = !this.coursesVisible[index];
    }

    showFieldCreationForm(): void {
        this.fieldCreationFormVisible = !this.fieldCreationFormVisible;
        if (this.fieldCreationFormVisible) {
            this.createFieldButtonText = "Hide form";
        } else {
            this.createFieldButtonText = "Create new field";
        }
    }

    addNewField(): void {
        this.fieldService.addNewField(this.newField).subscribe({
            next: (field: Field) => {
                console.log("Successfully created field: "+ field.name);
                reload(this.router.url);
            },
            error: (error: HttpErrorResponse) => {
                console.error("Failed creating field", error);
            }
        })
    }
}
