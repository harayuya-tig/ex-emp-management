/**
 * administratorsテーブルを操作するリポジトリ
 * 
 * @author harayuya
 */

package com.example.repository;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.example.domain.Administrator;

@Repository
public class AdministratorRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    /**
     * 管理者情報を挿入する
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator) {
        String sql = "INSERT INTO administrators(name,mail_address,password) VALUES(:name,:mailAddress,:password);";
        
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

        template.update(sql, param);
    }

    /**
     * メールアドレスとパスワードから管理者情報を取得する。
     * 一件も存在しない場合はnullを返す
     * @param mailAddress メールアドレス
     * @param password パスワード
     * @return Administrator 管理者情報
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address = :mailAddress AND password = :password;";

        SqlParameterSource param 
            = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
        
        Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
        
        if(administrator == null) {
            return null;
        }

        return administrator;
    }

}
