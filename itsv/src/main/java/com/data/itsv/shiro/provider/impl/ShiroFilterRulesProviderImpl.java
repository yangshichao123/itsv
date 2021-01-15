package com.data.itsv.shiro.provider.impl;

import com.data.itsv.mapper.AuthResourceMapper;
import com.data.itsv.shiro.provider.ShiroFilterRulesProvider;
import com.data.itsv.shiro.rule.RolePermRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tomsun28
 * @date 16:46 2018/3/7
 */
@Service("ShiroFilterRulesProvider")
public class ShiroFilterRulesProviderImpl implements ShiroFilterRulesProvider {

    @Autowired
    private AuthResourceMapper authResourceMapper;

    @Override
    public List<RolePermRule> loadRolePermRules() {

        return authResourceMapper.selectRoleRules();
    }

}
