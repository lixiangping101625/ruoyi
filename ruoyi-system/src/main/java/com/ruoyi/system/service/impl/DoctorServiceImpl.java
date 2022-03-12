package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.constant.DataStatus;
import com.ruoyi.common.enums.GenderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DoctorMapper;
import com.ruoyi.system.domain.Doctor;
import com.ruoyi.system.service.IDoctorService;

/**
 * 预约医生 Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-13
 */
@Service
public class DoctorServiceImpl implements IDoctorService 
{
    @Autowired
    private DoctorMapper doctorMapper;

    /**
     * 查询预约医生 
     * 
     * @param id 预约医生 主键
     * @return 预约医生 
     */
    @Override
    public Doctor selectDoctorById(Long id)
    {
        return doctorMapper.selectDoctorById(id);
    }

    /**
     * 查询预约医生 列表
     * 
     * @param doctor 预约医生 
     * @return 预约医生 
     */
    @Override
    public List<Doctor> selectDoctorList(Doctor doctor)
    {
        doctor.setState(DataStatus.NORMAL);
        List<Doctor> list = doctorMapper.selectDoctorList(doctor);
        if (list.size() > 0) {
            list.forEach(doctor1 -> doctor1.setGenderStr(GenderEnum.getDesc(doctor1.getGender())));
        }
        return list;
    }

    /**
     * 新增预约医生 
     * 
     * @param doctor 预约医生 
     * @return 结果
     */
    @Override
    public int insertDoctor(Doctor doctor)
    {
        return doctorMapper.insertDoctor(doctor);
    }

    /**
     * 修改预约医生 
     * 
     * @param doctor 预约医生 
     * @return 结果
     */
    @Override
    public int updateDoctor(Doctor doctor)
    {
        return doctorMapper.updateDoctor(doctor);
    }

    /**
     * 批量删除预约医生 
     * 
     * @param ids 需要删除的预约医生 主键
     * @return 结果
     */
    @Override
    public int deleteDoctorByIds(Long[] ids)
    {
        return doctorMapper.deleteDoctorByIds(ids);
    }

    /**
     * 删除预约医生 信息
     * 
     * @param id 预约医生 主键
     * @return 结果
     */
    @Override
    public int deleteDoctorById(Long id)
    {
        return doctorMapper.deleteDoctorById(id);
    }
}
