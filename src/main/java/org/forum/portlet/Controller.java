package org.forum.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import juzu.Response;
import juzu.SessionScoped;
import juzu.Action;
import juzu.Path;
import juzu.Route;
import juzu.View;

import org.exoplatform.forum.service.ForumService;
import org.exoplatform.forum.service.Category;
import org.exoplatform.forum.service.Forum;
import org.exoplatform.forum.service.Topic;
import org.exoplatform.services.security.Identity;
import org.forum.portlet.models.CategoryBean;
import org.forum.portlet.models.ForumBean;

import org.forum.portlet.templates.index;
import org.forum.portlet.templates.category;
import org.forum.portlet.templates.forum;
import org.forum.portlet.templates.form.addcategory;

public class Controller {
  @Inject ForumService forumService;

  @Inject @Path("index.gtmpl") index index;

  @Inject @Path("category.gtmpl") category category;

  @Inject @Path("forum.gtmpl") forum forum;

  @Inject @Path("form/addcategory.gtmpl") addcategory addcategory;

//  @Inject @SessionScoped Identity currentUser;

  @View
  public void index() throws IOException, Exception {
    index.with()
         .categoryBeans(buildCategoryBean()).render();
  }

  public List<CategoryBean> buildCategoryBean() throws Exception {
    List<CategoryBean> categoryBeans = new ArrayList<CategoryBean>();
    List<Category> categories = forumService.getCategories();
    for (Category category : categories) {
      categoryBeans.add(getCategoryBean(category));
    }
    return categoryBeans;
  }

  public CategoryBean getCategoryBean(Category category) throws Exception {
    CategoryBean categoryBean = new CategoryBean(category);
    categoryBean.setForumBeans(buildForumBeans(forumService.getForums(category.getId(), null)));
    return categoryBean;
  }

  public List<ForumBean> buildForumBeans(List<Forum> forums) throws Exception {
    List<ForumBean> forumBeans = new ArrayList<ForumBean>();
    for (Forum forum : forums) {
      forumBeans.add(getForumBean(forum));
    }
    return forumBeans;
  }

  public ForumBean getForumBean(Forum forum) throws Exception {
    ForumBean forumBean = new ForumBean(forum);
    forumBean.setLastTopic(getTopic(forum.getLastTopicPath()));
    return forumBean;
  }

  public Topic getTopic(String topicPath) throws Exception {
    Topic topic = null;
    if (topicPath != null && topicPath.trim().length() > 0) {
      topic = forumService.getTopicSummary(topicPath);
    }
    return topic;
  }

  @View
  @Route("/category/{id}")
  public void loadCategory(String id) throws Exception {
    CategoryBean categoryBean = getCategoryBean(forumService.getCategory(id));
    categoryBean.setHome(false);
    category.with()
            .CategoryBean(categoryBean).render();
  }

  @View
  @Route("/addcategory")
  public void addCategory() throws Exception {
    Category cate = new Category();
    cate.setCategoryName("");
    cate.setOwner("root");
//    cate.setOwner(currentUser.getUserId());
    cate.setCategoryOrder(1);
    cate.setDescription("");
    cate.setPath("");
    addcategory.with()
               .categoryBean(new CategoryBean(cate)).render();
  }
  
  @View
  @Route("/addcategory/{id}")
  public void editCategory(String id) throws Exception {
    addcategory.with()
               .categoryBean(new CategoryBean(forumService.getCategory(id))).render();
  }
  
  @Action
  @Route("/addcategory/{id}/category")
  public Response processSaveCategory(String id, CategoryBean category) throws Exception {
    if(category != null) {
      category.setId(id);
      Category cate  = category.toCategory();
      if(cate.getPath() == null || cate.getPath().length() == 0){
        forumService.saveCategory(cate, true);
      } else {
        cate = forumService.getCategory(id);
        if(cate != null) {
          cate = category.toCategory(cate);
          forumService.saveCategory(cate, false);
        }
      }
    }
    return Controller_.index();
  }
}
