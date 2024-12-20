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
    private String species;   //Тип животного (кот, собака, птица и т.д.)
    private int age;
    private String statement;  //Описание здоровья питомца
    private String gender;


    @ManyToOne(fetch=FetchType.LAZY ,cascade = CascadeType.PERSIST)
    @JoinColumn(updatable = false)
    private PetOwner petOwner;  //У питомца может быть только один владелец
}
