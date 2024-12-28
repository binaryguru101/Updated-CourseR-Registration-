import java.util.*;


import static java.util.Arrays.asList;

public class Main {
    private static Catalog catalog = new Catalog();
    private static Map<Integer, Student> students = new HashMap<>();
    private static Map<Integer, Professor> professors = new HashMap<>();
    private static List<Complaint> complaints = new ArrayList<>();
    private static Admin admin;
    private static Map<Integer,TeachingAssistant> teachingAssistants = new HashMap<>();

    // Sample courses
    private static Courses course1 = new Courses("Introduction to Computer Science", "CS101", 4, 4, null, "MWF 10:00-11:00 AM", "Basic", new ArrayList<>(), 30, new ArrayList<>(),"A",null);
    private static Courses course2 = new Courses("Data Structures and Algorithms", "CS202", 4, 4, null, "TR 1:00-2:30 PM", "Study", new ArrayList<>(), 25,new ArrayList<>(), "D",null);
    private static Courses course3 = new Courses("Database Systems", "CS303", 4, 4, null, "MW 2:00-3:30 PM", "Introduction", new ArrayList<>(), 20, new ArrayList<>(),"C",null);

    public static void main(String[] args) throws Exceptions.CourseFullException, Exceptions.InvalidLoginException {
        // Initialize data
        Professor prof1 = new Professor(3021, "prof1@gmail.com", "Mike", new ArrayList<>(), "9-11 MW");
        Professor prof2 = new Professor(2023, "prof2@gmail.com", "Hu", new ArrayList<>(), "11-7 WED-THUR");
        professors.put(prof1.getID(), prof1);
        professors.put(prof2.getID(), prof2);

        Student john1 = new Student(2023566, "203@123", "John", 4, new ArrayList<>(), new ArrayList<>());
        Student amy1 = new Student(2023567, "204@123", "Amy", 4, new ArrayList<>(), new ArrayList<>());
        students.put(john1.getID(), john1);
        students.put(amy1.getID(), amy1);

        admin = new Admin(301, "admin@yahoo.com", "Hu", catalog, students, professors, complaints, teachingAssistants);

        catalog.addSubject(course1);
        catalog.addSubject(course2);
        catalog.addSubject(course3);

//        admin.addcourse(course2);
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the University Course Registration System");
            System.out.println("1. Signup as a Student");
            System.out.println("2. Signup as a Professor");
            System.out.println("3. Signup as a Teaching Assistant");
            System.out.println("4. Login as Student");
            System.out.println("5. Login as Professor");
            System.out.println("6. Login as Administrator");
            System.out.println("7. Login as Teaching Assistant");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent(input);
                    break;
                case 2:
                    addProfessor(input);
                    break;
                case 3:
                    addTeachingAssistant(input);
                    break;
                case 4:
                    handleStudentLogin(input);
                    break;
                case 5:
                    handleProfessorLogin(input);
                    break;
                case 6:
                    handleAdminLogin(input);
                    break;
                case 7:
                    handleTaLogin(input);
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        input.close();
    }





    private static void handleTaLogin(Scanner input) {
        System.out.print("Enter your TA id: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.println("Enter Password: ");
        String password = input.nextLine();

        TeachingAssistant teachingAssistant = teachingAssistants.get(id);
        if (teachingAssistant == null) {
            System.out.println("Invalid ID. Please try again.");
        } else {
            TaMenu(input, teachingAssistant);
        }
    }

    private static void TaMenu(Scanner input, TeachingAssistant TA) {
        boolean taRunning = true;

        while (taRunning) {
            System.out.println("\n--- TA Menu ---");
            // Add options for TA menu
            System.out.println("1. View Assigned Courses");
            System.out.println("2. Manage Student Grades");
            System.out.println("3. View Students");
            System.out.println("4. View Student Grades");
            System.out.println("5. Logout");

            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    TA.allTeachingAssignedCourses(); // Assuming this method exists
                    break;
                case 2:
                    UpdateStudentGrade(input, TA);
                    break;
                case 3:
                    TAviewStudent(input, TA);
                    break;
                case 4:
                    System.out.println("Enter Course code");
                    String courseCode = input.nextLine();

                    Courses courseByCode = catalog.findCourseByCode(courseCode);
                    if(courseByCode != null) {
                        TA.viewGrades(courseByCode);
                    }
                    else{
                        System.out.println("Invalid Course Code. Please try again.");
                    }



                    break;
                case 5:
                    taRunning = false; // Logout
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }





    private static void handleStudentLogin(Scanner input) throws Exceptions.CourseFullException {
        System.out.print("Enter student ID: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        Student student = students.get(id);
        if (student == null) {
            try{
                throw new Exceptions.InvalidLoginException("Invalid student ID or password. Please try again.");
            } catch (Exceptions.InvalidLoginException e) {
                throw new RuntimeException(e);
            }
//            System.out.println("Student with ID " + id + " not found.");
        } else {
            studentmenu(input, student);
        }
    }

    private static void studentmenu(Scanner input, Student student) throws Exceptions.CourseFullException {
        boolean studentRunning = true;
        while (studentRunning) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. View All Courses");
            System.out.println("2. View Registered Courses");
            System.out.println("3. Register for a Course");
            System.out.println("4. View Schedule");
            System.out.println("5. Drop a Course");
            System.out.println("6. View CGPA");
            System.out.println("7. View SGPA");
            System.out.println("8. View Grades");
            System.out.println("9. Register a Complaint");
            System.out.println("10. View Complaint Status");
            System.out.println("11. Give Feedback");
            System.out.println("12. Log out");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    student.View_Course(catalog);
                    break;
                case 2:
                    List<Courses> registeredCourses = student.getRegistered_Courses();
                    if (registeredCourses.isEmpty()) {
                        System.out.println("No registered courses.");
                    } else {
                        System.out.println("Registered Courses:");
                        for (Courses course : registeredCourses) {
                            System.out.println("Course Code: " + course.getCourseCode() + " | Course Name: " + course.getCourseName());
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter Course Code to Register: ");
                    String courseCode = input.nextLine();
                    Courses course = catalog.findCourseByCode(courseCode);
                    if (course != null) {
                        List<Courses> availableCourses = catalog.getSubjects();
                        student.RegisterCourse(course, availableCourses);
                    } else {
                        try{
                            throw new Exceptions.CourseNotFoundException("Invalid course code. Please try again.");
                        } catch (Exceptions.CourseNotFoundException e) {
                            System.out.println("Invalid course code. Please try again.");
                        }
                    }
                    break;
                case 4:
                    student.View_Schedule();
                    break;
                case 5:
                    System.out.print("Enter Course Code to Drop: ");
                    String dropCode = input.nextLine();
                    Courses dropCourse = catalog.findCourseByCode(dropCode);
                    if (dropCourse != null) {
                        student.Drop_Course(dropCourse);
                        System.out.println("Course Dropped.");
                    } else {
                        try{
                            throw new Exceptions.CourseNotFoundException("Invalid course code. Please try again.");
                        } catch (Exceptions.CourseNotFoundException e) {
                            System.out.println("Invalid course code. Please try again.");
                        }
                    }
                    break;
                case 6:
                    double CGPA = student.CGPA();
                    System.out.println("YOUR CGPA FOR " + student.getSemester() + " is " + CGPA);
                    break;
                case 7:
                    double SGPA = student.SGPA();
                    System.out.println("YOUR SGPA FOR " + student.getSemester() + " is " + SGPA);
                    break;
                case 8:
                    student.ViewGrades();
                    break;
                case 9:
                    System.out.print("Enter Complaint Description: ");
                    String description = input.nextLine();
                    Complaint complaint = new Complaint(description, student);
                    complaints.add(complaint);
                    System.out.println("Complaint filed with ID: " + complaint.getComplaintID());
                    break;
                case 10:
                    List<Complaint> studentComplaints = new ArrayList<>();
                    for (Complaint comp : complaints) {
                        if (comp.getStudent().equals(student)) {
                            studentComplaints.add(comp);
                        }
                    }
                    if (studentComplaints.isEmpty()) {
                        System.out.println("You have not submitted any complaints.");
                    } else {
                        System.out.println("Your Complaints:");
                        for (Complaint comp : studentComplaints) {
                            System.out.println("Complaint ID: " + comp.getComplaintID() +
                                    ", Description: " + comp.getDescription() +
                                    ", Status: " + comp.getStatus() +
                                    ", Resolution: " + comp.getResoloutionDetails());
                        }
                    }
                    break;
                case 11:
                    System.out.println("Enter the Course to give feedback:");
                    String c = input.nextLine();
                    Courses code = catalog.findCourseByCode(c);
                    if (code != null) {
                        System.out.println("Enter your feedback (numeric rating or text):");
                        String feedbackInput = input.nextLine();
                        Feedback<String> feedback = new Feedback<>(feedbackInput);
                        student.GiveFeedback(code, feedback);
                    } else {
                        try{
                            throw new Exceptions.CourseNotFoundException("Invalid course code. Please try again.");
                        } catch (Exceptions.CourseNotFoundException e) {
                            System.out.println("Invalid course code. Please try again.");
                        }
                    }
                    break;
                case 12:
                    studentRunning = false; // Log out
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void handleAdminLogin(Scanner input) throws Exceptions.InvalidLoginException {
        System.out.print("Enter admin ID: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        String fixedPassword = "admin123";

        // Check if the provided ID and password are correct
        if (id == admin.getID() && password.equals(fixedPassword)) {
            System.out.println("Admin logged in.");
            adminMenu(input, admin);
        } else {
            try{
                throw new Exceptions.InvalidLoginException("Invalid Admin ID or password. Please try again.");
            }catch (Exceptions.InvalidLoginException e) {
                System.out.println(e.getMessage());
            };
        }
    }

    private static void adminMenu(Scanner input, Admin admin) {
        boolean adminRunning = true;

        while (adminRunning) {
            System.out.println("\nAdministrator Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Add Professor");
            System.out.println("3. Add Teaching Assistant");
            System.out.println("4. View Course");
            System.out.println("5. Add Course");
            System.out.println("6. Update Student Record");
            System.out.println("7. Update Student Marks");
            System.out.println("8. Update Drop Deadline");
            System.out.println("9. Assign Professor");
            System.out.println("10. Assign TA");
            System.out.println("11. View All Complaints");
            System.out.println("12. Filter Complaints by Status");
            System.out.println("13. Filter Complaints by Date");
            System.out.println("14. Update Complaint Resolution");
            System.out.println("15. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent(input);
                    break;
                case 2:
                    addProfessor(input);
                    break;
                case 3:
                    addTeachingAssistant(input);
                    break; // Added break here
                case 4:
                    admin.viewallcourses();
                    break;
                case 5:
                    admin.addcourse(input);
                    break; // Added break here
                case 6:
                    admin.updateStudentRecord(input);
                    break; // Added break here
                case 7:
                    admin.updateStudentMarks(input);
                    break;
                case 8:
                    admin.updateDropDeadline(input);
                    break;
                case 9:
                    admin.assignprof(input);
                    break;
                case 10:
                    admin.assignSubtoTA(input);
                    break; // Added break here
                case 11:
                    admin.AllComplaints();
                    break;
                case 12:
                    admin.FilterByStatus(input);
                    break;
                case 13:
                    admin.FilterByDate(input);
                    break;
                case 14:
                    admin.updateComplaintResolution(input);
                    break;
                case 15:
                    adminRunning = false; // Exit
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }


    private static void handleProfessorLogin(Scanner input) {
        System.out.print("Enter professor ID: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Enter professor password: ");
        String password = input.nextLine();

        Professor professor = professors.get(id);

        if (professor != null) {
            profmenu(input, professor);
        } else {
            try{
                throw new Exceptions.InvalidLoginException("Invalid student ID or password. Please try again.");
            } catch (Exceptions.InvalidLoginException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void profmenu(Scanner input, Professor prof) {
        boolean professorRunning = true;

        while (professorRunning) {
            System.out.println("\n--- Professor Menu ---");
            System.out.println("1. View Assigned Courses");
            System.out.println("2. View Students in a Course");
            System.out.println("3. Update Course Details");
            System.out.println("4. View Teaching Assistant");
            System.out.println("5. View Course Feedback");
            System.out.println("6. Log out"); // Changed to 6
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    prof.AllteachingCourses();
                    break;
                case 2:
                    viewStudents(input, prof);
                    break;
                case 3:
                    updateCourseDetails(input, prof);
                    break;
                case 4:
                    System.out.print("Enter the course code: ");
                    String courseCode = input.nextLine();

                    try {
                        Courses course = catalog.findCourseByCode(courseCode);
                        if (course != null) {
                            prof.viewTAAssignedToCourse(course);
                        } else {
                            throw new Exceptions.NotHandlingCourse("Invalid course code. Please try again.");
                        }
                    } catch (Exceptions.NotHandlingCourse e) {
                        System.out.println(e.getMessage());
                    }
                    break;


                case 5:
                    System.out.print("Enter the course code: ");
                    String code = input.nextLine();

                    Courses subject = catalog.findCourseByCode(code);
                    if (subject != null) {
                        prof.viewFeedBack(subject);
                    } else {
                        // Handle the invalid course code without using exceptions
                        System.out.println("Invalid course code. Please try again.");
                    }
                    break;

                case 6:
                    professorRunning = false; // Log out
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }






    private static void addStudent(Scanner input) {
        System.out.print("Enter student ID: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Enter student name: ");
        String name = input.nextLine();

        System.out.print("Enter student email: ");
        String email = input.nextLine();

        System.out.print("Enter your current semester: ");
        int semester = input.nextInt();
        input.nextLine();

        List<Courses> registeredcourses = new ArrayList<>();
        List<Courses> completedcourses = new ArrayList<>();

        Student student = new Student(id,email,name,semester,registeredcourses,completedcourses);
        students.put(id, student);
        System.out.println("Student added "+student.getID());
    }

    private static void addProfessor(Scanner input) {
        System.out.print("Enter professor ID: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Enter professor name: ");
        String name = input.nextLine();
        System.out.print("Enter professor email: ");
        String email = input.nextLine();

        System.out.print("Enter Professors Office Hours: ");
        String officeHours = input.nextLine();

        List<Courses> TeachingCourses = new ArrayList<>();

        Professor professor = new Professor(id,email,name,TeachingCourses,officeHours);
        professors.put(id,professor);
        System.out.println("Proffessor added successfully "+professor.getID());

    }

    private static void addTeachingAssistant(Scanner input) {
        System.out.print("Enter teaching Assistant ID: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Enter teaching Assistant name: ");
        String name = input.nextLine();
        System.out.print("Enter teaching Assistant Email: ");
        String email = input.nextLine();

        System.out.print("Enter teaching Assistant Semeseter: ");
        int semester = input.nextInt();
        input.nextLine();


        List<Courses> registeredcourses = new ArrayList<>();
        List<Courses> completedcourses = new ArrayList<>();
        List<Courses> AssignedCourses = new ArrayList<>();

        TeachingAssistant teachingAssistant = new TeachingAssistant(id,email,name,semester,registeredcourses,completedcourses,AssignedCourses);
        teachingAssistants.put(id,teachingAssistant);
        System.out.println("Teaching Assistant added successfully "+teachingAssistant.getID());


    }

    private static void UpdateStudentGrade(Scanner input,TeachingAssistant teachingAssistant) {
        System.out.print("Enter student ID: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Enter course Code ");
        String code = input.nextLine();

        System.out.print("Enter grade ");
        String grade = input.nextLine();

        teachingAssistant.setGrades(id,code,grade,students);

    }
    private static void TAviewStudent(Scanner input, TeachingAssistant teachingAssistant) {
        System.out.print("Enter Course Code: ");
        String code = input.nextLine();

        // Check if the TA is assigned to any course that matches the code
        boolean isAssigned = teachingAssistant.getAssigned().stream()
                .anyMatch(course -> course.getCourseCode().equals(code));

        if (isAssigned) {
            // Find the course object in the catalog
            Courses course = catalog.findCourseByCode(code);

            if (course != null) {
                List<Student> enrolledStudents = course.getEnrolled_Students();
                System.out.println("Enrolled students for course " + code + ":");
                if (enrolledStudents.isEmpty()) {
                    System.out.println("No students enrolled in this course.");
                } else {
                    for (Student student : enrolledStudents) {
                        System.out.println("Student Name: " + student.getName() + " | Student ID: " + student.getID());
                    }
                }
            } else {
                // Directly handle the error without throwing an exception
                System.out.println("Invalid course code. Please try again.");
            }
        } else {
            // Handle the case where the TA is not assigned to the course
            System.out.println("You are not assigned to this course.");
        }
    }


    private static void viewStudents(Scanner input,Professor prof){
        System.out.println("Enter Course Code: ");
        String courseCode = input.nextLine();

        Courses course = prof.findCourseByCode(courseCode);
        if(course!=null){
            prof.ViewStudents(course);
        }
        else{
            System.out.println("Invalid Course Code. Please try again. OR no students found ");
        }
    }

    private static void updateCourseDetails(Scanner input, Professor professor) {
        System.out.print("Enter Course Code: ");
        String courseCode = input.nextLine();

        Courses course = professor.findCourseByCode(courseCode);
        if (course != null) {
            System.out.print("Enter new syllabus: ");
            String syllabus = input.nextLine();

            System.out.print("Enter new credits: ");
            int credits = input.nextInt();
            input.nextLine(); // Consume newline

            System.out.print("Enter new enrollment limit: ");
            int limit = input.nextInt();
            input.nextLine(); // Consume newline

            System.out.print("Enter prerequisites (comma-separated): ");
            List<String> prerequisites = asList(input.nextLine().split(","));

            professor.UpdateDetails(course, syllabus, credits, prerequisites, limit, professor.getOffice_Hours());
            System.out.println("Course details updated successfully.");
        } else {
            System.out.println("Invalid Course Code. Please try again.");
        }
    }}





