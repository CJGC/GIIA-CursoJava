package classhomework;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author cj
 */
public class MainClass {

    public static String mainMenu = ( "\n" +
        "  ***********************************************\n" +
        "  *                  Main menu                  *\n" +
        "  ***********************************************\n" +
        "  *  list                                       *\n" +
        "  *  add                                        *\n" +
        "  *  edit (object_id)                           *\n" +
        "  *  remove (object_id)                         *\n" +
        "  *  exit                                       *\n" +
        "  ***********************************************\n\n" +
        "Type desired operator with it's id: ");
    public static Scanner opt = new Scanner(System.in);
    public static String userInput;
    public static Pattern pattern = Pattern.compile("^\\w+(\\s[0-9]+)?");
    public static Matcher matcher;
    public static String messages[] = 
        {"Type name: ", "Type address: ", "Type phone: ", "Type email: "};
    public static String data[] = new String[4];
    public static AddressNotebook addressnotebook = new AddressNotebook();
    
    public static void printMenu() {
        System.out.print(mainMenu);
    }
    
    public static void setUserInput() {
        do {
            userInput = opt.nextLine();
            matcher = pattern.matcher(userInput);
            if(!matcher.matches())
                System.err.print("Please type following the main menu example: ");
            else
                break;
        } while (true);
        System.out.println();
    }
    
    public static void setData() {
        for(int i=0; i< messages.length; i++) {
            System.out.print(messages[i]);
            data[i] = opt.nextLine();
        }
    }
    
    public static void executeOrder() {
        String op[] = userInput.split(" ");
        switch (op[0]) {
            case "list":
                addressnotebook.listNotebooks();
                break;
            case "add":
                setData();
                addressnotebook.addNotebook(data);
                break;
            case "edit":
                if(op.length != 2) {
                    System.err.println("You must to provide an object id!");
                    break;
                }
                setData();
                addressnotebook.editNotebook(Integer.parseInt(op[1]), data);
                break;
            case "remove":
                if(op.length != 2) {
                    System.err.println("You must to provide an object id!");
                    break;
                }
                addressnotebook.delNotebook(Integer.parseInt(op[1]));
                break;
            case "exit":
                break;
            default:
                System.err.println("Invalid operator was entered!");
                break;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        do {
            printMenu();
            setUserInput();
            executeOrder();
        } while (!"exit".equals(userInput));
    }
    
}
