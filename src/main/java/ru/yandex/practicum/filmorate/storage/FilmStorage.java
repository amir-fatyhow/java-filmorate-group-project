package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exeption.FilmNotFound;
import ru.yandex.practicum.filmorate.model.Director;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;

@Repository
public interface FilmStorage {

    Film addFilm(Film Film);

    List<Film> getAllFilms();

    Film getFilmById(long id) throws FilmNotFound;

    Film updateFilm(Film film) throws FilmNotFound;

    List<Film> getPopularFilms(int count);

    List<Film> getPopularByGenre(int genreId);

    List<Film> getPopularFilmsByYear(String year);

    List<Film> getPopularFilmsByGenreAndYear(int count, int genreId, String year);

    List<Film> getCommonFilms(int userId, int friendId);

    List<Film> getSearchFilmsByTittleAndDirector(String query);

    List<Film> getSearchFilmsByTittle(String query);

    List<Film> getSearchFilmsByDirector(String query);

    void deleteFilm(long filmId) throws FilmNotFound;
    void setFilmGenres(long filmId, List<Genre> genres);

    List<Genre> getFilmGenres(long filmId);

    void setFilmDirectors(long filmId, List<Director> directors);

    List<Film> getAllFilmsByDirector(int directorId, String sortBy);

    List<Film> getRecommendations(long userId);

}
