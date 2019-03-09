package com.cdy.myblog.mapper;

import com.cdy.myblog.model.Archive;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: cdy
 * @Date: 2019/3/9 16:38
 * @Version 1.0
 */
public interface ArchiveMapper extends Mapper<Archive> {

    int findArchiveNameByArchiveName(@Param("archiveName") String archiveName);
}
