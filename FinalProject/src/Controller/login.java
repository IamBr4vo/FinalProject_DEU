/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.List;
import javax.swing.JOptionPane;
import Model.Users;
import Model.UsersDAO;
import View.Interface;
import View.votesFrame;

/**
 *
 * @author Bravo
 */
public class login {

    Users authenticatedUser = null;
    UsersDAO dao = new UsersDAO();
    CtrlUsers userCtrl = new CtrlUsers();
    //determines whether the user has been successfully authenticated or not.
    boolean isAuthenticated = false;

    public void login(String idNumber) {
        //read the User list
        List<Users> userList = dao.read();

        for (Users user : userList) {
            //validates if the email and key are the same as the entered data registered in the database
            if (user.getId_number().equals(idNumber)) {
                authenticatedUser = user;
                isAuthenticated = true;
                break;
            }
        }

        //Displays the frame if the user has been authenticated.
        if (isAuthenticated) {
            openFrame();
        } else {
            JOptionPane.showMessageDialog(null, "Número de cédula incorrecto", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Method to displays the frame 
    private void openFrame() {
        //Gets the authenticated user's role identifier
        int rolId = authenticatedUser.getRol_id();
        //if the role identifier is 1, the Admin frame is displayed.
        if (rolId == 1) {
            openAdminFrame();
            //if the role identifier is 2, the user frame is displayed.
        } else if (rolId == 2) {
            openUserFrame();
        }
    }

    private void openAdminFrame() {
        Interface login = new Interface();
        login.setVisible(true);
    }

    private void openUserFrame() {
        userCtrl.setRolId(2);
        votesFrame login = new votesFrame(authenticatedUser.getId());
        login.setVisible(true);
    }

    public boolean getIsAuthenticated() {
        return isAuthenticated;
    }
}
