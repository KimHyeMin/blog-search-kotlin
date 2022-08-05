package com.lily.backend.search.dto;


public enum ResultSort {
  ACCURACY,
  RECENCY;

  public String getRequestParam() {
    //todo must be moved to BlogSearchClient implementation. this is only for kakao api
    return this.name().toLowerCase();
  }
}
