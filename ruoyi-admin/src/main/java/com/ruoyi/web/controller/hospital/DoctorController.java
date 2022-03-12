package com.ruoyi.web.controller.hospital;

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
import com.ruoyi.system.domain.Doctor;
import com.ruoyi.system.service.IDoctorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 预约医生 Controller
 * 
 * @author ruoyi
 * @date 2022-03-13
 */
@RestController
@RequestMapping("/hospital/doctor")
public class DoctorController extends BaseController
{
    @Autowired
    private IDoctorService doctorService;

    /**
     * 查询预约医生 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:doctor:list')")
    @PostMapping("/list")
    public AjaxResult list(@RequestBody Doctor doctor)
    {
//        startPage();
        List<Doctor> list = doctorService.selectDoctorList(doctor);
        return AjaxResult.success(list);
    }

    /**
     * 导出预约医生 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:doctor:export')")
    @Log(title = "预约医生 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Doctor doctor)
    {
        List<Doctor> list = doctorService.selectDoctorList(doctor);
        ExcelUtil<Doctor> util = new ExcelUtil<Doctor>(Doctor.class);
        util.exportExcel(response, list, "预约医生 数据");
    }

    /**
     * 获取预约医生 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:doctor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(doctorService.selectDoctorById(id));
    }

    /**
     * 新增预约医生 
     */
//    @PreAuthorize("@ss.hasPermi('system:doctor:add')")
    @Log(title = "预约医生 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Doctor doctor)
    {
        return toAjax(doctorService.insertDoctor(doctor));
    }

    /**
     * 修改预约医生 
     */
//    @PreAuthorize("@ss.hasPermi('system:doctor:edit')")
    @Log(title = "预约医生 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Doctor doctor)
    {
        return toAjax(doctorService.updateDoctor(doctor));
    }

    /**
     * 删除预约医生 
     */
//    @PreAuthorize("@ss.hasPermi('system:doctor:remove')")
    @Log(title = "预约医生 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(doctorService.deleteDoctorByIds(ids));
    }
}
