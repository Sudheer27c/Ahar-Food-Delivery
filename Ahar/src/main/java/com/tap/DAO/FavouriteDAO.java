package com.tap.DAO;

import java.util.List;

import com.tap.model.Favourite;

public interface FavouriteDAO {

    void addFavourite(Favourite favourite);

    void removeFavourite(
            int userId,
            int restaurantId);

    List<Favourite> getFavouritesByUser(
            int userId);
}