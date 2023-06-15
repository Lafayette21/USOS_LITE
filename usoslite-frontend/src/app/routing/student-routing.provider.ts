export const studentRoutingMap = new Map<string, string>([
    ["Home", "/student/home"],
    ["Courses", "/student/courses"]
])

export function getStudentRouteNames(): string[] {
    return Array.from(studentRoutingMap.keys());
}