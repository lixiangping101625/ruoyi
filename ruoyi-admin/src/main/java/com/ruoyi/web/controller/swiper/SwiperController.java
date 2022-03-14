package com.ruoyi.web.controller.swiper;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.service.ISwiperService;
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
import com.ruoyi.system.domain.Swiper;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 轮播图;轮播图Controller
 * 
 * @author ruoyi
 * @date 2022-03-10
 */
@RestController
@RequestMapping("/swiper")
public class SwiperController extends BaseController
{
    @Autowired
    private ISwiperService swiperService;

    /**
     * 查询轮播图;轮播图列表
     */
//    @PreAuthorize("@ss.hasPermi('system:swiper:list')")
    @GetMapping("/list")
    public TableDataInfo list(Swiper swiper)
    {
        startPage();
        List<Swiper> list = swiperService.selectSwiperList(swiper);
        return getDataTable(list);
    }

    /**
     * 导出轮播图;轮播图列表
     */
//    @PreAuthorize("@ss.hasPermi('system:swiper:export')")
    @Log(title = "轮播图;轮播图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Swiper swiper)
    {
        List<Swiper> list = swiperService.selectSwiperList(swiper);
        ExcelUtil<Swiper> util = new ExcelUtil<Swiper>(Swiper.class);
        util.exportExcel(response, list, "轮播图;轮播图数据");
    }

    /**
     * 获取轮播图;轮播图详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:swiper:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(swiperService.selectSwiperById(id));
    }

    /**
     * 新增轮播图;轮播图
     */
//    @PreAuthorize("@ss.hasPermi('system:swiper:add')")
    @Log(title = "轮播图;轮播图", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Swiper swiper)
    {
        return toAjax(swiperService.insertSwiper(swiper));
    }

    /**
     * 修改轮播图;轮播图
     */
//    @PreAuthorize("@ss.hasPermi('system:swiper:edit')")
    @Log(title = "轮播图;轮播图", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Swiper swiper)
    {
        return toAjax(swiperService.updateSwiper(swiper));
    }

    /**
     * 删除轮播图;轮播图
     */
//    @PreAuthorize("@ss.hasPermi('system:swiper:remove')")
    @Log(title = "轮播图;轮播图", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(swiperService.deleteSwiperByIds(ids));
    }
}
