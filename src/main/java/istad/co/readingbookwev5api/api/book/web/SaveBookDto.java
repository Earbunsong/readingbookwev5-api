package istad.co.readingbookwev5api.api.book.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import istad.co.readingbookwev5api.shared.constraint.fileid.ConstraintFieId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class SaveBookDto {

    @JsonIgnore
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String author;

    @ConstraintFieId
    private Long fileId;

    private String pdf;

    @NotNull
    private Boolean isPublished;

    //@ConstraintGenreId
    @NotEmpty(message = "You must specify the genre id.")
    private List<Integer> genreIds;


}
