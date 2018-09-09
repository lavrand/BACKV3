package com.cvbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class CVactivity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	//private Long cv_id;//1 CV many CV activity
	@Column(name = "activity_type_id", length = 255)
	private Long activityTypeId;

	//@Column(name = "position_id", length = 255)
	private Long positionId;

 //   @JsonIgnore
////	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="position_preference_id")
//	private Position position;

	@Column(name = "description", length = 255)
	private String description;

	@Column(name = "year_start")
	private int yearStart;

	@Column(name = "year_end")
	private int yearEnd;

	@Column(name = "back_front")
	private int backFront;

    @JsonIgnore
    //@JsonManagedReference
    @ManyToOne
    @JoinColumn(name="cv_id")
    private CV cv;

}





