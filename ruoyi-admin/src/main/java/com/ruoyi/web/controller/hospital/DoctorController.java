package com.ruoyi.web.controller.hospital;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.enums.GenderEnum;
import com.ruoyi.common.utils.bean.DozerBeanUtils;
import com.ruoyi.system.domain.vo.DoctorVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @Log(title = "查询预约医生 ", businessType = BusinessType.OTHER)
    @PostMapping("/list")
    public AjaxResult list(@RequestBody Doctor doctor)
    {
        if (doctor.getHospitalId() == null) {
            return AjaxResult.error("医院id必填~");
        }
        if (doctor.getDepartofficeId() == null) {
            return AjaxResult.error("科室id必填~");
        }
        List<DoctorVO> result = new ArrayList<>();
//        startPage();
        List<Doctor> list = doctorService.selectDoctorList(doctor);
        if (list.size() > 0) {
            list.forEach(doctor1 -> {
                result.add(DozerBeanUtils.deepCopy(doctor1, DoctorVO.class));
            });
        }
        return AjaxResult.success(result);
    }

    /**
     * 导出预约医生 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:doctor:export')")
//    @Log(title = "预约医生 ", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, Doctor doctor)
//    {
//        List<Doctor> list = doctorService.selectDoctorList(doctor);
//        ExcelUtil<Doctor> util = new ExcelUtil<Doctor>(Doctor.class);
//        util.exportExcel(response, list, "预约医生 数据");
//    }

    /**
     * 获取预约医生 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:doctor:query')")
    @GetMapping(value = "/detail")
    @Log(title = "预约医生详情 ", businessType = BusinessType.OTHER)
    public AjaxResult getInfo(@RequestParam("doctorId") Long doctorId)
    {
        if (doctorId == null) {
            return AjaxResult.error("预约医生id不能为空~");
        }
        return AjaxResult.success(doctorService.selectDoctorById(doctorId));
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
