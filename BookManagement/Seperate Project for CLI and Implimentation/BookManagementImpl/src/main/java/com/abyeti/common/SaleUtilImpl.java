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

public class SaleUtilImpl implements SaleUtil {
	public static Scanner scan = new Scanner(System.in);

	// -----------------Sale Functions Start---------------//
	public void listAllSale() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Sale> allsales = session.createQuery("FROM Sale").list();
			for (Iterator iterator = allsales.iterator(); iterator.hasNext();) {
				Sale sale = (Sale) iterator.next();
				System.out.println("Sale ID : " + sale.getSaleid());
				System.out.println("\tBook ISBN: " + sale.getIsbn());
				System.out.println("\tUser ID: " + sale.getUserid());
				System.out.println("\tNo. of copies: " + sale.getQuantity());
				System.out.println("\tDate: " + sale.getDate());
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

	public void addSale() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Sale sale = new Sale();

			System.out.print("Enter Book ISBN: ");
			long isbn = scan.nextLong();
			Book bk = (Book) session.get(Book.class, isbn);
			if (bk == null) {
				System.out.println("Book with ISBN=" + isbn + " does not exists");
				return;
			}
			sale.setIsbn(isbn);
			System.out.print("Enter User ID: ");
			Integer userid = scan.nextInt();
			User user = (User) session.get(User.class, userid);
			if (user == null) {
				System.out.println("User with UserID=" + userid + " not found");
				return;
			}
			sale.setUserid(userid);
			System.out.print("How many copies: ");
			Integer qty = scan.nextInt();
			if (bk.getavailable_units() < qty) {
				System.out.println("Sorry! only " + bk.getavailable_units() + " left, press y to buy "
						+ bk.getavailable_units() + " copies, else press n");
				String ip = scan.next();
				if (ip == "y") {
					qty = bk.getavailable_units();
				} else {
					return;
				}

				session.update(bk);
			}
			bk.setavailable_units(0);
			session.update(bk);
			sale.setQuantity(qty);

			session.save(sale);
			System.out.println("Sale of ID = " + sale.getSaleid() + " has been created successfully");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			// session.close();
		}
	}

	public void displaySale() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.print("Enter Sale ID: ");
			Integer saleid = scan.nextInt();
			Sale sale = (Sale) session.get(Sale.class, saleid);
			if (sale == null) {
				System.out.println("Sale with ID=" + saleid + " does not exists");
				return;
			}
			System.out.println("Sale ID : " + sale.getSaleid());
			System.out.println("\tBook ISBN: " + sale.getIsbn());
			System.out.println("\tUser ID: " + sale.getUserid());
			System.out.println("\tNo. of copies: " + sale.getQuantity());
			System.out.println("\tDate: " + sale.getDate());
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
	// -------------------Sale Functions END---------------//

}
