package istad.co.readingbookwev5api.api.file;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class File {

    public File(Long id){
        this.id= id;
    }

    private Long id;
    private String uuid;
    private String name;
    private String uri;
    private String extension;
    private Long Size;
    private Boolean isEnabled;
}
