package com.benjamingbaxter.beer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

//{
//"name": "Rickards Cardigan",
//"beer_id": 120,
//"image_url": "http://www.thebeerstore.ca/sites/default/files/styles/brand_hero/public/brand/hero/Rickards%20Cardigan%20Hero.jpg?itok=IRGzYwK0",
//"category": "Craft",
//"abv": "5.5",
//"type": "Lager",
//"brewer": "Molson",
//"country": "Canada",
//"on_sale": false
//},
public class Beer {

    @JsonProperty("beer_id")
    private int id;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    private String category;
    private String abv;
    private String type;
    private String brewer;
    private String country;
    @JsonProperty("on_sale")
    private boolean onSale;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrewer() {
        return brewer;
    }

    public void setBrewer(String brewer) {
        this.brewer = brewer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }
}
