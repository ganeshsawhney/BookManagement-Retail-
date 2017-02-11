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

public class Department {
	public static Scanner scan = new Scanner(System.in);

	// -----------------Combiner Function Start---------------//
	public static void BookDept() {
		BookUtilImpl view = new BookUtilImpl();
		System.out.println("Press ");
		System.out.println("1 to add a book");
		System.out.println("2 to retrieve a book");
		System.out.println("3 to update a book");
		System.out.println("4 to delete a book");
		System.out.println("5 to display all books");
		System.out.print("=========>>");
		Integer option = scan.nextInt();
		switch (option) {
		case 1: {
			view.addBook();
			break;
		}
		case 2: {
			view.displayBook();
			break;
		}
		case 3: {
			view.updateBook();
			break;
		}
		case 4: {
			view.deleteBook();
			break;
		}
		case 5: {
			view.listAllBook();
			break;
		}
		}
	}

	public static void UserDept() {
		UserUtilImpl view = new UserUtilImpl();
		System.out.println("Press ");
		System.out.println("1 to add a user");
		System.out.println("2 to retrieve a user");
		System.out.println("3 to update a user");
		System.out.println("4 to delete a user");
		System.out.println("5 to display all user");
		System.out.print("=========>>");
		Integer option = scan.nextInt();
		switch (option) {
		case 1: {
			view.addUser();
			break;
		}
		case 2: {
			view.displayUser();
			break;
		}
		case 3: {
			view.updateUser();
			break;
		}
		case 4: {
			view.deleteUser();
			break;
		}
		case 5: {
			view.listAllUser();
			break;
		}
		}
	}

	public static void SaleDept() {
		SaleUtilImpl view = new SaleUtilImpl();
		System.out.println("Press ");
		System.out.println("1 to add a sale");
		System.out.println("2 to retrieve a sale");
		System.out.println("3 to display all sale");
		System.out.print("=========>>");
		Integer option = scan.nextInt();
		switch (option) {
		case 1: {
			view.addSale();
			break;
		}
		case 2: {
			view.displaySale();
			break;
		}
		case 3: {
			view.listAllSale();
			break;
		}
		}
	}
	// -----------------Combiner Function End---------------//
}
