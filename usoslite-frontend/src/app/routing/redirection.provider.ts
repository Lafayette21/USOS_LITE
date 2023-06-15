import {Router} from "@angular/router";
import {adminRoutingMap} from "./admin-routing.provider";
import {studentRoutingMap} from "./student-routing.provider";
import {UserLocalStorageService} from "../service/user-local-storage.service";
import {User} from "../model/user.model";

const routingMap = mergeMaps(adminRoutingMap, studentRoutingMap);

function mergeMaps<T, U>(map1: Map<T, U>, map2: Map<T, U>): Map<T, U> {
    const mergedMap = new Map<T, U>(map1);

    for (const [key, value] of map2) {
        mergedMap.set(key, value);
    }

    return mergedMap;
}

export function redirectTo(routeName: string, router: Router): void {
    router.navigate([getRouteForName(routeName)])
        .then(() => {
            console.log("Successfully redirected to: " + routeName)
        })
        .catch((error) => {
            console.error("Error navigating to page", error)
        });
}

function getRouteForName(routeName: string): string {
    if (routingMap.has(routeName)) {
        return String(routingMap.get(routeName));
    }
    throw new Error(("No routing for name: " + routeName));
}

export function logout(router: Router, localStorageService: UserLocalStorageService<User>): void {
    localStorageService.removeItem();
    router.navigate([""])
        .then(() => {
            console.log("Logged out user: ")
        })
        .catch((error) => {
            console.error("Error logging out user: ", error)
        })
}

export function reload(url: string): void {
    window.location.reload();
    console.log("Successfully reloaded: " + url)
}