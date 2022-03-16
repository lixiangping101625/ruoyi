package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.domain.dto.OrderPZDTO;

/**
 * 用户订单 Service接口
 * 
 * @author ruoyi
 * @date 2022-03-16
 */
public interface IOrdersService 
{

    /**
     * 陪诊服务下单
     * @param orderPZDTO
     * @return
     */
    public AjaxResult placeOrderPZ(OrderPZDTO orderPZDTO);

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
     * 批量删除用户订单 
     * 
     * @param ids 需要删除的用户订单 主键集合
     * @return 结果
     */
    public int deleteOrdersByIds(Long[] ids);

    /**
     * 删除用户订单 信息
     * 
     * @param id 用户订单 主键
     * @return 结果
     */
    public int deleteOrdersById(Long id);
}
