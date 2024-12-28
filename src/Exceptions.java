public class Exceptions extends Exception {

    public static class CourseFullException extends Exception {
        public CourseFullException(String message) {
            super(message);
        }
    }
    public static class DropDeadlinePassedException extends Exception {
        public DropDeadlinePassedException(String message) {
            super(message);
        }
    }
    public static class NotHandlingCourse extends Exception {
        public NotHandlingCourse(String message) {
            super(message);
        }
    }
    public static class InvalidLoginException extends Exception {
        public InvalidLoginException(String message) {
            super(message);
        }
    }
    public static class CourseNotFoundException extends Exception {
        public CourseNotFoundException(String message) {
            super(message);
        }
    }
}








