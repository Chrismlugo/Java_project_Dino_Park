package com.codeclan.db;

import models.Dino;
import models.Enums.SpeciesType;
import models.dinosaurs.Raptor;
import models.dinosaurs.TRex;
import models.paddocks.Paddock;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBHelper {
    private static Transaction transaction;
    private static Session session;

    public static void saveOrUpdate(Object object) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> void deleteAll(Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(classType);
            List<T> results = cr.list();
            for (T result : results) {
                session.delete(result);
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Object object){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> T getUnique(Criteria criteria){
        T result = null;
        try {
            transaction = session.beginTransaction();
            result = (T)criteria.uniqueResult();
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T>List<T> getList(Criteria cr){
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            results = cr.list();
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        } finally{
            session.close();
        }
        return  results;
    }

    public static <T> T find(Class classType, int id){
        session = HibernateUtil.getSessionFactory().openSession();
        T result = null;
        Criteria criteria = session.createCriteria(classType);
        criteria.add(Restrictions.idEq(id));
        result = getUnique(criteria);
        return result;
    }

    public static <T>List<T> getAll(Class classType){
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(classType);
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getList(cr);
    }

    public static List<Paddock> getAllPaddocksOfSpeciesType(SpeciesType species){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Paddock> results = null;
        Criteria cr = session.createCriteria(Paddock.class);
        cr.add(Restrictions.eq("species", species));
        results = getList(cr);
        return results;
    }

    public static List<Dino> getAllDinosOfSpeciesType(SpeciesType species){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Dino> results = null;
        Criteria cr = session.createCriteria(Paddock.class);
        cr.add(Restrictions.eq("species", species));
        results = getList(cr);
        return results;
    }

    public static List<Paddock> getPaddocksAvailableToSpecificDino(SpeciesType species, Class classType){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Paddock> results = null;
        Criteria cr = session.createCriteria(Paddock.class);
        cr.add(Restrictions.eq("species", species));
        cr.add(Restrictions.eq("classType", classType));
        results = getList(cr);
        return results;
    }

    public static List<Dino> getDinosOfPaddock(Paddock paddock){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Dino> results = null;
        Criteria cr = session.createCriteria(Dino.class);
        cr.add(Restrictions.eq("paddock",paddock));
        results = getList(cr);
        return results;
    }







}