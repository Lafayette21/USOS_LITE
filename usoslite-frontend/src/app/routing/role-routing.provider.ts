import {UserRole} from "../model/userRole.enum";

const roleToRoutingMap = new Map<UserRole, string>([
    [UserRole.ADMIN, "admin/home"],
    [UserRole.STUDENT, "student/home"],
])

export function getRoutingForRole(userRole: UserRole): string {
    if (roleToRoutingMap.has(userRole)) {
        return String(roleToRoutingMap.get(userRole));
    }
    throw new Error(("No routing for role: " + userRole))
}