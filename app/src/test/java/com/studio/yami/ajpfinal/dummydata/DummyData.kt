package com.studio.yami.ajpfinal.dummydata

import com.studio.yami.ajpfinal.data.local.Favorite
import com.studio.yami.ajpfinal.data.server.Film
import com.studio.yami.ajpfinal.ui.activity.MainActivity.Companion.MOVIE

private const val EN = "en"

fun dummyFavorite(): List<Favorite> {
    return mutableListOf(
        Favorite(
            429203,
            "/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg",
            "The Old Man & the Gun",
            "6.3",
            EN,
            MOVIE
        ),

        Favorite(
            429617,
            "/lcq8dVxeeOqHvvgcte707K0KVx5.jpg",
            "Spider-Man: Far from Home",
            "7.7",
            EN,
            MOVIE
        ),

        Favorite(
            522938,
            "/kTQ3J8oTTKofAVLYnds2cHUz9KO.jpg",
            "Rambo: Last Blood",
            "6.2",
            EN,
            MOVIE
        ),

        Favorite(
            301528,
            "/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg",
            "Toy Story 4",
            "7.6",
            EN,
            MOVIE
        ),

        Favorite(
            474350,
            "/zfE0R94v1E8cuKAerbskfD3VfUt.jpg",
            "It Chapter Two",
            "7.7",
            EN,
            MOVIE
        )
    )
}

fun dummyFilmResult(): Film.Result{
    return Film.Result(
        429617,
        "/lcq8dVxeeOqHvvgcte707K0KVx5.jpg",
        dummyGenre,
        "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
        "Released",
        7.7,
        129,
        null,
        EN,
        MOVIE,
        "Spider-Man: Far from Home",
        "2019-06-28"
    )
}

val dummyGenre = mutableListOf(
    Film.Genres("Action"),
    Film.Genres("Adventure"),
    Film.Genres("Science Fiction")
)