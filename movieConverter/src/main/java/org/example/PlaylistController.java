package org.example;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.Document;
import javax.swing.text.Element;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@EnableAsync
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;
    @PostMapping("/convertPlaylist")
    public String convertPlaylistToMp3(@RequestParam String playlistUrl) {
        String outputDirectory = "downloaded_video"; // Pasta onde o vídeo será salvo
        String videoUrl = "https://www.youtube.com/watch?v=VIDEO_ID"; // Substitua pelo URL do vídeo


        try {
            //List<VideoInfo> videoUrls = playlistService.getPlaylistVideos(playlistUrl);
            //for (VideoInfo videoUrl : videoUrls) {
                playlistService.downloadVideo("https://www.youtube.com/watch?v=pq2JKVI6F5s", outputDirectory,"audio.mp3");
            //}
            return "Playlist conversion to MP3 started.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error converting playlist: " + e.getMessage();
        }
    }

    private List<String> getPlaylistVideoUrls(String playlistUrl) throws Exception {
        String command = "yt-dlp --flat-playlist --get-id " + playlistUrl;
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        List<String> videoIds = reader.lines().collect(Collectors.toList());
        process.waitFor();

        return videoIds.stream()
                .map(id -> "https://www.youtube.com/watch?v=" + id)
                .collect(Collectors.toList());
    }

    private void convertVideoToMp3(String videoUrl) throws Exception {
        String command = String.format("yt-dlp -x --audio-format mp3 --audio-quality 192K --output \"downloaded_videos/%%(title)s.%%(ext)s\" %s", videoUrl);

        ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        process.waitFor();
    }
}
