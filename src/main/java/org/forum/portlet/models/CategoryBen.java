package org.forum.portlet.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.exoplatform.forum.service.Category;
import org.exoplatform.forum.service.Forum;

public class CategoryBen {
  private String        id;

  private String        owner;

  private String        path;

  private long          categoryOrder = 0;

  private Date          createdDate;

  private String        modifiedBy;

  private Date          modifiedDate;

  private String        name;

  private String        description;

  private boolean       isHome = true;

  public List<ForumBen> forumBens     = new ArrayList<ForumBen>();

  public CategoryBen(Category category) {
    setId(category.getId());
    setPath(category.getPath());
    setName(category.getCategoryName());
    setDescription(category.getDescription());
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public long getCategoryOrder() {
    return categoryOrder;
  }

  public void setCategoryOrder(long categoryOrder) {
    this.categoryOrder = categoryOrder;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public Date getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(Date modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<ForumBen> getForumBens() {
    return forumBens;
  }

  public void setForumBens(List<ForumBen> forums) {
    this.forumBens = forums;
  }

  public void setForums(List<Forum> forums) throws Exception {
    forumBens = new ArrayList<ForumBen>();
    for (Forum forum : forums) {
      forumBens.add(new ForumBen(forum));
    }
  }

  public boolean isHome() {
    return isHome;
  }

  public void setHome(boolean isHome) {
    this.isHome = isHome;
  }
}
