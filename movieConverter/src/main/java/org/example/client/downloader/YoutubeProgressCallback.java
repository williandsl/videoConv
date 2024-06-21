package org.example.client.downloader;

import org.example.client.downloader.YoutubeCallback;

public interface YoutubeProgressCallback<T> extends YoutubeCallback<T> {

    void onDownloading(int progress);

}
