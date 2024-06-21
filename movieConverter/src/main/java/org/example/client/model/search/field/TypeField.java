package org.example.client.model.search.field;

import org.example.client.model.search.field.SearchField;

public enum TypeField implements org.example.client.model.search.field.SearchField {
    VIDEO(2, 1),
    CHANNEL(2, 2),
    PLAYLIST(2, 3),
    MOVIE(2, 4);

    private final byte[] data;

    private TypeField(int... data) {
        this.data = SearchField.convert(data);
    }

    @Override
    public byte[] data() {
        return data;
    }
}
