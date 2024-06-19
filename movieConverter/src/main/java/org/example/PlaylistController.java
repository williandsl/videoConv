package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
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
        try {
            List<VideoInfo> videoUrls = playlistService.getPlaylistVideos(playlistUrl);
            for (VideoInfo videoUrl : videoUrls) {
                playlistService.convertVideoToMp3(videoUrl.getUrl());
            }
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
