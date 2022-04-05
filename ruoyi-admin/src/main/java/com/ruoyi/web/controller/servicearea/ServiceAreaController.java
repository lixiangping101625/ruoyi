package com.ruoyi.web.controller.servicearea;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.DozerBeanUtils;
import com.ruoyi.system.domain.AdministrationCity;
import com.ruoyi.system.domain.AdministrationProvince;
import com.ruoyi.system.domain.vo.ProvCityVO;
import com.ruoyi.system.mapper.AdministrationProvinceMapper;
import com.ruoyi.system.service.IAdministrationCityService;
import com.ruoyi.system.service.IAdministrationProvinceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年03月10日 19:16
 * @decription: 业务开通城市
 */
@RestController
@RequestMapping("/serviceArea")
public class ServiceAreaController {

    @Resource
    private AdministrationProvinceMapper provinceMapper;
    @Resource
    private IAdministrationProvinceService provinceService;
    @Resource
    private IAdministrationCityService cityService;

    /**
     * 将Android 高德地图sdk获取的定位信息转换为省市id
     * @param provinceName 省份名称
     * @param cityName 城市名称
     * @return 定位的省市开通，返回省、市id
     */
    @GetMapping("/convertPosition2Ids")
    public AjaxResult convertPosition(@RequestParam String provinceName,
                                      @RequestParam String cityName){

        if (StringUtils.isEmpty(provinceName) || StringUtils.isEmpty(cityName)) {
            return AjaxResult.error("省市名称为空~");
        }
        //1、根据provinceName查询省份是都开通
        AdministrationProvince queryDomain = new AdministrationProvince();
        queryDomain.setProvinceName(provinceName);
        queryDomain.setDataState(1);//开通
        List<AdministrationProvince> list = provinceService.selectAdministrationProvinceList(queryDomain);
        //省份未开通，直接返回
        if (list.size() == 0) {
            return AjaxResult.success("当前省份暂未开通~");
        }
        //2、根据cityName查询城市是否开通
        AdministrationProvince province = list.get(0);
        String provinceCode = province.getProvinceCode();

        AdministrationCity queryCity = new AdministrationCity();
        queryCity.setProvinceCode(provinceCode);
        queryCity.setDataState(1);//开通
        queryCity.setCityName(cityName);

        List<AdministrationCity> cityList = cityService.selectAdministrationCityList(queryCity);
        //城市未开通，直接返回
        if (cityList.size() == 0) {
            return AjaxResult.success("当前城市暂未开通~");
        }
        //3、城市开通，正常返回
        HashMap<String, Long> result = new HashMap<>();
        result.put("provinceId", province.getProvinceId());
        result.put("cityId", cityList.get(0).getCityId());
        return AjaxResult.success(result);
    }

    @GetMapping("/list")
    public AjaxResult provCityOfService(){
        List<AdministrationProvince> areas = provinceMapper.queryServiceAreas();
        List<ProvCityVO> provCityVOS = new ArrayList<>();
        if (areas.size() > 0) {
            areas.forEach(administrationProvince -> {
                ProvCityVO provCityVO = DozerBeanUtils.deepCopy(administrationProvince, ProvCityVO.class);
                provCityVOS.add(provCityVO);
            });
        }
        return AjaxResult.success(provCityVOS);
    }


}
