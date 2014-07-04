/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendi.tes2;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.regex.Pattern;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.AttributesImpl;

/**
 *
 * @author Senju Hashirama
 */
public class Item {

    public static void main(String args[]) {
        Item.readWrite("CsvFiles/csvToRead.csv", "CsvFiles/test.xml");
    }

    public static void readWrite(String fromFile, String toFile) {
        try {
            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
            BufferedReader in = new BufferedReader(new FileReader(fromFile));
            FileOutputStream fos = new FileOutputStream(toFile);
            OutputFormat of = new OutputFormat("XML", "windows-1250", true);
            of.setIndent(1);
            of.setIndenting(true);
            XMLSerializer serializer = new XMLSerializer(fos, of);
            ContentHandler hd = serializer.asContentHandler();
            hd.startDocument();
            AttributesImpl atts = new AttributesImpl();
            hd.startElement("", "", "CONTACTS", atts);
            String line = null, tag;
            while ((line = in.readLine()) != null) {
                if (line.equals("Contact")) {
                    line = in.readLine();
                    hd.startElement("", "", "CONTACT", atts);
                    int i = 0;
                    while (!line.equals("Contact")) {
                        if (i == 0) {
                            tag = "FirstName";
                        } else if (i == 1) {
                            tag = "LastName";
                        } else {
                            if (p.matcher(line).matches()) {
                                tag = "EMail";
                            } else {
                                tag = "URL";
                            }
                        }
                        hd.startElement("", "", tag, atts);
                        hd.characters(line.toCharArray(), 0, line.length());
                        hd.endElement("", "", tag);
                        i++;
                        line = in.readLine();
                    }
                    hd.endElement("", "", "CONTACT");
                }
            }
            hd.endElement("", "", "CONTACTS");
            hd.endDocument();
            fos.close();
            in.close();
        } catch (Exception E) {
            System.out.println("Cannot Generate XML!!!");
        }

    }

}
