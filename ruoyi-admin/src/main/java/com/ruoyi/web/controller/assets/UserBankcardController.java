package com.ruoyi.web.controller.assets;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.system.domain.UserBankcard;
import com.ruoyi.system.service.IUserBankcardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户银行卡 Controller
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@RestController
@RequestMapping("/bankcard")
public class UserBankcardController extends BaseController
{
    @Autowired
    private IUserBankcardService userBankcardService;

    /**
     * 查询用户银行卡 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:bankcard:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<UserBankcard> list = userBankcardService.selectUserBankcardList();
        return AjaxResult.success(list);
    }

    /**
     * 导出用户银行卡 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:bankcard:export')")
//    @Log(title = "用户银行卡 ", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, UserBankcard userBankcard)
//    {
//        List<UserBankcard> list = userBankcardService.selectUserBankcardList(userBankcard);
//        ExcelUtil<UserBankcard> util = new ExcelUtil<UserBankcard>(UserBankcard.class);
//        util.exportExcel(response, list, "用户银行卡 数据");
//    }

    /**
     * 获取用户银行卡 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:bankcard:query')")
    @GetMapping(value = "/detail/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        if (id == null) {
            return AjaxResult.error("银行卡id不能为空~");
        }
        return AjaxResult.success(userBankcardService.selectUserBankcardById(id));
    }

    /**
     * 新增用户银行卡 
     */
//    @PreAuthorize("@ss.hasPermi('system:bankcard:add')")
    @Log(title = "用户银行卡 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserBankcard userBankcard)
    {
        AjaxResult ajaxResult = paramsVerify(userBankcard);
        if (ajaxResult != null) {
            return ajaxResult;
        }
        return toAjax(userBankcardService.insertUserBankcard(userBankcard),"新增成功~","新增失败");
    }

    /**
     * 修改用户银行卡 
     */
//    @PreAuthorize("@ss.hasPermi('system:bankcard:edit')")
    @Log(title = "用户银行卡 ", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody UserBankcard userBankcard)
    {
        AjaxResult ajaxResult = paramsVerify(userBankcard);
        if (ajaxResult != null) {
            return ajaxResult;
        }
        if (userBankcard.getId() == null) {
            return AjaxResult.error("银行卡id不能为空~");
        }
        return userBankcardService.updateUserBankcard(userBankcard);
    }

    private AjaxResult paramsVerify(UserBankcard userBankcard){
        if (StringUtils.isEmpty(userBankcard.getBankcardUsername())) {
            return AjaxResult.error("银行名称不能为空~");
        }
        if (userBankcard.getBankcardType() == null) {
            return AjaxResult.error("银行类型不能为空~");
        }
        if (StringUtils.isEmpty(userBankcard.getBankcardUsername())) {
            return AjaxResult.error("持卡人姓名不能为空~");
        }
        if (StringUtils.isEmpty(userBankcard.getBankcardCard())) {
            return AjaxResult.error("持卡人身份证不能为空~");
        }
        if (StringUtils.isEmpty(userBankcard.getBankcardOpenAddr())) {
            return AjaxResult.error("开户行地址不能为空~");
        }
        if (StringUtils.isEmpty(userBankcard.getBankcardNo())) {
            return AjaxResult.error("银行看卡号不能为空~");
        }
        return null;
    }

    /**
     * 删除用户银行卡 
     */
//    @PreAuthorize("@ss.hasPermi('system:bankcard:remove')")
    @Log(title = "用户银行卡 ", businessType = BusinessType.DELETE)
	@GetMapping("/del/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return userBankcardService.deleteUserBankcardById(id);
    }
}
