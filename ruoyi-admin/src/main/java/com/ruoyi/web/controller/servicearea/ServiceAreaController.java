package com.ruoyi.web.controller.servicearea;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.bean.DozerBeanUtils;
import com.ruoyi.system.domain.AdministrationProvince;
import com.ruoyi.system.domain.vo.ProvCityVO;
import com.ruoyi.system.domain.vo.ProvinceVO;
import com.ruoyi.system.mapper.AdministrationProvinceMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年03月10日 19:16
 * @decription: 业务开通城市
 */
@RestController
public class ServiceAreaController {

    @Resource
    private AdministrationProvinceMapper provinceMapper;

    @GetMapping("/serviceAreas")
    public AjaxResult provCityOfService(){
        List<AdministrationProvince> areas = provinceMapper.queryServiceAreas();
        List<ProvCityVO> provCityVOS = new ArrayList<>();
        if (areas.size() > 0) {
            Mapper mapper = DozerBeanMapperBuilder.buildDefault();
            areas.forEach(administrationProvince -> {
                ProvCityVO provCityVO = DozerBeanUtils.deepCopy(administrationProvince, ProvCityVO.class);
                provCityVOS.add(provCityVO);
            });
        }
        return AjaxResult.success(provCityVOS);
    }


}
