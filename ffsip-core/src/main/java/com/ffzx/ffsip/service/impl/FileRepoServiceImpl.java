package com.ffzx.ffsip.service.impl;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.ffsip.mapper.FileRepoMapper;
import com.ffzx.ffsip.model.FileRepo;
import com.ffzx.ffsip.service.FileRepoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class FileRepoServiceImpl extends BaseServiceImpl<FileRepo, String> implements FileRepoService {

    @Resource
    private FileRepoMapper mapper;

    @Override
    public FileRepoMapper getMapper() {
        return mapper;
    }
}
