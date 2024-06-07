package com.java5.assignment.controllers.admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java5.assignment.utils.Page;
import com.java5.assignment.utils.PageType;
import com.java5.assignment.model.ProductVersionModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class ManageOrderController {
    @ModelAttribute("page")
    public Page page() {
        return Page.route.get(PageType.ADMIN_ORDER);
    }

    @GetMapping("/manage-order")
    public String get(Model model) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/static/json/product-version.json");
        byte[] jsonData = inputStream.readAllBytes();
        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductVersionModel> productVersionModels = objectMapper.readValue(jsonData, new TypeReference<List<ProductVersionModel>>() {
        });

        model.addAttribute("productVersions", productVersionModels);
        return "admin/layout";
    }

}
