package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("START!");

        try{
            // 0 PAN STANIS£AW
            // 1 YOYOSIOM03
            RiotAPIConnection.connect(0);
        }catch(IOException e){
            System.out.println("An error occured while connecting to Riot - IOException: " + e.getMessage());
        }catch(InterruptedException e){
            System.out.println("An error occured while connecting to Riot - InterruptedException: " + e.getMessage());
        }

    }
}