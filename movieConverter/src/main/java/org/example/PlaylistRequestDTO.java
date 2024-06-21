package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlaylistRequestDTO {
    @JsonProperty("playlist")
    private String playlistId;
}
