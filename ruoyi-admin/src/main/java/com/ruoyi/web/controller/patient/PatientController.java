package com.ruoyi.web.controller.patient;

import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.enums.RelationEnum;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.DozerBeanUtils;
import com.ruoyi.common.utils.page.CustomPageInfo;
import com.ruoyi.common.utils.page.PageInfoUtils;
import com.ruoyi.system.domain.vo.PatientVO;
import com.ruoyi.system.domain.vo.RelationVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @Log(title = "查询就诊人员列表 ", businessType = BusinessType.OTHER)
    @PostMapping("/list")
    public AjaxResult list(@RequestBody Patient patient)
    {
        startPage();
        List<PatientVO> result = new ArrayList<>();

        List<Patient> list = patientService.selectPatientList(patient);
        if (list.size() > 0) {
            list.stream().forEach(patient1 -> result.add(DozerBeanUtils.deepCopy(patient1, PatientVO.class)));
        }
        CustomPageInfo<Patient> customPageInfo = PageInfoUtils.wrapperData(list);

        return AjaxResult.success(customPageInfo);
    }

    /**
     * 导出就诊人员 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:patient:export')")
//    @Log(title = "就诊人员 ", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, Patient patient)
//    {
//        List<Patient> list = patientService.selectPatientList(patient);
//        ExcelUtil<Patient> util = new ExcelUtil<Patient>(Patient.class);
//        util.exportExcel(response, list, "就诊人员 数据");
//    }

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
    @Log(title = "新增就诊人员 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Patient patient)
    {
        if (StringUtils.isEmpty(patient.getName())) {
            return AjaxResult.error("就诊人姓名不能不为空~");
        }
        if (patient.getGender()==null) {
            return AjaxResult.error("性别不为空~");
        }
        if (StringUtils.isEmpty(patient.getContact())) {
            return AjaxResult.error("联系方式不能不为空~");
        }
        if (StringUtils.isEmpty(patient.getCardNum())) {
            return AjaxResult.error("身份证号码不能不为空~");
        }
        if (patient.getRelation()==null) {
            return AjaxResult.error("与就诊人关系不为空~");
        }
        return patientService.insertPatient(patient)>0 ? AjaxResult.success("添加成功~"):AjaxResult.error("添加失败~");
    }

    /**
     * 修改就诊人员 
     */
//    @PreAuthorize("@ss.hasPermi('system:patient:edit')")
    @Log(title = "编辑就诊人员 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Patient patient)
    {
        return toAjax(patientService.updatePatient(patient));
    }

    /**
     * 删除就诊人员 
     */
//    @PreAuthorize("@ss.hasPermi('system:patient:remove')")
    @Log(title = "删除就诊人员 ", businessType = BusinessType.DELETE)
	@GetMapping("/del}")
    public AjaxResult remove(@RequestParam Long patientId,@RequestParam Long userId)
    {
        if (patientId == null) {
            return AjaxResult.error("就诊人id不能为空~");
        }
        if (userId == null) {
            return AjaxResult.error("用户id不能为空~");
        }
        if (!userId.equals(SecurityUtils.getUserId())) {
            return AjaxResult.error("只能删除本人名下的纠正人~");
        }
        return patientService.deletePatientById(patientId);
    }
}
