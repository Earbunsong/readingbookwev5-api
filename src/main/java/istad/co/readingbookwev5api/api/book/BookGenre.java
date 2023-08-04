package istad.co.readingbookwev5api.api.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Builder;

@AllArgsConstructor

@Getter
@Setter
public class BookGenre {
    private Long id;
    private Long bookId;
    private Integer genreId;

}
