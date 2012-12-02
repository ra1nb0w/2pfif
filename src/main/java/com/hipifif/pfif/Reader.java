package com.hipifif.pfif;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    
    private BufferedReader in;
    private String line;
    
    public Reader(String path) throws FileNotFoundException {
       in = new BufferedReader(new FileReader(path));
       line = null;
    }
    
    public boolean hasNextLine() throws IOException {
        if(line == null) {
            line = in.readLine();
        }
        return line != null;
    }
    
    public String nextLine() throws IOException {
        if(this.line == null) {
            return in.readLine();
        } else {
            String result = this.line;
            this.line = null;
            return result;
        }
    }
    
    public void close() throws IOException {
        in.close();
    }
}
