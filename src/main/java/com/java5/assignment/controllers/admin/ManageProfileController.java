package com.java5.assignment.controllers.admin;

import com.java5.assignment.utils.Page;
import com.java5.assignment.utils.PageType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ManageProfileController {
    @ModelAttribute("page")
    public Page page() {
        return Page.route.get(PageType.PROFILE);
    }

    @GetMapping("/manage-profile")
    public String get() {
        return "admin/layout";
    }

}
