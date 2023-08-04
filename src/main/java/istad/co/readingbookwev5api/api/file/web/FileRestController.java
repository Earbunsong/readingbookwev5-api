package istad.co.readingbookwev5api.api.file.web;

import istad.co.readingbookwev5api.api.file.FileServiceImpl;
import istad.co.readingbookwev5api.shared.rest.Rest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileRestController {
    private final FileServiceImpl fileService;

    /**
     * Upload a file
     * @parm file is an object from request part
     * @return ResponseEntity
     */

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> uploadOne(@RequestPart("file")MultipartFile file){

        Rest<FileDto> rest = new Rest<>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("File has been uploaded");
        rest.setData(fileService.uploadOne(file));

        return ResponseEntity.ok(rest);
    }

    /**
     * upload all files in the same time
     * @param files is the list of object from request part
     * @return ResponseEntity
     * @author Ear Bunsong
     */

    @PostMapping(path = "/upload-all", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> uploadAll(@RequestPart("files")List<MultipartFile> files){

        Rest<List<FileDto>> rest = new Rest<>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("File have been upload.");

        return ResponseEntity.ok(rest);
    }

    @GetMapping("/{uuid}")
    ResponseEntity<?> getFileByUUID(@PathVariable String uuid){

        FileDto fileDto = fileService.getFileByUUID(uuid);

        var rest = new Rest<FileDto>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("File has been found.");
        rest.setData(fileDto);

        return ResponseEntity.ok(rest);
    }

    @DeleteMapping("/{uuid}")
    ResponseEntity<?> deleteFileByUUID(@PathVariable String uuid){

        fileService.deleteFileByUUID(uuid);

        var rest = new Rest<String>();
        rest.setStatus(true);
        rest.setCode(HttpStatus.OK.value());
        rest.setMessage("File has been deleted.");
        rest.setData(uuid);

        return ResponseEntity.ok(rest);
    }

}
