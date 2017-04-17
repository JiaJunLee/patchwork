package com.sourcetech.patchwork.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

/**
 * Created by 李佳骏 on 2017/4/4.
 */
@Component
public class HibernateFactory {

    private ServiceRegistry serviceRegistry;
    private Metadata metadata;
    private SessionFactory sessionFactory;

    public HibernateFactory(){
        long start = System.currentTimeMillis();
        System.err.println("\n\n\nCREATE FACTORY");
        this.serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        this.metadata = new MetadataSources(serviceRegistry).buildMetadata();
        this.sessionFactory = metadata.buildSessionFactory();
        long stop = System.currentTimeMillis();
        System.err.println("USE TIME : " + (stop-start) +"ms");
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
