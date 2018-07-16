package inheritancehomeworks;
import java.util.Scanner;
/**
 *
 * @author cj
 */
public class MainClass {

    static Scanner scanIn = new Scanner(System.in);
    static String basicStudentInfo[] = new String[6];
    static String messagesBasicInfo[] = 
        {"code","name","surname", "email", "phone", "address"};
    static String messagesRegCourses[];
    
    public static void setMessagesRegCourses(int numRegisteredCourses) {
        messagesRegCourses = new String[numRegisteredCourses];
        for(int i=1; i <= numRegisteredCourses; i++)
            messagesRegCourses[i-1] = "name course " + i + ": ";
    }
    
    public static void fillData(String data[],String messages[]) {
        for(int i=0; i<data.length ;i++ ) {
            System.out.print("Type " + messages[i] + ": ");
            data[i] = scanIn.nextLine();
        }
    }
    
    public static void showData(String data[]) {
        for(String field : data) {
            System.out.println(field);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Type the number of registered courses: ");
        String userInput = scanIn.nextLine();
        int numRegisteredCourses;
        
        try {
            numRegisteredCourses = Integer.parseInt(userInput);
        } 
        catch (NumberFormatException e) {
            System.err.println(e.getMessage() + "\nEnter a number!");
            return;
        }
        if(numRegisteredCourses < 0) {
            System.err.println("\nThe number must be a positive number!");
            return;
        }
        
        String registeredCourses[] = new String[numRegisteredCourses];
        setMessagesRegCourses(numRegisteredCourses);
        InformaticStudent informaticStudent = new InformaticStudent();
        informaticStudent.setNumRegisteredCourses(numRegisteredCourses);
        
        System.out.println("\nEnter student's basic info");
        fillData(basicStudentInfo,messagesBasicInfo);
        System.out.println("\nEnter the registered courses for student");
        fillData(registeredCourses,messagesRegCourses);
        
        informaticStudent.setBasicStudentInfo(basicStudentInfo);
        informaticStudent.setFacultyCourses(registeredCourses);
        
        System.out.println("Informatic student was created sucessfully"
                + " with the next info:\n");
        showData(informaticStudent.getBasicStudentInfo());
        System.out.println("\nRegistered courses:\n");
        showData(informaticStudent.getFacultyCourses());
    }
    
}
