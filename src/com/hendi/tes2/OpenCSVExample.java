/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hendi.tes2;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Senju Hashirama
 */
public class OpenCSVExample {
    public static void main(String[] args)
    {
        CSVReader reader = null;
        try
        {
            //Get the CSVReader instance with specifying the delimiter to be used
            reader = new CSVReader(new FileReader("CsvFiles/csvToRead.csv"),',');
            String [] nextLine;
            //Read one line at a time
            while ((nextLine = reader.readNext()) != null)
            {
                for(String token : nextLine)
                {
                    //Print all tokens
                    System.out.println(token);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
