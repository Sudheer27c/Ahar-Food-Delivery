package com.tap.model;

public class Favourite {

    private int favouriteId;
    private int userId;
    private int restaurantId;

    public Favourite() {}

    public Favourite(int userId,
                     int restaurantId) {

        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public Favourite(int favouriteId,
                     int userId,
                     int restaurantId) {

        this.favouriteId = favouriteId;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public int getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(int favouriteId) {
        this.favouriteId = favouriteId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}