package com.example.demo.domainEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;

import lombok.Data;

@Entity
@Data
public class Burger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long burger_id;
	
	@NotNull
	@Size(min = 3,max = 100,message = "Validation size error")
	private String burger_name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Ingredient> ingredients;
	
	private Date creation_date;
	
	@PrePersist
	void  creation_date ( ) {
		creation_date = new Date();
	}
	
}
