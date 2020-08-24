package br.unisantos.fehidro.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.unisantos.fehidro.util.jpa.JPAEntityManager;

public class DAOFactory<T> {
	private final Class<T> classe;
	private EntityManager manager;

	public DAOFactory(Class<T> classe) {
		this.classe = classe;
	}

	public void adicionar(T t) {
		manager = JPAEntityManager.getEntityManager();
		try {
			manager.getTransaction().begin();
			manager.persist(t);
			manager.getTransaction().commit();
		} finally {
			//manager.close();//FIXME
		}
	}

	public T consultar(Long id) {
		manager = JPAEntityManager.getEntityManager();
		T instancia = manager.find(classe, id);
		//manager.close();//FIXME
		return instancia;
	}

	public void alterar(T t) {
		manager = JPAEntityManager.getEntityManager();
		try {
			manager.getTransaction().begin();
			manager.merge(t);
			manager.getTransaction().commit();
		} finally {
			//manager.close();//FIXME
		}
	}

	public boolean excluir(Long id) {
		manager = JPAEntityManager.getEntityManager();
		try {
			T t = manager.find(classe, id);
			if (t == null)
				return false;
			manager.getTransaction().begin();
			manager.remove(t);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
		return true;
	}

	public List<T> listarGenerico(String query, Object... params) {
		manager = JPAEntityManager.getEntityManager();
		TypedQuery<T> q = manager.createNamedQuery(query, classe);
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i + 1, params[i]);
		}
		List<T> lista = q.getResultList();
		//manager.close();//FIXME
		return lista;
	}

	public T consultarGenerico(String query, Object... params) {
		manager = JPAEntityManager.getEntityManager();
		TypedQuery<T> q = manager.createNamedQuery(query, classe);
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i + 1, params[i]);
		}
		try {
			T obj = q.getSingleResult();
			return obj;
		} catch (NoResultException e) {
			return null;
		} finally {
			//manager.close();//FIXME
		}
	}
}
