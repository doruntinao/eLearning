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
public interface ProfesoriInterface {
    void create(Profesori p) throws eLearningException;
    void edit(Profesori p) throws eLearningException;
    void delete(Profesori p) throws eLearningException;
    List<Profesori> findAll() throws eLearningException;
    Profesori findById(Integer Id) throws eLearningException;
}
