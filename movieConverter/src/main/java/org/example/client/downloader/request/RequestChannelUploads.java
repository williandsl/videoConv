package org.example.client.downloader.request;

import org.example.client.downloader.request.Request;
import org.example.client.downloader.request.RequestPlaylistInfo;
import org.example.client.model.playlist.PlaylistInfo;

public class RequestChannelUploads extends Request<RequestPlaylistInfo, PlaylistInfo> {

    private final String channelId;

    public RequestChannelUploads(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelId() {
        return channelId;
    }

}
