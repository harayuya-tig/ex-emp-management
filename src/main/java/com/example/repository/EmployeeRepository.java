/**
 * employeesテーブルを操作するリポジトリ
 * 
 * @author harayuya
 */

package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.util.List;

import com.example.domain.Employee;

@Repository
public class EmployeeRepository {
    
    @Autowired
    NamedParameterJdbcTemplate template;

    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setMailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependents_count"));
        
        return employee;
    };

    /**
     * 従業員一覧情報を入社日順で取得する
     * @return List<Employee> 従業員一覧のリスト
     */
    public List<Employee> findAll() {
        String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees ORDER BY hire_date DESC;";
        
        List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);

        return employeeList;
    }

    /**
     * 主キーから従業員情報を取得する
     * @param id ID
     * @return Employee 従業員情報
     */
    public Employee load(Integer id) {
        Employee employee = new Employee();

        String sql = "";
        
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);    
        
        employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
        
        return employee; 
    }

    /**
     * 従業員情報を変更する
     * @param employee 従業員情報
     */
    public void update(Employee employee) {
        String sql = "UPDATE employees SET name=':name',image=':image',gender=':gender',hire_date=':hireDate',mail_address=':mailAddress',zip_code=':zipCode',address=':address',telephone=':telephone',salary=':salary',characteristics=':characteristics' WHERE id=23;";
        
        SqlParameterSource param 
            = new MapSqlParameterSource()
            .addValue("name", employee.getName())
            .addValue("image", employee.getImage())
            .addValue("gender", employee.getGender())
            .addValue("hireDate", employee.getHireDate())
            .addValue("mailAddress", employee.getMailAddress())
            .addValue("zipCode", employee.getZipCode())
            .addValue("address", employee.getAddress())
            .addValue("telephone", employee.getTelephone())
            .addValue("salary", employee.getSalary())
            .addValue("characteristics", employee.getCharacteristics())
            .addValue("dependentsCount", employee.getDependentsCount());

        template.update(sql, param);
            
    } 
}
