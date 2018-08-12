package com.exceptions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author cj
 */
public class Exceptions {
    private static Matcher matcher;
    private final static String NAME_REGEX = "[a-zA-Z\\s]{1,100}";
    private final static String SURNAME_REGEX = "[a-zA-Z\\s]{1,100}";
    private final static String EMAIL_REGEX = "^[\\w_\\.]+@[a-zA-Z]+\\.com$";
    private final static String DATE_REGEX = 
            "[0-9]{1,4}\\-[0-9]{1,2}\\-[0-9]{1,2}";
    private final static String ADDRESS_REGEX = "[a-zA-Z#\\-0-9\\s]{1,150}";
    private final static String NUM_REGEX = "[0-9]+";
    private final static String PASS_REGEX = "([a-z0-9@#$%A-Z]){6,16}";
    private final static String NICKNAME_REGEX = "[a-zA-Z0-9_$@\\s]{1,100}";
    private final static String PHONE_REGEX = "[0-9]{1,20}";
    private final static String COUN_COD_REGEX = "^\\([0-9]{1,2}\\)$";
    private final static String GROUP_NAME_REGEX = "[a-zA-Z0-9#_$@()*+]{1,100}";
    
    
    private final static Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private final static Pattern SURNAME_PATTERN = 
            Pattern.compile(SURNAME_REGEX);
    private final static Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);
    private final static Pattern ADDRESS_PATTERN = 
            Pattern.compile(ADDRESS_REGEX);
    private final static Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private final static Pattern NUM_PATTERN = Pattern.compile(NUM_REGEX);
    private final static Pattern PASS_PATTERN = Pattern.compile(PASS_REGEX);
    private final static Pattern NICKNAME_PATTERN = 
            Pattern.compile(NICKNAME_REGEX);
    private final static Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    private final static Pattern COUN_COD_PATTERN = 
            Pattern.compile(COUN_COD_REGEX);
    private final static Pattern GROUP_NAME_PATTERN = 
            Pattern.compile(GROUP_NAME_REGEX);
    
    private static void checkEntry(String entry,Pattern pattern,String message)
    {
        matcher = pattern.matcher(entry);
        if(!matcher.matches())
            System.err.println(message);
    }
    
    public static void checkCountryData(String []content) {
        String countryName;
        
        try {
            countryName = content[0];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There are not enough data"
                    + " inside content array!, error from country controller");
        }
        
        checkEntry(countryName,NAME_PATTERN,"Invalid format for country name");
    }
    
    public static void checkProvinceData(String []content) {
        String provinceName;
        String country_id;
        
        try {
            provinceName = content[0];
            country_id = content[1];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There are not enough data"
                    + " inside content array!, error from province controller");
        }
        
        checkEntry(provinceName,NAME_PATTERN,
                "Invalid format for province name");
        checkEntry(country_id,NUM_PATTERN,"Invalid format for number");
    }

    public static void checkCityData(String []content) {
        String cityName;
        String province_id;
        
        try {
            cityName = content[0];
            province_id = content[1];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There are not enough data"
                    + " inside content array!, error from city controller");
        }
        
        checkEntry(cityName,NAME_PATTERN,"Invalid format for city name");
        checkEntry(province_id,NUM_PATTERN,"Invalid format for number");
    }
    
    public static void checkAddressData(String []content) {
        String addressValue;
        String city_id;
        
        try {
            addressValue = content[0];
            city_id = content[1];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There are not enough data"
                    + " inside content array!, error from address controller");
        }
        
        checkEntry(addressValue,ADDRESS_PATTERN,
                "Invalid format for address value");
        checkEntry(city_id,NUM_PATTERN,"Invalid format for number");
    }
    
    public static void checkPersonData(String []content) {
        String personName;
        String personSurname;
        String personBirthday;
        String address_id;
        
        try {
            personName = content[0];
            personSurname = content[1];
            personBirthday = content[2];
            address_id = content[3];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There are not enough data"
                    + " inside content array!, error from person controller");
        }
        
        checkEntry(personName,NAME_PATTERN,"Invalid format for person's name");
        checkEntry(personSurname,SURNAME_PATTERN,
                "Invalid format for person's surname");
        checkEntry(personBirthday,DATE_PATTERN,
                "Invalid format for person's date");
        if(address_id != "")
            checkEntry(address_id,NUM_PATTERN,"Invalid format for number");
    }
    
    public static void checkUserData(String []content) {
        String nickname;
        String password;
        String person_id;
        
        try {
            nickname = content[0];
            password = content[1];
            person_id = content[2];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There are not enough data"
                    + " inside content array!, error from user controller");
        }
        
        checkEntry(nickname,NICKNAME_PATTERN,
                "Invalid format for user's nickname");
        checkEntry(password,PASS_PATTERN,"Invalid format for user's password");
        checkEntry(person_id,NUM_PATTERN,"Invalid format for number");
    }
    
    public static void checkRegisterData(String []content) {
        String nickname;
        String person_id;
        
        try {
            nickname = content[0];
            person_id = content[1];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There are not enough data"
                    + " inside content array!, error from register controller");
        }
        
        checkEntry(nickname,NICKNAME_PATTERN,
                "Invalid format for register nickname");
        checkEntry(person_id,NUM_PATTERN,"Invalid format for number");
    }
    
    public static void checkEmailData(String []content) {
        String emailAddress;
        String person_id;
        
        try {
            emailAddress = content[0];
            person_id = content[1];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There are not enough data"
                    + " inside content array!, error from email controller");
        }
        
        checkEntry(emailAddress,EMAIL_PATTERN,"Invalid format for email ");
        checkEntry(person_id,NUM_PATTERN,"Invalid format for number");
    }
    
    public static void checkPhoneData(String []content) {
        String number;
        String countryCode;
        String person_id;
        
        try {
            number = content[0];
            countryCode = content[1];
            person_id = content[2];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There are not enough data"
                    + " inside content array!, error from phone controller");
        }
        
        checkEntry(number,PHONE_PATTERN,"Invalid format for phone number");
        checkEntry(countryCode,COUN_COD_PATTERN,"Invalid format for country "
                + "code");
        checkEntry(person_id,NUM_PATTERN,"Invalid format for number");
    }
    
    public static void checkGroupData(String []content) {
        String name;
        String maxAllowed;
        
        try {
            name = content[0];
            maxAllowed = content[1];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There are not enough data"
                    + " inside content array!, error from group controller");
        }
        
        checkEntry(name,GROUP_NAME_PATTERN,"Invalid format for group name");
        checkEntry(maxAllowed,NUM_PATTERN,"Invalid format for number");
    }
    
    public static void checkUserGroupData(String []content) {
        String group_id;
        String user_id;
        
        try {
            group_id = content[0];
            user_id = content[1];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There are not enough data"
                    + " inside content array!, error from user group "
                    + "controller");
        }
        
        checkEntry(group_id,NUM_PATTERN,"Invalid format for number");
        checkEntry(user_id,NUM_PATTERN,"Invalid format for number");
    }

    public static void checkRegisterGroupData(String []content) {
        String group_id;
        String register_id;
        
        try {
            group_id = content[0];
            register_id = content[1];
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There are not enough data"
                    + " inside content array!, error from register group "
                    + "controller");
        }
        
        checkEntry(group_id,NUM_PATTERN,"Invalid format for number");
        checkEntry(register_id,NUM_PATTERN,"Invalid format for number");
    }
}
