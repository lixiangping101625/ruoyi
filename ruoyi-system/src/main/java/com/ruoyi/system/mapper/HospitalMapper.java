package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Hospital;

/**
 * 医院信息 Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-13
 */
public interface HospitalMapper 
{
    /**
     * 查询医院信息 
     * 
     * @param id 医院信息 主键
     * @return 医院信息 
     */
    public Hospital selectHospitalById(Long id);

    /**
     * 查询医院信息 列表
     * 
     * @param hospital 医院信息 
     * @return 医院信息 集合
     */
    public List<Hospital> selectHospitalList(Hospital hospital);

    /**
     * 新增医院信息 
     * 
     * @param hospital 医院信息 
     * @return 结果
     */
    public int insertHospital(Hospital hospital);

    /**
     * 修改医院信息 
     * 
     * @param hospital 医院信息 
     * @return 结果
     */
    public int updateHospital(Hospital hospital);

    /**
     * 删除医院信息 
     * 
     * @param id 医院信息 主键
     * @return 结果
     */
    public int deleteHospitalById(Long id);

    /**
     * 批量删除医院信息 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHospitalByIds(Long[] ids);
}
