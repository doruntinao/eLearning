/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Kursi;
import java.util.List;

/**
 *
 * @author Doruntina
 */
public class KursiRepository extends EntMngClass implements KursiInterface {

    @Override
    public void create(Kursi k) throws eLearningException {
     try {
            em.getTransaction().begin();
            em.persist(k);
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
    public void edit(Kursi k) throws eLearningException{
        try {
            em.getTransaction().begin();
            em.merge(k);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("eLearning:" + e.getMessage());
        }
    }

    @Override
    public void delete(Kursi k)throws eLearningException {
       try {
            em.getTransaction().begin();
            em.remove(k);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("eLearning:" + e.getMessage());
        }
    }

    @Override
    public List<Kursi> findAll() throws eLearningException{
       try{ return (List<Kursi>)em.createNamedQuery("Kursi.findAll").getResultList();
    }
       catch(Exception e) {
           throw new eLearningException("eLearning:" + e.getMessage());
       }
    }

    @Override
    public Kursi findById(Integer id)  throws eLearningException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void test(){
        
    }
    
}
