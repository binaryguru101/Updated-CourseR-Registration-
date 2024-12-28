import java.time.LocalDate;


public interface ComplaintData {
    String getDescription();
    String getStatus();
    void setStatus(String status);
    LocalDate getDate();
}
