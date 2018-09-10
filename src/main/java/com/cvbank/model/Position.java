package com.cvbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Position {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String postName;
/*
        @JsonIgnore
        @OneToMany(mappedBy = "positionPreference", orphanRemoval = true)
        private List<CV> cv;
        */
}
