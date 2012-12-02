/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hipifif.pfif;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    
    private static final String SEPARATOR = ";";
    
    private Reader in;
    private int numberOfFields;
    private List<String> keys;
    
    public CSVReader() { }
    
    public CSVReader(String path) throws FileNotFoundException, IOException {
        in = new Reader(path);
        String firstLine = in.nextLine();
        keys = Arrays.asList(firstLine.split(SEPARATOR));
        numberOfFields = keys.size();
    }
    
    public boolean hasRecord() throws IOException {
        return in.hasNextLine();
    }
    
    public Map<String, String> getRecord() throws IOException {
        Map<String, String> result = new HashMap<String, String>();   
        String line = in.nextLine();
        String[] record = line.split(SEPARATOR);

        if(record.length != numberOfFields) {
            throw new IllegalArgumentException("This line has an invalid number of fields. "
                    + "Was " + record.length + ", expected " + numberOfFields);
        }
        
        for(int i = 0; i < numberOfFields; i++) {
            System.out.println(keys.get(i) + " " + record[i]);
            result.put(keys.get(i), record[i]);
        }
        
        return result;
    }
    
    
    public void close() throws IOException {
        in.close();
    }
    
    public static void Send(String arg) throws FileNotFoundException, IOException {
	CSVReader reader = new CSVReader(arg);
        PFIFSender sender = new PFIFSender();
        while(reader.hasRecord()) {
            PFIFBuilder builder = new PFIFBuilder(reader.getRecord());                 
            sender.feed(builder);
        }
        sender.send();
        reader.close();
    }   
    
}
