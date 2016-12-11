/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ct60a2411.harkkatyö;

import java.io.FileNotFoundException;
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
            wr.println("*** LOKI ***");
            wr.println("");
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
    
    public void writer(String a, String b, String c, String d) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter wr = new PrintWriter("loki.txt", "UTF-8");
        wr.println("Nimi: " + a);
        wr.println("Lähetyspaikka: " + b);
        wr.println("Saapumispaikka: " + c);
        Date date = new Date();
        wr.println("Lähetysaika: " + df.format(date));
        wr.println("");
        wr.close();
        
    }
    
}
