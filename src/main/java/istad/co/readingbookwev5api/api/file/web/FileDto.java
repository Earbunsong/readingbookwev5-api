package istad.co.readingbookwev5api.api.file.web;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FileDto {

    private Long id;
    private String uuid;
    private String name;
    private String uri;
    private String extension;
    private Long size;

//    private Boolean isEnabled;

    public void buildNameAndUri(String baseUri){
        name = String.format("%s.%s", uuid, extension);
        uri = baseUri + name;
    }
}
