package com.abyeti.common;

import org.hibernate.Session;
import com.abyeti.persistence.HibernateUtil;
import java.util.Scanner; 

public class App
{
    public static void main( String[] args )
    {
        System.out.println("Book Management System using Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();
                
        session.beginTransaction();
        Book book = new Book();
        book.setall4values("415","name2","author2",1);
        session.save(book);
        session.getTransaction().commit();
    }
}
