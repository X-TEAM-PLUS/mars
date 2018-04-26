package org.xteam.plus.mars.manager;

import java.util.HashMap;

public interface AreaManager {

    /**
     * 获取地市信息展示对象
     *
     * @return HashMap<province_code,List<String>>
     *                <province_name,List<String>>
     *                <省_proinvce_code,List<String>> 例如: <3_proinvce_code,[37,38,39,40...]
     *                <省_proinvce_name,List<String>> 例如: <3_proinvce_name,[蚌埠,巢湖,池州,滁州]
     *                <市_city_code,List<String>> 例如: <40_city_code,[425,426,427,428,429,430,431,432]
     *                <市_city_code,List<String>> 例如: <40_city_name,[琅琊区,南谯区,天长市,明光市,来安县,全椒县,定远县,凤阳县]
     */
    public HashMap getAreaAllJson();
}
