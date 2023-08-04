package istad.co.readingbookwev5api.api.file.web;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class FilePublicDto {
    private Long id;
    private String uuid;
    private String name;
    private  String uri;

}
