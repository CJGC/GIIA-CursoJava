package com.exceptions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author cj
 */
public class Exceptions {
    private static Matcher matcher;
    private final static String NAME_REGEX = "[a-zA-Z]+";
    private final static String SURNAME_REGEX = "[a-zA-Z]+";
    private final static String EMAIL_REGEX = "^[\\w_\\.]+@[a-zA-Z]+\\.com$";
    private final static String DATE_REGEX = "[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}";
    private final static String NUM_REGEX = "[0-9]+";
    
    private final static Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private final static Pattern SURNAME_PATTERN = 
            Pattern.compile(SURNAME_REGEX);
    private final static Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);
    private final static Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private final static Pattern NUM_PATTERN = Pattern.compile(NUM_REGEX);
    
    private static void checkEntry(String entry,Pattern pattern,String message)
    {
        matcher = pattern.matcher(entry);
        if(!matcher.matches())
            throw new IllegalArgumentException(message);
    }
    
    public static void checkPersonData(String []content) {
        String personName;
        String personSurname;
        String personBirthday;
        String foreignKey;
        
        try {
            personName = content[0];
            personSurname = content[1];
            personBirthday = content[2];
            foreignKey = content[3];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There are not enough data"
                    + " inside content array!, error from person controller");
        }
        
        checkEntry(personName,NAME_PATTERN,"Invalid format for name");
        checkEntry(personSurname,SURNAME_PATTERN,"Invalid format for surname");
        checkEntry(personBirthday,DATE_PATTERN,"Invalid format for date");
        checkEntry(foreignKey,NUM_PATTERN,"Invalid format for number");
    }
}