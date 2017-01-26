package com.bionic.edu.mariana.service;

import com.bionic.edu.mariana.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

import static com.bionic.edu.mariana.persistence.GroupDAOImpl.*;

@Service
public class ArticleService {
    @Autowired
    private ArticleDAO articleDAO;
    @Autowired
    private GroupDAO groupDAO;

    @Transactional
    public void addArticle(Article article) {
        articleDAO.save(article);
    }

    @Transactional
    public void addGroup(Group group) {
        groupDAO.add(group);
    }

    @Transactional
    public void deleteArticle(long[] ids) {
        for (long id : ids) {
            Group g = articleDAO.findById(id).getGroup();
            Iterator<Article> iter = g.getArticles().iterator();
            while (iter.hasNext()) {
                Article a = iter.next();
                if (a.getId() == id) {
                    iter.remove();
                }
            }
            articleDAO.findById(id).setGroup(DEFAULT_GROUP);
        }
        articleDAO.delete(ids);
    }

    @Transactional
    public Article findArticleById(long id){
        return articleDAO.findById(id);
    }

    @Transactional
    public void deleteGroup(long[] ids) {
        for (long id : ids) {
            if (findGroup(id).getGroupType().equals(GroupType.PRIVATE)) {
                throw new IllegalArgumentException("Cannot delete default group");
            }
            Group g = groupDAO.findById(id);
            List<Article> articles = g.getArticles();
            for (Article article : articles) {
                article.setGroup(DEFAULT_GROUP);
                articleDAO.save(article);
            }
        }
        groupDAO.delete(ids);
    }

    @Transactional
    public List<Group> showAllGroups() {
        if (groupDAO.showAllGroups().isEmpty()) {
            groupDAO.add(DEFAULT_GROUP);
        }
        return groupDAO.showAllGroups();
    }

    @Transactional
    public List<Group> listUserGroups() {
        return groupDAO.showUserGroups();
    }

    @Transactional
    public List<Article> listArticles(Group group) {
        return (group != null) ? group.getArticles() : articleDAO.list(ALL_ARTICLES_GROUP);
    }

    @Transactional
    public Group findGroup(long id) {
        return groupDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Article> getArticles(long groupId, int levelId) {
        if (levelId != (-1)) {
            return articleDAO.list(UsefulnessLevel.byValue(Integer.valueOf(levelId)));
        }
        if (groupId != -1) {
            return articleDAO.list(groupDAO.findById(groupId));
        }
        return articleDAO.list(ALL_ARTICLES_GROUP);
    }

    @Transactional(readOnly = true)
    public List<Article> searchArticles(String pattern) {
        return articleDAO.list(pattern);
    }
}
