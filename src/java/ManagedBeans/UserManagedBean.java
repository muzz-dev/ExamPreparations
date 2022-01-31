/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package ManagedBeans;

import Entity.TblUser;
import User.UserSessionBeanLocal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author muzz
 */
@ManagedBean(name = "userManagedBean")
@SessionScoped
@Dependent
public class UserManagedBean {

    @EJB
    UserSessionBeanLocal usb;
    String Username, Password;
    Integer userid;

    public UserSessionBeanLocal getUsb() {
        return usb;
    }

    public void setUsb(UserSessionBeanLocal usb) {
        this.usb = usb;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
    }

    public String insertUser() {
//        System.out.println(Username);
        this.usb.insertUser(Username, Password);
        return "/ExamPreparations/index.jsf";
    }

    public Collection<TblUser> allUsers() {
        return this.usb.getAllUsers();
    }

    public String updateUser(Integer userid, String Username, String Password) {
        System.out.println(userid);
        this.usb.updateUser(userid, Username, Password);
        return "/ExamPreparations/index.jsf";
    }

    public String removeUser(Integer userid) {
        this.usb.removeUser(userid);
        return "/ExamPreparations/index.jsf";
    }

}
