package org.example;

import com.google.gson.JsonObject;

import java.io.IOException;

public class GameInfoProvider {
    private final JsonObject gameInfo;
    public GameInfoProvider() throws IOException, InterruptedException {
        this.gameInfo = RiotAPIConnection.getPlayerInfo(0);
        getInfo();
        //RiotAPIConnection.ConnectAndGet("//127.0.0.1:2999/liveclientdata/allgamedata");

    }
    public void getInfo() throws IOException, InterruptedException {
        System.out.println(gameInfo.keySet());
        System.out.println(gameInfo.get("puuid").getAsString());
    }
}
