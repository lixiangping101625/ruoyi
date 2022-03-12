package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Doctor;

/**
 * 预约医生 Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-13
 */
public interface DoctorMapper 
{
    /**
     * 查询预约医生 
     * 
     * @param id 预约医生 主键
     * @return 预约医生 
     */
    public Doctor selectDoctorById(Long id);

    /**
     * 查询预约医生 列表
     * 
     * @param doctor 预约医生 
     * @return 预约医生 集合
     */
    public List<Doctor> selectDoctorList(Doctor doctor);

    /**
     * 新增预约医生 
     * 
     * @param doctor 预约医生 
     * @return 结果
     */
    public int insertDoctor(Doctor doctor);

    /**
     * 修改预约医生 
     * 
     * @param doctor 预约医生 
     * @return 结果
     */
    public int updateDoctor(Doctor doctor);

    /**
     * 删除预约医生 
     * 
     * @param id 预约医生 主键
     * @return 结果
     */
    public int deleteDoctorById(Long id);

    /**
     * 批量删除预约医生 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDoctorByIds(Long[] ids);
}
