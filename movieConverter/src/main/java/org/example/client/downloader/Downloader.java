package org.example.client.downloader;

import org.example.client.downloader.request.RequestVideoFileDownload;
import org.example.client.downloader.request.RequestVideoStreamDownload;
import org.example.client.downloader.request.RequestWebpage;
import org.example.client.downloader.response.Response;

import java.io.File;

public interface Downloader {

    Response<String> downloadWebpage(RequestWebpage request);

    Response<File> downloadVideoAsFile(RequestVideoFileDownload request);

    Response<Void> downloadVideoAsStream(RequestVideoStreamDownload request);

}
