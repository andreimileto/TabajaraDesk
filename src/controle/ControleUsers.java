/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.UserDAO;
import entidade.Users;
import java.util.ArrayList;

/**
 *
 * @author AndreiMileto
 */


public class ControleUsers {
      Users user;
    ArrayList<Users> users;

    
     public ArrayList<Users> listar(Users user) {
        this.user = user;
        UserDAO usuarioDAO = new UserDAO();
        return usuarioDAO.listar(user);

    }
}
