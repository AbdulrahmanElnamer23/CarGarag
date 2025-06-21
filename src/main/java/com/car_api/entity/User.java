package com.car_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private String phone;
    private String bankAccountNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private Car car;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    public Boolean isDeleted() {
        return isDeleted;
    }


    public void setIsDeleted(Boolean b) {
    }
}