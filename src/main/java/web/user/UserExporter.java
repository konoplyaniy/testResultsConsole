package web.user;

//import hibernate.dao.UserDao;
//import hibernate.entities.UserEntity;

import db_worker.dao.UserDao;
import db_worker.entities.UserEntity;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;


@ManagedBean(name = "login")
@ViewScoped
public class UserExporter implements Serializable{
    private UserDao userDAO = new UserDao();
    public UserEntity user = new UserEntity();

    public String send() {
        userDAO.openCurrentSession();
        user = userDAO.getUser(user.getUsername(), user.getPassword());
        userDAO.closeCurrentSession();
        if (user != null) {
            System.out.println(true);
            return "/mainpage.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "User not found!", " Login Error!"));
            return null;
        }
    }

    public UserDao getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}