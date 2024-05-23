package com.java5.assignment.controllers.admin;

import com.java5.assignment.content.Page;
import com.java5.assignment.content.PageType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ManageBrandController {
    @ModelAttribute("page")
    public Page page() {
        return Page.route.get(PageType.ADMIN_BRAND);
    }

    @GetMapping("/manage-brand")
    public String get() {
        return "admin/layout";
    }

}