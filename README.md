# find_user_github

METHOD 1

Go to the page: https://hub.docker.com/r/shandar1997/users_github/tags?page=1&ordering=last_updated. There are docker images that are created in this program. Pull these images from this page in own docker or insert in terminal commands:

docker pull shandar1997/users_github:mysqldb

docker pull shandar1997/users_github:app_jdk

Than create containers by these images. First run MySQL database:

docker run --name localhost -e MYSQL_ROOT_PASSWORD=1111 -e MYSQL_DATABASE=test_github -e MYSQL_PASSWORD=1111 -d shandar1997/users_github:mysqldb

Wait until the database is ready to use (about 30s). Then run application (app_jdk):

docker run -d -p 8089:8080 --name app --link localhost:mysql  shandar1997/users_github:app_jdk

After open page: http://localhost:8089/graphiql. Run command to add user from github by login:

mutation{
  createUserFromGithub(login:"dhh"){
    name,
    repos{
      name_rep
    }
  }
}

Find users from database by login:

query{
    userByLogin(login: "IhorShandar"){
    name,
    repos {
      name_rep
    }
  }
}


Find users from database by id:

query{
    user(id: 1){
    name,
    repos {
      name_rep
    }
  }
}

METHOD 2

Download archive this project from github, unpack it. Open this project in IDE. Open terminal in IDE and run command: docker-compose up

After open page: http://localhost:8089/graphiql. Use the commands written above.
