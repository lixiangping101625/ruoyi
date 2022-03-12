package com.ruoyi.web.controller.service;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.page.CustomPageInfo;
import com.ruoyi.common.utils.page.PageInfoUtils;
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
import com.ruoyi.system.domain.ServiceInfo;
import com.ruoyi.system.service.IServiceInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 服务(陪诊)基本信息 Controller
 * 
 * @author ruoyi
 * @date 2022-03-12
 */
@RestController
@RequestMapping("/service/info")
public class ServiceInfoController extends BaseController
{
    @Autowired
    private IServiceInfoService serviceInfoService;

    /**
     * 查询服务(陪诊)基本信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/list")
    public AjaxResult list(@RequestBody ServiceInfo serviceInfo)
    {
        startPage();
        List<ServiceInfo> list = serviceInfoService.selectServiceInfoList(serviceInfo);
        CustomPageInfo<ServiceInfo> customPageInfo = PageInfoUtils.wrapperData(list);
        return AjaxResult.success(customPageInfo);
    }

    /**
     * 导出服务(陪诊)基本信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "服务(陪诊)基本信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceInfo serviceInfo)
    {
        List<ServiceInfo> list = serviceInfoService.selectServiceInfoList(serviceInfo);
        ExcelUtil<ServiceInfo> util = new ExcelUtil<ServiceInfo>(ServiceInfo.class);
        util.exportExcel(response, list, "服务(陪诊)基本信息 数据");
    }

    /**
     * 获取服务(陪诊)基本信息 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(serviceInfoService.selectServiceInfoById(id));
    }

    /**
     * 新增服务(陪诊)基本信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "服务(陪诊)基本信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceInfo serviceInfo)
    {
        return toAjax(serviceInfoService.insertServiceInfo(serviceInfo));
    }

    /**
     * 修改服务(陪诊)基本信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "服务(陪诊)基本信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceInfo serviceInfo)
    {
        return toAjax(serviceInfoService.updateServiceInfo(serviceInfo));
    }

    /**
     * 删除服务(陪诊)基本信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "服务(陪诊)基本信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serviceInfoService.deleteServiceInfoByIds(ids));
    }
}
