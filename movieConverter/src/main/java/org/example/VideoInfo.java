package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class VideoInfo {
    private String videoId;
    private String playlistId;
    private String url;
    private int index;
}
