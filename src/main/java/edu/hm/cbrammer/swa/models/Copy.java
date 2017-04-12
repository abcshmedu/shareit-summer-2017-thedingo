package edu.hm.cbrammer.swa.models;

public class Copy
{
    private final Medium medium;
    private final String owner;

    public Copy(Medium medium, String owner)
    {
        this.medium = medium;
        this.owner = owner;
    }

    public Medium getMedium()
    {
        return medium;
    }

    public String getOwner()
    {
        return owner;
    }
}
