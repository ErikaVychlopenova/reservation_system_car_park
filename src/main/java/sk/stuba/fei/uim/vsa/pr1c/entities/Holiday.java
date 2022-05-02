package sk.stuba.fei.uim.vsa.pr1c.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Holiday implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private Date date;

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    @Override
//    public String toString() {
//        return "Holiday{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", date=" + date +
//                "}\n";
//    }
}
