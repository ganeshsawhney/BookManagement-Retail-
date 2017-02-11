package com.abyeti.common;

import java.util.List;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.abyeti.persistence.HibernateUtil;

public class UserUtilImpl implements UserUtil {
	public static Scanner scan = new Scanner(System.in);

	// -----------------User Functions Start---------------//
	public void listAllUser() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<User> allusers = session.createQuery("FROM User").list();
			for (Iterator iterator = allusers.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				System.out.println("User ID : " + user.getUserid());
				System.out.println("\tUserName: " + user.getUsername());
				System.out.println("\tMobile: " + user.getMobile());
				System.out.print("\n");
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			// session.close();
		}
	}

	public void addUser() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = new User();

			System.out.print("Enter UserName: ");
			user.setUsername(scan.next());
			System.out.print("Enter Mobile Number: ");
			user.setMobile(scan.nextLong());
			System.out.print("Is user admin (y/n): ");
			String ip = scan.next();
			if (ip.equals("y"))
				user.setAdmin(true);
			else
				user.setAdmin(false);

			session.save(user);
			System.out.println("User of ID = " + user.getUserid() + " has been created successfully");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			// session.close();
		}
	}

	public void updateUser() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.print("Enter User ID to be updated: ");
			Integer userid = scan.nextInt();
			User user = (User) session.get(User.class, userid);
			if (user == null) {
				System.out.println("User with UserID=" + userid + " not found");
				return;
			}
			System.out.print("Enter Updated UserName: ");
			user.setUsername(scan.next());
			System.out.print("Enter Updated Mobile Number: ");
			user.setMobile(scan.nextLong());
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			// session.close();
		}
	}

	public void deleteUser() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.print("Enter User ID to be deleted: ");
			Integer userid = scan.nextInt();
			User user = (User) session.get(User.class, userid);
			if (user == null) {
				System.out.println("User with UserID=" + userid + " not found");
				return;
			}
			session.delete(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			// session.close();
		}
	}

	public void displayUser() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.print("Enter User ID: ");
			Integer userid = scan.nextInt();
			User user = (User) session.get(User.class, userid);
			if (user == null) {
				System.out.println("User with UserID=" + userid + " not found");
				return;
			}
			System.out.println("User ID : " + user.getUserid());
			System.out.println("\tUserName: " + user.getUsername());
			System.out.println("\tMobile: " + user.getMobile());
			System.out.print("\n");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			// session.close();
		}
	}
	// -------------------User Functions END---------------//

}
