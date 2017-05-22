package edu.hm.cbrammer.stachl.swa.models;

public class User
{
    private final String name;
    private final String password;

    public User()
    {
        name = "";
        password = "";
    }

    public User(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!name.equals(user.name)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode()
    {
        int result = name.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }
}
