package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Patient;

/**
 * 就诊人员 Service接口
 * 
 * @author ruoyi
 * @date 2022-03-12
 */
public interface IPatientService 
{
    /**
     * 查询就诊人员 
     * 
     * @param id 就诊人员 主键
     * @return 就诊人员 
     */
    public Patient selectPatientById(Long id);

    /**
     * 查询就诊人员 列表
     * 
     * @param patient 就诊人员 
     * @return 就诊人员 集合
     */
    public List<Patient> selectPatientList(Patient patient);

    /**
     * 新增就诊人员 
     * 
     * @param patient 就诊人员 
     * @return 结果
     */
    public Patient insertPatient(Patient patient);

    /**
     * 修改就诊人员 
     * 
     * @param patient 就诊人员 
     * @return 结果
     */
    public int updatePatient(Patient patient);

    /**
     * 批量删除就诊人员 
     * 
     * @param ids 需要删除的就诊人员 主键集合
     * @return 结果
     */
    public int deletePatientByIds(Long[] ids);

    /**
     * 删除就诊人员 信息
     * 
     * @param id 就诊人员 主键
     * @return 结果
     */
    public AjaxResult deletePatientById(Long id);
}
