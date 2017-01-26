package com.bionic.edu.mariana.persistence;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Articles")
public class Article {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;
    private String name;
    private String link;
    @Lob
    private String description;
    private UsefulnessLevel usefulnessLevel;

    public Article() {
    }

    public Article(Group group, String name, String link, String description, UsefulnessLevel usefulnessLevel) {
        this.group = group;
        this.name = name;
        this.link = link;
        this.description = description;
        this.usefulnessLevel = usefulnessLevel;
    }

    public Group getGroup() {
        return group;
    }

    public Article setGroup(Group group) {
        this.group = group;
        return this;
    }

    public String getName() {
        return name;
    }

    public Article setName(String name) {
        this.name = name;
        return this;
    }

    public String getLink() {
        return link;
    }

    public Article setLink(String link) {
        this.link = link;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Article setDescription(String description) {
        this.description = description;
        return this;
    }

    public UsefulnessLevel getUsefulnessLevel() {
        return usefulnessLevel;
    }

    public Article setUsefulnessLevel(UsefulnessLevel usefulnessLevel) {
        this.usefulnessLevel = usefulnessLevel;
        return this;
    }

    public long getId() {
        return id;
    }
}
