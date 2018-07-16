package inheritancehomeworks;
/**
 *
 * @author cj
 */
public class InformaticStudent extends Student {
    private int numRegisteredCourses;
    private String[] facultyCourses;
    private String[] basicStudentInfo;
    
    InformaticStudent() {
        this.numRegisteredCourses = 0;
        this.basicStudentInfo = new String[super.fillableFields];
    }

    public int getNumRegisteredCourses() {
        return numRegisteredCourses;
    }

    public void setNumRegisteredCourses(int numRegisteredCourses) {
        this.numRegisteredCourses = numRegisteredCourses;
    }
    
    public String[] getFacultyCourses() {
        return this.facultyCourses;
    }

    public void setFacultyCourses(String FacultyCourses[]) {
        if(FacultyCourses.length != this.numRegisteredCourses) {
            System.err.println("Firstly you need to tell me the number of "
                    + "student courses\nCall "
                    + "setNumRegisteredCourses(any number) method");
            return;
        }
        this.facultyCourses = new String[this.numRegisteredCourses];
        
        System.arraycopy(FacultyCourses, 0, this.facultyCourses, 0, 
                this.facultyCourses.length);
    }

    public String[] getBasicStudentInfo() {
        return this.basicStudentInfo;
    }

    public void setBasicStudentInfo(String[] basicStudentInfo) {
        if(basicStudentInfo.length != super.fillableFields ) {
            System.err.println("Not enought data for basic student!\n"
                    + "The required fields are: Code, Name, Surname, "
                    + "Institutional email, Phone, Adress.");
            return;
        }
        
        this.setCode(basicStudentInfo[0]);
        this.setName(basicStudentInfo[1]);
        this.setSurname(basicStudentInfo[2]);
        this.setInstitutionalEmail(basicStudentInfo[3]);
        this.setPhone(basicStudentInfo[4]);
        this.setAddress(basicStudentInfo[5]);
        System.arraycopy(basicStudentInfo, 0, this.basicStudentInfo,0,
                this.basicStudentInfo.length);
    }
    
}
