package com.ruoyi.web.controller;

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
import com.ruoyi.system.domain.ServiceCategory;
import com.ruoyi.system.service.IServiceCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * （商品）服务类目;服务类目Controller
 * 
 * @author ruoyi
 * @date 2022-03-11
 */
@RestController
@RequestMapping("/service/category")
public class ServiceCategoryController extends BaseController
{
    @Autowired
    private IServiceCategoryService serviceCategoryService;

    /**
     * 查询（商品）服务类目;服务类目列表
     */
//    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @PostMapping("/list")
    public AjaxResult list(@RequestBody ServiceCategory serviceCategory)
    {
        startPage();
        List<ServiceCategory> list = serviceCategoryService.selectServiceCategoryList(serviceCategory);
//        return getDataTable(list);
        CustomPageInfo<ServiceCategory> pageInfo = PageInfoUtils.wrapperData(list);
        return AjaxResult.success(pageInfo);
    }

    /**
     * 导出（商品）服务类目;服务类目列表
     */
//    @PreAuthorize("@ss.hasPermi('system:category:export')")
    @Log(title = "（商品）服务类目;服务类目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceCategory serviceCategory)
    {
        List<ServiceCategory> list = serviceCategoryService.selectServiceCategoryList(serviceCategory);
        ExcelUtil<ServiceCategory> util = new ExcelUtil<ServiceCategory>(ServiceCategory.class);
        util.exportExcel(response, list, "（商品）服务类目;服务类目数据");
    }

    /**
     * 获取（商品）服务类目;服务类目详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(serviceCategoryService.selectServiceCategoryById(id));
    }

    /**
     * 新增（商品）服务类目;服务类目
     */
//    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "（商品）服务类目;服务类目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceCategory serviceCategory)
    {
        return toAjax(serviceCategoryService.insertServiceCategory(serviceCategory));
    }

    /**
     * 修改（商品）服务类目;服务类目
     */
//    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "（商品）服务类目;服务类目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceCategory serviceCategory)
    {
        return toAjax(serviceCategoryService.updateServiceCategory(serviceCategory));
    }

    /**
     * 删除（商品）服务类目;服务类目
     */
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "（商品）服务类目;服务类目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serviceCategoryService.deleteServiceCategoryByIds(ids));
    }
}
