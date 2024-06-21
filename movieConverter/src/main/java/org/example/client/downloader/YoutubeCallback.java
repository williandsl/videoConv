package org.example.client.downloader;

public interface YoutubeCallback<T> {

    void onFinished(T data);

    void onError(Throwable throwable);
}
