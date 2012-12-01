package com.hipifif.pfif;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author noname
 */
public class PFIFBuilder {
    
    private final static String HEADER = "<!--?xml version=\"1.0\" encoding=\"UTF-8\"?-->\n" +
        "<pfif:pfif xmlns:pfif=\"http://zesty.ca/pfif/1.4\">\n";
    
    private final static String FOOTER = "</pfif:pfif> \n";
    
    private final static int INITIAL_STRINGBUFFER_CAPACITY = 1024 * 6;
    
    private final static String KEY_PERSON_RECORD_ID = "person_record_id";
    private final static String KEY_ENTRY_DATE = "entry_date";
    private final static String KEY_EXPIRY_DATE = "expiry_date";
    private final static String KEY_FULL_NAME = "full_name";
    private final static String KEY_GIVEN_NAME = "given_name";
    private final static String KEY_DESCRIPTION = "description";
    private final static String KEY_SEX = "sex";
    private final static String KEY_AGE = "age";
    private final static String KEY_HOME_STREET = "home_street";
    private final static String KEY_HOME_NEIGHBORHOOD = "home_neighborhood";
    private final static String KEY_HOME_CITY = "home_city";
    private final static String KEY_HOME_STATE = "home_state";
    private final static String KEY_PHOTO_URL = "photo_url";    
            
    private Map<String, String> data; 
    
    public PFIFBuilder() {
        this.data = new TreeMap<String, String>();
    }
    
    public PFIFBuilder personRecordId(String id) {
        this.data.put(KEY_PERSON_RECORD_ID, id);
        return this;
    }
    
    public PFIFBuilder entryDate(String entryDate) {
        this.data.put(KEY_ENTRY_DATE, entryDate);
        return this;
    }
    
    public PFIFBuilder entryExpiry(String expiryDate) {
        this.data.put(KEY_EXPIRY_DATE, expiryDate);
        return this;
    }
    
    public PFIFBuilder age(int age) {
        this.data.put(KEY_AGE, String.valueOf(age));
        return this;
    }
    
    public PFIFBuilder description(String description) {
        this.data.put(KEY_DESCRIPTION, description);
        return this;
    }
    
    public PFIFBuilder fullName(String fullName) {
        this.data.put(KEY_FULL_NAME, fullName);
        return this;
    }
    
    public PFIFBuilder sex(String sex) {
        this.data.put(KEY_SEX, sex);
        return this;
    }

    public PFIFBuilder givenName(String givenName) {
        this.data.put(KEY_GIVEN_NAME, givenName);
        return this;
    }
    
    public PFIFBuilder homeCity(String homeCity) {
        this.data.put(KEY_HOME_CITY, homeCity);
        return this;
    }
    
    public PFIFBuilder homeNeighborhood(String homeNeighborhood) {
        this.data.put(KEY_HOME_NEIGHBORHOOD, homeNeighborhood);
        return this;
    }
    
    public PFIFBuilder homeState(String homeState) {
        this.data.put(KEY_HOME_STATE, homeState);
        return this;
    }
    
    public PFIFBuilder homeStreet(String homeStreet) {
        this.data.put(KEY_HOME_STREET, homeStreet);
        return this;
    }
    
    public PFIFBuilder photoUrl(String url) {
        this.data.put(KEY_PHOTO_URL, url);
        return this;
    }
    
    public String build() {
        StringBuilder buffer = new StringBuilder(INITIAL_STRINGBUFFER_CAPACITY);
        
        buffer.append(HEADER);
        
        for(String field : this.data.keySet()) {
            buffer.append("  <pfif:");
            buffer.append(field);
            buffer.append(">\n");
            buffer.append("   ");
            buffer.append(this.data.get(field));
            buffer.append("\n  </pfif:");
            buffer.append(field);
            buffer.append(">\n");
        }
        
        buffer.append(FOOTER);
        
        return buffer.toString();
    }
    
    public static void main(String[] args) {
        String xml = new PFIFBuilder()
                .personRecordId("person-1")
                .age(25)
                .fullName("Massimo Nicosia")
                .givenName("Massimo")
                .homeCity("Monopoli")
                .homeState("Italy")
                .sex("male")
                .description("a grattate!")
                .build();
        
        System.out.println(xml);
    }
}
