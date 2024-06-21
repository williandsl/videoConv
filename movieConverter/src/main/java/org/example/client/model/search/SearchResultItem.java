package org.example.client.model.search;

import org.example.client.model.search.SearchResultElement;
import org.example.client.model.search.SearchResultPlaylistDetails;
import org.example.client.model.search.SearchResultVideoDetails;

public interface SearchResultItem extends SearchResultElement {

    SearchResultItemType type();

    default SearchResultVideoDetails asVideo() {
        throw new UnsupportedOperationException();
    }

    default SearchResultChannelDetails asChannel() {
        throw new UnsupportedOperationException();
    }

    default SearchResultPlaylistDetails asPlaylist() {
        throw new UnsupportedOperationException();
    }

    default SearchResultShelf asShelf() {
        throw new UnsupportedOperationException();
    }
}
