package org.forum.portlet.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import juzu.Param;

import org.exoplatform.forum.service.Category;
import org.exoplatform.forum.service.Forum;

@Param
public class CategoryBean {
  private String         id;

  private String         owner;

  private String         path;

  private long           categoryOrder = 0;

  private Date           createdDate;

  private String         modifiedBy;

  private Date           modifiedDate;

  private String         name;

  private String         description;

  private String         userPrivates;

  private String         moderators;

  private boolean        isHome        = true;

  public List<ForumBean> forumBeans    = new ArrayList<ForumBean>();

  public CategoryBean() {
  }

  public CategoryBean(Category category) {
    setId(category.getId());
    setPath(category.getPath());
    setName(category.getCategoryName());
    setDescription(category.getDescription());
    setOwner(category.getOwner());
    setCategoryOrder(category.getCategoryOrder());
    setUserPrivates(category.getUserPrivate());
    setModerators(category.getModerators());
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

  public List<ForumBean> getForumBeans() {
    return forumBeans;
  }

  public void setForumBeans(List<ForumBean> forums) {
    this.forumBeans = forums;
  }

  public void setForums(List<Forum> forums) throws Exception {
    forumBeans = new ArrayList<ForumBean>();
    for (Forum forum : forums) {
      forumBeans.add(new ForumBean(forum));
    }
  }

  public boolean isHome() {
    return isHome;
  }

  public void setHome(boolean isHome) {
    this.isHome = isHome;
  }

  public String getUserPrivates() {
    return userPrivates;
  }

  public String[] getArrUserPrivates() {
    return valueToArray(userPrivates);
  }

  public void setUserPrivates(String userPrivates) {
    this.userPrivates = userPrivates;
  }

  public void setUserPrivates(String[] userPrivates) {
    this.userPrivates = arrayToString(userPrivates);
  }

  public String getModerators() {
    return moderators;
  }

  public String[] getArrModerators() {
    return valueToArray(moderators);
  }

  public void setModerators(String moderators) {
    this.moderators = moderators;
  }

  public void setModerators(String[] moderators) {
    this.moderators = arrayToString(moderators);
  }

  public Category toCategory() {
    Category category = new Category(id);
    category.setOwner(owner);
    category.setPath(path);
    return toCategory(category);
  }

  public Category toCategory(Category category) {
    category.setCategoryName(name);
    category.setCategoryOrder(categoryOrder);
    category.setDescription(description);
    category.setUserPrivate(getArrUserPrivates());
    category.setModerators(getArrModerators());
    return category;
  }

  private String[] valueToArray(String txt) {
    if (txt != null) {
      txt.replaceAll(" ", "").split(",");
    }
    return new String[] { "" };
  }
  
  private String arrayToString(String[] arrays) {
    if (arrays == null) {
      return "";
    } else {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < arrays.length; i++) {
        builder.append(arrays[i]).append(",");
      }
      return builder.toString();
    }
  }
}
