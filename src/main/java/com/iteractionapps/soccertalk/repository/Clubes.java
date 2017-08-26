package com.iteractionapps.soccertalk.repository;

import com.iteractionapps.soccertalk.model.Clube;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Clubes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
        
	public Clube porId(Long id) {
		return this.manager.find(Clube.class, id);
	}
	
	public List<Clube> todosClubes() {
		Query query = manager.createQuery("select c from Clube c", Clube.class);
		return query.getResultList();
	}
}