package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.DataStatus;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.RelationEnum;
import com.ruoyi.common.snowflake.SnowflakeUtils;
import com.ruoyi.common.utils.SecurityUtils;
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
        //查询当前用户的接诊人列表
        patient.setUserId(SecurityUtils.getUserId());
        patient.setState(DataStatus.NORMAL);
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
    public Patient insertPatient(Patient patient)
    {
        patient.setId(SnowflakeUtils.nextId());
        patient.setUserId(SecurityUtils.getUserId());
        patient.setCreatedTime(new Date());
        patient.setCreatedBy(SecurityUtils.getUserId().toString());
        patient.setState(DataStatus.NORMAL);

        return patientMapper.insertPatient(patient)>0 ? patient:null;
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
    public AjaxResult deletePatientById(Long id)
    {
        //判断就诊人是否存在
        Patient querayDomain = new Patient();
        querayDomain.setUserId(SecurityUtils.getUserId());
        querayDomain.setId(id);
        List<Patient> list = patientMapper.selectPatientList(querayDomain);
        if (list.size()==0) {
            return AjaxResult.error("当前就诊人不存在~");
        }
        return patientMapper.deletePatientById(id)>0 ? AjaxResult.success("删除成功~"):AjaxResult.error("删除失败~");
    }
}
