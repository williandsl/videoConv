package org.example.client.model.videos;


import com.alibaba.fastjson.JSONObject;
import org.example.client.model.AbstractVideoDetails;

import java.util.ArrayList;
import java.util.List;

public class VideoDetails extends AbstractVideoDetails {

    private List<String> keywords;
    private String shortDescription;
    private long viewCount;
    private int averageRating;
    private boolean isLiveContent;
    private String liveUrl;

    public VideoDetails(String videoId) {
        this.videoId = videoId;
    }

    public VideoDetails(JSONObject json, String liveHLSUrl) {
        super(json);
        title = json.getString("title");
        author = json.getString("author");
        isLive = json.getBooleanValue("isLive");

        keywords = json.containsKey("keywords") ? json.getJSONArray("keywords").toJavaList(String.class) : new ArrayList<String>();
        shortDescription = json.getString("shortDescription");
        averageRating = json.getIntValue("averageRating");
        viewCount = json.getLongValue("viewCount");
        isLiveContent = json.getBooleanValue("isLiveContent");
        liveUrl = liveHLSUrl;
    }

    @Override
    public boolean isDownloadable()  {
        return !isLive() && !(isLiveContent && lengthSeconds() == 0);
    }

    public List<String> keywords() {
        return keywords;
    }

    public String description() {
        return shortDescription;
    }

    public long viewCount() {
        return viewCount;
    }

    public int averageRating() {
        return averageRating;
    }

    public boolean isLiveContent() {
        return isLiveContent;
    }

    public String liveUrl() {
        return liveUrl;
    }
}
