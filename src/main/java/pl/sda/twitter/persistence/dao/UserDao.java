package pl.sda.twitter.persistence.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.sda.twitter.exception.IncorrectLoginPasswordException;
import pl.sda.twitter.persistence.HibernateUtil;
import pl.sda.twitter.persistence.entities.TbUser;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;

public class UserDao implements Serializable {
    public TbUser getUserByLogin(String login) throws IncorrectLoginPasswordException {

//        try(final Session session=HibernateUtil
//                .getSESSION_FACTORY()
//                .openSession()){
//            final Query q= session.createQuery("select e from "
//                    + TbUser.class.getName()+ " e where e.login = :username");
//            q.setParameter("username", login);
//            session.beginTransaction();
//            q.getSingleResult();
//            final TbUser singleResult = (TbUser) q.getSingleResult();
//            return singleResult;
//
//        }catch (NoResultException | NonUniqueResultException e){
//            throw new IncorrectLoginPasswordException(e.getMessage());
//        }
        // lub    ->
        //          |
        //         V

        try(final Session session=HibernateUtil
                .getSESSION_FACTORY()
                .openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TbUser> criteriaQuery =builder.createQuery(TbUser.class);
            final Root<TbUser> root=criteriaQuery.from(TbUser.class);
            criteriaQuery
                    .select(root)
                    .where(builder.equal(root.get("login"), login));
            criteriaQuery.select(root);
            final Query<TbUser> query=session.createQuery(criteriaQuery);
            return query.getSingleResult();
        }catch (NoResultException | NonUniqueResultException e){
            throw new IncorrectLoginPasswordException(e.getMessage());
        }
    }
}
