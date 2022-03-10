package com.ruoyi.web.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.AdministrationCity;
import com.ruoyi.system.service.IAdministrationCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 城市设置Controller
 * 
 * @author ruoyi
 * @date 2022-03-10
 */
@RestController
@RequestMapping("/city")
public class AdministrationCityController extends BaseController
{
    @Autowired
    private IAdministrationCityService administrationCityService;

    /**
     * 查询城市设置列表
     */
    @PreAuthorize("@ss.hasPermi('system:city:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdministrationCity administrationCity)
    {
        startPage();
        List<AdministrationCity> list = administrationCityService.selectAdministrationCityList(administrationCity);
        return getDataTable(list);
    }

    /**
     * 导出城市设置列表
     */
    @PreAuthorize("@ss.hasPermi('system:city:export')")
    @Log(title = "城市设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdministrationCity administrationCity)
    {
        List<AdministrationCity> list = administrationCityService.selectAdministrationCityList(administrationCity);
        ExcelUtil<AdministrationCity> util = new ExcelUtil<AdministrationCity>(AdministrationCity.class);
        util.exportExcel(response, list, "城市设置数据");
    }

    /**
     * 获取城市设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:city:query')")
    @GetMapping(value = "/{cityId}")
    public AjaxResult getInfo(@PathVariable("cityId") Long cityId)
    {
        return AjaxResult.success(administrationCityService.selectAdministrationCityByCityId(cityId));
    }

    /**
     * 新增城市设置
     */
    @PreAuthorize("@ss.hasPermi('system:city:add')")
    @Log(title = "城市设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdministrationCity administrationCity)
    {
        return toAjax(administrationCityService.insertAdministrationCity(administrationCity));
    }

    /**
     * 修改城市设置
     */
    @PreAuthorize("@ss.hasPermi('system:city:edit')")
    @Log(title = "城市设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdministrationCity administrationCity)
    {
        return toAjax(administrationCityService.updateAdministrationCity(administrationCity));
    }

    /**
     * 删除城市设置
     */
    @PreAuthorize("@ss.hasPermi('system:city:remove')")
    @Log(title = "城市设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cityIds}")
    public AjaxResult remove(@PathVariable Long[] cityIds)
    {
        return toAjax(administrationCityService.deleteAdministrationCityByCityIds(cityIds));
    }
}
