package com.find_user_github.services;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.find_user_github.models.User;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserMutation implements GraphQLMutationResolver {

    @Autowired
    private UserServices userServices;

    public User createUserFromGithub(final  String name) throws IOException, JSONException {
        return this.userServices.createUserFromGithub(name);
    }
}
