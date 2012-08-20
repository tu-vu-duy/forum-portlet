package org.forum.portlet;

import org.exoplatform.web.application.RequestContext;
import org.exoplatform.forum.service.Category;
import org.exoplatform.forum.service.ForumService;
import org.forum.portlet.qualifiers.Current;
import org.forum.portlet.templates.index;
import org.forum.portlet.templates.categories;
import org.forum.portlet.utils.Utils;

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
  @Inject @Path("categories.gtmpl")    categories categories;

//  @Inject @SessionScoped Identity currentUser;

  @View
  public void index() throws IOException, Exception  {
    index.render();
  }

  @View
  public void loadForums() throws Exception {
//    String userId = currentUser.getRemoteId();
   List<Category> cates = forumService.getCategories();
   
   categories.with()
             .categories(cates).render();
    
  }

}
