package com.cvbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameFolder;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "folder_cv", joinColumns = @JoinColumn(name = "folder_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "cv_id", referencedColumnName = "id"))
    public List<CV> cv;

    @JsonIgnore
    private Long profileId;

}
