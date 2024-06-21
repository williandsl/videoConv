package org.example.client.downloader.request;

import org.example.client.downloader.request.Request;
import org.example.client.model.videos.VideoInfo;

public class RequestVideoInfo extends Request<RequestVideoInfo, VideoInfo> {

    private final String videoId;

    public RequestVideoInfo(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }
}
