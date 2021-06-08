package com.find_user_github.services;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.find_user_github.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserQuery implements GraphQLQueryResolver {

    @Autowired
    private UserServices userServices;

    public List<User> getUsers(final int count){
        return this.userServices.getAllUser(count);
    }

    public Optional<User> getUser(final int id){
        return this.userServices.getUser(id);
    }

    public User getUserByLogin(final String login){
        return this.userServices.getUserByLogin(login);
    }

}
