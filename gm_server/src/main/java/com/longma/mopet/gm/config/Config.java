package com.longma.mopet.gm.config;

import com.longma.mopet.gm.util.ConfigUtil;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:17 2018/5/17
 * @Modified By:
 */
public class Config {
    public static String UPLOAD_TEM_PATH;
    public static String IMG_PATH;

    public Config() {
        UPLOAD_TEM_PATH = ConfigUtil.getConfigPath("temp");
        IMG_PATH = ConfigUtil.getConfigPath("static/img");
    }
}
