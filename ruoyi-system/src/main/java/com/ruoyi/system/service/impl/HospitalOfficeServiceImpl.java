package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.constant.DataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.HospitalOfficeMapper;
import com.ruoyi.system.domain.HospitalOffice;
import com.ruoyi.system.service.IHospitalOfficeService;

/**
 * 科室信息 Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-13
 */
@Service
public class HospitalOfficeServiceImpl implements IHospitalOfficeService 
{
    @Autowired
    private HospitalOfficeMapper hospitalOfficeMapper;

    /**
     * 查询科室信息 
     * 
     * @param id 科室信息 主键
     * @return 科室信息 
     */
    @Override
    public HospitalOffice selectHospitalOfficeById(String id)
    {
        return hospitalOfficeMapper.selectHospitalOfficeById(id);
    }

    /**
     * 查询科室信息 列表
     * 
     * @param hospitalOffice 科室信息 
     * @return 科室信息 
     */
    @Override
    public List<HospitalOffice> selectHospitalOfficeList(HospitalOffice hospitalOffice)
    {
        hospitalOffice.setState(DataStatus.NORMAL);
        return hospitalOfficeMapper.selectHospitalOfficeList(hospitalOffice);
    }

    /**
     * 新增科室信息 
     * 
     * @param hospitalOffice 科室信息 
     * @return 结果
     */
    @Override
    public int insertHospitalOffice(HospitalOffice hospitalOffice)
    {
        return hospitalOfficeMapper.insertHospitalOffice(hospitalOffice);
    }

    /**
     * 修改科室信息 
     * 
     * @param hospitalOffice 科室信息 
     * @return 结果
     */
    @Override
    public int updateHospitalOffice(HospitalOffice hospitalOffice)
    {
        return hospitalOfficeMapper.updateHospitalOffice(hospitalOffice);
    }

    /**
     * 批量删除科室信息 
     * 
     * @param ids 需要删除的科室信息 主键
     * @return 结果
     */
    @Override
    public int deleteHospitalOfficeByIds(String[] ids)
    {
        return hospitalOfficeMapper.deleteHospitalOfficeByIds(ids);
    }

    /**
     * 删除科室信息 信息
     * 
     * @param id 科室信息 主键
     * @return 结果
     */
    @Override
    public int deleteHospitalOfficeById(String id)
    {
        return hospitalOfficeMapper.deleteHospitalOfficeById(id);
    }
}
