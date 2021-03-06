package com.g4g.basic;

import java.util.Scanner;


//Power Of 2
//Show Topic Tags

/**
 * Maths, Bit Operations
 */

/*Description*/
//******************************************************************************************************************
//Given a positive integer N, check if N is a power of 2.

//Input:
//        The first line contains 'T' denoting the number of test cases. Then follows description of test cases.
//        Each test case contains a single positive integer N.
//
//
//        Output:
//        Print "YES" if it is a power of 2 else "NO". (Without the double quotes)
//
//
//        Constraints:
//        1<=T<=100
//        0<=N<=10^18
//
//        Example:
//        Input:
//        2
//        1
//        98
//
//        Output :
//        YES
//        ​NO
//
//        Explanation:  (2^0 == 1)
//******************************************************************************************************************
//Companies: Microsoft, Mallow Technologies, Intel, FactSet, BankBazaar, Adobe, Samsung, Practo, Oracle
//******************************************************************************************************************
//Link: http://practice.geeksforgeeks.org/problems/power-of-2/0
//******************************************************************************************************************

public class PowerOf2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        while (T-- > 0) {
            long n = scan.nextLong();

            System.out.println(run(n)? "YES": "NO");
        }
    }


    //using bit wise operations
    //only 1 bit should come as 1
    static boolean run(long n) {

        int ones = 0;
        //loop through 32 bits (integer size)
        for(char i=0;i<32;i++){
            //validate current bit and maintain count of 1s in ones variable
            if((n & (1 << i)) > 0)
                ones++;
            //validate if ones is greater than 1 which means we have more than 1 bits in the integer, exit and return false
            if(ones > 1)
                return false;
        }

        //validate if we have only 1
        if(ones == 1)
            return true;

        //otherwise we don't have any, it is zero, return false
        return false;



    }

    //much simpler
    static boolean run2(long n){
        //continue to get divisible and as long its remainder is 0 (divide by 2) .... until you get more than 0 .. then make sure the number == 1 otherwise return false
        //e.g. 8 = 8 / 2 = 4 / 2 = 2 / 2 = 1 => TRUE
        while(n % 2 == 0)
            n = n / 2;

        return n == 1;
    }

    //math trick, easy and simple too
    //if we noticed the previous number to any number that is pow of 2 have all bits set to "1" except that last BIT so if we bitwise AND both number, it should give us zero. let's have examples
    //1 => 0001 and 2 => 0010       => bitwise AND both 0001 & 0010 = 0
    //3 => 0011 and 4 => 0100       => bitwise AND both 0011 & 0100 = 0
    //7 => 0111 and 8 => 1000       => bitwise AND both 0111 & 1000 = 0
    static boolean run3(long n){
        return (n & (n-1)) == 0;
    }
}
