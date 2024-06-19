package org.example;


import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService2 {
    public List<VideoInfo> getPlaylistVideos(String playlistUrl) throws IOException {
        List<VideoInfo> videos = new ArrayList<>();

        // Obtém o JSON da página da playlist
        String jsonContent = retrieveJsonFromUrl(playlistUrl);

        // Parse o JSON para um objeto JSON
        JSONObject jsonObject = new JSONObject(jsonContent);

        // Extrai a lista de vídeos do JSON
        JSONArray items = jsonObject.getJSONObject("contents")
                .getJSONObject("twoColumnBrowseResultsRenderer")
                .getJSONArray("tabs")
                .getJSONObject(0)
                .getJSONObject("tabRenderer")
                .getJSONObject("content")
                .getJSONObject("sectionListRenderer")
                .getJSONArray("contents")
                .getJSONObject(0)
                .getJSONObject("itemSectionRenderer")
                .getJSONArray("contents")
                .getJSONObject(0)
                .getJSONObject("playlistVideoListRenderer")
                .getJSONArray("contents");

        for (int i = 0; i < items.length(); i++) {
            if (!items.getJSONObject(i).has("playlistVideoRenderer")) {
                continue;
            }
            JSONObject item = items.getJSONObject(i).getJSONObject("playlistVideoRenderer");

            String videoId = item.getString("videoId");
            String playlistId = String.valueOf(jsonObject.getJSONObject("metadata")
                    .getJSONObject("playlistMetadataRenderer"));
            int index = item.getJSONObject("index").getInt("simpleText");

            // Construa o URL do vídeo se necessário
            String url = "https://www.youtube.com/watch?v=" + videoId;

            VideoInfo video = new VideoInfo(videoId, playlistId, url, index);
            videos.add(video);
        }

        return videos;
    }

    private String retrieveJsonFromUrl(String url) throws IOException {
        // Faz a requisição HTTP e obtém o JSON da página
        Document doc = Jsoup.connect(url).ignoreContentType(true).get();

        // Aqui você deve procurar o bloco JSON que contém as informações da playlist
        // Por exemplo, o JSON pode estar em um <script> específico na página HTML
        String jsonString = null;
        for (Element script : doc.select("script")) {
            String scriptContent = script.html();
            if (scriptContent.contains("ytInitialData")) {
                int start = scriptContent.indexOf("ytInitialData =") + 15;
                int end = scriptContent.indexOf(";", start);
                jsonString = scriptContent.substring(start, end).trim();
                break;
            }
        }

        if (jsonString == null) {
            throw new IOException("Não foi possível encontrar o JSON na página.");
        }

        return jsonString;
    }


}
