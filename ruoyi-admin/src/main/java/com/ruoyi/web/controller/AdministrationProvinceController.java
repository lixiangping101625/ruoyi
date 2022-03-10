package com.ruoyi.web.controller;

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
import com.ruoyi.system.domain.AdministrationProvince;
import com.ruoyi.system.service.IAdministrationProvinceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 省份设置Controller
 * 
 * @author ruoyi
 * @date 2022-03-10
 */
@RestController
@RequestMapping("/system/province")
public class AdministrationProvinceController extends BaseController
{
    @Autowired
    private IAdministrationProvinceService administrationProvinceService;

    /**
     * 查询省份设置列表
     */
    @PreAuthorize("@ss.hasPermi('system:province:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdministrationProvince administrationProvince)
    {
        startPage();
        List<AdministrationProvince> list = administrationProvinceService.selectAdministrationProvinceList(administrationProvince);
        return getDataTable(list);
    }

    /**
     * 导出省份设置列表
     */
    @PreAuthorize("@ss.hasPermi('system:province:export')")
    @Log(title = "省份设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdministrationProvince administrationProvince)
    {
        List<AdministrationProvince> list = administrationProvinceService.selectAdministrationProvinceList(administrationProvince);
        ExcelUtil<AdministrationProvince> util = new ExcelUtil<AdministrationProvince>(AdministrationProvince.class);
        util.exportExcel(response, list, "省份设置数据");
    }

    /**
     * 获取省份设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:province:query')")
    @GetMapping(value = "/{provinceId}")
    public AjaxResult getInfo(@PathVariable("provinceId") Long provinceId)
    {
        return AjaxResult.success(administrationProvinceService.selectAdministrationProvinceByProvinceId(provinceId));
    }

    /**
     * 新增省份设置
     */
    @PreAuthorize("@ss.hasPermi('system:province:add')")
    @Log(title = "省份设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdministrationProvince administrationProvince)
    {
        return toAjax(administrationProvinceService.insertAdministrationProvince(administrationProvince));
    }

    /**
     * 修改省份设置
     */
    @PreAuthorize("@ss.hasPermi('system:province:edit')")
    @Log(title = "省份设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdministrationProvince administrationProvince)
    {
        return toAjax(administrationProvinceService.updateAdministrationProvince(administrationProvince));
    }

    /**
     * 删除省份设置
     */
    @PreAuthorize("@ss.hasPermi('system:province:remove')")
    @Log(title = "省份设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{provinceIds}")
    public AjaxResult remove(@PathVariable Long[] provinceIds)
    {
        return toAjax(administrationProvinceService.deleteAdministrationProvinceByProvinceIds(provinceIds));
    }
}
