/**
 * <h3>标题 : Quick通用系统框架 </h3>
 * <h3>描述 : 应用中的相关配置信息都放在此</h3>
 * <h3>日期 : 2014-09-22</h3>
 * <h3>版权 : Copyright (C) 海口鑫网计算机网络有限公司</h3>
 *
 * <p>
 * @author admin admin@xinwing.com.cn
 * @version <b>v1.0.0</b>
 *
 * <b>修改历史:</b>
 * ------------------------------------------- 修改人 修改日期 修改描述
 * -------------------------------------------
 *
 *
 * </p>
 */
package com.quick.core.base;


import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.common.QCookie;
import com.quick.portal.sysUser.SysUserDO;
import com.quick.portal.web.login.WebLoginUser;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public abstract class SysWebController extends SysBaseController<DataStore> {
    private final Logger logger = Logger.getLogger(getClass());
    public final static int ROLEID_ADMIN = 1;

    /**
     * 当Action方法执行后被调用
     */
    @Override
    public void actionAfter(HttpServletRequest request,
                            HttpServletResponse response, Object handler,
                            ModelAndView modelAndView) {
        if (modelAndView == null)
            return;
        String viewName = modelAndView.getViewName();

        if (QCommon.isNullOrEmpty(viewName)) {
            modelAndView.clear();
        } else if (viewName.startsWith("redirect:")) {
            // modelAndView.addAllObjects(_ASSIGN_);
        } else {

            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + request.getContextPath();
            Map<String, Object> G = new HashMap<String, Object>();
            G.put("host", url);

            modelAndView.addObject("G", G);

        }
    }

    public void config(){}

    @Override
    public DataStore deleteAction(){
        return null;
    }

    @Override
    public Object getData(String json) {
        return null;
    }


}
