package com.ruoyi.web.controller.servicearea;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Lixiangping
 * @createTime 2022年03月10日 19:16
 * @decription: 业务开通城市
 */
@RestController
public class ServiceAreaController {

    @GetMapping("/serviceAreas")
    public AjaxResult provCityOfService(){
        return AjaxResult.success("ceshi");
    }


}
