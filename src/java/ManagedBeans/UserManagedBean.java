/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package ManagedBeans;

import Entity.TblUser;
import User.UserSessionBeanLocal;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

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
    @PersistenceContext(unitName = "ExamPreparationsPU")
    EntityManager em;
    String Username, Password, msg;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
        System.out.println(userid);
    }

    public String insertUser() {
//        System.out.println(Username);
        this.usb.insertUser(Username, Password);
        return "/ExamPreparations/ViewUsers.jsf";
    }

    public Collection<TblUser> allUsers() {
        return this.usb.getAllUsers();
    }
    
    public void Submit(){
        System.out.println(userid);
    }

//    public String updateUser(Integer userid, String Username, String Password) {
//        System.out.println(userid);
//        this.usb.updateUser(userid, Username, Password);
//        return "/ExamPreparations/index.jsf";
//    }
//
//    public String removeUser(Integer userid) {
//        this.usb.removeUser(userid);
//        return "/ExamPreparations/index.jsf";
//    }

    public String Login() throws SQLException {
        try {
            TblUser user = (TblUser) em.createNamedQuery("TblUser.findbyUsername").setParameter("username", Username).getSingleResult();

            if ((user != null) && user.getPassword().equals(Password)) {
                HttpSession objHttpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                objHttpSession.setAttribute("username", Username);
                return "Welcome.xhtml?faces-redirect=true";
            }else{
                msg = "Username and/or password is incorrect";
            }

        } catch (Exception e) {
            msg = "Username and/or password is incorrect";
        }
        return null;
    }
    
    public void Logout() throws IOException{
        HttpSession objHttpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        objHttpSession.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
    }
    

}
