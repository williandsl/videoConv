package org.example.client.downloader.request;

import org.example.client.downloader.request.Request;
import org.example.client.model.search.ContinuatedSearchResult;
import org.example.client.model.search.SearchContinuation;
import org.example.client.model.search.SearchResult;

public class RequestSearchContinuation extends Request<RequestSearchContinuation, SearchResult> {

    private final SearchContinuation continuation;

    public RequestSearchContinuation(SearchResult result) {
        if (!result.hasContinuation()) {
            throw new IllegalArgumentException("Search result must have a continuation");
        }
        this.continuation = ((ContinuatedSearchResult) result).continuation();
    }

    public SearchContinuation continuation() {
        return continuation;
    }
}
