package org.example;

import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.downloader.request.RequestPlaylistInfo;
import com.github.kiulian.downloader.downloader.request.RequestVideoFileDownload;
import com.github.kiulian.downloader.downloader.request.RequestVideoInfo;
import com.github.kiulian.downloader.downloader.response.Response;
import com.github.kiulian.downloader.model.playlist.PlaylistInfo;
import com.github.kiulian.downloader.model.playlist.PlaylistVideoDetails;
import com.github.kiulian.downloader.model.videos.VideoInfo;
import com.github.kiulian.downloader.model.videos.formats.AudioFormat;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class PlaylistService {

    private final YoutubeDownloader downloader = new YoutubeDownloader();

    public void downloadAudioFromPlaylist(PlaylistRequestDTO requestDTO) {
        String playlistId = requestDTO.getPlaylistId();

        // Solicitar informações da playlist
        RequestPlaylistInfo request = new RequestPlaylistInfo(playlistId);
        Response<PlaylistInfo> response = downloader.getPlaylistInfo(request);
        PlaylistInfo playlistInfo = response.data();

        List<PlaylistVideoDetails> videos = playlistInfo.videos();

        // Iterar sobre os vídeos da playlist e baixar o melhor áudio de cada um
        for (PlaylistVideoDetails videoDetails : videos) {
            String videoId = videoDetails.videoId();

            // Obter informações do vídeo
            RequestVideoInfo videoRequest = new RequestVideoInfo(videoId);
            Response<VideoInfo> videoResponse = downloader.getVideoInfo(videoRequest);
            VideoInfo videoInfo = videoResponse.data();

            // Selecionar o melhor formato de áudio
            AudioFormat bestAudioFormat = videoInfo.bestAudioFormat();

            // Baixar o áudio
            RequestVideoFileDownload audioDownloadRequest = new RequestVideoFileDownload(bestAudioFormat)
                    .saveTo(new File("downloads")) // diretório onde será salvo
                    .overwriteIfExists(true); // sobrescrever se já existir

            Response<File> audioResponse = downloader.downloadVideoFile(audioDownloadRequest);
            File downloadedFile = audioResponse.data();

            System.out.println("Áudio baixado: " + downloadedFile.getAbsolutePath());
        }
    }
}
