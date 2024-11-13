package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.List;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    
    /** サービスクラスのオブジェクトを注入 */
    @Autowired
    private EmployeeService employeeService;

    /** フォームオブジェクトをrequestスコープに格納 */
    @ModelAttribute
    public UpdateEmployeeForm setUpForm() {
        return new UpdateEmployeeForm();
    }

    /** 
     * 従業員一覧を出力する
     * @param model requestスコープのオブジェクト
     * @return 従業員一覧のviewへフォワード
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        List<Employee> employeeList = employeeService.showList();
        
        for (Employee employee : employeeList) {
            System.out.println(employee.getDependentsCount());
        }
        
        model.addAttribute("employeeList", employeeList);

        return "employee/list";
    }

    @GetMapping("/showDetail")
    public String showDetail(String id, UpdateEmployeeForm form, Model model) {
        Employee employee = employeeService.showDetail(Integer.parseInt(id));

        model.addAttribute("employee", employee);

        return "employee/detail";
    }
}
