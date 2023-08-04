package istad.co.readingbookwev5api.api.book;

import istad.co.readingbookwev5api.api.genre.Genre;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Book {
    private Long id;
    private String title;
    private String description;
    private String author;
    private Short starRating;
    private LocalDate datePublished;
    private String pdf;
    private Boolean isEnabled;
    private List<Genre> genres;
}
