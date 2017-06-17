package edu.hm.cbrammer.stachl.swa.models;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Medium implements Serializable
{
    private String title;

    public Medium()
    {

    }
    public Medium(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medium medium = (Medium) o;

        return title != null ? title.equals(medium.title) : medium.title == null;
    }
}
