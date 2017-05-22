package edu.hm.cbrammer.stachl.swa.models;

import java.util.HashMap;
import java.util.Map;

public class UserRepository
{
    public static final String SECRET = "NSfRkNVR0CsgJ324iJFnCQ5CnR/FNrR2TkbSIaYbcOSqpg5oxggpassWmJFx9CxhUKl02T8fCw3Ds+4C5NgzEA==";
    private static final Map<String, User> users = new HashMap<>();

    static
    {
        User admin = new User("Admin", "1234");
        users.put(admin.getName(), admin);
    }

    public static boolean authenticateUser(User user)
    {
        if (users.containsKey(user.getName()))
        {
            return users.get(user.getName()).getPassword().equals(user.getPassword());
        }
        return false;
    }
}
