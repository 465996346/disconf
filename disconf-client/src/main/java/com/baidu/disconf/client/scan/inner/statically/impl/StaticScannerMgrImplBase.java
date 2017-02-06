package com.baidu.disconf.client.scan.inner.statically.impl;

import org.apache.commons.lang.StringUtils;

import com.baidu.disconf.client.common.model.DisConfCommonModel;
import com.baidu.disconf.client.config.DisClientConfig;

/**
 * @author liaoqiqi
 * @version 2014-9-9
 */
public class StaticScannerMgrImplBase {

    /**
     * env/version 默认是应用整合设置的，但用户可以在配置中更改它
     */
    protected static DisConfCommonModel makeDisConfCommonModel(String app, String env, String version) {

        DisConfCommonModel disConfCommonModel = new DisConfCommonModel();

        // app
        if (!app.isEmpty()) {
            disConfCommonModel.setApp(app);
        } else {
            disConfCommonModel.setApp(DisClientConfig.getInstance().APP);
        }

        // env
        if (!env.isEmpty()) {
            disConfCommonModel.setEnv(env);
        } else {
            disConfCommonModel.setEnv(DisClientConfig.getInstance().ENV);
        }

        // version
        if (!version.isEmpty()) {
            disConfCommonModel.setVersion(version);
        } else {
            disConfCommonModel.setVersion(DisClientConfig.getInstance().VERSION);
        }

        return disConfCommonModel;
    }

    /**
     * 通过配置的通用app通配符匹配该配置文件是否是通用配置如果是通用配置则返回通用配置信息
     * @param fileName
     * @return
     * @author 张鹏
     */
    protected static DisConfCommonModel makeDisConfCommonModel(String fileName) {
    	if (StringUtils.isBlank(fileName)) {
    		return makeDisConfCommonModel("", "", "");
    	}
    	if (fileName.contains(DisClientConfig.getInstance().COMMON_APP_LOCATION_MATCHES)) {
    		return makeDisConfCommonModel(DisClientConfig.getInstance().COMMON_APP, DisClientConfig.getInstance().COMMON_APP_ENV, DisClientConfig.getInstance().COMMON_APP_VERSION);
    	}
    	return makeDisConfCommonModel("", "", "");
    }
}
