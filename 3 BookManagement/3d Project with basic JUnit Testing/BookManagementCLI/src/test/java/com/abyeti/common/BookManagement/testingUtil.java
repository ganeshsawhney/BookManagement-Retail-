package com.abyeti.common.BookManagement;

import java.math.BigInteger;
import org.junit.*;
import com.abyeti.common.*;

import static org.junit.Assert.assertEquals;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.abyeti.common.User;
import com.abyeti.persistence.HibernateUtil;
import java.security.SecureRandom;

import org.hibernate.Session;
import com.abyeti.persistence.HibernateUtil;
import java.util.Scanner;
import static org.junit.Assert.*;

public class testingUtil {

	@Test
	public void testUser() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction inputTrans = null;
		inputTrans = session.beginTransaction();
		User userIp = new User();
		SecureRandom token = new SecureRandom();
		String userName = new BigInteger(10, token).toString(10);
		userIp.setUsername(userName);
		session.save(userIp);
		Integer userid = userIp.getUserid();
		inputTrans.commit();
		session.close();

		session = HibernateUtil.getSessionFactory().openSession();
		Transaction outputTrans = null;
		outputTrans = session.beginTransaction();
		User userOp = (User) session.get(User.class, userid);
		assertNotNull(userOp);
		assertEquals(userOp.getUsername(), userName);
		outputTrans.commit();

	}


	@Test
	public void testfn() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		UserUtil view=new UserUtilImpl();
		view.addUser();
	}

}