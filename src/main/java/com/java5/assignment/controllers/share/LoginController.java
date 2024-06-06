package com.java5.assignment.controllers.share;

import com.java5.assignment.content.Page;
import com.java5.assignment.content.PageType;
import com.java5.assignment.dto.UserDto;
import com.java5.assignment.entities.User;
import com.java5.assignment.jpa.UserRepository;
import com.java5.assignment.model.share.LoginModel;
import com.java5.assignment.services.CookieService;
import com.java5.assignment.services.EncodeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    CookieService cookieService;

    @Autowired
    HttpSession session;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EncodeService encode;

    @ModelAttribute("page")
    public Page setPageContent() {
        return Page.route.get(PageType.LOGIN);
    }

    @GetMapping("/login")
    public String login() {
        String usernameCookie = cookieService.getCookie("username");

        if(usernameCookie!=null){
            User user = null;
            for(User u : userRepository.findAll()){
                String username = u.getUsername();
                if(encode.checkCode(username, usernameCookie)){
                    user = u;
                }
            }
            if(user!=null){
                UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getRole(), user.getStatus());
                session.setAttribute("user", userDto);
                return "redirect:/home";
            }
        }
        return "public/login";
    }

    @PostMapping("/login")
    public String goToPage(Model model, @Valid LoginModel loginModel, BindingResult error) {
        if(!error.hasErrors()) {
            User user = userRepository.findByUsername(loginModel.getUsername());
            if(user!=null){
                String loginPassword = loginModel.getPassword();
                String userPassword = user.getPassword();
                if(!encode.checkCode(loginPassword, userPassword)){
                    error.addError(new FieldError("loginModel", "username", "Username or password is incorrect!"));
                    loginModel.setPassword("");
                }
            }
            else{
                error.addError(new FieldError("loginModel", "username", "Username or password is incorrect!"));
                loginModel.setPassword("");
            }
        }
        if(error.hasErrors()) {
            for(FieldError err : error.getFieldErrors()){
                if(err.getField().contentEquals("username")){
                    loginModel.setUsername("");
                }
                if(err.getField().contentEquals("password")){
                    loginModel.setPassword("");
                }
            }
            model.addAttribute("loginError", error);
            model.addAttribute("login", loginModel);
            model.addAttribute("page", "login");
            return "public/login";
        }
        else{
            User user = userRepository.findByUsername(loginModel.getUsername());
            UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getRole(), user.getStatus());
            session.setAttribute("user", userDto);
            if(loginModel.isRememberMe()){
                cookieService.setCookie("username", user.getUsername(), 3);
            }
            if(user.getRole()){
                return "redirect:/dashboard";
            }
            else{
                return "redirect:/home";
            }
        }
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("user");
        cookieService.setCookie("username", "", 0);
        return "redirect:/login";
    }

}
