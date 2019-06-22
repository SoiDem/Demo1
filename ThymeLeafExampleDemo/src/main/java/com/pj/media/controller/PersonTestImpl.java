/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.controller;

import com.learning.model.Person;
import com.pj.media.util.MysqlHibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

@Repository
public class PersonTestImpl {

    public List<Person> getAll() {
        Session session = null;
        Transaction tx = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            String insertCm = "select id, name, age, create_Time as createTime,country_Name as countryName,img FROM person left join country_info on person.country = country_info.country ";
            SQLQuery query = session.createSQLQuery(insertCm);
            query.addScalar("id", LongType.INSTANCE);
            query.addScalar("name", StringType.INSTANCE);
            query.addScalar("age", IntegerType.INSTANCE);
            query.addScalar("createTime", TimestampType.INSTANCE);
            query.addScalar("countryName", StringType.INSTANCE);
            query.addScalar("img", StringType.INSTANCE);
            query.setResultTransformer(Transformers.aliasToBean(Person.class));
            List<Person> lst = query.list();
            tx.commit();
            return lst;
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
        return new ArrayList<>();
    }

    public void deletePerson(long personID) {
        Session session = null;
        Transaction tx = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Person p = new Person();
            p.setId(personID);
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

    public void addPerson(Person p) {
        Session session = null;
        Transaction tx = null;
        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(p);
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

    public Person getById(Long id) {
        /*
return dataMap.get(id);
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
    
    public void findPerson(String findname) {

        Transaction tx = null;
        Session session = null;

        try {
            session = (Session) MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);
            if (findname != null) {
                criteria.add(Restrictions.ilike("name", findname.trim(), MatchMode.ANYWHERE));
                criteria.add(Restrictions.eq("age", 18));
            }
            // moc toan bo du lieu db
            List<Person> lst = criteria.list();

            if (lst != null) {
                System.out.println("abc");
                for (Person p : lst) {

                    System.out.println("name: " + p.getName() + "-age:" + p.getAge());
                }

            }

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

    
}
