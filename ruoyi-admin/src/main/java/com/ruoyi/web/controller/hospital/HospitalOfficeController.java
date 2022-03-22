package com.ruoyi.web.controller.hospital;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.DozerBeanUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.HospitalOffice;
import com.ruoyi.system.domain.vo.HospitalOfficeVO;
import com.ruoyi.system.domain.vo.HospitalVO;
import com.ruoyi.system.service.IHospitalOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 科室信息 Controller
 * 
 * @author ruoyi
 * @date 2022-03-13
 */
@RestController
@RequestMapping("/hospital/office")
public class HospitalOfficeController extends BaseController
{
    @Autowired
    private IHospitalOfficeService hospitalOfficeService;

    /**
     * 查询科室信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:office:list')")
    @Log(title = "查询医院科室列表 ", businessType = BusinessType.OTHER)
    @PostMapping("/list")
    public AjaxResult list(@RequestBody HospitalOffice hospitalOffice)
    {
        if (hospitalOffice.getHospitalId() == null) {
            return AjaxResult.error("医院id不能为空~");
        }
        List<HospitalOfficeVO> result = new ArrayList<>();

        List<HospitalOffice> list = hospitalOfficeService.selectHospitalOfficeList(hospitalOffice);
        //组装树结构
        List<HospitalOffice> rootList = new ArrayList<>();
        if (list.size() > 0) {
            list.forEach(hospitalOffice1 -> {
                if (hospitalOffice1.getParentId() == null) {
                    rootList.add(hospitalOffice1);
                }
            });
            list.removeAll(rootList);
            //遍历顶技术，查询下一级
            if (rootList.size() > 0) {
                rootList.forEach(rootHospitalOffice -> cycleSub(rootHospitalOffice, list));
            }
        }
        //返回简要信息
        if (rootList.size() > 0) {
            rootList.forEach(rootHospital -> {
                HospitalOfficeVO officeVO = DozerBeanUtils.deepCopy(rootHospital, HospitalOfficeVO.class);
                result.add(officeVO);
            });
        }
        return AjaxResult.success(result);
    }

    /**
     * 递归查询子项
     * @param hospitalOffice 顶级
     * @param list 除顶级外所有
     */
    private void cycleSub(HospitalOffice hospitalOffice, List<HospitalOffice> list) {
        if (list.size() > 0) {
            List<HospitalOffice> subList = new ArrayList<>();
            list.forEach(hospitalOffice1 -> {
                if (hospitalOffice1.getParentId().toString().equals(hospitalOffice.getId().toString())) {
                    subList.add(hospitalOffice1);
                }
            });
            if (subList.size() > 0) {
                hospitalOffice.setSubHospitalOffices(subList);
            }
            //递归子项
            List<HospitalOffice> subHospitalOffices = hospitalOffice.getSubHospitalOffices();
            if (subHospitalOffices!=null && subHospitalOffices.size() > 0) {
                subHospitalOffices.forEach(hospitalOffice1 -> {
                    cycleSub(hospitalOffice1, list);
                });
            }
        }
    }

    /**
     * 导出科室信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:office:export')")
//    @Log(title = "科室信息 ", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, HospitalOffice hospitalOffice)
//    {
//        List<HospitalOffice> list = hospitalOfficeService.selectHospitalOfficeList(hospitalOffice);
//        ExcelUtil<HospitalOffice> util = new ExcelUtil<HospitalOffice>(HospitalOffice.class);
//        util.exportExcel(response, list, "科室信息 数据");
//    }

    /**
     * 获取科室信息 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:office:query')")
    @Log(title = "获取科室详情 ", businessType = BusinessType.OTHER)
    @GetMapping(value = "/detail")
    public AjaxResult getInfo(@RequestParam("officeId") String officeId)
    {
        if (StringUtils.isEmpty(officeId)) {
            return AjaxResult.error("科室id不能为空~");
        }
        return AjaxResult.success(hospitalOfficeService.selectHospitalOfficeById(officeId));
    }

    /**
     * 新增科室信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:office:add')")
    @Log(title = "新增科室信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HospitalOffice hospitalOffice)
    {
        return toAjax(hospitalOfficeService.insertHospitalOffice(hospitalOffice));
    }

    /**
     * 修改科室信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:office:edit')")
//    @Log(title = "编辑科室信息 ", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody HospitalOffice hospitalOffice)
//    {
//        return toAjax(hospitalOfficeService.updateHospitalOffice(hospitalOffice));
//    }

    /**
     * 删除科室信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:office:remove')")
//    @Log(title = "科室信息 ", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable String[] ids)
//    {
//        return toAjax(hospitalOfficeService.deleteHospitalOfficeByIds(ids));
//    }
}
