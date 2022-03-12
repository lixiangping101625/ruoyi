package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.enums.RelationEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PatientMapper;
import com.ruoyi.system.domain.Patient;
import com.ruoyi.system.service.IPatientService;

/**
 * 就诊人员 Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-12
 */
@Service
public class PatientServiceImpl implements IPatientService 
{
    @Autowired
    private PatientMapper patientMapper;

    /**
     * 查询就诊人员 
     * 
     * @param id 就诊人员 主键
     * @return 就诊人员 
     */
    @Override
    public Patient selectPatientById(Long id)
    {
        return patientMapper.selectPatientById(id);
    }

    /**
     * 查询就诊人员 列表
     * 
     * @param patient 就诊人员 
     * @return 就诊人员 
     */
    @Override
    public List<Patient> selectPatientList(Patient patient)
    {
        List<Patient> list = patientMapper.selectPatientList(patient);
        if (list.size() > 0) {
            list.stream().forEach(patient1 -> patient1.setRelationStr(RelationEnum.getDesc(patient1.getRelation())));
        }
        return list;
    }

    /**
     * 新增就诊人员 
     * 
     * @param patient 就诊人员 
     * @return 结果
     */
    @Override
    public int insertPatient(Patient patient)
    {
        return patientMapper.insertPatient(patient);
    }

    /**
     * 修改就诊人员 
     * 
     * @param patient 就诊人员 
     * @return 结果
     */
    @Override
    public int updatePatient(Patient patient)
    {
        return patientMapper.updatePatient(patient);
    }

    /**
     * 批量删除就诊人员 
     * 
     * @param ids 需要删除的就诊人员 主键
     * @return 结果
     */
    @Override
    public int deletePatientByIds(Long[] ids)
    {
        return patientMapper.deletePatientByIds(ids);
    }

    /**
     * 删除就诊人员 信息
     * 
     * @param id 就诊人员 主键
     * @return 结果
     */
    @Override
    public int deletePatientById(Long id)
    {
        return patientMapper.deletePatientById(id);
    }
}
