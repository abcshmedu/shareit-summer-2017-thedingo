package edu.hm.cbrammer.stachl.swa.models;

import java.util.ArrayList;
import java.util.List;

public class UserRepository
{
    public static final String SECRET = "NSfRkNVR0CsgJ324iJFnCQ5CnR/FNrR2TkbSIaYbcOSqpg5oxggpassWmJFx9CxhUKl02T8fCw3Ds+4C5NgzEA==";
    private static final List<User> users = new ArrayList<>();
    static {
        users.add(new User("Admin","1234"));
    }

    public static boolean authenticateUser(User user){
        for(User u : users) {
            if(u.equals(user))
                return true;
        }
        return false;
    }
}
