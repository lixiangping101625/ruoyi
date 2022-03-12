package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Patient;

/**
 * 就诊人员 Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-12
 */
public interface PatientMapper 
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
    public int insertPatient(Patient patient);

    /**
     * 修改就诊人员 
     * 
     * @param patient 就诊人员 
     * @return 结果
     */
    public int updatePatient(Patient patient);

    /**
     * 删除就诊人员 
     * 
     * @param id 就诊人员 主键
     * @return 结果
     */
    public int deletePatientById(Long id);

    /**
     * 批量删除就诊人员 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePatientByIds(Long[] ids);
}
