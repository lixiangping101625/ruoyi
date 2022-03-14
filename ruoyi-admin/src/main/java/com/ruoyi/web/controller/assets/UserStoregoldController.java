package com.ruoyi.web.controller.assets;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.ExchangeConstants;
import com.ruoyi.common.utils.bean.DozerBeanUtils;
import com.ruoyi.system.domain.vo.UserStoregoldVO;
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
import com.ruoyi.system.domain.UserStoregold;
import com.ruoyi.system.service.IUserStoregoldService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户储值金 Controller
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@RestController
@RequestMapping("/storegold")
public class UserStoregoldController extends BaseController
{
    @Autowired
    private IUserStoregoldService userStoregoldService;

    /**
     * 查询用户储值金 明细
     */
//    @PreAuthorize("@ss.hasPermi('system:storegold:list')")
    @PostMapping("/list")
    public AjaxResult list(@RequestBody UserStoregold userStoregold)
    {
        List<UserStoregoldVO> result = new ArrayList<>();
//        startPage();
        List<UserStoregold> list = userStoregoldService.selectUserStoregoldList(userStoregold);
        if (list.size()>0){
            list.stream().forEach(userStoregold1 -> {
                UserStoregoldVO userStoregoldVO = DozerBeanUtils.deepCopy(userStoregold1, UserStoregoldVO.class);
                if (userStoregoldVO.getExchangeType()== ExchangeConstants.CONSUME){
                    userStoregoldVO.setExchangeTypeStr("消费");
                }
                if (userStoregoldVO.getExchangeType()== ExchangeConstants.CHARGE){
                    userStoregoldVO.setExchangeTypeStr("充值");
                }
                result.add(userStoregoldVO);
            });
        }
        return AjaxResult.success(result);
    }

    /**
     * 导出用户储值金 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:storegold:export')")
//    @Log(title = "用户储值金 ", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, UserStoregold userStoregold)
//    {
//        List<UserStoregold> list = userStoregoldService.selectUserStoregoldList(userStoregold);
//        ExcelUtil<UserStoregold> util = new ExcelUtil<UserStoregold>(UserStoregold.class);
//        util.exportExcel(response, list, "用户储值金 数据");
//    }

    /**
     * 获取用户储值金 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:storegold:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userStoregoldService.selectUserStoregoldById(id));
    }

    /**
     * 查询当前储值金
     * @param id
     * @return
     */
    @GetMapping(value = "/curr")
    public AjaxResult getCurrAmount()
    {
        return userStoregoldService.getCurrAmount();
    }

    /**
     * 新增用户储值金 
     */
//    @PreAuthorize("@ss.hasPermi('system:storegold:add')")
    @Log(title = "用户储值金 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserStoregold userStoregold)
    {
        if (userStoregold.getExchangeType() == null) {
            return AjaxResult.error("交易类型必填");
        }
        if (userStoregold.getExchangeAmount() == null) {
            return AjaxResult.error("交易金额必填");
        }
        return toAjax(userStoregoldService.insertUserStoregold(userStoregold));
    }

    /**
     * 修改用户储值金 
     */
    @PreAuthorize("@ss.hasPermi('system:storegold:edit')")
    @Log(title = "用户储值金 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserStoregold userStoregold)
    {
        return toAjax(userStoregoldService.updateUserStoregold(userStoregold));
    }

    /**
     * 删除用户储值金 
     */
    @PreAuthorize("@ss.hasPermi('system:storegold:remove')")
    @Log(title = "用户储值金 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userStoregoldService.deleteUserStoregoldByIds(ids));
    }
}
