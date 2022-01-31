/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package User;

import Entity.TblUser;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author muzz
 */
@Local
public interface UserSessionBeanLocal {
    public void insertUser(String username, String password);
    public Collection<TblUser> getAllUsers();
    public void updateUser(Integer userid, String username, String password);
    public void removeUser(Integer userid);
}
