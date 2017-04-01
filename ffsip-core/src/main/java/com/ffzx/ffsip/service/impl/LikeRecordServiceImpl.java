package com.ffzx.ffsip.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.ffsip.mapper.LikeRecordMapper;
import com.ffzx.ffsip.model.LikeRecord;
import com.ffzx.ffsip.service.LikeRecordService;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class LikeRecordServiceImpl extends BaseServiceImpl<LikeRecord,String> implements LikeRecordService {

    @Resource
    private LikeRecordMapper mapper;

    @Override
    public LikeRecordMapper getMapper() {
        return mapper;
    }
}
