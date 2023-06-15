export const adminRoutingMap = new Map<string, string>([
    ["Admin Home", "/admin/home"],
    ["Students settings", "/admin/student-settings"],
    ["Field settings", "/admin/field-settings"],
])

export function getAdminRouteNames(): string[] {
    return Array.from(adminRoutingMap.keys());
}