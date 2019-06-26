package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {



        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("\t\t\tMENU\n\n1. Simple Parity Check(Even Parity)\n2. Two-dimensional Parity Check");
        System.out.println("3. Checksum(Hashsum)\n4. Cyclic Redundancy Check\n");
        System.out.print("Enter choice number: ");

        choice = sc.nextInt();
        sc.nextLine(); //next int does not consume line

        switch(choice){

            case 1:
                System.out.println("Simple Parity Check selected.");
                System.out.print("Input 8 bit dataword sent: ");
                String dataword = sc.nextLine();

                System.out.print("Input codeword received: ");
                String codeword = sc.nextLine();

                simpleParityCheck(dataword, codeword);
                break;

            case 2:
                System.out.println("Two-dimensional Parity Check selected.");
                System.out.print("Input 45 bit binary stream(separate with space every 9 bits): ");

                String binaryString = "101100111 101010111 010110100 110101011 100101111";


                // String binaryString = sc.nextLine();
                two_DimensionalParityCheck(binaryString);
                break;

            case 3:
                checkSum();
                break;

            case 4:
                cyclicRedundancyCheck();
                break;

            default:
                System.out.println("Invalid input");
        }

        sc.close();
    }


    static void simpleParityCheck(String input1, String input2){

        String sent, received;

        sent = appendParityBit(input1);

        if(checkSyndrome(input2))
            received = extractDataWord(input2);
        else
            received = "Discarded";

        //print

        System.out.println("@Sender\nCodeword: " + sent);
        System.out.println("@Receiver\nData word: " + received);
        return;
    }

    static void two_DimensionalParityCheck(String input){

        String codeWord1, codeWord2, codeWord3, codeWord4, columnParity;
        String col1, col2, col3, col4, col5, col6, col7, col8;
        String dataWord1, dataWord2, dataWord3, dataWord4, rowParity="";

        int errorCount = 0;

        codeWord1 = input.substring(0,9);
        codeWord2 = input.substring(10,19);
        codeWord3 = input.substring(20,29);
        codeWord4 = input.substring(30,39);
        columnParity = input.substring(40,49); // last row of 2d array

        dataWord1 = extractDataWord(codeWord1);
        dataWord2 = extractDataWord(codeWord2);
        dataWord3 = extractDataWord(codeWord3);
        dataWord4 = extractDataWord(codeWord4);

        rowParity = rowParity.concat((extractParityBit(codeWord1)));
        rowParity = rowParity.concat((extractParityBit(codeWord2)));
        rowParity = rowParity.concat((extractParityBit(codeWord3)));
        rowParity = rowParity.concat((extractParityBit(codeWord4)));

        if(!checkSyndrome(codeWord1))
            errorCount++;
        if(!checkSyndrome(codeWord2))
            errorCount++;
        if(!checkSyndrome(codeWord3))
            errorCount++;
        if(!checkSyndrome(codeWord4))
            errorCount++;

        col1 = makeColumn(dataWord1,dataWord2,dataWord3,dataWord4,columnParity,0);
        col2 = makeColumn(dataWord1,dataWord2,dataWord3,dataWord4,columnParity,1);
        col3 = makeColumn(dataWord1,dataWord2,dataWord3,dataWord4,columnParity,2);
        col4 = makeColumn(dataWord1,dataWord2,dataWord3,dataWord4,columnParity,3);
        col5 = makeColumn(dataWord1,dataWord2,dataWord3,dataWord4,columnParity,4);
        col6 = makeColumn(dataWord1,dataWord2,dataWord3,dataWord4,columnParity,5);
        col7 = makeColumn(dataWord1,dataWord2,dataWord3,dataWord4,columnParity,6);
        col8 = makeColumn(codeWord1,codeWord2,codeWord3,codeWord4,columnParity,7);

        if(!checkSyndrome(col1))
            errorCount++;
        if(!checkSyndrome(col2))
            errorCount++;
        if(!checkSyndrome(col3))
            errorCount++;
        if(!checkSyndrome(col4))
            errorCount++;
        if(!checkSyndrome(col5))
            errorCount++;
        if(!checkSyndrome(col6))
            errorCount++;
        if(!checkSyndrome(col7))
            errorCount++;
        if(!checkSyndrome(col8))
            errorCount++;



        System.out.println(errorCount);
//        System.out.println(col2);
//        System.out.println(col3);
//        System.out.println(col4);
//        System.out.println(col5);
//        System.out.println(col6);
//        System.out.println(col7);
//        System.out.println(col8);

//        System.out.println(codeWord3);
//        System.out.println(codeWord4);
//        System.out.println(columnParity);
        return;
    }

    static void checkSum(){
        return;
    }

    static void cyclicRedundancyCheck(){
        return;
    }

    static int count1s(String input){
        int counter = 0;

        for( int i=0; i<input.length(); i++ ){
            if(input.charAt(i)=='1')
                counter++;
        }
        return counter;
    }

    static String appendParityBit(String input){

        if(count1s(input)%2 == 0)
            return input.concat("0");
        else
            return input.concat("1");
    }

    static boolean checkSyndrome(String input){

        return (count1s(input)%2 == 0) && !(input.isEmpty());
    }

    static String extractDataWord(String input){

        return input.substring(0,input.length()-2);
    }

    static String extractParityBit(String input){

        return input.substring(input.length()-1);
    }

    static String makeColumn(String s1, String s2, String s3, String s4, String colpar, int index){
        String output = "";
        output = output.concat(s1.substring(index,index+1));
        output = output.concat(s2.substring(index,index+1));
        output = output.concat(s3.substring(index,index+1));
        output = output.concat(s4.substring(index,index+1));
        output = output.concat(colpar.substring(index,index+1));
        return output;
    }
}
