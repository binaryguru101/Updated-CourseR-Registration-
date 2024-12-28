import java.time.LocalDate;
import java.util.*;

public class Admin extends User {
    private Catalog catalog;
    private Map<Integer,Student> students;
    private Map<Integer, Professor> professor;
    private List<Complaint> Complaints;
    private Map<Integer,TeachingAssistant> teachingAssistants;

    public Admin(int ID, String email, String name, Catalog catalog, Map<Integer,
            Student> students, Map<Integer, Professor>  professor, List<Complaint> Complaints
            , Map<Integer,TeachingAssistant> teachingAssistants) {
        super(ID, email, name);
        this.catalog = catalog;
        this.students = students;
        this.professor = professor;
        this.Complaints = Complaints;
        this.teachingAssistants = teachingAssistants;
    }

    public void viewallcourses(){
        catalog.viewallsubjects();
    }

//    public void addcourse(Courses courses){
//        catalog.addSubject(courses);
//        System.out.println("Successfully added course"+courses);
//    }

    public void addcourse(Scanner input){
        System.out.print("Enter course name: ");
        String name = input.nextLine();
        System.out.print("Enter course code: ");
        String code = input.nextLine();
        System.out.print("Enter credits: ");
        int credits = input.nextInt();
        input.nextLine(); // Consume newline
        System.out.print("Enter schedule: ");
        String schedule = input.nextLine();
        System.out.print("Enter syllabus: ");
        String syllabus = input.nextLine();
        System.out.print("Enter prerequisites (comma-separated): ");
        List<String> prerequisites = Arrays.asList(input.nextLine().split(","));
        System.out.print("Enter enrollment limit: ");
        int limit = input.nextInt();
        input.nextLine(); // Consume newline
        System.out.print("Enter grade scale: ");
        String gradeScale = input.nextLine();

        Courses newCourse = new Courses(name, code, credits, credits, null, schedule, syllabus, prerequisites, limit,new ArrayList<>(), gradeScale,null);
        catalog.addSubject(newCourse);
        System.out.println("Course added successfully.");
    }

//    public void deletecourse(Scanner input){
//        System.out.print("Enter course code: ");
//        String code = input.nextLine();
//
//        Courses toremove=null;
//
//        if(Courses courses : catalog.viewallsubjects(){}
//
//    }

    public void deletecourse(Courses courses){
        catalog.removeSubject(courses);
        System.out.println("Successfully deleted course"+courses);
    }
//redundant

//    public void addcourse(String courseCode, String courseName, int credits, String timings,
//                          List<String> prerequisites,int semester,String syllabus,int limit, String grade){
//        Professor defaultprof=null;
//        Courses newCourse= new Courses(courseName,courseCode,credits,semester,defaultprof,timings,syllabus,prerequisites,limit,grade);
//    }

    public void updatecourse(Courses courses, String courseName, int credits, String timings,List<String>Prereq,int limit){
        courses.setCourseName(courseName);
        courses.setCredit(credits);
        courses.setTiming(timings);
        courses.setLimit(limit);
        courses.setPreReq(Prereq);

    }

    public void viewStudentRecord(int Student_ID){
        Student student = students.get(Student_ID);
        if(student != null){
            System.out.println("Successfully viewed student record"+student);
        }else{
            System.out.println("Student not found");
        }
    }

//    public void updateStudentRecord(int Student_ID,Student updatedstudent){
//        if(students.containsKey(Student_ID)){
//            students.put(Student_ID, updatedstudent);
//            System.out.println("Successfully updated student record"+updatedstudent);
//
//        }else{
//            System.out.println("Student not found");
//        }
//    }

    public void updateStudentMarks(Scanner input){
        System.out.print("Enter student ID: ");
        int Student_ID = input.nextInt();
        input.nextLine();

        Student student = students.get(Student_ID);
        if(student == null){
            System.out.println("Student not found");
            return;
        }
        System.out.print("Enter course Code: ");
        String courseCode = input.nextLine();

        System.out.print("Enter New Grade : ");
        String grade = input.nextLine();

        boolean found=false;
        for(Courses c : student.getRegistered_Courses()){
            if(c.getCourseCode().equals(courseCode)){
                c.setGrade(grade);
                System.out.println("Successfully updated student record"+student.getName());
                found=true;
                break;
            }
        }
        if(!found){
            System.out.println("Student not found");
        }

    }

    public void updateStudentRecord(Scanner input){
        System.out.print("Enter student ID: ");
        int Student_ID = input.nextInt();
        input.nextLine();

        Student student = students.get(Student_ID);

        if(student == null){
            System.out.println("Student not found");
            return;
        }

        System.out.println("Current details for Student ID " + Student_ID + ":");
        System.out.println(student);

        System.out.println("Enter new email (or press Enter to keep current): ");
        String newEmail = input.nextLine();
        if (!newEmail.isEmpty()) {
            student.setEmail(newEmail);
        }

        System.out.println("Enter new name (or press Enter to keep current): ");
        String newName = input.nextLine();
        if (!newName.isEmpty()) {
            student.setName(newName);
        }
        boolean correct= false;

        System.out.println("Enter New semester : ");

        int newSemester = input.nextInt();
        input.nextLine();
        student.setSemester(newSemester);
        changeSemesterForStudent(student);

    }

    public void changeSemesterForStudent(Student student) {
        student.updateCompletedCourses();
        System.out.println("Semester updated for student: " + student.getName());
    }




//    public void updatestudentmarks(int StudentID, String Coursecode, String Newgrade) {
//        if (students.containsKey(StudentID)) {
//            Student student = students.get(StudentID);
//            System.out.println("Student found: " + student);
//
//            boolean found = false;
//
//            for (Courses course : student.getRegistered_Courses()) {
//                if (course.getCourseCode().equals(Coursecode)) {
//                    course.setGrade(Newgrade);
//                    System.out.println("Successfully updated grade for course " + Coursecode + " to " + Newgrade + " for student ID " + StudentID);
//                    found = true;
//                    break;
//                }
//            }
//
//            if (!found) {
//                System.out.println("Course with code " + Coursecode + " not found for student ID " + StudentID);
//            }
//
//        } else {
//            System.out.println("Student with ID " + StudentID + " not found");
//        }
//    }

    public void assignTA(int taID,Courses courses){
        TeachingAssistant teachingAssistant = teachingAssistants.get(taID);
        if(teachingAssistant != null){
            courses.setTeachingAssistant(teachingAssistant);
            teachingAssistant.getAssigned().add(courses);
            System.out.println("Assigned " + courses.getCourseCode() + " to " + teachingAssistant);
        }
    }

    public void assignSubtoTA(Scanner input){
        System.out.print("Enter TA ID: ");
        int TA_ID = input.nextInt();

        input.nextLine();

        System.out.println("Enter Course Code :");
        String courseCode = input.nextLine();


        Courses course = catalog.findCourseByCode(courseCode);

        if(course == null){
            System.out.println("Course not found");
        }else{
            assignTA(TA_ID,course);
        }
    }

    public void updateDropDeadline(Scanner input) {
        System.out.print("Enter new drop deadline (YYYY-MM-DD): ");
        String dateInput = input.nextLine();
        LocalDate newDeadline = LocalDate.parse(dateInput);
        Courses.setDropDeadline(newDeadline);
        System.out.println("Drop deadline updated to: " + newDeadline);
    }


    public void assignprofessors(int proffesorID,Courses courses){

        Professor professor1= this.professor.get(proffesorID);
        if(professor1 != null){
            courses.setProfessor(professor1);
            professor1.getTeachingCourses().add(courses);
            System.out.println("Successfully assigned professor "+professor1+" to "+courses.getCourseCode());
        }}

    public void assignprof(Scanner input){
        System.out.print("Enter Professor ID: ");
        int professorID = input.nextInt();
        input.nextLine(); // Consume newline

        System.out.print("Enter Course Code: ");
        String courseCode = input.nextLine();

        Courses course = catalog.findCourseByCode(courseCode);
        if(course == null){
            System.out.println("Course not found");
        }
        else{
            assignprofessors(professorID,course);
            System.out.println("SUCCESS ADDED PROF");
        }
    }

    public void addstudent(Student student){
        students.put(student.getID(), student);
        System.out.println("Successfully added student "+student);

    }

    public void addTA(TeachingAssistant TA){
        teachingAssistants.put(getID(),TA);
        System.out.println("Successfully added TA "+TA);
    }
    public void addprof(Professor addproffessor){
        professor.put(addproffessor.getID(),addproffessor);
        System.out.println("Successfully added professor "+addproffessor);
    }


    public void AllComplaints(){
        for(Complaint complaint : Complaints){
            System.out.println(complaint);
        }
    }
//    public List<Complaint> FilterByStatus(String Status){
//        List<Complaint> FilteredComplaints = new ArrayList<>();
//        for(Complaint complaint : Complaints){
//            if(complaint.getStatus().equals(Status)){
//                FilteredComplaints.add(complaint);
//
//            }
//        }
//        return FilteredComplaints;
//    }

    public void FilterByStatus(Scanner input){
        System.out.print("Enter Status to Filter (Pending/Resolved): ");
        String status = input.nextLine();

        boolean found = false;
        for (Complaint complaint : Complaints) {
            if (complaint.getStatus().equalsIgnoreCase(status)) {
                System.out.println("Complaint ID: " + complaint.getComplaintID());
                System.out.println("Student ID: " + complaint.getStudent());
                System.out.println("Description: " + complaint.getDescription());
                System.out.println("Status: " + complaint.getStatus());
                System.out.println("Resolution: " + complaint.getResoloutionDetails());
                System.out.println("-----------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No complaints found with status: " + status);
        }
    }


//    public List<Complaint> FilterByDate(LocalDate Date){
//        List<Complaint> FilteredComplaints = new ArrayList<>();
//        for(Complaint complaint : Complaints){
//            if(complaint.getDate().equals(Date)){
//                FilteredComplaints.add(complaint);
//            }
//        }
//        return FilteredComplaints;
//    }


    public void FilterByDate(Scanner input){
        System.out.print("Enter Date to Filter(YYYY-MM-DD): ");
        String date = input.nextLine();

        boolean found = false;
        for (Complaint complaint : Complaints) {
            if (complaint.getDate().equals(date)) {
                System.out.println("Complaint ID: " + complaint.getComplaintID());
                System.out.println("Student ID: " + complaint.getStudent());
                System.out.println("Description: " + complaint.getDescription());
                System.out.println("Status: " + complaint.getStatus());
                System.out.println("Resolution: " + complaint.getResoloutionDetails());
                System.out.println("-----------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No complaints found for date: " + date);
        }
    }


//    public void updateComplaintResoloution(int ComplainID,String ResoloutionDetails){
//        Complaint complaint = Complaints.get(ComplainID);
//        if(complaint != null){
//            complaint.setResoloutionDetails(ResoloutionDetails);
//
//            complaint.setStatus("Resolved");
//
//            System.out.println("Successfully updated complaint for "+complaint.getComplaintID());
//        }else{
//            System.out.println("Complaint not found Please try again ");
//        }
//    }

    private Complaint findComplaintByID(int complaintID) {
        for (Complaint complaint : Complaints) {
            if (complaint.getComplaintID() == complaintID) {
                return complaint;
            }
        }
        return null;
    }

    public void updateComplaintResolution(Scanner input) {
        System.out.print("Enter Complaint ID to update: ");
        int complaintID = input.nextInt();
        input.nextLine(); // Consume newline

        Complaint complaint = findComplaintByID(complaintID);
        if (complaint != null) {
            System.out.print("Enter new resolution details: ");
            String newResolution = input.nextLine();
            complaint.setResoloutionDetails(newResolution);
            complaint.setStatus("Resolved");
            System.out.println("Successfully updated complaint resolution.");
        } else {
            System.out.println("Complaint not found.");
        }
    }








    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }

    public void setStudents(Map<Integer, Student> students) {
        this.students = students;
    }

    public Map<Integer, Professor>  getProfessor() {
        return professor;
    }

    public void setProfessor(Map<Integer, Professor>  professor) {
        this.professor = professor;
    }

    public List<Complaint> getComplaints() {
        return Complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        Complaints = complaints;
    }
}
