package com.example.restapi.controllers.uiControllers;

import com.example.restapi.models.appEntities.IssueEntity;
import com.example.restapi.services.issue.IssueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("ui/issue")
public class IssueUIController {
    private IssueService issueService;

    public IssueUIController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public String getIssues(Model model){
        List<IssueEntity> issues = issueService.findAll();
        model.addAttribute("issues", issues);
        return "issues";
    }
}
