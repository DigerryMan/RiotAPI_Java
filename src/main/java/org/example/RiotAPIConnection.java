package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RiotAPIConnection {
    private static final String REGION = "eun1";
    private static String API_KEY = "RGAPI-37eac63e-395f-4341-bae0-c9e545235846";
    static{ API_KEY = "RGAPI-637e0522-bfbc-4354-8ec9-b6250edab6e7";}
    private static final String FIRST_URL_PART = "https://" + REGION + ".api.riotgames.com";
    private static final String AUTHORIZATION = "?api_key=" + API_KEY;
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
        //puuid = "FCIgtQnEeiSjyIcs3xHjQfCIzf9dtlpFE1nZCl3GwqOP2Mypkw3DQAUCstktJeJSo-2oRjrN0__NJQ";
        //String url = FIRST_URL_PART + "/lol/summoner/v4/summoners/by-puuid/"+ puuid + AUTHORIZATION;
        String url = "https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/" +
                "Pan%20Stanis%C5%82aw/EUNE?api_key=RGAPI-637e0522-bfbc-4354-8ec9-b6250edab6e7";
        System.out.println(url);

        String res = ConnectAndGet(url);
        String[] words = res.split("[\"{}:,]");
        for (String word : words){
            System.out.println(word);
        }
        List<String> lista = new ArrayList<>();
        for (String word : words) {
            if(word != null && !word.isEmpty()){
                lista.add(word);
            }
        }
        puuid = lista.get(1);
        url = FIRST_URL_PART + "/lol/summoner/v4/summoners/by-puuid/" + puuid + AUTHORIZATION;
        res = ConnectAndGet(url);
        System.out.println(res);
    }

    private static String ConnectAndGet(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){
            System.out.println("Summoner info retrieved successfully!");
            return response.body();
        }
        else{
            System.out.println("Summoner info retrieved failed! Code: " + response.statusCode());
            return "";
        }
    }

}
