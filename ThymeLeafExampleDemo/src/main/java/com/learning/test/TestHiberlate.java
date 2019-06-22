/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.test;

import com.learning.model.Person;
import com.pj.media.util.MysqlHibernateUtil;
import java.util.Date;
import java.util.List;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;

/**
 *
 * @author DELL
 */
public class TestHiberlate {

    public void addPerson() {

        Transaction tx = null;
        Session session = null;

        try {
            session = (Session) MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Person p = new Person();
            p.setName("tu");
            p.setAge(30);
            p.setCountry("hn");
            p.setCreateTime(new Date());
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

    public void updatePerson() {

        Transaction tx = null;
        Session session = null;

        try {
            session = (Session) MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Person p = new Person();
            p.setId(1l);
            p.setName("tu");
            p.setAge(20);
            p.setCountry("hanam");
            p.setCreateTime(new Date());
            session.update(p);
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

    public void deletePerson(Long id) {

        Transaction tx = null;
        Session session = null;

        try {
            session = (Session) MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Person p = new Person();
            p.setId(id);
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

    public void findPerson2(String findname) {

        Transaction tx = null;
        Session session = null;

        try {
            session = (Session) MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);
            if (findname != null) {
                criteria.add(Restrictions.ilike("name", findname.trim(), MatchMode.ANYWHERE));
                criteria.add(Restrictions.gt("age", 18));
                criteria.add(Restrictions.le("age", 20));
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

    public void findQueryOr() {

        Transaction tx = null;
        Session session = null;

        try {
            session = (Session) MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);

            criteria.add(Restrictions.or(Restrictions.eq("name", "cong"), Restrictions.eq("name", "son")));

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

    public void findQueryOrAnd() {

        Transaction tx = null;
        Session session = null;

        try {
            session = (Session) MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);

            criteria.add(Restrictions.or(Restrictions.ilike("name", "T", MatchMode.ANYWHERE),
                     Restrictions.ilike("name", "C", MatchMode.ANYWHERE))
            );
            criteria.add(Restrictions.ge("age", 19));

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

    public Integer conut() {

        Transaction tx = null;
        Session session = null;
        int count = 0;
        try {
            session = (Session) MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);

            criteria.add(Restrictions.ge("age", 19));
            criteria.setProjection(Projections.rowCount());
            Object c = criteria.uniqueResult();
            count = c == null ? 0 : ((Long) c).intValue();
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
        return count;
    }

    
      public void paging() {

        Transaction tx = null;
        Session session = null;
    
        try {
            session = (Session) MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Person.class);

            criteria.setFirstResult(0);
            criteria.setMaxResults(1);
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

    public static void main(String[] args) {
        TestHiberlate t = new TestHiberlate();
        t.paging();

    }

}




  //luon luon dung cout(1) k nen dung cout(*)
