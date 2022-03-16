package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Orders;

/**
 * 用户订单 Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-16
 */
public interface OrdersMapper 
{
    /**
     * 查询用户订单 
     * 
     * @param id 用户订单 主键
     * @return 用户订单 
     */
    public Orders selectOrdersById(Long id);

    /**
     * 查询用户订单 列表
     * 
     * @param orders 用户订单 
     * @return 用户订单 集合
     */
    public List<Orders> selectOrdersList(Orders orders);

    /**
     * 新增用户订单 
     * 
     * @param orders 用户订单 
     * @return 结果
     */
    public int insertOrders(Orders orders);

    /**
     * 修改用户订单 
     * 
     * @param orders 用户订单 
     * @return 结果
     */
    public int updateOrders(Orders orders);

    /**
     * 删除用户订单 
     * 
     * @param id 用户订单 主键
     * @return 结果
     */
    public int deleteOrdersById(Long id);

    /**
     * 批量删除用户订单 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrdersByIds(Long[] ids);
}
