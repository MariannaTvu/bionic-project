package com.bionic.edu.mariana.persistence;

import java.util.List;

public interface ArticleDAO {
    void save(Article article);
    void delete(Article article);
    void delete(long[] ids);
    Article findById(long id);
    List<Article> list(Group group);
    List<Article> list(String pattern);
    List<Article> list(UsefulnessLevel level);
}
