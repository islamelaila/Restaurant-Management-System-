package services.Interfaces;


import models.User;

public interface UserService {

    User login(String username, String password);


}