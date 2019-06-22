/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.model;

import com.pj.media.util.MysqlHibernateUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author DELL
 */
public class Test {

    public static ShopT3h getShow;

    public static ShopT3h timKiem(long id) {
        Session session = null;
        Transaction tx = null;

        try {
            session = MysqlHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(ShopT3h.class);
            criteria.add(Restrictions.eq("id", id));
            criteria.setMaxResults(1);
            List<ShopT3h> shops = criteria.list();
            if (shops.isEmpty()) {
                System.out.println("k tim thay");
                return null;
            }
            ShopT3h shop = shops.get(0);
            Set<StaffT3h> st = shop.getStaffs();
            for (StaffT3h staffT3h : st) {
                System.out.println(staffT3h.getStaffName());

            }

            return shop;
//            Set<StaffT3h> staffs = shop.getStaffs();
//            for (StaffT3h staff : staffs) {
//                System.out.println(staff.getStaffCode() + "---" + staff.getStaffName());
//            }
            //session.save(shop);
            // tx.commit();
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
        return null;

    }

    public static void main(String[] args) {

        ShopT3h s = timKiem(9L);
//select this_.ID as ID1_4_0_, this_.SHOP_CODE as SHOP_COD2_4_0_, this_.SHOP_NAME as SHOP_NAM3_4_0_ from Shop this_ where this_.ID=? limit ?
//select this_.ID as ID1_4_1_, this_.SHOP_CODE as SHOP_COD2_4_1_, this_.SHOP_NAME as SHOP_NAM3_4_1_, staffs2_.SHOP_ID as SHOP_ID5_5_3_, staffs2_.STAFF_ID as STAFF_ID1_5_3_, staffs2_.STAFF_ID as STAFF_ID1_5_0_, staffs2_.SHOP_ID as SHOP_ID5_5_0_, staffs2_.STAFF_CODE as STAFF_CO2_5_0_, staffs2_.STAFF_NAME as STAFF_NA3_5_0_ from Shop this_ left outer join STAFF staffs2_ on this_.ID=staffs2_.SHOP_ID where this_.ID=? limit ?

        System.out.println(s);
        // et<StaffT3h> st = s.getStaffs();

//        Session session = null;
//        Transaction tx = null;
//        List<Person> lst = new ArrayList<>();
//
//        try {
//
//            session = MysqlHibernateUtil.getSessionFactory().openSession();
//            tx = session.beginTransaction();
//            
//            ShopT3h st = new ShopT3h();
//            
//            st.setShopCode("a");
//            st.setShopName("a");
//            
//            Set<StaffT3h> set = new HashSet<>();
//
//            StaffT3h b = new StaffT3h();
//            b.setStaffCode("b");
//            b.setStaffName("b");
//           // b.setShop(st);
//            set.add(b);
//     
//            st.setStaffs(set);
//            
//            session.save(st);
//
//            tx.commit();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (tx != null) {
//                tx.rollback();
//            }
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        Session session = null;
//        Transaction tx = null;
//        session = MysqlHibernateUtil.getSessionFactory().openSession();
//        tx = session.beginTransaction();
//        Criteria criteria = session.createCriteria(ShopT3h.class);
//        criteria.add(Restrictions.eq("id", 8L));
//        criteria.setMaxResults(1);
//        List<ShopT3h> shops = criteria.list();
//        if (shops.isEmpty()) {
//            System.out.println("k tim thay");
//            return;
//
//        }
//        ShopT3h shop = shops.get(0);
//        Set<StaffT3h> staffs = shop.getStaffs();
//        for (StaffT3h staff : staffs) {
//            System.out.println(staff.getStaffCode() + "---" + staff.getStaffName());
//        }
//        session.delete(shop);
//        tx.commit();
    }
}
