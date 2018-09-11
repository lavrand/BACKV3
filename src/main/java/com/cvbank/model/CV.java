package com.cvbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class CV {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
    private Long profileId;

    private String firstName;
    private String lastName;
	private String summary;
	private String about;
	private String email;
	private String phone;
	private String residence;
	private Date birthday;
	private String linkedin;
	private String position_preference;
	private String github;
	private String recommendations;
	private String title;
	private boolean activated;
    private Integer views;

	private String portfolio;

    @Column(name = "preferenced_area")
	private String preferencedArea;

  	@Column(name = "salary_from_preference")
	private Integer salaryFromPreference;

    @Column(name = "salary_till_preference")
	private Integer salaryTillPreference;

    //@JsonBackReference
    @OneToMany(mappedBy="cv")
    private List<CVactivity> cvActivity;

    //@JsonBackReference
    @OneToMany(mappedBy="cv")
    private List<Education> education;

    @ManyToOne
    @JoinColumn(name="template_id")
    private Template template;

    @ManyToMany
    @JoinTable(
            name="cv_lang",
            joinColumns=@JoinColumn(name="cv_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="lang_id", referencedColumnName="id"))
    private List<Languages> languages;

    //@JsonProperty("cvSkill")
    @ManyToMany
    @JoinTable(
            name="cv_skill",
            joinColumns=@JoinColumn(name="cv_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="skill_id", referencedColumnName="id"))
    private List<Skills> skills;
}






