/**
 * フォームに入力された管理者情報を受け取る
 * @author harayuya
 */

package com.example.form;

public class AdministratorForm {
    
    /** 名前 */
    private String name;
    /** メールアドレス */
    private String mailAddress;
    /** パスワード */
    private String password;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMailAddress() {
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password + "]";
    }
}
