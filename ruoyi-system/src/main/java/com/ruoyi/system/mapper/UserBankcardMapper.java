package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.UserBankcard;

/**
 * 用户银行卡 Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
public interface UserBankcardMapper 
{
    /**
     * 查询用户银行卡 
     * 
     * @param id 用户银行卡 主键
     * @return 用户银行卡 
     */
    public UserBankcard selectUserBankcardById(Long id);

    /**
     * 查询用户银行卡 列表
     * 
     * @param userBankcard 用户银行卡 
     * @return 用户银行卡 集合
     */
    public List<UserBankcard> selectUserBankcardList(UserBankcard userBankcard);

    /**
     * 新增用户银行卡 
     * 
     * @param userBankcard 用户银行卡 
     * @return 结果
     */
    public int insertUserBankcard(UserBankcard userBankcard);

    /**
     * 修改用户银行卡 
     * 
     * @param userBankcard 用户银行卡 
     * @return 结果
     */
    public int updateUserBankcard(UserBankcard userBankcard);

    /**
     * 删除用户银行卡 
     * 
     * @param id 用户银行卡 主键
     * @return 结果
     */
    public int deleteUserBankcardById(Long id);

    /**
     * 批量删除用户银行卡 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserBankcardByIds(Long[] ids);
}
