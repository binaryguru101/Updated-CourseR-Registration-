import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TeachingAssistant extends Student{
    private List<Courses> Assigned;


    public TeachingAssistant(int ID, String email, String name, int semester, List<Courses> registered_Courses, List<Courses> completed_Courses, List<Courses> assigned) {
        super(ID, email, name, semester, registered_Courses, completed_Courses);
        Assigned = assigned;
    }

    public void setGrades(int studentID, String CourseCode, String newGrade, Map<Integer,Student> Students){
        Student student = Students.get(studentID);
        if(student != null){
            for(Courses courses : student.getRegistered_Courses()){
                if(courses.getCourseCode().equals(CourseCode)){
                    courses.setGrade(newGrade);
                    Students.put(studentID, student);
                    return;
                }else{
                    System.out.println("Course does not exist");
                }
            }
        }else{
            System.out.println("Student "+ studentID+" does not exist");
        }

    }

    public void allTeachingAssignedCourses(){
        for(Courses courses : Assigned){
            System.out.println(courses.getCourseCode());
        }
    }

    public void ViewStudentGrade(Courses course,Student student){
        student.GradesByCourse(course);
    };
    public void viewGrades(Courses course){
        List<Student> enrolled = course.getEnrolled_Students();
        System.out.println("Grades for "+ course.getCourseCode()+": ");
        for(Student student : enrolled){
            System.out.println("Marks for "+course.getCourseCode()+"is "+student.GradesByCourse(course));
        }
    };

    public void viewStudents(Courses course){
        List<Student> enrolled = course.getEnrolled_Students();
        System.out.println("Students for "+ course.getCourseCode()+": ");
        for(Student student : enrolled){
            System.out.println(student);
        }
    };

    public List<Courses> getAssigned() {
        return Assigned;
    }

    public void setAssigned(List<Courses> assigned) {
        Assigned = assigned;
    }
}
