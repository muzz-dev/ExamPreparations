/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package User;

import Entity.TblUser;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author muzz
 */
@Stateless
public class UserSessionBean implements UserSessionBeanLocal {

    @PersistenceContext(unitName = "ExamPreparationsPU")
    EntityManager em;

    @Override
    public void insertUser(String username, String password) {
        TblUser user = new TblUser();
        user.setUsername(username);
        user.setPassword(password);
        em.persist(user);
    }

    @Override
    public Collection<TblUser> getAllUsers() {
        return em.createNamedQuery("TblUser.findAll").getResultList();
    }

    @Override
    public void updateUser(Integer userid, String username, String password) {
        TblUser user= em.find(TblUser.class, userid);
        user.setUsername(username);
        user.setPassword(password);
        em.merge(user); 
    }

    @Override
    public void removeUser(Integer userid) {
        TblUser id = em.find(TblUser.class, userid);
        em.remove(id);
    }

}
