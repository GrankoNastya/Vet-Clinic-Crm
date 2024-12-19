package com.app.vetClinicApp.model.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Getter
@Setter
public class Pet extends BaseEntity {

    private String name;
    private String breed;
    @Column(name = "species")
    private String species;   //what type of pet (cat,dog,bird etc..)
    private int age;
    private String statement;  //description about pet health
    private String gender;


    @ManyToOne(fetch=FetchType.LAZY ,cascade = CascadeType.PERSIST)
    @JoinColumn(updatable = false)
    private PetOwner petOwner;  //a pet must have just one owner
}
