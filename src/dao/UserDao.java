package dao;

import entities.UserInfo;
import entities.UserPassword;
import java.util.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao extends BaseDao {

	public static final String KEY_MD5 = "MD5";
	public static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	public boolean check(String username, String password) {
		String hql1 = "FROM UserInfo user WHERE user.usrName = ?";
		String hql2 = "FROM UserPassword u WHERE u.usrPwd = ? AND u.usrId = ?";
		List list = getSession().createQuery(hql1).setString(0, username).list();
		if (list.isEmpty() == true) {
			return false;
		}

		UserInfo user = (UserInfo) list.get(0);
		list = getSession().createQuery(hql2).setString(0, password).setInteger(1, user.getUsrId()).list();
		if (list.isEmpty() == true) {
			return false;
		}

		user.setLastLogin(new Date());
		getSession().save(user);
		return true;
	}

	public int checkSid(String sid) {
		String hql = "FROM UserInfo e WHERE e.securityId = ?";
		List list = getSession().createQuery(hql).setString(0, sid).list();
		if (list.isEmpty() == true) {
			return -1;
		} else {
			UserInfo user = (UserInfo) list.get(0);
			user.setLastLogin(new Date());
			getSession().save(user);
			return user.getUsrId();
		}

	}

	public boolean signUp(UserInfo user) {

		System.out.println("Saving user Messages !");
		System.out.println(user);
		Date now = new Date();
		user.setFirstLogin(now);
		try {
			user.setSecurityId(UserDao.genSid(user.getUsrName()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getSession().save(user);

		// getSession().flush();
		return true;
	}

	public boolean savePassword(UserInfo user, UserPassword u) {
		u.setUsrId(user.getUsrId());
		u.setUserInfo(user);
		getSession().save(u);
		getSession().flush();
		return true;

	}

	public UserInfo checkProfile(int id) {

		String hql = "FROM UserInfo e WHERE e.usrId = ?";
		List list = getSession().createQuery(hql).setLong(0, id).list();
		UserInfo user = (UserInfo) list.get(0);
		return user;
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String genSid(String userName) {
		byte[] user = userName.getBytes();
		MessageDigest md5 = null;

		try {
			md5 = MessageDigest.getInstance(KEY_MD5);
			md5.update(user);
			byte[] md = md5.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		// return md5.digest().toString();
	}

	public boolean isExist(String name) {

		String hql = "FROM  UserInfo user WHERE user.usrName = ?)";
		List list = getSession().createQuery(hql).setString(0, name).list();
		if (list.isEmpty() == true) {
			return false;
		}
		return true;
	}

	public void refreshTime(int id) {
		String hql1 = "FROM UserInfo user WHERE user.usrId = ?";
		List list = getSession().createQuery(hql1).setInteger(0, id).list();
		if (list.isEmpty() != true) {
			UserInfo tem = (UserInfo) list.get(0);
			tem.setLastLogin(new Date());
			getSession().save(tem);
		}

	}
}