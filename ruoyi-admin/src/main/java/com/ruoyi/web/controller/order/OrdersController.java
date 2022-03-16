package com.ruoyi.web.controller.order;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.HttpMethod;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.domain.dto.OrderPZDTO;
import com.ruoyi.system.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/list")
    public AjaxResult list(Orders orders)
    {
        startPage();
        List<Orders> list = ordersService.selectOrdersList(orders);
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
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(ordersService.selectOrdersById(id));
    }

    /**
     * 修改用户订单 
     */
//    @PreAuthorize("@ss.hasPermi('system:orders:edit')")
//    @Log(title = "用户订单 ", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody Orders orders)
//    {
//        return toAjax(ordersService.updateOrders(orders));
//    }

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
