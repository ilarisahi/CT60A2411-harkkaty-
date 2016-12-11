/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ct60a2411.harkkatyö;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petri
 */
public class LokiWriter {
    static LokiWriter lw = null;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    private LokiWriter() {
        PrintWriter wr;
        try {
            wr = new PrintWriter("loki.txt", "UTF-8");
            wr.close();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(LokiWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static public LokiWriter getInstance() throws FileNotFoundException, UnsupportedEncodingException {
        if (lw == null) {
            lw = new LokiWriter();
        }
        return lw;
    }
    
    public void writer(String a, String b, String c, String d) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        FileWriter wr = new FileWriter("loki.txt", true);
        BufferedWriter bw = new BufferedWriter(wr);
        bw.write("Nimi: ");
        bw.write(a);
        bw.write(System.getProperty("line.separator"));
        bw.write("Lähetyspaikka: ");
        bw.write(b);
        bw.write(System.getProperty("line.separator"));
        bw.write("Saapumispaikka: ");
        bw.write(c);
        bw.write(System.getProperty("line.separator"));
        Date date = new Date();
        bw.write("Lähetysaika: ");
        bw.write(df.format(date));
        bw.write(System.getProperty("line.separator"));
        bw.write(System.getProperty("line.separator"));
        bw.close();
        
    }
    
    public String reader() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("loki.txt"));
        String sb = "";
        String line = br.readLine();
        while(line != null) {
            sb += line + "\n";
            line = br.readLine();
            
        }
        br.close();
        return sb;
    }
    
}
