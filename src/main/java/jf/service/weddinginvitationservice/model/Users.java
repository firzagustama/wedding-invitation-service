package jf.service.weddinginvitationservice.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "Users")
public class Users {
    @Id
    @Column(name = "id", length = 16, unique = true, nullable = false)
    private UUID id = UUID.randomUUID();
    private String name;
    private int gender; // 0 = male, 1 = female
    private boolean married;
    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<UserWishes> userWishes;

    @OneToOne(mappedBy = "user")
    private Rsvp rsvp;

    public Users() {
    }

    public Users(String name, int gender, boolean married, String phoneNumber) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.gender = gender;
        this.married = married;
        this.phoneNumber = phoneNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public List<UserWishes> getUserWishes() {
        return userWishes;
    }

    public void setUserWishes(List<UserWishes> userWishes) {
        this.userWishes = userWishes;
    }

    public Rsvp getRsvp() {
        return rsvp;
    }

    public void setRsvp(Rsvp rsvp) {
        this.rsvp = rsvp;
    }
}
