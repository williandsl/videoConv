package org.example.client.downloader.request;

import org.example.client.downloader.request.Request;
import org.example.client.model.search.SearchResult;
import org.example.client.model.search.query.Searchable;

public class RequestSearchable extends Request<RequestSearchable, SearchResult> {

    private final String searchPath;

    public RequestSearchable(Searchable searchable) {
        this.searchPath = searchable.searchPath();
    }

    public String searchPath() {
        return searchPath;
    }

}
