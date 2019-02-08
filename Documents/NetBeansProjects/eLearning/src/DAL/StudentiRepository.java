/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Studenti;
import java.util.List;

/**
 *
 * @author Doruntina
 */
public class StudentiRepository extends EntMngClass implements StudentiInterface {

    @Override
    public void create(Studenti s) throws eLearningException {
     try {
            em.getTransaction().begin();
            em.persist(s);
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
    public void edit(Studenti s) throws eLearningException{
        try {
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("eLearning:" + e.getMessage());
        }
    }

    @Override
    public void delete(Studenti s)throws eLearningException {
       try {
            em.getTransaction().begin();
            em.remove(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("eLearning:" + e.getMessage());
        }
    }

    @Override
    public List<Studenti> findAll() throws eLearningException{
       try{ return (List<Studenti>)em.createNamedQuery("Studenti.findAll").getResultList();
    }
       catch(Exception e) {
           throw new eLearningException("eLearning:" + e.getMessage());
       }
    }

    @Override
    public Studenti findById(Integer id)  throws eLearningException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void test(){
        
    }
    
}
