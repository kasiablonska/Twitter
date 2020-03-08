package pl.sda.twitter.services;

import pl.sda.twitter.exception.IncorrectLoginPasswordException;
import pl.sda.twitter.persistence.dao.UserDao;
import pl.sda.twitter.persistence.entities.TbUser;

public class UserService {
    private UserDao userDao= new UserDao();

    public TbUser getUser(
            String username
            ,String password
    ) throws IncorrectLoginPasswordException {
            final TbUser user = userDao.getUserByLogin(username);
            if(user==null || !user.getPassword().equals(password)){
                throw new IncorrectLoginPasswordException("Password or username does not match");
            }
            return user;
        }
    }

