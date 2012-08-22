package org.forum.portlet.models;

import java.util.Date;

import org.exoplatform.forum.service.Forum;
import org.exoplatform.forum.service.Topic;

public class ForumBen {
  private String  id;

  private String  owner;

  private String  path;

  private int     forumOrder  = 0;

  private Date    createdDate;

  private String  name;

  private String  description;

  private long    postCount   = 0;

  private long    topicCount  = 0;

  private boolean isClosed    = false;

  private boolean isLock      = false;

  private String  classIcon   = "ForumNormalIcon";

  private String  titleStatus = "Forum normal";

  public Topic    lastTopic   = null;

  public String   categoryId;

  public ForumBen(Forum forum) {
    setId(forum.getId());
    setPath(forum.getPath());
    setName(forum.getForumName());
    setDescription(forum.getDescription());
    setClosed(forum.getIsClosed());
    setLock(forum.getIsLock());
    setTopicCount(forum.getTopicCount());
    setPostCount(forum.getPostCount());
    this.categoryId = forum.getCategoryId();
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

  public int getForumOrder() {
    return forumOrder;
  }

  public void setForumOrder(int forumOrder) {
    this.forumOrder = forumOrder;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
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

  public long getPostCount() {
    return postCount;
  }

  public void setPostCount(long postCount) {
    this.postCount = postCount;
  }

  public long getTopicCount() {
    return topicCount;
  }

  public void setTopicCount(long topicCount) {
    this.topicCount = topicCount;
  }

  public boolean isClosed() {
    return isClosed;
  }

  public void setClosed(boolean isClosed) {
    this.isClosed = isClosed;
  }

  public boolean isLock() {
    return isLock;
  }

  public void setLock(boolean isLock) {
    this.isLock = isLock;
  }

  public String getCategoryId() {
    return this.categoryId;
  }

  public void setLastTopic(Topic topic) {
    this.lastTopic = topic;
  }

  public Topic getLastTopic() {
    return this.lastTopic;
  }

  public String getClassIcon() {
    if (isLock) {
      classIcon = "ForumLockedIcon";
      titleStatus = "Forum locked";
    }
    if (isClosed) {
      classIcon = "ForumCloseIcon";
      titleStatus = "Forum closed";
    }
    return classIcon;
  }

  public String getTitleStatus() {
    return titleStatus;
  }

  public void setClassIcon(String classIcon) {
    this.classIcon = classIcon;
  }
}
