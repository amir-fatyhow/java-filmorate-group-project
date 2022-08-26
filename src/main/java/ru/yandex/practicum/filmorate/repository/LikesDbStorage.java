package ru.yandex.practicum.filmorate.repository;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exeption.FilmNotFound;
import ru.yandex.practicum.filmorate.exeption.UserNotFound;
import ru.yandex.practicum.filmorate.service.EventService;
import ru.yandex.practicum.filmorate.storage.LikesStorage;

@Repository
@Data
@RequiredArgsConstructor
public class LikesDbStorage implements LikesStorage {

    public final JdbcTemplate jdbcTemplate;
    private final EventService eventService;

    @Override
    public void addLike(long userId, long filmId, int mark) throws UserNotFound, FilmNotFound {
        try {
            String sql = "MERGE INTO LIKES (USER_ID, FILM_ID, MARK) KEY (USER_ID, FILM_ID) VALUES ( ?, ?, ? )";
            jdbcTemplate.update(sql, userId, filmId, mark);

            String sqlFilm = "UPDATE FILMS SET RATE = ? WHERE ID = ?";
            jdbcTemplate.update(sqlFilm, getLikesCount(filmId), filmId);
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void deleteLikeFromFilm(long filmId, long userId) throws FilmNotFound, UserNotFound {
        try {
            String sql = "DELETE FROM LIKES WHERE FILM_ID = ? AND USER_ID = ?";
            jdbcTemplate.update(sql, filmId, userId);
        } catch (EmptyResultDataAccessException e) {
            throw new FilmNotFound("Неверно указан id = " + filmId + " фильма.");
        }
    }

    @Override
    public Double getLikesCount(long id) throws UserNotFound {
        String sql = "SELECT AVG(MARK) FROM LIKES WHERE FILM_ID = ?";
        Double likes = jdbcTemplate.queryForObject(sql, Double.class, id);
        return likes;
    }


}
