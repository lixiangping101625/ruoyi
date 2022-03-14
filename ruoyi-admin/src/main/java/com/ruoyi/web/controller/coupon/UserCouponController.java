package com.ruoyi.web.controller.coupon;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.vo.UserCouponVO;
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
import com.ruoyi.system.domain.UserCoupon;
import com.ruoyi.system.service.IUserCouponService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户优惠券 Controller
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@RestController
@RequestMapping("/user/coupon")
public class UserCouponController extends BaseController
{
    @Autowired
    private IUserCouponService userCouponService;

    /**
     * 查询用户优惠券 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:coupon:list')")
    @GetMapping("/list/{type}")
    public AjaxResult list(@PathVariable Integer type)
    {
        List<UserCouponVO> list = userCouponService.selectUserCouponList(type);
        return AjaxResult.success(list);
    }

    /**
     * 导出用户优惠券 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:coupon:export')")
//    @Log(title = "用户优惠券 ", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, UserCoupon userCoupon)
//    {
//        List<UserCoupon> list = userCouponService.selectUserCouponList(userCoupon);
//        ExcelUtil<UserCoupon> util = new ExcelUtil<UserCoupon>(UserCoupon.class);
//        util.exportExcel(response, list, "用户优惠券 数据");
//    }

    /**
     * 获取用户优惠券 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:coupon:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userCouponService.selectUserCouponById(id));
    }

    /**
     * 新增用户优惠券 
     */
//    @PreAuthorize("@ss.hasPermi('system:coupon:add')")
    @Log(title = "用户优惠券 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserCoupon userCoupon)
    {
        if (userCoupon.getCouponId() == null) {
            return AjaxResult.error("优惠券id不能为空~");
        }
        return userCouponService.insertUserCoupon(userCoupon);
    }

    /**
     * 修改用户优惠券 
     */
//    @PreAuthorize("@ss.hasPermi('system:coupon:edit')")
    @Log(title = "用户优惠券 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserCoupon userCoupon)
    {
        return toAjax(userCouponService.updateUserCoupon(userCoupon));
    }

    /**
     * 删除用户优惠券 
     */
//    @PreAuthorize("@ss.hasPermi('system:coupon:remove')")
    @Log(title = "用户优惠券 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userCouponService.deleteUserCouponByIds(ids));
    }
}
