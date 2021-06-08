package com.find_user_github.services;

import com.find_user_github.models.RepositoryUser;
import com.find_user_github.models.User;
import com.find_user_github.repository.RepositoryRepo;
import com.find_user_github.repository.UserRepo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServices {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RepositoryRepo repositoryRepo;

    @Transactional
    public User createUserFromGithub(String login) throws IOException, JSONException {

        String url_str = "https://api.github.com/users/"+ login;
        JSONObject jsonObject = new JSONObject(takeResponse(url_str).toString());
        String fullName = jsonObject.get("name").toString();

        User user = new User();

        List<RepositoryUser> userRepositories = new ArrayList<>();
        String url_str1 = jsonObject.get("repos_url").toString();
        JSONArray jsonArray = new JSONArray(takeResponse(url_str1).toString());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
            userRepositories.add(new RepositoryUser(jsonObject1.get("name").toString()));
        }

        user.setName(fullName);
        user.setLogin(login);
        user.setRepos(userRepositories);
        this.userRepo.save(user);

        userRepositories.forEach(repositoryUser -> {
            repositoryUser.setUser(user);
            this.repositoryRepo.save(repositoryUser);
        });

        return user;
    }

    @Transactional
    public List<User> getAllUser(final int count){
        return this.userRepo.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Transactional
    public Optional<User> getUser(final int id){
        return this.userRepo.findById(id);
    }

    @Transactional
    public User getUserByLogin(final String login){
        return this.userRepo.findByLogin(login);
    }

    private static StringBuilder takeResponse(String url_str) throws IOException {
        URL url = new URL(url_str);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        InputStream inputStream = httpURLConnection.getInputStream();

        int read;
        StringBuilder response = new StringBuilder();
        while ((read = inputStream.read()) != -1){
            response.append((char) read);
        }
        return response;
    }
}
