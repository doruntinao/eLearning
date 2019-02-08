/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Login;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Doruntina
 */
public class LoginRepository extends EntMngClass implements LoginInterface {

    @Override
    public void create(Login l) throws eLearningException {
     try {
            em.getTransaction().begin();
            em.persist(l);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (e.getMessage().contains("2627")) {
                throw new eLearningException("E dhena veq ekziston!");
            } else {
                throw new eLearningException("Fixed Assets and Inventory:" + e.getMessage());
            }
        }
    }

    @Override
    public void edit(Login l) throws eLearningException{
        try {
            em.getTransaction().begin();
            em.merge(l);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("Fixed Assets and Inventory:" + e.getMessage());
        }
    }

    @Override
    public void delete(Login l)throws eLearningException {
       try {
            em.getTransaction().begin();
            em.remove(l);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("Fixed Assets and Inventory:" + e.getMessage());
        }
    }

    @Override
    public List<Login> findAll() throws eLearningException{
       try{ return (List<Login>)em.createNamedQuery("Login.findAll").getResultList();
    }
       catch(Exception e) {
           throw new eLearningException("Fixed Assets and Inventory:" + e.getMessage());
       }
    }

    
    public Login findByEmail(String username)  throws eLearningException{
        Query query = em.createQuery("select l from Login l where l.username=:username");
        query.setParameter("username", username);
        List<Login> login = query.getResultList();
        
        if(!login.isEmpty())
            return login.get(0);
        
        return null;
    }
    
    public void test(){
        
    }

    @Override
    public Login findById(Integer Id) throws eLearningException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
