package org.forum.portlet;

import org.exoplatform.web.application.RequestContext;
import org.exoplatform.forum.ForumUtils;
import org.exoplatform.forum.service.Category;
import org.exoplatform.forum.service.ForumService;
import org.exoplatform.forum.service.Topic;
import org.forum.portlet.qualifiers.Current;
import org.forum.portlet.utils.Utils;
import org.forum.portlet.templates.index;
import org.forum.portlet.templates.category;

import juzu.Action;
import juzu.Path;
import juzu.Resource;
import juzu.Response;
import juzu.SessionScoped;
import juzu.View;
import juzu.plugin.ajax.Ajax;
import juzu.template.Template;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Controller {
  @Inject ForumService forumService;
  
  @Inject @Path("index.gtmpl")    index index;
  @Inject @Path("category.gtmpl")    category category;
  @Inject @Path("forum.gtmpl")    forum forum;

//  @Inject @SessionScoped Identity currentUser;

  @View
  public void index() throws IOException, Exception  {
    List<Category> cates = forumService.getCategories();
    index.with()
         .categories(cates).render();
  }

  @View
  public void loadCategory(String cateId) throws Exception {
    category.with()
            .category(forumService.getCategory(cateId))
            .forums(forumService.getForums(cateId, null))
            .render();
  }

  @View
  public void loadForum(String cateId, String forumId) throws Exception {
    forum.with()
      .forums(forumService.getForum(cateId, forumId)).render();
  }
  
  public Topic getTopic(String topicPath) {
    Topic topic = null;
    if (topicPath != null && topicPath.trim().length() > 0) {
      topic = forumService.getTopicSummary(topicPath);
    }
    return topic;
  }
}
