package com.data.itsv.service;

import com.data.itsv.model.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ysc
 * @since 2021-02-05
 */
public interface DictionaryService extends IService<Dictionary> {

    /**
     * 添加字典
     * @param dictionary
     * @return
     */
    boolean addDictionary(Dictionary dictionary);

    /**
     * 修改字典 按主键修改
     * @param dictionary
     * @return
     */
    boolean updateDictionary(Dictionary dictionary);

    /**
     * 删除字典  根据主键
     * @param dictionary
     * @return
     */

    boolean deleteDictionary(Dictionary dictionary);

    /**
     * 查询字典
     * @param dictionary
     * @return
     */
    List<Dictionary> getDictionary(Dictionary dictionary);
}
