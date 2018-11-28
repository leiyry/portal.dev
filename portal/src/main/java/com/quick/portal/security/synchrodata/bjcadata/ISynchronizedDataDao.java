/**
 * <h3>标题 : potal统一门户-metric_privilege </h3>
 * <h3>描述 : metric_privilege数据访问接口</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 北京东方金信科技有限公司</h3>
 * 
 * <p>
 * @author 你自己的姓名 cxh@seaboxdata.com
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
package com.quick.portal.security.synchrodata.bjcadata;

import java.util.List;
import java.util.Map;

import com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.DepartmentInformation;
import com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.PersonInformation;
import com.quick.portal.security.synchrodata.bjcadata.uums.client.bean.RoleInformation;
import com.quick.core.base.ISysBaseDao;

/**
 * metric_privilege数据访问接口
 */
public interface ISynchronizedDataDao<DepartmentInformation> extends ISysBaseDao<DepartmentInformation> {
	
    //MERGE角色表数据
    public void mergeRoleData();
    //删除角色临时表数据
    public boolean removeTmpRoleData();
    //插入角色临时表数据
    public int insertTmpRoleData(RoleInformation role);

    /*
     * 部门数据同步开始
     */
	//删除部门编号删部门表数据
	public int removeDeptDataByDeptCode(String deptCode);
	//通过部门编号查询数据是否重复
	public int isExistDeptGlobalIDByDepGlogbalID(String depGlogbalID);
	//新增部门表数据
	public int insertDeptData(DepartmentInformation department);
	//修改部门表数据
	public int updateDeptDataByDeptCode(DepartmentInformation department);
	//删除公服系统所有机构数据
	public void removeAllDeptData();
	//公服标识查询部门上级编号数据
	public List<Map<String, Object>> searchFullDeptInfo(String depID);
	
	//修改部门上级编号
	public void updateSuperDepId(Map<String,Object> paramMap);
	

	//删除用户表数据
	public int removePersonDataByID(String uid);
	//通过用户编号查询数据是否重复
	public int isExistUserGlobalIDByUserGlogbalID(String userGlogbalID);
	//修改用户表数据
	public int updatePersonData(PersonInformation person);
	//新增用户数据
	public int insertPersonData(PersonInformation person);
	//MERGE用户表数据
	public void mergePersonData();

	//删除用户与部门关系临时表数据
	public void removeTmpPersonDeptRelaData();
	//新增用户与部门关系临时表数据
	public void insertPersonDeptRelaData(Map<String,Object> paramMap);
	//MERGE用户与部门关系临时表数据
	public void mergePersonDeptRelaData();
	
	public void saveDeptData(Map<String,Object> paramMap);
	
	/*
	 * 通过用户编号删除用户与部门关系
	 */
	public boolean removePersonDeptRelaDataByUserID(String userID);
	/*
	 * 通过用户编号查询用户ID
	 */
	public List<Map<String, Object>> searchPersonByGlobalID(String userGlobalID);
	/*
	 * 通过部门编号查询部门ID
	 */
	public List<Map<String, Object>> searchDeptByGlobalID(String deptGlobalID);
	

	/*
	 * 通过应用编号获取该应用系统下所有的用户应用权限数据接口，返回报文数据
	 */
	public List<Map<String, Object>> getUsersDataByUserID(Map<String, Object> paramMap);
	
	/*
	 * 通过应用编号获取该应用系统下所有的用户应用权限数据接口，返回报文数据
	 */
	public List<Map<String, Object>> getUserBatchDataByAppName(Map<String, Object> paramMap);


	/**
	 * 通过用户编号查询用户岗位数据是否重复
	 * @param userDuty
	 * @return
	 */
	public int isExistPersonJobDataInfoByUserID(int userDuty);

	/**
	 * 新增用户岗位
	 * @param userDuty
	 * @return
	 */
	public void insertPersonJobData(String userDuty);


    /**
     * 通过用户编号查询数据是否重复
     * @param
     * @return
     */
    public int isExistUserDeptByParm(Map<String, Object> paramMap);


    /**
     * 通过用户编号查询用户岗位数据是否重复
     * @param userGlobalID
     * @return
     */
    public int isExistPersonRoleDataInfoByUserID(String userGlobalID);




    /**
     * 新增用户角色关系
     * @param paramMap
     * @return
     */
    public void insertPersonRoleData(Map<String, Object> paramMap);



	public int isExistRolePrivilegeDataInfo(int globalID);



	public void insertRolePrivilegeDataInfo(Map<String, Object> paramMap);



	/*
	 * 通过用户帐号获取机构数据接口，返回报文数据
	 */
	 public List<Map<String, Object>>  getOrgDataByUserID(String userID);

	/*
	 * 批量获取机构数据接口，返回报文数据
	 */
	public List<Map<String, Object>> getOrgBatchData();
}