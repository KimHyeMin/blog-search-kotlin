package com.lily.backend.search.client;

import com.lily.backend.search.dto.BlogSearchRequest;

public interface BlogSearchClient {

    String search(BlogSearchRequest request);
}
