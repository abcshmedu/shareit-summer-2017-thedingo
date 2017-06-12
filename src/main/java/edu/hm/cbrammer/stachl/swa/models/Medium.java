package edu.hm.cbrammer.stachl.swa.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="TMedium")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Medium implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

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
