import {UserRole} from "./userRole.enum";

export abstract class User {
    id!: number;
    email!: string;
    password!: string;
    firstName!: string;
    lastName!: string;
    userRole!: UserRole;
}