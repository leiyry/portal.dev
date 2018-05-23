/**
 * <h3>标题 : potal统一门户-menu_privilege </h3>
 * <h3>描述 : menu_privilege服务实现类</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 * 
 * <p>
 * @author 你自己的姓名 mazong@seaboxdata.com
 * @version <b>v1.0.0</b>
 *          
 * <b>修改历史:</b>
 * -------------------------------------------
 * 修改人 修改日期 修改描述
 * -------------------------------------------
 *          
 *          
 * </p>
 */
package com.quick.portal.menuPrivilege;

import com.quick.core.base.SysBaseService;
import com.quick.core.base.ISysBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.quick.core.base.model.DataStore;
import com.quick.core.util.common.DateTime;

/**
 * menu_privilege服务实现类
 */
 @Transactional
 @Service("menuPrivilegeService")
public class MenuPrivilegeServiceImpl extends SysBaseService<MenuPrivilegeDO> implements IMenuPrivilegeService {
    
    /**
     * 构造函数
     */
    public MenuPrivilegeServiceImpl() {
        BaseTable = "menu_privilege";
        BaseComment = "menu_privilege";
        PrimaryKey = "pri_id";
    }
    
    @Autowired
    private IMenuPrivilegeDao dao;
    
    @Override
    public ISysBaseDao getDao(){
        return dao;
    }
    
    /**
     * 保存业务
     * @return 
     */
    @Override
    public DataStore save(MenuPrivilegeDO entity) {
        //如果编号为空,新增实体对象,否则更新实体对象
        Integer val = entity.getPri_id();
        int c = 0;
        if(val == null || val == 0) {
            entity.setCre_time( DateTime.Now().getTime() );  //新增时间
			entity.setUpd_time( DateTime.Now().getTime() );  //修改时间

            c = dao.insert(entity);
        }else {
            entity.setUpd_time( DateTime.Now().getTime() );  //修改时间

            c = dao.update(entity);
        }
        if(c == 0)
            return ActionMsg.setError("操作失败");
        ActionMsg.setValue(entity);
        return ActionMsg.setOk("操作成功");
    }
    
    /**
     * 删除业务
     * @param sysid
     * @return 
     */
    @Override
    public DataStore delete(String sysid) {
        dao.delete(sysid);
        return ActionMsg.setOk("操作成功");
    }
}