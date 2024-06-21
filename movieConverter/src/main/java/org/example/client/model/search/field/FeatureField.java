package org.example.client.model.search.field;

import org.example.client.model.search.field.SearchField;

public enum FeatureField implements org.example.client.model.search.field.SearchField {

    LIVE(8, 1),
    SUBTITLES(5, 1),
    CREATIVE_COMMONS(6, 1),
    LOCATION(23, 1, 1),
    PURCHASED(9, 1);

    private final byte[] data;

    private FeatureField(int... data) {
        this.data = SearchField.convert(data);
    }

    @Override
    public byte[] data() {
        return data;
    }
}
