package inheritancehomeworks;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author cj
 */
public class Student {
    // ------------ fillable by user fields ------------
    private String code;
    private String name;
    private String surname;
    private String institutionalEmail;
    private String phone;
    private String address;
    
    // ------------ rule fields ------------
    protected final int fillableFields;
    private final Pattern emailPattern;
    private final Pattern phonePattern;
    private final Pattern codePattern;
    private Matcher matcher;

    Student() {
        this.fillableFields = 6; // this field must be setup manually
        String emailRule = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\\.[a-z]+";
        String phoneRule = "^[0-9]*";
        String codeRule = "^[0-9]*";
        this.emailPattern = Pattern.compile(emailRule);
        this.phonePattern = Pattern.compile(phoneRule);
        this.codePattern = Pattern.compile(codeRule);
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.matcher = this.codePattern.matcher(code);
        if(!this.matcher.matches()) {
            System.err.println("Invalid code format!\n setting with default");
            this.code = "";
            return;
        }
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInstitutionalEmail() {
        return institutionalEmail;
    }

    public void setInstitutionalEmail(String institutionalEmail) {
        this.matcher = this.emailPattern.matcher(institutionalEmail);
        if(!this.matcher.matches()) {
            System.err.println("Invalid email format!\n setting with default");
            this.institutionalEmail = "";
            return;
        }
        this.institutionalEmail = institutionalEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.matcher = this.phonePattern.matcher(phone);
        if(!this.matcher.matches()) {
            System.err.println("Invalid phone format!\n setting with default");
            this.phone = "";
            return;
        }
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
