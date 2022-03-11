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
import com.ruoyi.system.domain.ServiceGoods;
import com.ruoyi.system.service.IServiceGoodsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * （商品）服务定价;Controller
 * 
 * @author ruoyi
 * @date 2022-03-11
 */
@RestController
@RequestMapping("/service/goods")
public class ServiceGoodsController extends BaseController
{
    @Autowired
    private IServiceGoodsService serviceGoodsService;

    /**
     * 查询（商品）服务定价;列表
     */
//    @PreAuthorize("@ss.hasPermi('system:goods:list')")
    @PostMapping("/list")
    public AjaxResult list(@RequestBody ServiceGoods serviceGoods)
    {
        startPage();
        List<ServiceGoods> list = serviceGoodsService.selectServiceGoodsList(serviceGoods);
        CustomPageInfo<ServiceGoods> pageInfo = PageInfoUtils.wrapperData(list);
        return AjaxResult.success(pageInfo);
    }

    /**
     * 导出（商品）服务定价;列表
     */
//    @PreAuthorize("@ss.hasPermi('system:goods:export')")
    @Log(title = "（商品）服务定价;", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceGoods serviceGoods)
    {
        List<ServiceGoods> list = serviceGoodsService.selectServiceGoodsList(serviceGoods);
        ExcelUtil<ServiceGoods> util = new ExcelUtil<ServiceGoods>(ServiceGoods.class);
        util.exportExcel(response, list, "（商品）服务定价;数据");
    }

    /**
     * 获取（商品）服务定价;详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:goods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(serviceGoodsService.selectServiceGoodsById(id));
    }

    /**
     * 新增（商品）服务定价;
     */
//    @PreAuthorize("@ss.hasPermi('system:goods:add')")
    @Log(title = "（商品）服务定价;", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceGoods serviceGoods)
    {
        return toAjax(serviceGoodsService.insertServiceGoods(serviceGoods));
    }

    /**
     * 修改（商品）服务定价;
     */
//    @PreAuthorize("@ss.hasPermi('system:goods:edit')")
    @Log(title = "（商品）服务定价;", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceGoods serviceGoods)
    {
        return toAjax(serviceGoodsService.updateServiceGoods(serviceGoods));
    }

    /**
     * 删除（商品）服务定价;
     */
//    @PreAuthorize("@ss.hasPermi('system:goods:remove')")
    @Log(title = "（商品）服务定价;", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serviceGoodsService.deleteServiceGoodsByIds(ids));
    }
}
