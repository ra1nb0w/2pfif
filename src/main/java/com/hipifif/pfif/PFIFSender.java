/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hipifif.pfif;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author noname
 */
public class PFIFSender {
    
    private final static int INITIAL_STRINGBUFFER_CAPACITY = 1024 * 16;
        
    private final static String HEADER = "<!--?xml version=\"1.0\" encoding=\"UTF-8\"?-->\n" +
        "<pfif:pfif xmlns:pfif=\"http://zesty.ca/pfif/1.4\">\n";
    
    private final static String FOOTER = "</pfif:pfif> \n";
    
    List<PFIFBuilder> persons;
    
    public PFIFSender() {
        this.persons = new ArrayList<PFIFBuilder>();
    }
    
    public void feed(PFIFBuilder builder) throws IOException {
        if(this.persons.size() == 100) {
            this.send();
        }
        
        this.persons.add(builder);
    }
    
    public void send() throws IOException {
        if(this.persons.isEmpty()) { 
            return;
        }
        
        StringBuilder buffer = new StringBuilder(INITIAL_STRINGBUFFER_CAPACITY);
        
        buffer.append(HEADER);
        
        for(PFIFBuilder person : this.persons) {
            buffer.append(person.build());
        }
        
        buffer.append(FOOTER);
        
        System.out.println(buffer.toString());
        
	// check if send is ok
        if(HTTPRequest.SendPostData(buffer.toString())) {
		JOptionPane.showMessageDialog(null, "Commit executed");
	}
        
        this.persons.clear();
    }
}
