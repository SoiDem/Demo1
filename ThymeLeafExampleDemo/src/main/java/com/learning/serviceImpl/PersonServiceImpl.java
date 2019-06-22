/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.serviceImpl;

import com.learning.service.PersonService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import com.learning.model.Person;
import com.pj.media.util.MysqlHibernateUtil;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Admin
 */
@Repository
public class PersonServiceImpl implements PersonService {

    /*
    Integer ids = 0;
    HashMap<Integer, Person> dataMap = new HashMap<>();
     */
    public PersonServiceImpl() {
        /*
        Person p = new Person(ids++, "Trần văn A", 1, 10, "Cầu Giấy", "ĐN");
        dataMap.put(p.getId(), p);

        p = new Person(ids++, "Nguyễn văn B" , 0, 11, "Ba Đình", "HCM");
        dataMap.put(p.getId(), p);

        p = new Person(ids++, "Lê Thành Long",  1, 15, "Tây Hồ", "HN");
        dataMap.put(p.getId(), p);
         */
    }

    @Override
    public List<Person> getData(String searchName, Integer searchSex, String searchCountry, Date startTime, Date endTime, int pageIndex, int pageSize) {
        /*
        List<Person> list = new ArrayList<>();
        list.addAll(dataMap.values());
        return list;
         */
        Session session = null;
        Transaction tx = null;
        List<Person> lst = new ArrayList<>();

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);

            if (searchName != null && !searchName.trim().isEmpty()) {
                criteria.add(Restrictions.ilike("name", searchName.trim(), MatchMode.ANYWHERE));
            }

            if (searchSex != null) {
                criteria.add(Restrictions.eq("sex", searchSex));
            }

            if (searchCountry != null && !searchCountry.trim().isEmpty()) {
                criteria.add(Restrictions.eq("country", searchCountry));
            }

            if (startTime != null) {
                criteria.add(Restrictions.ge("createTime", startTime));
            }

            if (endTime != null) {
                criteria.add(Restrictions.le("createTime", endTime));
            }

            criteria.setFirstResult((pageIndex - 1) * pageSize);
            criteria.setMaxResults(pageSize);

            lst = criteria.list();

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return lst;
    }

    @Override
    public void addData(Person p) {
        /*
        if (p.getId() == null) {
            p.setId(ids++);
        }

        dataMap.put(p.getId(), p);
         */

        Session session = null;
        Transaction tx = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(p);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void delete(Person p) {

        /*
        if (dataMap.containsKey(id)) {
            dataMap.remove(id);
        }
         */
        Session session = null;
        Transaction tx = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(p);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Person getById(Long id) {

        /*
        return  dataMap.get(id);
         */
        Session session = null;
        Transaction tx = null;
        Person o = null;

        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);
            criteria.add(Restrictions.eq("id", id));
            o = (Person) criteria.uniqueResult();

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return o;
    }

    @Override
    public Integer count(String searchName, Integer searchSex, String searchCountry, Date startTime, Date endTime) {
        Session session = null;
        Transaction tx = null;
        int count = 0;
        try {

            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);

            if (searchName != null && !searchName.trim().isEmpty()) {
                criteria.add(Restrictions.ilike("name", searchName.trim(), MatchMode.ANYWHERE));
            }

            if (searchSex != null) {
                criteria.add(Restrictions.eq("sex", searchSex));
            }

            if (searchCountry != null && !searchCountry.trim().isEmpty()) {
                criteria.add(Restrictions.eq("country", searchCountry));
            }

            if (startTime != null) {
                criteria.add(Restrictions.ge("createTime", startTime));
            }

            if (endTime != null) {
                criteria.add(Restrictions.le("createTime", endTime));
            }

            criteria.setProjection(Projections.rowCount());
            Object c = criteria.uniqueResult();
            count = c == null ? 0 : ((Long) c).intValue();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return count;
    }

}
