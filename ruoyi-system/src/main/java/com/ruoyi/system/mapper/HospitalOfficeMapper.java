package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.HospitalOffice;

/**
 * 科室信息 Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-13
 */
public interface HospitalOfficeMapper 
{
    /**
     * 查询科室信息 
     * 
     * @param id 科室信息 主键
     * @return 科室信息 
     */
    public HospitalOffice selectHospitalOfficeById(String id);

    /**
     * 查询科室信息 列表
     * 
     * @param hospitalOffice 科室信息 
     * @return 科室信息 集合
     */
    public List<HospitalOffice> selectHospitalOfficeList(HospitalOffice hospitalOffice);

    /**
     * 新增科室信息 
     * 
     * @param hospitalOffice 科室信息 
     * @return 结果
     */
    public int insertHospitalOffice(HospitalOffice hospitalOffice);

    /**
     * 修改科室信息 
     * 
     * @param hospitalOffice 科室信息 
     * @return 结果
     */
    public int updateHospitalOffice(HospitalOffice hospitalOffice);

    /**
     * 删除科室信息 
     * 
     * @param id 科室信息 主键
     * @return 结果
     */
    public int deleteHospitalOfficeById(String id);

    /**
     * 批量删除科室信息 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHospitalOfficeByIds(String[] ids);
}
