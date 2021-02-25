package ru.geekbrains.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductsApp {
    private static SessionFactory factory;


    public static void init(){
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("hibernate_products.cfg.xml")
                .buildSessionFactory();
    }

    public static void shutdown() {
        factory.close();
    }

    public static void read (){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Product product = session.find(Product.class,2L);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    public static void showAll(){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product").getResultList();
            for (Product p: products  ) {
                System.out.println(p + "\n");
            }
            session.getTransaction().commit();
        }
    }

    public static void delete(){
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class,2L);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public static void create(){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Product product = new Product("cola", 55);
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public static void update(){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class,5L);
            product.setCost(999);
            session.getTransaction().commit();
        }
    }







    public static void main(String[] args) {
        init();
        read();
        showAll();
        //delete();
        //create();
        update();
        showAll();
        shutdown();
    }
}
