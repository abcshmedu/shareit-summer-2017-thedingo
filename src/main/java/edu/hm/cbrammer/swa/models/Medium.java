package edu.hm.cbrammer.swa.models;

public class Medium
{
    private final String title;

    public Medium(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medium medium = (Medium) o;

        return title != null ? title.equals(medium.title) : medium.title == null;
    }

    @Override
    public int hashCode()
    {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "Medium{" +
                "title='" + title + '\'' +
                '}';
    }
}
