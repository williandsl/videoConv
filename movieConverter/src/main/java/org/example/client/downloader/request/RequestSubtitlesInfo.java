package org.example.client.downloader.request;

import org.example.client.downloader.request.Request;
import org.example.client.model.subtitles.SubtitlesInfo;

import java.util.List;

public class RequestSubtitlesInfo extends Request<RequestSubtitlesInfo, List<SubtitlesInfo>> {

    private final String videoId;

    public RequestSubtitlesInfo(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }
}
