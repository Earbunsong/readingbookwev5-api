package istad.co.readingbookwev5api.api.file;

import istad.co.readingbookwev5api.api.file.web.FileDto;
import istad.co.readingbookwev5api.api.file.web.FilePublicDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper {

    File fromFileDto(FileDto fileDto);

    FileDto toFileDto(File file);

    FilePublicDto toFilePublicDto(File file);

    List<File> fromFileDtoList(List<FileDto>fileDtoList);

    List<FileDto> toFileDtoList(List<File> files);

    List<FilePublicDto> toFilePublicDtoList(List<File> files);



}
