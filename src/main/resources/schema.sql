CREATE TABLE IF NOT EXISTS USERS
(
    ID       BIGINT PRIMARY KEY AUTO_INCREMENT,
    EMAIL    VARCHAR(255) NOT NULL,
    LOGIN    VARCHAR(255) NOT NULL,
    NAME     VARCHAR(255) NOT NULL,
    BIRTHDAY DATE
);

CREATE TABLE IF NOT EXISTS FRIENDS
(
    USER_ID   BIGINT,
    FRIEND_ID BIGINT,
    STATUS    BOOLEAN,
    FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
    );

CREATE TABLE IF NOT EXISTS GENRE
(
    ID   INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS MPA
(
    ID   INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS DIRECTORS
(
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS FILMS_DIRECTORS
(
    FILM_ID BIGINT,
    DIRECTOR_ID INT
);

CREATE TABLE IF NOT EXISTS FILMS
(
    ID           BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME         VARCHAR(255),
    MPA_ID       INT,
    DESCRIPTION  VARCHAR(255),
    RELEASE_DATE DATE,
    DURATION     INTEGER,
    FOREIGN KEY (MPA_ID) REFERENCES MPA (ID)
    );

CREATE TABLE IF NOT EXISTS LIKES
(
    FILM_ID BIGINT,
    USER_ID BIGINT,
    UNIQUE (FILM_ID, USER_ID),
    FOREIGN KEY (FILM_ID) REFERENCES FILMS (ID)
    );

CREATE TABLE IF NOT EXISTS FILMS_GENRES
(
    FILM_ID  BIGINT,
    GENRE_ID BIGINT
);




