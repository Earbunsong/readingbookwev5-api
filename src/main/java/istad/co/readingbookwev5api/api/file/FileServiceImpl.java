package istad.co.readingbookwev5api.api.file;


import istad.co.readingbookwev5api.api.file.web.FileDto;
import istad.co.readingbookwev5api.data.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    private final FileMapper fileMapper;

    @Value("${file.server-path}")
    private String serverPath;

    @Value("${file.uri}")
    private String uri;
    @Override
    public FileDto uploadOne(MultipartFile file) {
        return save(file);
    }

    @Override
    public List<FileDto> uploadAll(List<MultipartFile> files) {

        List<FileDto> fileModels = new ArrayList<>();

        for(MultipartFile file : files){
            FileDto fileDto = save(file);
            fileModels.add(fileDto);
        }
        return fileModels;
    }

    @Override
    public List<FileDto> getAllFiles() {

        List<File> files = fileRepository.select();

        List<FileDto> fileDtoList = fileMapper.toFileDtoList(files);

        fileDtoList.forEach(fileDto -> {
            String name = fileDto.getUuid() + "." + fileDto.getExtension();
            fileDto.setName(name);
            fileDto.setUri(uri + name);
        });

        return fileDtoList;
    }


    @Override
    public FileDto getFileByID(Long id) {

        Optional<File> optionalFile = fileRepository.selectByID(id);

        File file = optionalFile.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "File with ID = " + id + "is not found in DB"));

        FileDto fileDto = fileMapper.toFileDto(file);
        String  name = fileDto.getUuid() + "." + fileDto.getExtension();
        fileDto.setName(name);
        fileDto.setUri(uri + name);


        return fileDto;
    }

    @Override
    public FileDto getFileByUUID(String uuid) {

        Optional<File> optionalFile = fileRepository.selectByUUID(uuid);

        File file = optionalFile.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"File with UUID = " + uuid + "is not found in DB"));

        FileDto fileDto = fileMapper.toFileDto(file);
        String name = fileDto.getUuid() + "." + fileDto.getExtension();
        fileDto.setName(name);
        fileDto.setUri(uri + name);

        return fileDto;
    }

    @Override
    public void deleteFileByUUID(String uuid) {
        File files = fileRepository.selectByUUID(uuid).orElseThrow(()->{
             String reason = "file resource cannot be deleted";
             Throwable cause = new Throwable("File with UUID = " + uuid + "is not found in DB");
             throw new ResponseStatusException(HttpStatus.NOT_FOUND,reason,cause);
        });

        fileRepository.deletedByUUID(uuid);

        try {
            Path path = Paths.get(serverPath + files.getUuid() + "."+files.getExtension());
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existFileID(Long id) {
        return fileRepository.existsFileId(id);
    }

    /**
     * Process of saving file by inserting into database and  create a DTO response
     *
     * @param file is the request part from client
     * @return FileDto
     */

    private FileDto save(MultipartFile file){

        String randomUUID = UUID.randomUUID().toString();
        String extension = "";
        String fileName = "";

        if(!file.isEmpty()){

            extension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".")+1);

            fileName = randomUUID + "." + extension;

            //Create path object for object
            Path path = Paths.get(serverPath + fileName);

            try {
                Files.copy(file.getInputStream(),path);
            } catch (IOException e) {
                log.error("UploadOne(MultipartFile file) = {}",e);
            }
        }
        else {
            String reason = "File is not found in body";
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason);
        }

        File fileModel = new File();
        fileModel.setUuid(randomUUID);
        fileModel.setExtension(extension);
        fileModel.setSize(file.getSize());
        fileModel.setIsEnabled(true);

        fileRepository.insert(fileModel);

        FileDto fileDto = fileMapper.toFileDto(fileModel);
        fileDto.setName(fileName);
        fileDto.setUri(uri + fileName);

        return fileDto;
    }

}
