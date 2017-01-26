package com.bionic.edu.mariana.controllers;

import com.bionic.edu.mariana.persistence.*;
import com.bionic.edu.mariana.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.bionic.edu.mariana.persistence.GroupDAOImpl.ALL_ARTICLES_GROUP;

@Controller
@RequestMapping("/*")
public class MyController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam(value = "group_id", defaultValue = "-1") String groupId,
                        @RequestParam(value = "usefulness_level_id", defaultValue = "-1") String usefulnessLevelId,
                        Model model) {
        model.addAttribute("groups", articleService.listGroups());
        model.addAttribute("levels", UsefulnessLevel.values());
        model.addAttribute("articles", articleService.getArticles(Long.valueOf(groupId), Integer.valueOf(usefulnessLevelId)));
        return "index";
    }

    @RequestMapping("/group/{id}")
    public String listGroup(@PathVariable("id") long groupId, Model model) {
        model.addAttribute("group_id", groupId);
        return "redirect:/";
    }

    @RequestMapping("/level/{id}")
    public String listLevel(@PathVariable(value = "id") String levelId, Model model) {
        model.addAttribute("usefulness_level_id", levelId);
        return "redirect:/";
    }

    @RequestMapping("/article_add_page")
    public String articleAddPage(Model model) {
        model.addAttribute("groups", articleService.listGroups());
        model.addAttribute("levels", UsefulnessLevel.values());
        model.addAttribute("article", new Article());
        return "article_add_page";
    }

    @RequestMapping("/{articleId}")
    public String editMerchant(@PathVariable String articleId, ModelMap model) {
        model.addAttribute("groups", articleService.listGroups());
        model.addAttribute("levels", UsefulnessLevel.values());
        int id = Integer.valueOf(articleId);
        Article article = articleService.findArticleById(id);
        model.addAttribute("article", article);
        model.addAttribute("id", articleId);
        model.addAttribute("link", article.getLink());
        model.addAttribute("name", article.getName());
        model.addAttribute("description", article.getDescription());
        model.addAttribute("usefulnessLevel", article.getUsefulnessLevel());
        model.addAttribute("group", article.getGroup());
        return "article_add_page";
    }

    @RequestMapping(value = "/save_article", method = RequestMethod.POST)
    public String articleAdd(@Valid @ModelAttribute("article") ArticleDTO articleDTO,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("groups", articleService.listGroups());
            model.addAttribute("levels", UsefulnessLevel.values());
            return "article_add_page";
        }
        Article article = new Article();

        article.setName(articleDTO.getName());
        article.setLink(articleDTO.getLink());
        article.setUsefulnessLevel(UsefulnessLevel.byValue(Integer.valueOf(articleDTO.getUsefulnessLevel())));
        article.setGroup(articleService.findGroup(Long.valueOf(articleDTO.getGroup())));
        article.setDescription(articleDTO.getDescription());

        articleService.addArticle(article);
        model.addAttribute("groups", articleService.listGroups());
        model.addAttribute("articles", articleService.listArticles(ALL_ARTICLES_GROUP));
        return ("redirect:/");
    }

    @RequestMapping("/group_add_page")
    public String groupAddPage() {
        return "group_add_page";
    }

    @RequestMapping("/group_delete_page")
    public String groupDeletePage(Model model) {
        model.addAttribute("groups", articleService.listUserGroups());
        return "group_delete_page";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("groups", articleService.listGroups());
        model.addAttribute("articles", articleService.searchArticles(pattern));
        return "index";
    }

    @RequestMapping(value = "/article/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteArticle(@RequestParam(value = "toDelete[]", required = false) long[] toDelete, Model model) {
        if (toDelete != null)
            articleService.deleteArticle(toDelete);
        model.addAttribute("groups", articleService.listGroups());
        model.addAttribute("articles", articleService.listArticles(null));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.POST)
    public String groupAdd(@RequestParam String name) {
        articleService.addGroup(new Group(name, GroupType.USER));
        return "redirect:/";
    }

    @RequestMapping(value = "/group/delete", method = RequestMethod.POST)
    public String deleteGroup(@RequestParam(value = "toDelete[]", required = false) long[] toDelete, Model model) {
        if (toDelete != null) {
            articleService.deleteGroup(toDelete);
        }
        model.addAttribute("groups", articleService.listGroups());
        return "redirect:/group_delete_page";
    }
}
