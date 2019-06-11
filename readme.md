# About

This is the back end code of alcohol application. 

The Link of ios application <https://github.com/yudongl/Project>

The Link of Android application <https://github.com/PuKaiShen/HesterLab>

The link of management system  https://github.com/gunannan1/alcohol-management



# How to deploy the project

1. Apply for a cloud server(mine is ubuntu 18.04),install Java(more than 1.8) and 
   other common tools. The server may have timezone problem. You can use 

   ```
   cd /etc
   sudo vim timezone
   ```

   to change the content to Australia/Melbourne. If not work ,you can try 

   ```
   cp /usr/share/zoneinfo/Australia/Melbourne /etc/localtime
   
   ```

   If you don't care about time, please ignore.

2. Install MySql(Mine is 8.0.16) in cloud server,allow remote access. The commands of allow remote access is like this:

   ```
   mysql -uroot -p
   create user 'root'@'%' identified by 'your  password';  
   grant all privileges on *.* to 'root'@'%'; 
   flush privileges;
   ```

   Then install mysqlworkbench in your local computer,remote access your mysql in cloud server,implement alcohol.sql in doc folder.  If you still cannot remote access, check the firewall of the cloud server and its security group. Maybe the port is not open. You can also use `netstat -an | grep 3306 `to check your port information

3. Install Tomcat(mine is 9.0). Then you can modify the port of the tomcat from 8080 to 80. Please note that if you use 80 port, you must add sudo in front ot the startup command. Command is like this:

   ```
   sudo sh /usr/local/tomcat9/bin/startup.sh 
   sudo sh /usr/local/tomcat9/bin/shutdown.sh 
   ```

   /usr/local/tomcat9/ is my tomcat's root directory.

4. Use Intellij Idea(recommend) open the java source code. Open application-prod.properties. Modify `spring.datasource.password=SwFqNhqk9JkUW88` to your own mysql password. If needed, you can also change other properties such as `file.local-prefix` or  `file.dropbox-prefix` according your need. Please note that sometimes `file.local-prefix` may also have permission problem.

5. Use maven to create jar. Cd to the project's root directory and use the command 

   ```
   mvn clean
   mvn install
   ```

   You can also use the Intellij Idea to fininsh this step. Then you will see alcohol.jar in target folder.

6. Move the alcohol.jar to your cloud server. Use the following command to run it.

   ```
   sudo nohup java -jar -server alcohol.jar & 
   ```

   If you want to stop the server ,type like this

   ```
   sudo jps
   sudo kill -9 2222(just example,this is the process id of jar)
   ```

7. Download the source code of management system on <https://github.com/gunannan1/alcohol-management> . Use webstorm(recommend) to open it.

8. Change BASE_API in prod.env.js in config package to ip address of cloud server. If you want to run it locally, please modify two assetsPublicPath in config/index.js from  './' to '/'.

9. Use `npm install`to download related dependencies. Use `npm run build` to package it. Use `npm run dev` to run it locally. After `npm run build`,you can see the dist folder.  If there are errors, check if you have installed some related enviroment such as npm,node.js,vue,webpack. 

10. Open the dist folder ,copy index.html and static to /usr/local/tomcat9/webapps/index/ .  /usr/local/tomcat9 is the root directory of tomcat and yours may different from mine. Folder index is not exist at beginning, you must create it firdt but it can have any name. 

11. Start tomcat.Input <http://45.113.232.152/index> in your local browser to see if the management system is run(Change 45.113.232.152 to your cloud server's ip). The local url of the system is  <http://localhost:8090. 

12. Before you deploy it ,you can also run the project locally and use postman to test it. It's very easy.  In fact, my deployment approach is simple but not good. I recommend you to use Nginx and you can package the java program to war and put it under Tomcat. Some steps is not very detailed, if you meet problems, you can google it or contact me gunannan1@qq.com. I also learned the knowledge of how to deploy a project from google.



# How to get access token of dropbox

If you want every researcher user their own dropbox, you must create access token of dropbox. Please see article <http://99rabbits.com/get-dropbox-access-token/>  or <https://www.iperiusbackup.net/en/create-dropbox-app-get-authentication-token/> . And then when you create or edit researcher in management system, use the new accrss token. If you just want to use the same dropbox to store all records, just copy the access token of researcher "alcoholproject2019".



# Notice

1. If you meet some errors when using management system, you can try logout and login again.
2. The project is now deployed in Netcar research server 45.113.232.152, it can work now. But this server has 6 months limit, so it will expire on Sep 2019. So maybe you need to deploy it on a new server.
3. Please be careful about the security of the  database. Our database has ever been attacked by hacker. So please set a strong password and limit the authority of remote access.