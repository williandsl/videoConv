package org.example.client.model.search;

import org.example.client.model.search.SearchContinuation;
import org.example.client.model.search.SearchResult;
import org.example.client.model.search.SearchResultItem;
import org.example.client.model.search.query.QueryElement;
import org.example.client.model.search.query.QueryElementType;

import java.util.List;
import java.util.Map;

public class ContinuatedSearchResult extends SearchResult {

    private final SearchContinuation continuation;

    public ContinuatedSearchResult(long estimatedResults, List<SearchResultItem> items,
            Map<QueryElementType, QueryElement> queryElements, SearchContinuation continuation) {
        super(estimatedResults, items, queryElements);
        this.continuation = continuation;
    }

    public boolean hasContinuation() {
        return true;
    }

    public SearchContinuation continuation() {
        return continuation;
    }
}
