package com.find_user_github.controllers;

import com.find_user_github.models.RepositoryUser;
import com.find_user_github.models.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    public static void main(String[] args) throws IOException, JSONException {
        System.out.print("Enter Github login: ");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String name = reader.readLine();

        String url_str = "https://api.github.com/users/"+ name;
        JSONObject jsonObject = new JSONObject(takeResponse(url_str).toString());
        String fullName = jsonObject.get("name").toString();

        List<RepositoryUser> userRepositories = new ArrayList<>();
        String url_str1 = jsonObject.get("repos_url").toString();
        JSONArray jsonArray = new JSONArray(takeResponse(url_str1).toString());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
            userRepositories.add(new RepositoryUser(jsonObject1.get("name").toString()));
        }
        User user = new User(fullName, userRepositories);

        System.out.println(user.getName());
        for (int i = 0; i < user.getRepos().size(); i++) {
            System.out.println("- " + user.getRepos().get(i).getName_rep());
        }
    }

//    @GetMapping("/{name}")
//    public User get(@PathVariable String name) throws IOException, JSONException {
//        String url_str = "https://api.github.com/users/"+ name;
//        JSONObject jsonObject = new JSONObject(takeResponse(url_str).toString());
//        String fullName = jsonObject.get("name").toString();
//
//
//        List<RepositoryUser> userRepositories = new ArrayList<>();
//        String url_str1 = jsonObject.get("repos_url").toString();
//        JSONArray jsonArray = new JSONArray(takeResponse(url_str1).toString());
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
//            userRepositories.add(new RepositoryUser(jsonObject1.get("name").toString()));
//        }
//
//        return new User(fullName, userRepositories);
//    }


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

