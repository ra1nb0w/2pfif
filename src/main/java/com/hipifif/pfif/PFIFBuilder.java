package com.hipifif.pfif;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PFIFBuilder {
    
    private final static String HEADER = "<!--?xml version=\"1.0\" encoding=\"UTF-8\"?-->\n" +
        "<pfif:pfif xmlns:pfif=\"http://zesty.ca/pfif/1.4\">\n";
    
    private final static String FOOTER = "</pfif:pfif> \n";
    
    private final static int INITIAL_STRINGBUFFER_CAPACITY = 1024 * 6;
    
    public final static String KEY_PERSON_RECORD_ID = "person_record_id";
    public final static String KEY_ENTRY_DATE = "entry_date";
    public final static String KEY_EXPIRY_DATE = "expiry_date";
    public final static String KEY_FULL_NAME = "full_name";
    public final static String KEY_GIVEN_NAME = "given_name";
    public final static String KEY_DESCRIPTION = "description";
    public final static String KEY_SEX = "sex";
    public final static String KEY_AGE = "age";
    public final static String KEY_HOME_STREET = "home_street";
    public final static String KEY_HOME_NEIGHBORHOOD = "home_neighborhood";
    public final static String KEY_HOME_CITY = "home_city";
    public final static String KEY_HOME_STATE = "home_state";
    public final static String KEY_PHOTO_URL = "photo_url";
    
    public final static Set<String> validKeys = new HashSet<String>(Arrays.asList(
                new String[] {
                    KEY_AGE, KEY_DESCRIPTION,
                    KEY_ENTRY_DATE, KEY_EXPIRY_DATE,
                    KEY_FULL_NAME, KEY_GIVEN_NAME,
                    KEY_HOME_CITY, KEY_HOME_NEIGHBORHOOD,
                    KEY_HOME_STATE, KEY_HOME_NEIGHBORHOOD,
                    KEY_HOME_STREET, KEY_PHOTO_URL,KEY_SEX
                })
            );
            
    private Map<String, String> data; 
    
    public PFIFBuilder() {
        this.data = new TreeMap<String, String>();
    }
    
    public void add(String key, String value) {
        if(validKeys.contains(key)) {
            data.put(key, value);
        }
    }
    
    public PFIFBuilder personRecordId(String id) {
        this.data.put(KEY_PERSON_RECORD_ID, id);
        return this;
    }
    
    public PFIFBuilder entryDate(String entryDate) {
        this.data.put(KEY_ENTRY_DATE, entryDate);
        return this;
    }
    
    public PFIFBuilder expiryDate(String expiryDate) {
        this.data.put(KEY_EXPIRY_DATE, expiryDate);
        return this;
    }
    
    public PFIFBuilder age(String age) {
        this.data.put(KEY_AGE, age);
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
                .age("25")
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
