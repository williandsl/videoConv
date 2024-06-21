package org.example.client.model.search.query;

import org.example.client.model.search.SearchResultElement;
import org.example.client.model.search.query.QueryElementType;

public interface QueryElement extends SearchResultElement {

    String title();

    QueryElementType type();

}
