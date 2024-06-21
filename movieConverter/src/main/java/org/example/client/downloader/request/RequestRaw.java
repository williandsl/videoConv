package org.example.client.downloader.request;

import org.example.client.downloader.request.Request;

public abstract class RequestRaw<T extends RequestRaw<T>> extends Request<T, String> {

    public abstract String getDownloadUrl();

}
