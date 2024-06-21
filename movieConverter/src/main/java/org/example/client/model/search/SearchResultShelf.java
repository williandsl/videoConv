package org.example.client.model.search;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.client.model.search.SearchResultItem;
import org.example.client.model.search.SearchResultItemType;
import org.example.client.model.search.SearchResultVideoDetails;

import java.util.ArrayList;
import java.util.List;

public class SearchResultShelf implements SearchResultItem {

    private final String title;
    private final List<org.example.client.model.search.SearchResultVideoDetails> videos;

    public SearchResultShelf(JSONObject json) {
        title = json.getJSONObject("title").getString("simpleText");
        JSONObject jsonContent = json.getJSONObject("content");
        
        // verticalListRenderer / horizontalMovieListRenderer
        String contentRendererKey = jsonContent.keySet().iterator().next();
        boolean isMovieShelf = contentRendererKey.contains("Movie");
        JSONArray jsonItems = jsonContent.getJSONObject(contentRendererKey).getJSONArray("items");
        videos = new ArrayList<>(jsonItems.size());
        for (int i = 0; i < jsonItems.size(); i++) {
            JSONObject jsonItem = jsonItems.getJSONObject(i);
            String itemRendererKey = jsonItem.keySet().iterator().next();
            videos.add(new org.example.client.model.search.SearchResultVideoDetails(jsonItem.getJSONObject(itemRendererKey), isMovieShelf));
        }
    }

    @Override
    public org.example.client.model.search.SearchResultItemType type() {
        return SearchResultItemType.SHELF;
    }

    @Override
    public SearchResultShelf asShelf() {
        return this;
    }

    @Override
    public String title() {
        return title;
    }

    public List<SearchResultVideoDetails> videos() {
        return videos;
    }

}
