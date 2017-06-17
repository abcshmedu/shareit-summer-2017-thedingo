package edu.hm.cbrammer.stachl.swa.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Disc extends Medium
{
    @Id
    private String barcode;
    private String director;
    private int fsk = -1;

    public Disc(String title, String barcode, String director, int fsk) {
        super(title);
        this.barcode = barcode;
        this.director = director;
        this.fsk = fsk;
    }

    public Disc()
    {
    }

    public String getBarcode() {
        return barcode;
    }

    public String getDirector() {
        return director;
    }

    public int getFsk() {
        return fsk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disc disc = (Disc) o;

        if (fsk != disc.fsk) return false;
        if (barcode != null ? !barcode.equals(disc.barcode) : disc.barcode != null) return false;
        if (!getTitle().equals(disc.getTitle())) return false;
        return director != null ? director.equals(disc.director) : disc.director == null;
    }

    @Override
    public String toString() {
        return "Disc{" +
                "barcode='" + barcode + '\'' +
                ", director='" + director + '\'' +
                ", fsk=" + fsk +
                '}';
    }
}
