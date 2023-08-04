package istad.co.readingbookwev5api.data.repository;

import istad.co.readingbookwev5api.api.file.File;
import istad.co.readingbookwev5api.data.provider.FileProvider;
import org.apache.ibatis.annotations.*;


import java.util.List;
import java.util.Optional;

public interface FileRepository {

    @InsertProvider(type = FileProvider.class,method = "buildInsertFileSql")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void insert(@Param("file")File file);


    @SelectProvider(type = FileProvider.class,method = "buildSelectFileSql")
    @Results(id = "fileResultMap",value = {
            @Result(column = "is_enabled",property = "isEnabled")
    })
    List<File> select();

    @SelectProvider(type = FileProvider.class,method = "buildSelectFileByIDSql")
    @ResultMap("fileResultMap")
    Optional<File> selectByID(@Param("id")Long id);

    @SelectProvider(type = FileProvider.class,method = "buildSelectFileByUUIDSql")
    @ResultMap("fileResultMap")
    Optional<File> selectByUUID(@Param("uuid")String uuid);

    @DeleteProvider(type = FileProvider.class,method = "buildDeletedByUUIDSql")
    void deletedByUUID(@Param("uuid")String uuid);

    @Select("SELECT EXISTS(SELECT * FROM images WHERE id = #{id}")
    boolean existsFileId(@Param("id")Long id);
}
