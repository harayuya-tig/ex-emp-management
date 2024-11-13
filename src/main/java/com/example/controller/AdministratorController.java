package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;

import com.example.form.LoginForm;

@Controller
@RequestMapping("/")
public class AdministratorController {
    
    /** サービスクラスの変数の宣言 */
    @Autowired
    private AdministratorService administratorService;
    /** セッションクラスの変数の宣言 */
    @Autowired
    private HttpSession session;

    /**
     * ログイン画面へフォワードする
     * @param form ログイン画面入力情報
     * @return ログイン画面のview
     */
    @GetMapping("/")
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }

    @PostMapping("/login")
    public String login(LoginForm loginForm, Model model) {
        
        System.out.println(loginForm.getMailAddress());
        System.out.println(loginForm.getPassword());

        try {
            Administrator administrator = administratorService.login(loginForm.getMailAddress(), loginForm.getPassword());
            session.setAttribute("administratorName", administrator.getName());
            return "redirect:/employee/showList";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です");
            return "administrator/login";
        }
    }

    /**
     * 管理者情報登録画面へフォワードする
     * @param form
     * @return 管理者情報登録画面のviewへのパス
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    /**
     * 管理者情報を登録する
     * @param form
     * @return ログイン画面にリダイレクト
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();
        
        administrator.setName(form.getName());
        administrator.setMailAddress(form.getMailAddress());
        administrator.setPassword(form.getPassword());

        administratorService.insert(administrator);

        return "redirect:/";
    }
}