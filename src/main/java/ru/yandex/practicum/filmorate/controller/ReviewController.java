package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exeption.FilmNotFound;
import ru.yandex.practicum.filmorate.exeption.ReviewNotFound;
import ru.yandex.practicum.filmorate.exeption.UserNotFound;
import ru.yandex.practicum.filmorate.model.Review;
import ru.yandex.practicum.filmorate.service.ReviewService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * Добавляем новый Review
     */
    @PostMapping
    public Review addReview(@RequestBody @Valid Review review) {
        return reviewService.addReview(review);
    }

    /**
     * Изменяем существующий Review
     */
    @PutMapping
    public Review updateReview(@RequestBody Review review) throws ReviewNotFound {
        return reviewService.updateReview(review);
    }

    /**
     * Удаляем Review
     */
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable long id) throws ReviewNotFound {
        reviewService.deleteReview(id);
    }

    /**
     * Получаем Review по id
     */
    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable @Valid long id) throws ReviewNotFound {
        return reviewService.getReviewById(id);
    }

    /**
     * Получаем все Review для указанного Film
     */
    @GetMapping
    public List<Review> getAllReviewByFilmId(@RequestParam(defaultValue = "0") long filmId,
                                             @RequestParam(defaultValue = "10") int count) throws FilmNotFound {
        return reviewService.getAllReviewByFilmId(filmId, count);
    }

    /**
     * Добавляем лайк и увеличиваем useful у Review
     */
    @PutMapping("/{id}/like/{userId}")
    public Review addLikeReview(@PathVariable long id,
                                @PathVariable long  userId) throws ReviewNotFound, UserNotFound {
        return reviewService.addLikeReview(id, userId);
    }

    /**
     * Добавляем дизлайк и уменьшаем useful у Review
     */
    @PutMapping("/{id}/dislike/{userId}")
    public Review addDislikeReview(@PathVariable long id,
                                   @PathVariable long userId) throws ReviewNotFound, UserNotFound {
        return reviewService.addDislikeReview(id, userId);
    }

    /**
     * Удаляем лайк и уменьшаем useful у Review
     */
    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLikeReview(@PathVariable long id,
                                 @PathVariable long userId) throws ReviewNotFound, UserNotFound {
        reviewService.deleteLikeReview(id, userId);
    }

    /**
     * Удаляем дизлайк и увеличиваем useful у Review
     */
    @DeleteMapping("/{id}/dislike/{userId}")
    public void deleteDislikeReview(@PathVariable long id,
                                    @PathVariable long userId) throws ReviewNotFound, UserNotFound {
        reviewService.deleteDislikeReview(id, userId);
    }

}
