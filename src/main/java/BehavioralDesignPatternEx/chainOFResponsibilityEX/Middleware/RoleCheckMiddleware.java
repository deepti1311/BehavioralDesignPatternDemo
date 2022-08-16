package BehavioralDesignPatternEx.chainOFResponsibilityEX.Middleware;

public class RoleCheckMiddleware extends Middleware{

    @Override
    public boolean check(String email, String password) {
      if (email.equals("admin@example.com")){
          System.out.println("hello, admin!");
          return  true;
      }
        System.out.println("Hello, User!");
      return checkNext(email,password);

    }
}
