/**
 * 监听器
 */
package com.ma969.linstener;

/**
 * author:5299892172.qq.com
 */
import java.util.HashMap;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import com.ma969.beans.User;

/**
 * 
 * @author zhao
 *
 */
@WebListener
public class LoginLinstener implements HttpSessionAttributeListener {
	private static HashMap<String, HttpSession> map = new HashMap<String, HttpSession>();
	private static final String LOGINUSER = "loginuser";
	int count;

	public static HashMap<String, HttpSession> getMap() {
		return map;
	}

	public static void setMap(HashMap<String, HttpSession> map) {
		LoginLinstener.map = map;
	}

	/**
	 * Default constructor.
	 */
	public LoginLinstener() {

	}

	@Override
	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent se) {

		if (LOGINUSER.equals(se.getName())) {
			User user = (User) se.getValue();
			if (map.get(user.getUserName()) != null) {
				HttpSession session = map.get(user.getUserName());
				session.removeAttribute(user.getUserName());
				session.invalidate();
			}
			map.put(user.getUserName(), se.getSession());
		}
	}

	@Override
	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		if (session.getId() != null) {
			if (LOGINUSER.equals(event.getName())) { 
				map.remove(((User) event.getValue()).getUserName()); 
			}
		}
	}

	@Override
	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent se) {

	}

}
