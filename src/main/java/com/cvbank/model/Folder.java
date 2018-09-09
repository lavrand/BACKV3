package com.cvbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameFolder;

    //@JsonIgnore
    @ManyToMany
    @JoinTable(
            name="folder_cv",
            joinColumns=@JoinColumn(name="folder_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="cv_id", referencedColumnName="id"))
    private List<CV> cv;

  /*  @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="folder_profile",
            joinColumns=@JoinColumn(name="folder_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="profile_id", referencedColumnName="id"))
    private List<Profile> profile;*/

    @JsonIgnore
    private Long profileId;

}
