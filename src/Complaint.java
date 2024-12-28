import java.time.LocalDate;

public class Complaint implements ComplaintData {
    private static int counter=1;
    private int ComplaintID;
    private String Description;
    private String Status;
    private String ResoloutionDetails;
    private LocalDate date;
    private Student student;


    public Complaint( String description,Student student) {

        Status = "Pending";
        Description = description;
        this.date=LocalDate.now();
        this.student=student;
        this.ComplaintID=counter++;
        this.ResoloutionDetails="";
    }

    public int getComplaintID() {
        return ComplaintID;
    }

    public int getCounter() {
        return counter;
    }

    public void setComplaintID(int complaintID) {
        ComplaintID = complaintID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getResoloutionDetails() {
        return ResoloutionDetails;
    }

    public void setResoloutionDetails(String resoloutionDetails) {
        ResoloutionDetails = resoloutionDetails;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "Description='" + Description + '\'' +
                ", Status='" + Status + '\'' +
                ", ResoloutionDetails='" + ResoloutionDetails + '\'' +
                '}';
    }

}
