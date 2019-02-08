/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import BLL.StudentiKursi;
import java.util.List;

/**
 *
 * @author Doruntina
 */
public interface StudentiKursiInterface {
    
    void create(StudentiKursi sk) throws eLearningException;
    void edit(StudentiKursi sk) throws eLearningException;
    void delete(StudentiKursi sk) throws eLearningException;
    List<StudentiKursi> findAll() throws eLearningException;
    StudentiKursi findById(Integer Id) throws eLearningException;
    
}
