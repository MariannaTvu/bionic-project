package com.bionic.edu.mariana.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Groups")
public class Group {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private GroupType groupType;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Article> articles = new ArrayList<Article>();

    public Group() {
    }

    public Group(String name, GroupType groupType) {
        this.name = name;
        this.groupType = groupType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public Group setGroupType(GroupType groupType) {
        this.groupType = groupType;
        return this;
    }
}
