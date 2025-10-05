package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<User> listUsers() {
        final TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<User> getUserByCarModelAndSeries(String model, int series) {
        final String hql = "FROM User u WHERE u.car.model = :model AND u.car.series = :series";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getResultList();
    }
}
