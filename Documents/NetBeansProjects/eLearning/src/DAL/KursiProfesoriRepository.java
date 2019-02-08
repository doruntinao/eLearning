/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.KursiProfesori;
import java.util.List;

/**
 *
 * @author Doruntina
 */
public class KursiProfesoriRepository extends EntMngClass implements KursiProfesoriInterface {

    @Override
    public void create(KursiProfesori kp) throws eLearningException {
     try {
            em.getTransaction().begin();
            em.persist(kp);
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
    public void edit(KursiProfesori kp) throws eLearningException{
        try {
            em.getTransaction().begin();
            em.merge(kp);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("eLearning:" + e.getMessage());
        }
    }

    @Override
    public void delete(KursiProfesori kp)throws eLearningException {
       try {
            em.getTransaction().begin();
            em.remove(kp);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("eLearning:" + e.getMessage());
        }
    }

    @Override
    public List<KursiProfesori> findAll() throws eLearningException{
       try{ return (List<KursiProfesori>)em.createNamedQuery("KursiProfesori.findAll").getResultList();
    }
       catch(Exception e) {
           throw new eLearningException("eLearning:" + e.getMessage());
       }
    }

    @Override
    public KursiProfesori findById(Integer id)  throws eLearningException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void test(){
        
    }
    
}
