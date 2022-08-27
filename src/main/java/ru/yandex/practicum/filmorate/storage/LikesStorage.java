package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.exeption.InvalidMark;

@Repository
public interface LikesStorage {

    void addLike(long userId, long filmId, int mark) throws InvalidMark;

    void deleteLikeFromFilm(long filmId, long userId);

    Double getLikesCount(long id);

}
