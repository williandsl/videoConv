package org.example.client.model.search.query;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.client.model.search.query.QueryElement;
import org.example.client.model.search.query.QueryElementType;
import org.example.client.model.search.query.QueryRefinement;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class QueryRefinementList extends ArrayList<org.example.client.model.search.query.QueryRefinement> implements QueryElement {

    private final String title;

    public QueryRefinementList(JSONObject json) {
        super(json.getJSONArray("cards").size());
        title = json.getJSONObject("header")
                .getJSONObject("richListHeaderRenderer")
                .getJSONObject("title")
                .getString("simpleText");
        JSONArray jsonCards = json.getJSONArray("cards");
        for (int i = 0; i < jsonCards.size(); i++) {
            JSONObject jsonRenderer = jsonCards.getJSONObject(i).getJSONObject("searchRefinementCardRenderer"); 
            add(new QueryRefinement(jsonRenderer));
        }
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public QueryElementType type() {
        return QueryElementType.REFINEMENT_LIST;
    }
}
