/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendi.tes2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Senju Hashirama
 */
public class ScannerExample {

    public static void main(String[] args) throws FileNotFoundException {
        //Set the delimiter used in file
        try ( //Get scanner instance
                Scanner scanner = new Scanner(new File("CsvFiles/csvToRead.csv"))) {
            //Set the delimiter used in file
            scanner.useDelimiter(",");
            
            //Get all tokens and store them in some data structure
            //I am just printing them
            while (scanner.hasNext()) {
                System.out.print(scanner.next() + "|");
            }
        }
    }
}
