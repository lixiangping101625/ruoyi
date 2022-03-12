package com.ruoyi.web.controller.hospital;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.bean.DozerBeanUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Hospital;
import com.ruoyi.system.domain.vo.HospitalVO;
import com.ruoyi.system.service.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 医院信息 Controller
 * 
 * @author ruoyi
 * @date 2022-03-13
 */
@RestController
@RequestMapping("/hospital")
public class HospitalController extends BaseController
{
    @Autowired
    private IHospitalService hospitalService;

    /**
     * 查询医院信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:hospital:list')")
    @PostMapping("/list")
    public AjaxResult list(@RequestBody Hospital hospital)
    {
        ArrayList<HospitalVO> result = new ArrayList<>();
//        startPage();
        List<Hospital> list = hospitalService.selectHospitalList(hospital);
        if (list.size() > 0) {
            list.stream().forEach(hospital1 -> result.add(DozerBeanUtils.deepCopy(hospital1, HospitalVO.class)));
        }
        return AjaxResult.success(result);
    }

    /**
     * 导出医院信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:hospital:export')")
    @Log(title = "医院信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Hospital hospital)
    {
        List<Hospital> list = hospitalService.selectHospitalList(hospital);
        ExcelUtil<Hospital> util = new ExcelUtil<Hospital>(Hospital.class);
        util.exportExcel(response, list, "医院信息 数据");
    }

    /**
     * 获取医院信息 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:hospital:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(hospitalService.selectHospitalById(id));
    }

    /**
     * 新增医院信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:hospital:add')")
    @Log(title = "医院信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Hospital hospital)
    {
        return toAjax(hospitalService.insertHospital(hospital));
    }

    /**
     * 修改医院信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:hospital:edit')")
    @Log(title = "医院信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Hospital hospital)
    {
        return toAjax(hospitalService.updateHospital(hospital));
    }

    /**
     * 删除医院信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:hospital:remove')")
    @Log(title = "医院信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hospitalService.deleteHospitalByIds(ids));
    }
}
