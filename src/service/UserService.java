package service;

import dao.UserDao;
import entities.UserInfo;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author a1611
 */
@Service
@Transactional
public class UserService {
    @Autowired
    public UserDao userDao;
    
    public boolean check(User user){
        return userDao.check(user);
    }
}

