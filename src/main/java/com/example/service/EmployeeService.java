package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

import com.example.domain.Employee;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    /**
     * 従業員情報を全県取得する
     * @return List<Employee> 全従業員情報のリスト
     */
    public List<Employee> showList() {
        List<Employee> employeeList = new ArrayList<Employee>();

        employeeList = employeeRepository.findAll();

        return employeeList;
    }
}
