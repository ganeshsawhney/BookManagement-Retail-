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

public class BookUtilImpl implements BookUtil {
	public static Scanner scan = new Scanner(System.in);

	// -----------------Book Functions Start---------------//
	public void listAllBook() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Book> allbooks = session.createQuery("FROM Book").list();
			for (Iterator iterator = allbooks.iterator(); iterator.hasNext();) {
				Book book = (Book) iterator.next();
				System.out.println("Book : " + book.getbkname());
				System.out.println("\tISBN: " + book.getisbn());
				System.out.println("\tAuthor ID: " + book.getauthorid());
				System.out.println("\tEdition: " + book.getedition());
				System.out.println("\tPrice: " + book.getprice());
				System.out.println("\tAvailable Units: " + book.getavailable_units());
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

	public void addBook() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			System.out.print("Enter Book ISBN: ");
			long isbn = scan.nextLong();
			Book bk1 = (Book) session.get(Book.class, isbn);
			if (bk1 != null) {
				System.out.println("Book with ISBN=" + isbn + " already exists");
				return;
			}
			Book bk = new Book();
			bk.setisbn(isbn);
			System.out.print("Enter Book name: ");
			bk.setbkname(scan.next());
			System.out.print("Enter Author (user id): ");
			Integer userid = scan.nextInt();
			User user = (User) session.get(User.class, userid);
			if (user == null) {
				System.out.println("User with UserID=" + userid + " not found");
				return;
			}
			bk.setauthorid(userid);
			System.out.print("Enter Book Edition: ");
			bk.setedition(scan.next());
			System.out.print("Enter Book price: ");
			bk.setprice(scan.nextInt());
			System.out.print("Enter Available Units: ");
			bk.setavailable_units(scan.nextInt());

			session.save(bk);
			System.out.println("Book of ISBN = " + bk.getisbn() + " has been created successfully");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			// session.close();
		}
	}

	public void updateBook() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.print("Enter Book ISBN to be updated: ");
			long isbn = scan.nextLong();
			Book bk = (Book) session.get(Book.class, isbn);
			if (bk == null) {
				System.out.println("Book with ISBN=" + isbn + " does not exists");
				return;
			}
			System.out.print("Enter Updated Book price: ");
			bk.setprice(scan.nextInt());
			System.out.print("Enter Updated Available Units: ");
			bk.setavailable_units(scan.nextInt());
			session.update(bk);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			// session.close();
		}
	}

	public void deleteBook() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.print("Enter Book ISBN to be deleted: ");
			long isbn = scan.nextLong();
			Book bk = (Book) session.get(Book.class, isbn);
			if (bk == null) {
				System.out.println("Book with ISBN=" + isbn + " does not exists");
				return;
			}
			bk.setavailable_units(0);
			session.update(bk);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			// session.close();
		}
	}

	public void displayBook() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.print("Enter Book ISBN: ");
			long isbn = scan.nextLong();
			Book book = (Book) session.get(Book.class, isbn);
			if (book.getisbn() != isbn) {
				System.out.println("Book with ISBN=" + isbn + " does not exists");
				return;
			}
			System.out.println("Book : " + book.getbkname());
			System.out.println("\tISBN: " + book.getisbn());
			System.out.println("\tAuthor ID: " + book.getauthorid());
			System.out.println("\tEdition: " + book.getedition());
			System.out.println("\tPrice: " + book.getprice());
			System.out.println("\tAvailable Units: " + book.getavailable_units());
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
	// -------------------Book Functions END---------------//
}
