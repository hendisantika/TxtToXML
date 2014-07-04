/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendi.tes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 *
 * @author Senju Hashirama
 */
public class TextToXML {

    /**
     * @param args the command line arguments
     */
        // TODO code application logic here
        BufferedReader in;
        StreamResult out;

        TransformerHandler th;
        AttributesImpl atts;
        // Here's our entry point ... 
    public static void main(String args[]) {
        new TextToXML().doit();
    }

    public void doit() {
        try {
            in = new BufferedReader(new FileReader("C:\\Users\\Senju Hashirama\\Documents\\NetBeansProjects\\TextToXML\\src\\com\\hendi\\tes/testdata.txt"));
            out = new StreamResult(new FileWriter("C:\\Users\\Senju Hashirama\\Documents\\NetBeansProjects\\TextToXML\\src\\com\\hendi\\testestdata.xml"));
            initXML();
            String str;
            // declare an array that can contain 4 strings
            String[] SArray = new String[4];
            int i = 0;

            while ((str = in.readLine()) != null) {
//              System.out.println("input line: " + str);
                SArray[i] = str;
                i++;

            }

            process(SArray);

            in.close();
            closeXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initXML() throws ParserConfigurationException,
            TransformerConfigurationException, SAXException {
        // JAXP + SAX
        SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory
                .newInstance();

        th = tf.newTransformerHandler();
        Transformer serializer = th.getTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        // pretty XML output
        serializer.setOutputProperty(
                "{http://xml.apache.org/xslt}indent-amount", "4");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        th.setResult(out);
        th.startDocument();
        atts = new AttributesImpl();
        th.startElement("", "", "SYSTEM-TRACKS", atts);

    }

    public void process(String[] elements) throws SAXException {
//      System.out.println("number of elements: " + elements.length);
        atts.clear();

        th.startElement("", "", "AIS-SENSOR", atts);
        th.characters(elements[0].toCharArray(), 0, elements[0].length());

        th.startElement("", "", "MMSI", atts);
        th.characters(elements[1].toCharArray(), 0, elements[1].length());
        th.endElement("", "", "MMSI");

        th.startElement("", "", "LATITUDE", atts);
        th.characters(elements[2].toCharArray(), 0, elements[2].length());
        th.endElement("", "", "LATITUDE");

        th.startElement("", "", "LONGITUDE", atts);
        th.characters(elements[3].toCharArray(), 0, elements[3].length());
        th.endElement("", "", "LONGITUDE");

    }

    public void closeXML() throws SAXException {
        th.endElement("", "", "AIS-SENSOR");
        th.endElement("", "", "SYSTEM-TRACKS");
        th.endDocument();
    }
}

