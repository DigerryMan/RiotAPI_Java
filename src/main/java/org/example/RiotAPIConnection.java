package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;


public class RiotAPIConnection {
    private static final String REGION = "eun1";
    private static final String API_KEY = "RGAPI-37eac63e-395f-4341-bae0-c9e545235846";
    private static final String FIRST_URL_PART = "https://" + REGION + ".api.riotgames.com";
    private static final String[] NICKNAMES = {"Pan Stanisław", "Yoyosim03"};
    private static final Map<String, String> NAME_TO_PUUID = new HashMap<String, String>();
    static{
        NAME_TO_PUUID.put("Pan Stanisław", "FCIgtQnEeiSjyIcs3xHjQfCIzf9dtlpFE1nZCl3GwqOP2Mypkw3DQAUCstktJeJSo-2oRjrN0__NJQ");
        NAME_TO_PUUID.put("Yoyosim03", "qy_-WsP5uprzNzrB3k2fRB88y425e8mLOmf6ULQIwbJP5DNod68DmRmQROM9H0JLKDT27ZewygH2uA");
    }

    public static void connectBySummonerName(String summonerName) throws IOException, InterruptedException {
        String summonerPUUID = NAME_TO_PUUID.get(NICKNAMES[0]);
        if (summonerPUUID != null){
            getPlayerInfo(summonerPUUID);
        }else{
            System.out.println("No such summoner");
        }
    }

    private static void getPlayerInfo(String puuid) throws IOException, InterruptedException {
        String url = FIRST_URL_PART + "/lol/summoner/v4/summoners/by-puuid/"+ puuid;
        System.out.println(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){
            System.out.println("Summoner info retrieved successfully!");
        }
        else{
            System.out.println("Summoner info retrieved failed! Code: " + response.statusCode());
        }


    }

}
