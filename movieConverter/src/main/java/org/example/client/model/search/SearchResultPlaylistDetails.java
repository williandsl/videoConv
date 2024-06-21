package org.example.client.model.search;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.client.model.Utils;
import org.example.client.model.search.AbstractSearchResultList;
import org.example.client.model.search.SearchResultItemType;

import java.util.LinkedList;

public class SearchResultPlaylistDetails extends AbstractSearchResultList {

    private final String playlistId;
    private final int videoCount;

    public SearchResultPlaylistDetails(JSONObject json) {
        super(json);
        playlistId = json.getString("playlistId");
        JSONArray thumbnailGroups = json.getJSONArray("thumbnails");
        thumbnails = new LinkedList<>();
        for (int i = 0; i < thumbnailGroups.size(); i++) {
            thumbnails.addAll(Utils.parseThumbnails(thumbnailGroups.getJSONObject(i)));
        }
        if (json.containsKey("videoCount")) {
            videoCount = Integer.parseInt(json.getString("videoCount"));
        } else {
            videoCount = -1;
        }
    }

    @Override
    public SearchResultItemType type() {
        return SearchResultItemType.PLAYLIST;
    }

    @Override
    public SearchResultPlaylistDetails asPlaylist() {
        return this;
    }

    public String playlistId() {
        return playlistId;
    }

    public int videoCount() {
        return videoCount;
    }
}