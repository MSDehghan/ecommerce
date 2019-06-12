package com.bestretail.ecommerce.app.product.dto;

import com.bestretail.ecommerce.app.product.Product;

import java.util.Date;

public class SearchResult {
    private Integer relevance;
    private Date date;
    private Integer popularity;
    private Product value;

    public Integer getRelevance() {
        return relevance;
    }

    public Date getDate() {
        return date;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public Product getValue() {
        return value;
    }

    public SearchResult(Product value, Date date, Integer relevance, Integer popularity) {
        this.relevance = relevance;
        this.date = date;
        this.popularity = popularity;
        this.value = value;
    }
}
