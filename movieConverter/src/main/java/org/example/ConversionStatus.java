package org.example;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
@AllArgsConstructor
public class ConversionStatus {
    private Map<String, String> videoStatus;

    public ConversionStatus() {
        this.videoStatus = new HashMap<>();
    }

    public synchronized void updateStatus(String videoId, String status) {
        videoStatus.put(videoId, status);
    }

    public synchronized Map<String, String> getStatus() {
        return new HashMap<>(videoStatus);
    }

    public synchronized String getStatus(String videoId) {
        return videoStatus.get(videoId);
    }

}
