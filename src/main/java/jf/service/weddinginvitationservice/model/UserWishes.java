package jf.service.weddinginvitationservice.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class UserWishes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String wish;
    private Date createdDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users user;

    public UserWishes() {
    }

    public UserWishes(String wish, Date createdDate, Users user) {
        this.id = id;
        this.wish = wish;
        this.createdDate = createdDate;
        this.user = user;
    }

    public UserWishes(int id, String wish, Date createdDate, Users user) {
        this.id = id;
        this.wish = wish;
        this.createdDate = createdDate;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
