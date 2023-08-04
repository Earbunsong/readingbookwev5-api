package istad.co.readingbookwev5api.api.book.web;

import istad.co.readingbookwev5api.api.file.web.FileDto;

import java.time.LocalDate;

public class BookDto {
    private Long id;
    private String title;
    private String description;
    private String author;
    private Short starRating;
    private LocalDate datePublished;
    private FileDto cover;
}
