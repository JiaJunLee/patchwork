package com.sourcetech.patchwork.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by 李佳骏 on 2017/4/4.
 */
public class HibernateFactory {

    private static volatile HibernateFactory HIBERNATE_FACTORY;

    private ServiceRegistry serviceRegistry;
    private Metadata metadata;
    private SessionFactory sessionFactory;

    private HibernateFactory(){
        long start = System.currentTimeMillis();
        System.err.println("\n\n\nCREATE FACTORY");
        this.serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        this.metadata = new MetadataSources(serviceRegistry).buildMetadata();
        this.sessionFactory = metadata.buildSessionFactory();
        long stop = System.currentTimeMillis();
        System.err.println("USE TIME : " + (stop-start) +"ms");
    }

    public static HibernateFactory getInstance(){
        if(HIBERNATE_FACTORY==null){
            synchronized (HibernateFactory.class){
                if(HIBERNATE_FACTORY==null){
                    HIBERNATE_FACTORY = new HibernateFactory();
                }
            }
        }
        return HIBERNATE_FACTORY;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
