package org.example.client.model.search.query;

import com.alibaba.fastjson.JSONObject;
import org.example.client.model.Utils;

public class QueryAutoCorrection implements QueryElement {

    private final String query;

    public QueryAutoCorrection(JSONObject json) {
        query = Utils.parseRuns(json.getJSONObject("correctedQuery"));
    }

    @Override
    public String title() {
        return null;
    }

    public String query() {
        return query;
    }

    @Override
    public QueryElementType type() {
        return QueryElementType.AUTO_CORRECTION;
    }
}
