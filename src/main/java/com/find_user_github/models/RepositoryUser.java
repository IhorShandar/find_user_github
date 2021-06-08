package com.find_user_github.models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
@ToString
@Entity
public class RepositoryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name_rep;
    @ManyToOne(fetch = FetchType.EAGER)
    User user;

    public RepositoryUser(String name_rep) {
        this.name_rep = name_rep;
    }
}
