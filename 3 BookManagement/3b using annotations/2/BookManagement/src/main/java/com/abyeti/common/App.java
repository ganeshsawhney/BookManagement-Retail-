package com.abyeti.common;

import org.hibernate.Session;
import com.abyeti.persistence.HibernateUtil;
import java.util.Scanner;

public class App {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Book Management System using Maven + Hibernate + MySQL");
		Integer option = 1;

		// Login
		System.out.println("Please Login");
		System.out.println("=========>>");
		while (Authentication.login() == false) {
		}

		// Session options
		while (option != 4) {
			System.out.println("Press ");
			System.out.println("1 for Books");
			System.out.println("2 for Users");
			System.out.println("3 for Sales");
			System.out.println("4 to quit");
			System.out.print("=========>>");
			option = scan.nextInt();
			switch (option) {
			case 1: {
				Department.BookDept();
				break;
			}
			case 2: {
				Department.UserDept();
				break;
			}
			case 3: {
				Department.SaleDept();
				break;
			}
			default:
				option = 4;
			}
		}

	}
}
