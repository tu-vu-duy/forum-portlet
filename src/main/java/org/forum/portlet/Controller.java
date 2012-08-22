package org.forum.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import juzu.Path;
import juzu.Route;
import juzu.View;

import org.exoplatform.forum.service.ForumService;
import org.exoplatform.forum.service.Category;
import org.exoplatform.forum.service.Forum;
import org.exoplatform.forum.service.Topic;
import org.forum.portlet.models.CategoryBen;
import org.forum.portlet.models.ForumBen;

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

  // @Inject @SessionScoped Identity currentUser;

  @View
  public void index() throws IOException, Exception {
    index.with()
         .categoryBens(buildCategoryBen()).render();
  }

  public List<CategoryBen> buildCategoryBen() throws Exception {
    List<CategoryBen> categoryBens = new ArrayList<CategoryBen>();
    List<Category> categories = forumService.getCategories();
    for (Category category : categories) {
      categoryBens.add(getCategoryBen(category));
    }
    return categoryBens;
  }

  public CategoryBen getCategoryBen(Category category) throws Exception {
    CategoryBen categoryBen = new CategoryBen(category);
    categoryBen.setForumBens(buildForumBens(forumService.getForums(category.getId(), null)));
    return categoryBen;
  }

  public List<ForumBen> buildForumBens(List<Forum> forums) throws Exception {
    List<ForumBen> forumBens = new ArrayList<ForumBen>();
    for (Forum forum : forums) {
      forumBens.add(getForumBen(forum));
    }
    return forumBens;
  }

  public ForumBen getForumBen(Forum forum) throws Exception {
    ForumBen forumBen = new ForumBen(forum);
    forumBen.setLastTopic(getTopic(forum.getLastTopicPath()));
    return forumBen;
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
    CategoryBen categoryBen = getCategoryBen(forumService.getCategory(id));
    categoryBen.setHome(false);
    category.with()
            .CategoryBen(categoryBen).render();
  }

  @View
  @Route("/addcategory")
  public void addCategory() throws Exception {
    System.out.println("\n\n=======> addCategory test");
    org.exoplatform.forum.service.Category cat = new org.exoplatform.forum.service.Category();
    addcategory.with()
               .category(cat).render();
  }
  
  @View
  @Route("/addcategory/{id}")
  public void editCategory(String id) throws Exception {
//    Category category = forumService.getCategory(id);
//    addcategory.with()
//               .category(category).render();
  }
  
  @View
  @Route("/form/addcategory/{id}")
  public void saveCategory(String id) throws Exception {

  }
}
