package com.bionic.edu.mariana.persistence;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.bionic.edu.mariana.persistence.GroupDAOImpl.ALL_ARTICLES_GROUP;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Article article) {
            entityManager.merge(article);
    }

    @Override
    public void delete(Article article) {
        entityManager.remove(article);
    }

    @Override
    public void delete(long[] ids) {
        Article a;
        for (long id : ids) {
            a = entityManager.getReference(Article.class, id);
            entityManager.remove(a);
        }
    }

    @Override
    public Article findById(long id) {
        return entityManager.createQuery("SELECT a FROM Article a WHERE a.id=:id", Article.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Article> list(Group group) {
        TypedQuery query;
        if (group.getName().equals("All articles")) {
            query = entityManager.createQuery("SELECT a FROM Article a", Article.class);
        } else {
            query = entityManager.createQuery("SELECT a FROM Article a WHERE a.group = :group", Article.class);
            query.setParameter("group", group);
        }
        return query.getResultList();
    }

    @Override
    public List<Article> list(String pattern) {
        TypedQuery query = entityManager.createQuery("SELECT a FROM Article a WHERE a.name LIKE :pattern", Article.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return query.getResultList();
    }

    @Override
    public List<Article> list(UsefulnessLevel level) {
        TypedQuery query;
        if (level != null) {
            query = entityManager.createQuery("SELECT a FROM Article a WHERE a.usefulnessLevel = :level", Article.class);
            query.setParameter("level", level);
            return query.getResultList();
        } else {
            query = entityManager.createQuery("SELECT a FROM Article a", Article.class);
        }
        return query.getResultList();
    }
}