import {User} from "./user.model";
import {Field} from "./field.model";

export class Student extends User {
    studentNumber!: string;
    field!: Field;
}