package com.scaler.boookmyshowmay23.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //getId()
    private Date createdAt; // getCreatedAt()
    private Date lastModifiedAt;
}
