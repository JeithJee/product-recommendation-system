package com.lyu.shopping.sysmanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyu.shopping.sysmanage.entity.Admin;
import com.lyu.shopping.sysmanage.service.AdminService;

/**
 * 类描述：处理管理员的请求
 * 类名称：com.lyu.shopping.sysmanage.controller.AdminController
 * @author 曲健磊
 * 2018年2月26日.下午7:20:23
 * @version V1.0
 */
@Controller
@RequestMapping(value="/sysmgr/admin")
public class AdminController {

	private Logger logger = Logger.getLogger(AdminController.class);
	
	/**
	 * 前台页面提示信息的属性名称
	 */
	private static final String FRONT_TIPS_ATTR = "message";
	
	/**
	 * 新增管理员成功的提示消息
	 */
	private static final String SAVE_ADMIN_SUCCESS = "新增管理员成功";
	
	/**
	 * 新增管理员失败的提示信息
	 */
	private static final String SAVE_ADMIN_FAILED = "新增管理员失败";
	
	/**
	 * 删除管理员成功的提示消息
	 */
	private static final String REMOVE_ADMIN_SUCCESS = "删除管理员成功";
	
	/**
	 * 删除管理员失败的提示消息
	 */
	private static final String REMOVE_ADMIN_FAILED = "删除管理员失败";
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * 跳转到管理员列表页面
	 * @return
	 */
	@RequestMapping(value="/gotoAdminList")
	public String gotoAdminList() {
		return "sysmanage/admin/adminList";
	}
	
	/**
	 * 跳转到管理员个人信息页面
	 * @return
	 */
	@RequestMapping(value="/gotoAdminInfo")
	public String gotoAdminInfo() {
		return "sysmanage/admin/adminInfo";
	}
	
	/**
	 * 查询管理员列表
	 * @return
	 */
	@RequestMapping(value="/listAdmin")
	public @ResponseBody List<Admin> listAdmin(String adminName, String date) {
		Admin admin = new Admin();
		admin.setAdminName(adminName);
		// 根据日期查询管理员
		
		List<Admin> adminList = this.adminService.listAdmin(admin);
		return adminList;
	}
	
	/**
	 * 新增管理员
	 * @return
	 */
	@RequestMapping(value="/saveAdmin")
	public @ResponseBody Map<String, Object> saveAdmin(@RequestBody Admin admin) {
		Map<String, Object> message = new HashMap<String, Object>();
		
		message.put(FRONT_TIPS_ATTR, SAVE_ADMIN_FAILED);
		if (admin != null) {
			boolean flag = this.adminService.saveAdmin(admin);
			if (flag) {
				message.put(FRONT_TIPS_ATTR, SAVE_ADMIN_SUCCESS);
			}
		}
		
		return message;
	}
	
	/**
	 * 删除（逻辑删除）管理员
	 * @return
	 */
	@RequestMapping(value="/removeAdmin")
	public @ResponseBody Map<String, Object> removeAdmin(Integer adminId) {
		Map<String, Object> message = new HashMap<String, Object>();
		message.put(FRONT_TIPS_ATTR, REMOVE_ADMIN_FAILED);
		
		if (adminId != null) {
			boolean flag = this.adminService.removeAdmin(adminId);
			if (flag) {
				message.put(FRONT_TIPS_ATTR, REMOVE_ADMIN_SUCCESS);
			}
		}
		
		return message;
	}
	
}