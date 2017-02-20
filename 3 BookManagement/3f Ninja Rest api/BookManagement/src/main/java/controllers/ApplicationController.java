/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import models.Book;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import javax.persistence.*;

//...

import com.google.inject.Inject;

import com.google.inject.Provider;

import com.google.inject.Singleton;

import ninja.Router;
import ninja.jpa.UnitOfWork;
import ninja.params.PathParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.inject.persist.Transactional;

@Singleton
public class ApplicationController {

	@Inject
	Provider<EntityManager> entitiyManagerProvider;
	@Inject
	Router router;

	public Result index() {
		return Results.html();
	}

	@UnitOfWork
	public Result getallBook() {
		EntityManager entityManager = entitiyManagerProvider.get();
		Query q = entityManager.createQuery("SELECT p FROM " + Book.class.getName() + " p");
		return Results.json().render(q.getResultList());
	}

	@UnitOfWork
	public Result getBook(@PathParam("isbn") long isbn) {
		EntityManager entityManager = entitiyManagerProvider.get();
		Query q = entityManager.createQuery("SELECT p FROM " + Book.class.getName() + " p WHERE p.isbn = " + isbn);
		return Results.json().render(q.getResultList());
	}
	
	  @Transactional
	public Result createBook(Book book) {
		EntityManager entityManager = entitiyManagerProvider.get();
		Query q = entityManager.createQuery("SELECT p FROM " + Book.class.getName() + " p WHERE p.isbn = " + book.getisbn());
		List<Book> list = q.getResultList();
		if ( !list.isEmpty())
			return Results.badRequest();
		entityManager.persist(book);
		return Results.json().render(book);
	} 
	@Transactional
	public Result delBook(Book book) {
		EntityManager entityManager = entitiyManagerProvider.get();
		Query q = entityManager.createQuery("SELECT p FROM " + Book.class.getName() + " p WHERE p.isbn = " + book.getisbn());
		entityManager.remove(q.getSingleResult());
		return Results.json().render(book);
	}
	@Transactional
	public Result editBook(Book book) {
		EntityManager entityManager = entitiyManagerProvider.get();
		Query q = entityManager.createQuery("SELECT p FROM " + Book.class.getName() + " p WHERE p.isbn = " + book.getisbn());
		if (q.getSingleResult() == null)
			return Results.badRequest();
		entityManager.remove(q.getSingleResult());
		entityManager.persist(book);
		return Results.json().render(q.getSingleResult());
	}


}
