package com.qigan.qiganshop.controller.backstage;

import com.qigan.qiganshop.service.XltcLocationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * create by qh
 */
@RestController
@RequestMapping("/locations")
public class XltcLocationController {

    @Resource
    XltcLocationService locationService;

    /***
     * 查询省市区
     * @return
     */
    @RequestMapping("/getLocationsWithInCountry.do")
    public Map<String,Object> getLocationsWithInCountry(){
        try {
            return locationService.getLocationsWithInCountry();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
