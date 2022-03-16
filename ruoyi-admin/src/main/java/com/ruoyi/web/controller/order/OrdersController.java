package com.ruoyi.web.controller.order;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.DozerBeanUtils;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.domain.dto.OrderPZDTO;
import com.ruoyi.system.domain.vo.OrderVO;
import com.ruoyi.system.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户订单 Controller
 * 
 * @author ruoyi
 * @date 2022-03-16
 */
@RestController
@RequestMapping("/orders")
public class OrdersController extends BaseController
{
    @Autowired
    private IOrdersService ordersService;

    /**
     * 新增用户订单
     */
    @Log(title = "用户下单 ", businessType = BusinessType.INSERT)
    @PostMapping(value = "/placePZ")
    public AjaxResult add(@RequestBody OrderPZDTO orderPZDTO)
    {
        return ordersService.placeOrderPZ(orderPZDTO);
    }

    /**
     * 查询用户订单 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:orders:list')")
    @PostMapping("/list")
    public AjaxResult list(@RequestBody Orders orders)
    {
//        startPage();
        List<Orders> list = ordersService.selectOrdersList(orders);
        ArrayList<OrderVO> orderVOS = new ArrayList<>();
        if (list.size() > 0) {
            list.stream().forEach(orders1 -> orders1.setSnapData(null));
//            list.stream().forEach(orders1 -> {
//                OrderVO orderVO = DozerBeanUtils.deepCopy(orders1, OrderVO.class);
//                String snapData = orderVO.getSnapData();
//                OrderPZDTO orderPZDTO = JSON.parseObject(snapData, OrderPZDTO.class);
//                orderVO.setSnapData(null);
//
//            });
        }
        return AjaxResult.success(list);
    }

    /**
     * 导出用户订单 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:orders:export')")
//    @Log(title = "用户订单 ", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, Orders orders)
//    {
//        List<Orders> list = ordersService.selectOrdersList(orders);
//        ExcelUtil<Orders> util = new ExcelUtil<Orders>(Orders.class);
//        util.exportExcel(response, list, "用户订单 数据");
//    }

    /**
     * 获取用户订单 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:orders:query')")
    @GetMapping(value = "/detail/pz/{id}/{userId}")
    public AjaxResult getInfo(@PathVariable("id") Long id, @PathVariable("userId") Long userId)
    {
        if (!userId.equals(SecurityUtils.getUserId())) {
            return AjaxResult.error("订单不属于当前用户");
        }
        Orders orders = ordersService.selectOrdersById(id);
        OrderVO orderVO = DozerBeanUtils.deepCopy(orders, OrderVO.class);
        if (orders != null) {
            String snapData = orders.getSnapData();
            OrderPZDTO orderPZDTO = JSON.parseObject(snapData, OrderPZDTO.class);
            orderVO.setOrderPZDTO(orderPZDTO);
        }
        return AjaxResult.success(orderVO);
    }

    /**
     * 修改用户订单 
     */
//    @PreAuthorize("@ss.hasPermi('system:orders:edit')")
    @Log(title = "用户取消订单 ", businessType = BusinessType.UPDATE)
    @PostMapping("/cancel")
    public AjaxResult edit(@RequestBody Orders orders)
    {
        if (StringUtils.isEmpty(orders.getOrderNo())) {
            return AjaxResult.error("订单号不能为空~");
        }
        if (orders.getUserId() == null) {
            return AjaxResult.error("用户id不能为空~");
        }
        if (!orders.getUserId().equals(SecurityUtils.getUserId())) {
            return AjaxResult.error("只能取消自己的订单~");
        }
        return ordersService.cancelOrder(orders);
    }

    /**
     * 删除用户订单 
     */
//    @PreAuthorize("@ss.hasPermi('system:orders:remove')")
//    @Log(title = "用户订单 ", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(ordersService.deleteOrdersByIds(ids));
//    }
}
