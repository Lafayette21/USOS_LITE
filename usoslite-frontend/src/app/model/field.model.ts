import {Course} from "./course.model";

export class Field {
    id!: number
    name!: string;
    courses!: Course[];
}