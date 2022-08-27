package ru.yandex.practicum.filmorate.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.enums.EventType;
import ru.yandex.practicum.filmorate.enums.Operation;
import ru.yandex.practicum.filmorate.exeption.FilmNotFound;
import ru.yandex.practicum.filmorate.exeption.InvalidMark;
import ru.yandex.practicum.filmorate.exeption.UserNotFound;
import ru.yandex.practicum.filmorate.storage.LikesStorage;

@Slf4j
@Service
@AllArgsConstructor
public class LikesService {

    private final LikesStorage likesStorage;
    private final UserService userService;

    private final EventService eventService;

    public void addLikeToFilm(long userId, long filmId, int mark) throws UserNotFound, FilmNotFound, InvalidMark {
        if (mark > 10 || mark < 1) {
            throw new InvalidMark("Неверное значение оценки.");
        }
        likesStorage.addLike(userId, filmId, mark);
        log.info("Like has been add");
    }

    public void deleteLikeFromFilm(long filmId, long userId) throws UserNotFound, FilmNotFound {
        userService.getUserById(userId);
        likesStorage.deleteLikeFromFilm(filmId, userId);
        eventService.addEvent(userId, EventType.LIKE, Operation.REMOVE, filmId);
        log.info("Like deleted");
    }

}
