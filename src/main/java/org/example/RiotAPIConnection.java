package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;


public class RiotAPIConnection {
    private static final String REGION = "eun1";
    private static String API_KEY = "RGAPI-37eac63e-395f-4341-bae0-c9e545235846";
    static{ API_KEY = "RGAPI-637e0522-bfbc-4354-8ec9-b6250edab6e7";}
    private static final String FIRST_URL_PART = "https://" + REGION + ".api.riotgames.com";
    private static final String FIRST_EUROPE_PART = "https://" + "europe" + ".api.riotgames.com";
    private static final String AUTHORIZATION = "?api_key=" + API_KEY;
    private static final List<String> NICKNAMES = new ArrayList<>(Arrays.asList("Pan Stanisław", "Yoyosim03"));

    public static void connect(int summonerIndex) throws IOException, InterruptedException {
        System.out.println(
            getPlayerInfo(NICKNAMES.get(summonerIndex))
        );

    }

    private static String getPLayerPUUID(String playerName) throws IOException, InterruptedException{
        if(playerName.equals("Pan Stanisław")){
            playerName = "Pan%20Stanis%C5%82aw";
        }

        String url = FIRST_EUROPE_PART + "/riot/account/v1/accounts/by-riot-id/" +
                playerName + "/EUNE" + AUTHORIZATION;

        String res = ConnectAndGet(url);
        String[] words = res.split("[\"{}:,]");

        List<String> lista = new ArrayList<>();
        for (String word : words) {
            if(word != null && !word.isEmpty()){
                lista.add(word);
            }
        }
        return lista.get(1);
    }

    private static String getPlayerInfo(String playerName) throws IOException, InterruptedException {
        String puuid = getPLayerPUUID(playerName);
        String url = FIRST_URL_PART + "/lol/summoner/v4/summoners/by-puuid/" + puuid + AUTHORIZATION;
        return ConnectAndGet(url);
    }

    private static String ConnectAndGet(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){
            System.out.println("Info retrieved successfully!");
            return response.body();
        }
        else{
            System.out.println("Connection ERROR! Code: " + response.statusCode());
            return "";
        }
    }

}
