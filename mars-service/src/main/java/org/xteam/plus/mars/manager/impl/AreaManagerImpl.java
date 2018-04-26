package org.xteam.plus.mars.manager.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.xteam.plus.mars.common.Logging;
import org.xteam.plus.mars.dao.AreaDao;
import org.xteam.plus.mars.domain.Area;
import org.xteam.plus.mars.manager.AreaManager;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class AreaManagerImpl extends Logging implements AreaManager {

    @Resource
    private AreaDao areaDao;

    @Override
    public HashMap getAreaAllJson() {
        HashMap returnValue = Maps.newHashMap();
        List<Area> areaList = areaDao.getAreaAllJson();
        for (Area area : areaList) {
            switch (area.getAreaType()) {
                case 1:
                    List<String> provinceCode = Lists.newArrayList();
                    List<String> provinceName = Lists.newArrayList();
                    pushValue(provinceCode, provinceName, area);
                    returnValue.put("province_code", provinceCode);
                    returnValue.put("province_name", provinceName);
                    break;
                case 2:
                    List<String> cityCode = Lists.newArrayList();
                    List<String> cityName = Lists.newArrayList();
                    pushValue(cityCode, cityName, area);
                    returnValue.put("proinvce_code_" + area.getParentCode(), cityCode);
                    returnValue.put("proinvce_name_" + area.getParentCode(), cityName);
                    break;
                case 3:
                    List<String> areaCode = Lists.newArrayList();
                    List<String> areaName = Lists.newArrayList();
                    pushValue(areaCode, areaName, area);
                    returnValue.put("city_code_" + area.getParentCode(), areaCode);
                    returnValue.put("city_name_" + area.getParentCode(), areaName);
                    break;
            }
        }

        return returnValue;
    }

    private void pushValue(List codeList, List nameList, Area area) {
        String codeValue = area.getCodeValue();
        String codeName = area.getCodeName();
        String[] codes = codeValue.split(",");
        String[] names = codeName.split(",");
        int index = 0;
        for (String code : codes) {
            codeList.add(code);
            nameList.add(names[index]);
            index++;
        }
    }
}
