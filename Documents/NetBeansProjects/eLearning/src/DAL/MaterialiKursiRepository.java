/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.MaterialiKursi;
import java.util.List;

/**
 *
 * @author Doruntina
 */
public class MaterialiKursiRepository extends EntMngClass implements MaterialiKursiInterface {

    @Override
    public void create(MaterialiKursi mk) throws eLearningException {
     try {
            em.getTransaction().begin();
            em.persist(mk);
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
    public void edit(MaterialiKursi mk) throws eLearningException{
        try {
            em.getTransaction().begin();
            em.merge(mk);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("eLearning:" + e.getMessage());
        }
    }

    @Override
    public void delete(MaterialiKursi mk)throws eLearningException {
       try {
            em.getTransaction().begin();
            em.remove(mk);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new eLearningException("eLearning:" + e.getMessage());
        }
    }

    @Override
    public List<MaterialiKursi> findAll() throws eLearningException{
       try{ return (List<MaterialiKursi>)em.createNamedQuery("MaterialiKursi.findAll").getResultList();
    }
       catch(Exception e) {
           throw new eLearningException("eLearning:" + e.getMessage());
       }
    }

    @Override
    public MaterialiKursi findById(Integer id)  throws eLearningException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void test(){
        
    }
    
}
