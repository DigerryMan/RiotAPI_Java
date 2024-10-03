package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try{
            RiotAPIConnection.connectBySummonerName("Yoyosim03");
        }catch(IOException e){
            System.out.println("An error occured while connecting to Riot - IOException: " + e.getMessage());
        }catch(InterruptedException e){
            System.out.println("An error occured while connecting to Riot - InterruptedException: " + e.getMessage());
        }

    }
}