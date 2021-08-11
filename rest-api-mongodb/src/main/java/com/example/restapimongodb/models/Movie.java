package com.example.restapimongodb.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("movies")
public class Movie
{
    //POJO// bean// entity
    @Id
    private String id;
    private String title;
    private String description;
    private String rating;
    private Double rentalPrice;
    private Double purchasePrice;
    private String genre;
    private String feature;
    private String bigPoster;
    private String smallPoster;

    public Movie() {
    }

    public Movie(String id, String title, String description, String rating, Double rentalPrice, Double purchasePrice, String genre, String feature, String bigPoster, String smallPoster) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.rentalPrice = rentalPrice;
        this.purchasePrice = purchasePrice;
        this.genre = genre;
        this.feature = feature;
        this.bigPoster = bigPoster;
        this.smallPoster = smallPoster;
    }

    public void setRentalPrice(Double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public void setBigPoster(String bigPoster) {
        this.bigPoster = bigPoster;
    }

    public void setSmallPoster(String smallPoster) {
        this.smallPoster = smallPoster;
    }

    public Double getRentalPrice() {
        return rentalPrice;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public String getGenre() {
        return genre;
    }

    public String getFeature() {
        return feature;
    }

    public String getBigPoster() {
        return bigPoster;
    }

    public String getSmallPoster() {
        return smallPoster;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }



}