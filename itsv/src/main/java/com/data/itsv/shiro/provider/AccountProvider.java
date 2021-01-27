package com.data.itsv.shiro.provider;


import com.data.itsv.model.vo.Account;

/**
 *    数据库用户密码账户提供
 * @author tomsun28
 * @date 16:35 2018/2/11
 */
public interface AccountProvider {

    /**
     * description 数据库用户密码账户提供
     *
     * @param userName 1
     * @return com.usthe.bootshiro.domain.vo.Account
     */
    Account loadAccount(String userName);

}
