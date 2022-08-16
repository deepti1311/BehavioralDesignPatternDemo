package BehavioralDesignPatternEx.chainOFResponsibilityEX;

import BehavioralDesignPatternEx.chainOFResponsibilityEX.Middleware.Middleware;
import BehavioralDesignPatternEx.chainOFResponsibilityEX.Middleware.RoleCheckMiddleware;
import BehavioralDesignPatternEx.chainOFResponsibilityEX.Middleware.ThrottlingMiddleware;
import BehavioralDesignPatternEx.chainOFResponsibilityEX.Middleware.UserExistsMiddleware;
import BehavioralDesignPatternEx.chainOFResponsibilityEX.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientDemo {

    private  static BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    private  static  void  init(){
        server= new Server();
        server.register("admin@example.com","admin");

        server.register("user@example.com","user");


        Middleware middleware= Middleware.link(
                new ThrottlingMiddleware(2),
                new UserExistsMiddleware(server),
                new RoleCheckMiddleware()
        );

        server.setMiddleware(middleware);
    }

    public  static void main(String[] args)throws IOException{
        init();
        boolean success;
        do {
            System.out.println("Enter email:");
            String email = reader.readLine();
            System.out.println("input password");
            String password= reader.readLine();
            success= server.logIn(email,password);
        }while (!success);
    }
}
