import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    
    private String name;
    private Date timestamp;
    private String message;

    public Comment(String name, Date timestamp, String message) {
        this.name = name;
        this.timestamp = timestamp;
        this.message = message;
    };

    public Comment() {};

    public String getName() {
        return this.name;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "***********\n" + "Name: " + this.getName() + "\nDate: " + this.getTimestamp() + "\nMessage: \n" + this.message;
    }

}
