package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/api")
public class PlaylistController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping("/convertPlaylist")
    public String downloadAudioFromPlaylist(@RequestBody PlaylistRequestDTO requestDTO) {
        playlistService.downloadAudioFromPlaylist(requestDTO);
        return "Áudios da playlist baixados com sucesso na pasta 'downloads'.";
    }
}
