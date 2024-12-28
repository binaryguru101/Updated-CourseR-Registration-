import java.util.ArrayList;
import java.util.List;
public class Catalog {
    private List<Courses> Subjects;


    public Catalog() {
        this.Subjects = new ArrayList<>();
    }
    public void addSubject(Courses subject) {
        Subjects.add(subject);
    }
    public void removeSubject(Courses subject) {
        Subjects.remove(subject);
    }

    public void viewallsubjects(){
        if(Subjects.isEmpty()){
            System.out.println("No subjects found");
        }else{
            for(Courses subject : Subjects){
                System.out.println(subject);
            }
        }
    }

    public Courses findCourseByCode(String courseCode) {
        for (Courses course : Subjects) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null; // Return null if the course is not found
    }

    public List<Courses> getCoursesperSem(int semester){
        List<Courses> coursesem = new ArrayList<>();
        for(Courses subject : Subjects){
            if(subject.getSemester() == semester){
                coursesem.add(subject);
            }
        }
        return coursesem;
    }
    public List<Courses> getSubjects() {
        return new ArrayList<>(Subjects);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "Subjects=" + Subjects +
                '}';
    }
}
