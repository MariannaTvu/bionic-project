package com.bionic.edu.mariana.persistence;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Maryana on 25.01.2017.
 */
public class ArticleDTO {
    private long id;
    private long group;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Link should not be empty")
    private String link;
    private String description;
    private int usefulnessLevel;

    public ArticleDTO() {
    }

    public ArticleDTO(Article article) {
        this.id = article.getId();
        this.group = article.getGroup().getId();
        this.name = article.getName();
        this.link = article.getLink();
        this.description = article.getDescription();
        this.usefulnessLevel = article.getUsefulnessLevel().getValue();
    }

    public long getId() {
        return id;
    }

    public ArticleDTO setId(long id) {
        this.id = id;
        return this;
    }

    public long getGroup() {
        return group;
    }

    public ArticleDTO setGroup(long group) {
        this.group = group;
        return this;
    }

    public String getName() {
        return name;
    }

    public ArticleDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getLink() {
        return link;
    }

    public ArticleDTO setLink(String link) {
        this.link = link;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArticleDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getUsefulnessLevel() {
        return usefulnessLevel;
    }

    public ArticleDTO setUsefulnessLevel(int usefulnessLevel) {
        this.usefulnessLevel = usefulnessLevel;
        return this;
    }
}