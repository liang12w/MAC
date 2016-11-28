package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("BaseDao")
public class BaseDao {

    @Autowired
    private SessionFactory sessionFactory;
    List list;

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}

