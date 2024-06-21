package org.example.client;


import org.example.client.cipher.CachedCipherFactory;
import org.example.client.downloader.Downloader;
import org.example.client.downloader.DownloaderImpl;
import org.example.client.downloader.request.*;
import org.example.client.downloader.response.Response;
import org.example.client.downloader.response.ResponseImpl;
import org.example.client.extractor.ExtractorImpl;
import org.example.client.model.playlist.PlaylistInfo;
import org.example.client.model.search.SearchResult;
import org.example.client.model.subtitles.SubtitlesInfo;
import org.example.client.model.videos.VideoInfo;
import org.example.client.parser.Parser;
import org.example.client.parser.ParserImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.example.client.model.Utils.createOutDir;

public class YoutubeDownloader {

    private final org.example.client.Config config;
    private final Downloader downloader;
    private final Parser parser;

    public YoutubeDownloader() {
        this(org.example.client.Config.buildDefault());
    }

    public YoutubeDownloader(org.example.client.Config config) {
        this.config = config;
        this.downloader = new DownloaderImpl(config);
        this.parser = new ParserImpl(config, downloader, new ExtractorImpl(downloader), new CachedCipherFactory(downloader));
    }

    public YoutubeDownloader(org.example.client.Config config, Downloader downloader) {
        this(config, downloader, new ParserImpl(config, downloader, new ExtractorImpl(downloader), new CachedCipherFactory(downloader)));
    }

    public YoutubeDownloader(org.example.client.Config config, Downloader downloader, Parser parser) {
        this.config = config;
        this.parser = parser;
        this.downloader = downloader;
    }

    public Config getConfig() {
        return config;
    }

    public Response<VideoInfo> getVideoInfo(RequestVideoInfo request) {
        return parser.parseVideo(request);
    }

    public Response<List<SubtitlesInfo>> getSubtitlesInfo(RequestSubtitlesInfo request) {
        return parser.parseSubtitlesInfo(request);
    }

    public Response<PlaylistInfo> getChannelUploads(RequestChannelUploads request) {
        return parser.parseChannelsUploads(request);
    }

    public Response<PlaylistInfo> getPlaylistInfo(RequestPlaylistInfo request) {
        return parser.parsePlaylist(request);
    }

    public Response<SearchResult> search(RequestSearchResult request) {
        return parser.parseSearchResult(request);
    }

    public Response<SearchResult> searchContinuation(RequestSearchContinuation request) {
        return parser.parseSearchContinuation(request);
    }

    public Response<SearchResult> search(RequestSearchable request) {
        return parser.parseSearcheable(request);
    }

    public Response<File> downloadVideoFile(RequestVideoFileDownload request) {
        File outDir = request.getOutputDirectory();
        try {
            createOutDir(outDir);
        } catch (IOException e) {
            return ResponseImpl.error(e);
        }

        return downloader.downloadVideoAsFile(request);
    }

    public Response<Void> downloadVideoStream(RequestVideoStreamDownload request) {
        return downloader.downloadVideoAsStream(request);
    }

    public Response<String> downloadSubtitle(RequestWebpage request) {
        return downloader.downloadWebpage(request);
    }

}
