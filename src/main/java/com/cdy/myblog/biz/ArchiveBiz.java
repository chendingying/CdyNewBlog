package com.cdy.myblog.biz;

import com.cdy.myblog.mapper.ArchiveMapper;
import com.cdy.myblog.model.Archive;
import com.cdy.myblog.util.BaseBiz;
import org.springframework.stereotype.Service;

/**
 * @Author: cdy
 * @Date: 2019/3/9 16:38
 * @Version 1.0
 */
@Service
public class ArchiveBiz extends BaseBiz<ArchiveMapper,Archive> {

    /**
     * 添加归档日期
     * @param archiveName
     */
    public void addArchiveName(String archiveName){
        Archive archive = new Archive();
        archive.setArchiveName(archiveName);
        int archiveNameIsExit = mapper.findArchiveNameByArchiveName(archiveName);
        if(archiveNameIsExit == 0){
            mapper.insert(archive);
        }
    }
}
