package org.example.client.model.playlist;


import org.example.client.model.Filter;
import org.example.client.model.playlist.PlaylistDetails;
import org.example.client.model.playlist.PlaylistVideoDetails;

import java.util.List;

public class PlaylistInfo {

    private PlaylistDetails details;
    private List<PlaylistVideoDetails> videos;

    public PlaylistInfo(PlaylistDetails details, List<PlaylistVideoDetails> videos) {
        this.details = details;
        this.videos = videos;
    }

    public PlaylistDetails details() {
        return details;
    }

    public List<PlaylistVideoDetails> videos() {
        return videos;
    }

    public PlaylistVideoDetails findVideoById(String videoId) {
        for (PlaylistVideoDetails video : videos) {
            if (video.videoId().equals(videoId))
                return video;
        }
        return null;
    }

    public List<PlaylistVideoDetails> findVideos(Filter<PlaylistVideoDetails> filter) {
        return filter.select(videos);
    }
}
