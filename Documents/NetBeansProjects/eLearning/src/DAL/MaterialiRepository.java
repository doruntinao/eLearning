/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Materiali;
import java.util.List;

/**
 *
 * @author Doruntina
 */
public class MaterialiRepository extends EntMngClass implements MaterialiInterface {

    @Override
    public void create(Materiali m) throws eLearningException {
     try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (e.getMessage().contains("2627")) {
                throw new eLearningException("E dhena veq ekziston!");
            } else {
                throw new eLearningException("eLearning:" + e.getMessage());
            }
        }
    }

    @Override
    public void edit(Materiali m) throws eLearningException{
        try {
            em.getTransaction().begin();
            em.merge(m);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("eLearning:" + e.getMessage());
        }
    }

    @Override
    public void delete(Materiali m)throws eLearningException {
       try {
            em.getTransaction().begin();
            em.remove(m);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("eLearning:" + e.getMessage());
        }
    }

    @Override
    public List<Materiali> findAll() throws eLearningException{
       try{ return (List<Materiali>)em.createNamedQuery("Materiali.findAll").getResultList();
    }
       catch(Exception e) {
           throw new eLearningException("eLearning:" + e.getMessage());
       }
    }

    @Override
    public Materiali findById(Integer id)  throws eLearningException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void test(){
        
    }
    
}
