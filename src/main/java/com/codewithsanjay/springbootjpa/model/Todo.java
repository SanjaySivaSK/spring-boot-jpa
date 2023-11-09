package com.codewithsanjay.springbootjpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue
    private int id;
    private String todo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userid",referencedColumnName = "id")
    private AppUser appUser;
}
