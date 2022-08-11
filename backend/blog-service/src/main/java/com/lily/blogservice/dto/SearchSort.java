package com.lily.blogservice.dto;


public enum SearchSort {
  ACCURACY,
  RECENCY;

  public String getRequestParam() {
    //todo must be moved to BlogSearchClient implementation. this is only for kakao api
    return this.name().toLowerCase();
  }
}
