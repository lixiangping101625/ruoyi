package com.ruoyi.web.controller.patient;

import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.enums.RelationEnum;
import com.ruoyi.common.utils.bean.DozerBeanUtils;
import com.ruoyi.system.domain.vo.PatientVO;
import com.ruoyi.system.domain.vo.RelationVO;
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
import com.ruoyi.system.domain.Patient;
import com.ruoyi.system.service.IPatientService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 就诊人员 Controller
 * 
 * @author ruoyi
 * @date 2022-03-12
 */
@RestController
@RequestMapping("/patient")
public class PatientController extends BaseController
{
    @Autowired
    private IPatientService patientService;

    /**
     * 用户和就诊人关系
     * @return
     */
    @GetMapping("/relations")
    public AjaxResult relationEnums(){
       List<RelationVO> result = new ArrayList<>();

        HashMap<String, Integer> map = new HashMap<>();
        RelationEnum[] values = RelationEnum.values();
        if (values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                map.put(values[i].getDesc(), values[i].getCode());
            }
            ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o1.getValue() - o2.getValue();
                }
            });
            list.forEach(stringIntegerEntry ->
                    result.add(RelationVO.builder().desc(stringIntegerEntry.getKey()).code(stringIntegerEntry.getValue()).build()));
        }
        return AjaxResult.success(result);
    }

    /**
     * 查询就诊人员 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:patient:list')")
    @PostMapping("/list")
    public AjaxResult list(@RequestBody Patient patient)
    {
        List<PatientVO> result = new ArrayList<>();

        List<Patient> list = patientService.selectPatientList(patient);
        if (list.size() > 0) {
            list.stream().forEach(patient1 -> result.add(DozerBeanUtils.deepCopy(patient1, PatientVO.class)));
        }
        return AjaxResult.success(result);
    }

    /**
     * 导出就诊人员 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:patient:export')")
    @Log(title = "就诊人员 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Patient patient)
    {
        List<Patient> list = patientService.selectPatientList(patient);
        ExcelUtil<Patient> util = new ExcelUtil<Patient>(Patient.class);
        util.exportExcel(response, list, "就诊人员 数据");
    }

    /**
     * 获取就诊人员 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:patient:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(patientService.selectPatientById(id));
    }

    /**
     * 新增就诊人员 
     */
//    @PreAuthorize("@ss.hasPermi('system:patient:add')")
    @Log(title = "就诊人员 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Patient patient)
    {
        return toAjax(patientService.insertPatient(patient));
    }

    /**
     * 修改就诊人员 
     */
//    @PreAuthorize("@ss.hasPermi('system:patient:edit')")
    @Log(title = "就诊人员 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Patient patient)
    {
        return toAjax(patientService.updatePatient(patient));
    }

    /**
     * 删除就诊人员 
     */
//    @PreAuthorize("@ss.hasPermi('system:patient:remove')")
    @Log(title = "就诊人员 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(patientService.deletePatientByIds(ids));
    }
}
