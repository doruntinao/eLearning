/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Profesori;
import java.util.List;

/**
 *
 * @author Doruntina
 */
public class ProfesoriRepository extends EntMngClass implements ProfesoriInterface {

    @Override
    public void create(Profesori p) throws eLearningException {
     try {
            em.getTransaction().begin();
            em.persist(p);
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
    public void edit(Profesori p) throws eLearningException{
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("eLearning:" + e.getMessage());
        }
    }

    @Override
    public void delete(Profesori p)throws eLearningException {
       try {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("eLearning:" + e.getMessage());
        }
    }

    @Override
    public List<Profesori> findAll() throws eLearningException{
       try{ return (List<Profesori>)em.createNamedQuery("Profesori.findAll").getResultList();
    }
       catch(Exception e) {
           throw new eLearningException("eLearning:" + e.getMessage());
       }
    }

    @Override
    public Profesori findById(Integer id)  throws eLearningException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void test(){
        
    }
    
}
