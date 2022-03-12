package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ServiceInfo;

/**
 * 服务(陪诊)基本信息 Service接口
 * 
 * @author ruoyi
 * @date 2022-03-12
 */
public interface IServiceInfoService 
{
    /**
     * 查询服务(陪诊)基本信息 
     * 
     * @param id 服务(陪诊)基本信息 主键
     * @return 服务(陪诊)基本信息 
     */
    public ServiceInfo selectServiceInfoById(Long id);

    /**
     * 查询服务(陪诊)基本信息 列表
     * 
     * @param serviceInfo 服务(陪诊)基本信息 
     * @return 服务(陪诊)基本信息 集合
     */
    public List<ServiceInfo> selectServiceInfoList(ServiceInfo serviceInfo);

    /**
     * 新增服务(陪诊)基本信息 
     * 
     * @param serviceInfo 服务(陪诊)基本信息 
     * @return 结果
     */
    public int insertServiceInfo(ServiceInfo serviceInfo);

    /**
     * 修改服务(陪诊)基本信息 
     * 
     * @param serviceInfo 服务(陪诊)基本信息 
     * @return 结果
     */
    public int updateServiceInfo(ServiceInfo serviceInfo);

    /**
     * 批量删除服务(陪诊)基本信息 
     * 
     * @param ids 需要删除的服务(陪诊)基本信息 主键集合
     * @return 结果
     */
    public int deleteServiceInfoByIds(Long[] ids);

    /**
     * 删除服务(陪诊)基本信息 信息
     * 
     * @param id 服务(陪诊)基本信息 主键
     * @return 结果
     */
    public int deleteServiceInfoById(Long id);
}
