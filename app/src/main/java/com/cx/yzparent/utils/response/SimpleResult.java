package com.cx.yzparent.utils.response;

import java.util.List;

public class SimpleResult<T> extends ResponseData {

    private List<T> results;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
