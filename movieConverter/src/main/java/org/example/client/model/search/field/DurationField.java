package org.example.client.model.search.field;

import org.example.client.model.search.field.SearchField;

public enum DurationField implements org.example.client.model.search.field.SearchField {
    UNDER_4_MINUTES(3, 1),
    OVER_20_MINUTES(3, 2),
    FROM_4_TO_20_MINUTES(3, 3);

    private final byte[] data;

    private DurationField(int... data) {
        this.data = SearchField.convert(data);
    }

    @Override
    public byte[] data() {
        return data;
    }
}
