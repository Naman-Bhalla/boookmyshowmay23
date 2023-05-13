package com.scaler.boookmyshowmay23.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
//@NoArgsConstructor
//@AllArgsConstructor
public class Screen extends BaseModel {
    private String name;

    @OneToMany
    private List<Seat> seats;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection // mapping table -> screen_features
    private List<Feature> features;
}
