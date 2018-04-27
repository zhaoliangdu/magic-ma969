package com.ma969.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.google.gson.Gson;
import com.ma969.beans.User;
import com.ma969.service.SystemSetService;
import com.ma969.service.TestDataService;
import com.ma969.service.UserService;
import com.ma969.test.Auth;
import com.ma969.utils.Sha;
import me.jor.util.Help;

/**
 * 
 * @author 作者: ZhaoLiangdu
 * @time 创建时间：2017年11月9日上午9:49:40
 */
@Controller
 
public class UserController {
	@Autowired
	UserService service;
	@Autowired
	SystemSetService systemService;
	@Autowired
	TestDataService testDataService;
	
	private static final Log LOG = LogFactory.getLog(UserController.class);

	/**
	 * 用户登录
	 * 
	 * @param uname
	 * @param upwd
	 * @return
	 */
	@RequestMapping("userlogin")
	public RedirectView login(@RequestParam("username") String uname, @RequestParam("password") String upwd,
			HttpServletRequest request) {
		LOG.info("login-name:" + uname + "，time:" + LocalDateTime.now().toLocalTime().toString());
		HttpSession session = request.getSession();
		User user = service.findUserByName(uname);
		if (!Help.isEmpty(user)) { 
			if (uname.equals(user.getUserName()) && Sha.getResult(uname + upwd).equals(user.getPassword())) {
				String ipAddr = service.getIpAddr(request);
				session.setAttribute("ipAddr", ipAddr);
				session.setAttribute("loginuser", user);
				HashMap<String, Object> map = new HashMap<>(16);
				map.put("userId", user.getUserId());
				map.put("lastLoginTime", LocalDateTime.now().toString());
				System.err.println("登录成功");
				service.updateLastLoginTime(map);
				String logintime = user.getLastLoginTime() != null
						? user.getLastLoginTime().substring(0, user.getLastLoginTime().length() - 2)
						: LocalDateTime.now().toString().substring(0, LocalDateTime.now().toString().length() - 2);
				String createTime = service.getCreateTime(user.getUserId());
				session.setAttribute("createtime", createTime.substring(0, createTime.length() - 2));
				session.setAttribute("lastLoginTime", logintime);
				session.setAttribute("username", uname);
				session.setAttribute("authDesc", Auth.getAuther(user.getAuth()));
				session.setAttribute("auth", user.getAuth());
				session.setAttribute("uid", user.getUserId());

				List<Map<String, Object>> pointStatistics = testDataService.getPointStatistics();
				session.setAttribute("pointstatistics", pointStatistics);
				return new RedirectView("index");
			}
			session.setAttribute("loginmsg", "密码错误,请重试！");
		} else {
			session.setAttribute("loginmsg", "账号不存在或已禁用！");
		}
		return new RedirectView("login");
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param uname
	 * @return RedirectView
	 */
	@RequestMapping("userlogout")
	public RedirectView logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginuser");
		session.invalidate();
		return new RedirectView("login");
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("regist")
	@ResponseBody
	public ModelAndView regist(@RequestParam("username") String username, @RequestParam("auth") Integer auth,
			@RequestParam("password") String password, @RequestParam("name") String name,
			@RequestParam("userBirthday") String userBirthday, @RequestParam("userAddress") String userAddress,
			@RequestParam("userPhone") String userPhone, @RequestParam("userCompany") String userCompany,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		LOG.info("regist-" + username + "-time:" + LocalDateTime.now());
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		if (username != null && name != null && password != null && userBirthday != null && userAddress != null
				&& userPhone != null && userCompany != null) {
			User user = new User(auth, username, password, name, userBirthday, userAddress, userPhone, userCompany);
			if (service.findUserByName(username) == null) {
				if (service.userRegist(user) == 1) {
					systemService.addUserId(user.getUserId());
					System.err.println("use:" + user);
					session.setAttribute("loginmsg", "注册成功！");
				} else {
					session.setAttribute("loginmsg", "注册失败！");
				}
			} else {
				session.setAttribute("loginmsg", "账号已被注册！");
			}
		} else {
			session.setAttribute("loginmsg", "信息不能为空！");
		}
		return new ModelAndView("login");
	}

	@RequestMapping("user/adduserview")
	public ModelAndView addUserView() {
		return new ModelAndView("jsp/user/add-user");
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "user/adduser", method = RequestMethod.POST)
	public ModelAndView addUser(@RequestParam("username") String userName, @RequestParam("auth") Integer auth,
			@RequestParam("password") String password, @RequestParam("name") String name,
			@RequestParam("userBirthday") String userBirthday, @RequestParam("userAddress") String userAddress,
			@RequestParam("userPhone") String userPhone, @RequestParam("userCompany") String userCompany,
			@RequestParam("userauth") Integer uauth, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		LOG.info("adduser-" + userName + "-time:" + LocalDateTime.now());
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		if (userName != null && name != null && password != null && userBirthday != null && userAddress != null
				&& userPhone != null && userCompany != null) {
			if (service.findUserByName(userName) == null) {
				User user = new User(auth, userName, password, name, userBirthday, userAddress, userPhone, userCompany);
				if (service.userRegist(user) == 1) {
					systemService.addUserId(user.getUserId());
					session.setAttribute("addumsg", "注册成功！");
					session.setAttribute("isok", 1);
				} else {
					session.setAttribute("addumsg", "注册失败！");
				}
			} else {
				session.setAttribute("addumsg", "账号已被注册！");
			}
		} else {
			session.setAttribute("addumsg", "信息不能为空！");
		}
		return new ModelAndView("jsp/user/add-user");
	}

	/**
	 * 查询全部用户信息
	 * 
	 * @param username
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("user/usermanage")
	@ResponseBody
	public ModelAndView getUsers(HttpServletResponse response, HttpServletRequest request) throws ParseException {
		return new ModelAndView("jsp/user/user-ma").addObject("userlist", service.getUsers());
	}

	/**
	 * 黑名单
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("user/blacklist")
	@ResponseBody
	public void getBlackList(HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.println(new Gson().toJson(service.blackList()));
		writer.flush();
		writer.close();
	}

	/**
	 * 恢复用户
	 * 
	 * @param id
	 * @param name
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping("user/userrecovery")
	public ModelAndView userRecovery(@RequestParam("id") int id, HttpServletRequest request) {
		LOG.info("userrecovery-time:" + LocalDateTime.now());
		HttpSession session = request.getSession();
		session.removeAttribute("updumessage");

		service.userRecovery(id);
		if (systemService.resetSysSet(id) == 1) {
			session.setAttribute("updumessage", "恢复成功");
		} else {
			session.setAttribute("updumessage", "恢复失败");
		}
		return new ModelAndView("/jsp/user/user-ma").addObject("userlist", service.getUsers());
	}

	/**
	 * 返回首页
	 * 
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("index")
	public ModelAndView blackIndex(HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		if (session.getId() != null) {
			String username = (String) session.getAttribute("username");
			String authDesc = (String) session.getAttribute("authDesc");
			int uid = (int) session.getAttribute("uid");
			session.removeAttribute("updumessage");
			session.removeAttribute("dmsg");
			session.removeAttribute("addumsg");
			session.removeAttribute("sysmsg");
			if (!(Help.isEmpty(username) && Help.isEmpty(authDesc))) {
				return new ModelAndView("jsp/index").addObject("username", username).addObject("authDesc", authDesc)
						.addObject("uid", uid);
			}
		}
		return new ModelAndView("login");
	}

	/***
	 * 添加用户页面
	 */
	@RequestMapping("user/adduserView")
	public ModelAndView addUserView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String auth = "auth";
		if ((int) session.getAttribute(auth) >= 1) {
			session.setAttribute("ismodel", "false");
		} else {
			session.setAttribute("ismodel", "true");
		}
		return new ModelAndView("jsp/user/adduser");
	}

	/**
	 * 修改用户信息
	 * 
	 * @param use
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("user/updateUser")
	public ModelAndView updateUser(@RequestParam Map<String, String> use, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		HttpSession session = request.getSession();
		session.removeAttribute("updumessage");

		String id = "id", usernameStr = "username", unameStr = "uname";
		System.err.println("useid:" + use.get("id") + (use.get("id") == ""));
		if (use.get(id) != null && use.get(usernameStr) != null && use.get(unameStr) != null
				&& use.get("userBirthday") != null && use.get("userAddress") != null && use.get("userPhone") != null
				&& use.get("userCompany") != null) {
			int uid = Integer.parseInt(use.get("id"));
			String username = use.get("username");
			String uname = use.get("uname");
			String birthdayStr = use.get("userBirthday");
			String address = use.get("userAddress");
			String phone = use.get("userPhone");
			String company = use.get("userCompany");
			User user = new User(uid, username, uname, birthdayStr, address, phone, company);
			if (service.updateUser(user) == 1) {
				session.setAttribute("updumessage", "修改成功!");
			} else {
				session.setAttribute("updumessage", "修改失败!");
			}
		} else {
			session.setAttribute("updumessage", "信息不能为空!");
		}
		return new ModelAndView("/jsp/user/edit-user").addObject("userlist", service.getUsers());
	}

	/**
	 * 根据id查找用户
	 * 
	 * @param id
	 * @param name
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("user/findUserById")

	public ModelAndView findById(@RequestParam("uid") int id, HttpServletRequest request, HttpServletResponse response)
			throws ParseException, IOException {
		User findById = service.findById(id);
		if (findById != null) {
			return new ModelAndView("jsp/user/edit-user").addObject("user", findById);
		} else {
			return new ModelAndView("jsp/user/edit-user");
		}
	}

	/**
	 * 删除用户
	 * 
	 * @param uid
	 * @param name
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("user/deleteUser")
	public ModelAndView deleteUser(@RequestParam("id") int uid, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("updumessage");
		if (service.findById(uid).getUstate() == 1) {
			if (service.deleteUser(uid) == 1 && systemService.delSysSet(uid) == 1) {
				session.setAttribute("updumessage", "删除成功！");
			}
		} else {
			session.setAttribute("updumessage", "删除失败！");
		}
		return new ModelAndView("jsp/user/user-ma").addObject("userlist", service.getUsers());
	}

	/**
	 * 权限管理
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("user/jurisdiction")
	public ModelAndView jurisdictionMa(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String auth = "auth";
		int auth2 = 2;
		if ((int) session.getAttribute(auth) == auth2) {
			session.setAttribute("ismodel", "false");
		} else {
			session.setAttribute("ismodel", "true");
		}
		return new ModelAndView("jsp/user/jurisdiction-ma").addObject("users", service.getUsers());
	}

	/**
	 * 查找权限
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("user/findauth")
	@ResponseBody
	public void findAuth(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(new Gson().toJson(service.findById(id)));
		writer.flush();
		writer.close();
	}

	@RequestMapping("user/updjru")
	public ModelAndView updjru(@RequestParam("uid") int id, @RequestParam("username") String username) {
		return new ModelAndView("jsp/user/upd-jru").addObject("uid", id).addObject("usernae", username);
	}

	/**
	 * 更新权限
	 * 
	 * @param uid
	 * @param uauth
	 * @param request
	 * @return
	 */
	@RequestMapping("user/updateauth")
	public ModelAndView updateAuth(@RequestParam("userId") int uid, @RequestParam("uauth") int uauth,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("updateauthm");

		User user = service.findById(uid);
		user.setAuth(uauth);
		if (service.updateUser(user) == 1) {
			session.setAttribute("updateauthm", "修改成功");
		} else {
			session.setAttribute("updateauthm", "修改失败");
		}
		return new ModelAndView("jsp/user/jurisdiction-ma").addObject("users", service.getUsers());
	}

	@RequestMapping("user/updpwd")
	public ModelAndView updpwd(@RequestParam("uid") int uid) {
		return new ModelAndView("jsp/user/upd-pwd").addObject("uid", uid);
	}

	/**
	 * 修改密码
	 * 
	 * @param uid
	 * @param oldpwd
	 * @param newpwd
	 * @param newpwd1
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping("user/updatepwd")
	public ModelAndView updatePwd(@RequestParam("uid") int uid, @RequestParam("oldpwd") String oldpwd,
			@RequestParam("newpwd") String newpwd, @RequestParam("newpwd1") String newpwd1,
			HttpServletRequest request) {
		User user = service.findById(uid);
		System.err.println("update PWD ?" + user.getUserName());
		HttpSession session = request.getSession();
		session.removeAttribute("updumessage");
		String usernameStr = "username";

		if (user != null) {
			if (newpwd.equals(newpwd1)) {
				if (user.getPassword().equals(Sha.getResult(user.getUserName() + oldpwd))) {
					user.setPassword(Sha.getResult(user.getUserName() + newpwd));
					if (service.updatePwd(user) == 1) {
						session.setAttribute("updumessage", "修改成功");
						if (session.getAttribute(usernameStr).equals(user.getUserName())) {
							session.setAttribute("updumessage", "修改成功,请重新登录");
							session.setMaxInactiveInterval(2);
						}
					} else {
						session.setAttribute("updumessage", "修改失败");
					}
				} else {
					session.setAttribute("updumessage", "密码错误！");
				}
			} else {
				session.setAttribute("updumessage", "两次密码不一致！");
			}
		} else {
			session.setAttribute("updumessage", "用户不存在！");
		}
		return new ModelAndView("/jsp/user/user-ma").addObject("userlist", service.getUsers());
	}
}
