package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.constant.DataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.HospitalMapper;
import com.ruoyi.system.domain.Hospital;
import com.ruoyi.system.service.IHospitalService;

/**
 * 医院信息 Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-13
 */
@Service
public class HospitalServiceImpl implements IHospitalService 
{
    @Autowired
    private HospitalMapper hospitalMapper;

    /**
     * 查询医院信息 
     * 
     * @param id 医院信息 主键
     * @return 医院信息 
     */
    @Override
    public Hospital selectHospitalById(Long id)
    {
        return hospitalMapper.selectHospitalById(id);
    }

    /**
     * 查询医院信息 列表
     * 
     * @param hospital 医院信息 
     * @return 医院信息 
     */
    @Override
    public List<Hospital> selectHospitalList(Hospital hospital)
    {
        hospital.setBusinessStatus(DataStatus.NORMAL);
        return hospitalMapper.selectHospitalList(hospital);
    }

    /**
     * 新增医院信息 
     * 
     * @param hospital 医院信息 
     * @return 结果
     */
    @Override
    public int insertHospital(Hospital hospital)
    {
        return hospitalMapper.insertHospital(hospital);
    }

    /**
     * 修改医院信息 
     * 
     * @param hospital 医院信息 
     * @return 结果
     */
    @Override
    public int updateHospital(Hospital hospital)
    {
        return hospitalMapper.updateHospital(hospital);
    }

    /**
     * 批量删除医院信息 
     * 
     * @param ids 需要删除的医院信息 主键
     * @return 结果
     */
    @Override
    public int deleteHospitalByIds(Long[] ids)
    {
        return hospitalMapper.deleteHospitalByIds(ids);
    }

    /**
     * 删除医院信息 信息
     * 
     * @param id 医院信息 主键
     * @return 结果
     */
    @Override
    public int deleteHospitalById(Long id)
    {
        return hospitalMapper.deleteHospitalById(id);
    }
}
