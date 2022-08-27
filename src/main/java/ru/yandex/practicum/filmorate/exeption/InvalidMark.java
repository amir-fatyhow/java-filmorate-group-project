package ru.yandex.practicum.filmorate.exeption;

public class InvalidMark extends RuntimeException {

    public InvalidMark(String message) {
        super(message);
    }

}
