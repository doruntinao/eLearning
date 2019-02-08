/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Kursi;
import BLL.StudentiKursi;
import java.util.List;

/**
 *
 * @author Doruntina
 */
public class StudentiKursiRepository extends EntMngClass implements StudentiKursiInterface {

    @Override
    public void create(StudentiKursi sk) throws eLearningException {
     try {
            em.getTransaction().begin();
            em.persist(sk);
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
    public void edit(StudentiKursi sk) throws eLearningException{
        try {
            em.getTransaction().begin();
            em.merge(sk);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("eLearning:" + e.getMessage());
        }
    }

    @Override
    public void delete(StudentiKursi sk)throws eLearningException {
       try {
            em.getTransaction().begin();
            em.remove(sk);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("eLearning:" + e.getMessage());
        }
    }

    @Override
    public List<StudentiKursi> findAll() throws eLearningException{
       try{ return (List<StudentiKursi>)em.createNamedQuery("StudentiKursi.findAll").getResultList();
    }
       catch(Exception e) {
           throw new eLearningException("eLearning:" + e.getMessage());
       }
    }

    @Override
    public StudentiKursi findById(Integer id)  throws eLearningException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void test(){
        
    }
    
}
