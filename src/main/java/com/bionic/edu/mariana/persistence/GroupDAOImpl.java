package com.bionic.edu.mariana.persistence;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GroupDAOImpl implements GroupDAO {

    public static final Group DEFAULT_GROUP = new Group("Default", GroupType.PRIVATE);
    public static final Group ALL_ARTICLES_GROUP = new Group("All articles", GroupType.PRIVATE);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Group group) {
        entityManager.merge(group);
    }

    @Override
    public void delete(long[] ids) {
        Group g;
        for (long id : ids) {
            g = entityManager.getReference(Group.class, id);
            if (!g.getName().equals("Default") || !g.getName().equals("All articles")) {
                entityManager.remove(g);
            }
        }
    }

    @Override
    public Group findById(long id) {
        return entityManager.createQuery("SELECT g FROM Group g WHERE g.id=:id", Group.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Group> showUserGroups() {
        TypedQuery<Group> query = entityManager.createQuery("SELECT g FROM Group g " +
                "WHERE g.groupType=:groupType", Group.class);
        query.setParameter("groupType", GroupType.USER);
        return query.getResultList();
    }

    @Override
    public List<Group> showDefaultGroupList() {
        TypedQuery<Group> query = entityManager.createQuery("SELECT g FROM Group g " +
                "WHERE g.groupType=:groupType", Group.class);
        query.setParameter("groupType", GroupType.USER);
        List<Group> groups = query.getResultList();
        Group additional = entityManager.createQuery("SELECT g FROM Group g WHERE g.name=:name", Group.class)
                .setParameter("name", "Default")
                .getSingleResult();
        groups.add(0, additional);

        return groups;
    }

    @Override
    public List<Group> showAllGroups() {
        TypedQuery<Group> query = entityManager.createQuery("SELECT g FROM Group g", Group.class);
        return query.getResultList();
    }

    @Override
    public Group findByName(String name) {
        return entityManager.createQuery("SELECT g FROM Group g WHERE g.name=:name", Group.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
