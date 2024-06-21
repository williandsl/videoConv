package org.example.client.model.search.query;

import com.alibaba.fastjson.JSONObject;
import org.example.client.model.Utils;
import org.example.client.model.search.query.Searchable;

import java.util.List;

public class QueryRefinement extends Searchable {

    private final List<String> thumbnails;

    public QueryRefinement(JSONObject json) {
        super(json);
        thumbnails = Utils.parseThumbnails(json.getJSONObject("thumbnail"));
    }

    public List<String> thumbnails() {
        return thumbnails;
    }

    @Override
    protected String extractQuery(JSONObject json) {
        return Utils.parseRuns(json.getJSONObject("query"));
    }

    @Override
    protected String extractSearchPath(JSONObject json) {
        return json.getJSONObject("searchEndpoint")
                .getJSONObject("commandMetadata")
                .getJSONObject("webCommandMetadata")
                .getString("url");
    }

}
