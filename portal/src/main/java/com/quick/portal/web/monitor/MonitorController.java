/**
 * <h3>标题 : portal统一门户-管理驾驶仓 </h3>
 * <h3>描述 : 应用中的相关配置信息都放在此</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 *
 * <p>
 * @author admin mazong@seaboxdata.com
 * @version <b>v1.0.0</b>
 *
 * <b>修改历史:</b>
 * -------------------------------------------
 * 修改人  修改日期   修改描述
 * -------------------------------------------
 *
 *
 * </p>
 */
package com.quick.portal.web.monitor;

import com.quick.core.base.SysWebController;
import com.quick.core.util.common.JsonUtil;
import com.quick.core.util.common.QCommon;
import com.quick.core.util.type.TypeUtil;
import com.quick.portal.application.ApplicationDO;
import com.quick.portal.application.IApplicationService;
import com.quick.portal.page.IPageService;
import com.quick.portal.section.ISectionService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门户请求类
 *
 * @author Administrator
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/monitor")
public class MonitorController extends SysWebController {

    @Resource(name = "applicationService")
    private IApplicationService applicationService;

    @Resource(name = "pageService")
    private IPageService pageService;

    @Resource(name = "sectionService")
    private ISectionService sectionService;

    /**
     * 管理驾驶仓(仪表盘)
     * @param model
     * @return
     */
    @RequestMapping
    public String index(Model model) {
        Integer app_id = rint("t", 0);
        Integer page_id = rint("p", 0);
        List<Map<String, Object>> plist = getPage(app_id);
        if(page_id == 0 && plist != null && plist.size() > 0){
            page_id = (Integer)TypeUtil.parse(Integer.class, plist.get(0).get("page_id"));
        }
        ApplicationDO app = applicationService.selectObj(app_id.toString());
        Object layout = getLayout(page_id);

        model.addAttribute("app", app);
        model.addAttribute("pageList", plist);
        model.addAttribute("pageJson", JsonUtil.serialize(plist));
        model.addAttribute("layout", layout);
        return view();
    }

    /**
     * 查询页面配置信息
     * @return
     */
    @PostMapping
    @ResponseBody
    public Object getLayout(Integer p){
        String layout = "[{id:0, no:1,x: 0, y: 0, width: 12, height: 6, metric:[]}]";
        if(p != null && p > 0){
            String res = sectionService.selectLayoutJson(p);
            if(!QCommon.isNullOrEmpty(res))
                layout = res;
        }
        return layout;
    }

    /**
     * 查询应用所有页面
     * @return
     */
    @PostMapping
    @ResponseBody
    public List<Map<String, Object>> getPage(Integer t){
        List<Map<String, Object>> ls = new ArrayList<>();
        if(t != null && t > 0){
            Map<String, Object> p = new HashMap<>();
            p.put("app_id", t.toString());
            ls = pageService.select(p);
        }
        return ls;
    }
}