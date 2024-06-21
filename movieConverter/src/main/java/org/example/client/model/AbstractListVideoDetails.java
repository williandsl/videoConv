package org.example.client.model;

import com.alibaba.fastjson.JSONObject;
import org.example.client.model.AbstractVideoDetails;
import org.example.client.model.Utils;

// Video item of a list (playlist, or search result).
public class AbstractListVideoDetails extends AbstractVideoDetails {

    public AbstractListVideoDetails(JSONObject json) {
        super(json);
        author = org.example.client.model.Utils.parseRuns(json.getJSONObject("shortBylineText"));
        JSONObject jsonTitle = json.getJSONObject("title");
        if (jsonTitle.containsKey("simpleText")) {
            title = jsonTitle.getString("simpleText");
        } else {
            title = Utils.parseRuns(jsonTitle);
        }
    }
}
