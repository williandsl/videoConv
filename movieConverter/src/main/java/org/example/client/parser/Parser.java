package org.example.client.parser;

import org.example.client.downloader.request.*;
import org.example.client.downloader.response.Response;
import org.example.client.model.playlist.PlaylistInfo;
import org.example.client.model.search.SearchResult;
import org.example.client.model.subtitles.SubtitlesInfo;
import org.example.client.model.videos.VideoInfo;

import java.util.List;

public interface Parser {

    /* Video */

    Response<VideoInfo> parseVideo(RequestVideoInfo request);

    /* Playlist */

    Response<PlaylistInfo> parsePlaylist(RequestPlaylistInfo request);

    /* Channel uploads */

    Response<PlaylistInfo> parseChannelsUploads(RequestChannelUploads request);

    /* Subtitles */

    Response<List<SubtitlesInfo>> parseSubtitlesInfo(RequestSubtitlesInfo request);

    /* Search */

    Response<SearchResult> parseSearchResult(RequestSearchResult request);

    Response<SearchResult> parseSearchContinuation(RequestSearchContinuation request);

    Response<SearchResult> parseSearcheable(RequestSearchable request);

}
