package classhomework;
import java.util.Stack;
/**
 *
 * @author cj
 */
public class AddressNotebook {
    
    private Stack<Integer> AvailIDS;
    private RegisterNotebook registerNotebook[];
    
    AddressNotebook(){
        this.registerNotebook = new RegisterNotebook[100];
        this.AvailIDS = new Stack();
        for(int i=99; i>=0; i--)
            this.AvailIDS.push(i);
    }
    
    public void listNotebooks() {
        System.out.println("Object id | Object name | Object address | "
                + "Object phone | Object email\n");
        for(RegisterNotebook regNote : this.registerNotebook)
            if(regNote != null)
                System.out.println(regNote.getId() + " | " + regNote.getName() +
                    " | " + regNote.getAddress() + " | " + regNote.getPhone() + 
                    " | " + regNote.getEmail() );
    }
    
    public void addNotebook(String data[]) {
        int id;
        try {
            id = this.AvailIDS.pop();
        }
        catch (Exception e) {
            System.err.println(e.getMessage() + 
                    "\nYou've reached the maximum of registers allowed (100)!");
            return;
        }
        
        RegisterNotebook regNote = new RegisterNotebook(data);
        regNote.setId(id);
        this.registerNotebook[id] = regNote;
    }
    
    public void editNotebook(int notebookID, String data[]) {
        RegisterNotebook targetedNotebook = this.registerNotebook[notebookID];
        
        if(targetedNotebook != null) {
            targetedNotebook.setName(data[0]);
            targetedNotebook.setAddress(data[1]);
            targetedNotebook.setPhone(data[2]);
            targetedNotebook.setEmail(data[3]);
        }
        else
            System.err.println("The register doesn't exist!");
    }
    
    public void delNotebook(int notebookID) {
        if(this.registerNotebook[notebookID] != null) {
            this.registerNotebook[notebookID] = null;
            this.AvailIDS.push(notebookID);
        }
        else 
            System.err.println("The register doesn't exist!");
    }
}
