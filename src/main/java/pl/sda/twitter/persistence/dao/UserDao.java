package pl.sda.twitter.persistence.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.sda.twitter.exception.IncorrectLoginPasswordException;
import pl.sda.twitter.persistence.HibernateUtil;
import pl.sda.twitter.persistence.entities.TbUser;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.io.Serializable;

public class UserDao implements Serializable {
    public TbUser getUserByLogin(String login) throws IncorrectLoginPasswordException {
        try(final Session session=HibernateUtil
                .getSESSION_FACTORY()
                .openSession()){
            final Query q= session.createQuery("select e from "
                    + TbUser.class.getName()+ " e where e.login = :username");
            q.setParameter("username", login);
            session.beginTransaction();
            q.getSingleResult();
            final TbUser singleResult = (TbUser) q.getSingleResult();
            return singleResult;

        }catch (NoResultException | NonUniqueResultException e){
            throw new IncorrectLoginPasswordException(e.getMessage());
        }
    }
}
