package com.api.test.common.util;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname RoleMap * @Description TODO * @Date 2019-10-22 11:07 * @Created by fengqian
 */
public class RoleMap {
    public static final Map<String,String> roleMap = new HashMap<String, String>(){
        {
            put("渠道经理","channelManager");
            put("渠道区域总监","channelAreaChief");
            put("渠道运营","channelOperate");
            put("城市策划","cityPlaningManager");
            put("BDM","agentSellerManager");
            put("CM","agentSellerManager");
            put("财务","affairsManager");
            put("BD","agentSeller");
            put("财务经理","affairsManager");
        }
    };

}
