import {Injectable} from "@angular/core";
import {User} from "../model/user.model";

@Injectable({
    providedIn: 'root'
})
export class UserLocalStorageService<T extends User> {
    private keyName: string = "user";

    constructor() {
    }

    persistUser(value: T): void {
        localStorage.setItem(this.keyName, JSON.stringify(value));
    }

    getUser(): T {
        const item = localStorage.getItem(this.keyName);
        return item ? JSON.parse(item) : null;
    }

    removeItem(): void {
        localStorage.removeItem(this.keyName);
    }

    clear(): void {
        localStorage.clear();
    }
}