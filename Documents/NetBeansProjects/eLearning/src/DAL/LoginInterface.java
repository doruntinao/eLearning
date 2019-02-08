/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Login;
import java.util.List;

/**
 *
 * @author Doruntina
 */
public interface LoginInterface {
    void create(Login l) throws eLearningException;
    void edit(Login l) throws eLearningException;
    void delete(Login l) throws eLearningException;
    List<Login> findAll() throws eLearningException;
    Login findById(Integer Id) throws eLearningException;
}
