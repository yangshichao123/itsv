package com.data.itsv.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.data.itsv.model.Dictionary;
import com.data.itsv.mapper.DictionaryMapper;
import com.data.itsv.service.DictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ysc
 * @since 2021-02-05
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Override
    public boolean addDictionary(Dictionary dictionary) {
        int insert = dictionaryMapper.insert(dictionary);
        return insert>0?true:false;
    }

    @Override
    public boolean updateDictionary(Dictionary dictionary) {
        int i = dictionaryMapper.updateById(dictionary);
        return i>0?true:false;
    }

    @Override
    public boolean deleteDictionary(Dictionary dictionary) {
        int i = dictionaryMapper.deleteById(dictionary);
        return i>0?true:false;
    }

    @Override
    public List<Dictionary> getDictionary(Dictionary dictionary) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",dictionary.getType());//type 必传
        if(StrUtil.isNotEmpty(dictionary.getName())){
            queryWrapper.like("name",dictionary.getName());
        }
        if(StrUtil.isNotEmpty(dictionary.getAlarmTypeCode())){
            queryWrapper.eq("alarmTypeCode",dictionary.getAlarmTypeCode());
        }
        queryWrapper.orderByAsc("sort");
        List<Dictionary> dictionaries = dictionaryMapper.selectList(queryWrapper);
        return dictionaries;
    }
}
