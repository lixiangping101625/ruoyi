package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ServiceInfoMapper;
import com.ruoyi.system.domain.ServiceInfo;
import com.ruoyi.system.service.IServiceInfoService;

/**
 * 服务(陪诊)基本信息 Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-12
 */
@Service
public class ServiceInfoServiceImpl implements IServiceInfoService 
{
    @Autowired
    private ServiceInfoMapper serviceInfoMapper;

    /**
     * 查询服务(陪诊)基本信息 
     * 
     * @param id 服务(陪诊)基本信息 主键
     * @return 服务(陪诊)基本信息 
     */
    @Override
    public ServiceInfo selectServiceInfoById(Long id)
    {
        return serviceInfoMapper.selectServiceInfoById(id);
    }

    /**
     * 查询服务(陪诊)基本信息 列表
     * 
     * @param serviceInfo 服务(陪诊)基本信息 
     * @return 服务(陪诊)基本信息 
     */
    @Override
    public List<ServiceInfo> selectServiceInfoList(ServiceInfo serviceInfo)
    {
        return serviceInfoMapper.selectServiceInfoList(serviceInfo);
    }

    /**
     * 新增服务(陪诊)基本信息 
     * 
     * @param serviceInfo 服务(陪诊)基本信息 
     * @return 结果
     */
    @Override
    public int insertServiceInfo(ServiceInfo serviceInfo)
    {
        return serviceInfoMapper.insertServiceInfo(serviceInfo);
    }

    /**
     * 修改服务(陪诊)基本信息 
     * 
     * @param serviceInfo 服务(陪诊)基本信息 
     * @return 结果
     */
    @Override
    public int updateServiceInfo(ServiceInfo serviceInfo)
    {
        return serviceInfoMapper.updateServiceInfo(serviceInfo);
    }

    /**
     * 批量删除服务(陪诊)基本信息 
     * 
     * @param ids 需要删除的服务(陪诊)基本信息 主键
     * @return 结果
     */
    @Override
    public int deleteServiceInfoByIds(Long[] ids)
    {
        return serviceInfoMapper.deleteServiceInfoByIds(ids);
    }

    /**
     * 删除服务(陪诊)基本信息 信息
     * 
     * @param id 服务(陪诊)基本信息 主键
     * @return 结果
     */
    @Override
    public int deleteServiceInfoById(Long id)
    {
        return serviceInfoMapper.deleteServiceInfoById(id);
    }
}
