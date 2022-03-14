package com.ruoyi.web.controller.coupon;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Coupon;
import com.ruoyi.system.service.ICouponService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 优惠券 Controller
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@RestController
@RequestMapping("/coupon")
public class CouponController extends BaseController
{
    @Autowired
    private ICouponService couponService;

    /**
     * 查询优惠券 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:coupon:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        startPage();
        List<Coupon> list = couponService.selectAvailableList();
        return AjaxResult.success(list);
    }

    /**
     * 导出优惠券 列表
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:export')")
    @Log(title = "优惠券 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Coupon coupon)
    {
        List<Coupon> list = couponService.selectCouponList(coupon);
        ExcelUtil<Coupon> util = new ExcelUtil<Coupon>(Coupon.class);
        util.exportExcel(response, list, "优惠券 数据");
    }

    /**
     * 获取优惠券 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(couponService.selectCouponById(id));
    }

    /**
     * 新增优惠券 
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:add')")
    @Log(title = "优惠券 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Coupon coupon)
    {
        return toAjax(couponService.insertCoupon(coupon));
    }

    /**
     * 修改优惠券 
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:edit')")
    @Log(title = "优惠券 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Coupon coupon)
    {
        return toAjax(couponService.updateCoupon(coupon));
    }

    /**
     * 删除优惠券 
     */
    @PreAuthorize("@ss.hasPermi('system:coupon:remove')")
    @Log(title = "优惠券 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(couponService.deleteCouponByIds(ids));
    }
}
