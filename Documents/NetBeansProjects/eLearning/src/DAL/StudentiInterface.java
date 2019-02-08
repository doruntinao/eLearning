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
public interface StudentiInterface {
    void create(Studenti p) throws eLearningException;
    void edit(Studenti p) throws eLearningException;
    void delete(Studenti p) throws eLearningException;
    List<Studenti> findAll() throws eLearningException;
    Studenti findById(Integer Id) throws eLearningException;
}
