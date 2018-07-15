package classhomework;
import static classhomework.MainClass.matcher;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author cj
 */
public class RegisterNotebook {
    
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private final Pattern emailPattern = 
            Pattern.compile("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\\.[a-z]+");
    private final Pattern phonePattern = Pattern.compile("^[0-9]*");
    private Matcher matcher;
    
    RegisterNotebook(){
    }
    
    RegisterNotebook(String values[]) {
        this.setName(values[0]);
        this.setAddress(values[1]);
        this.setPhone(values[2]);
        this.setEmail(values[3]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.matcher = this.phonePattern.matcher(phone);
        if(!matcher.matches()) {
            System.err.println("Invalid pattern for phone\n"
                    + "Setting with empty value!");
            this.phone = "";
            return;
        }
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.matcher = this.emailPattern.matcher(email);
        if(!matcher.matches()) {
            System.err.println("Invalid patter for email\n"
                    + "Setting with empty value!");
            this.email = "";
            return;
        }
        this.email = email;
    }
    
    
}
