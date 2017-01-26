package com.bionic.edu.mariana.persistence;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Maryana on 25.01.2017.
 */
public class ArticleDTO {
    private int id;
    private String group;
    @NotEmpty(message = "Name should nt be empty")
    private String name;
    @NotEmpty(message = "Link should nt be empty")
    private String link;
    private String description;
    private String usefulnessLevel;

    public ArticleDTO() {
    }

    public ArticleDTO(Object article) {

    }

    public int getId() {

        return id;
    }

    public String getGroup() {
        return group;
    }

    public ArticleDTO setGroup(String group) {
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

    public String getUsefulnessLevel() {
        return usefulnessLevel;
    }

    public ArticleDTO setUsefulnessLevel(String usefulnessLevel) {
        this.usefulnessLevel = usefulnessLevel;
        return this;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", group='" + group + '\'' +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", usefulnessLevel='" + usefulnessLevel + '\'' +
                '}';
    }
}
