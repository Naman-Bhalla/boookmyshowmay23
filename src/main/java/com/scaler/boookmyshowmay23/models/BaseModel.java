package com.scaler.boookmyshowmay23.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //getId()

    @CreatedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt; // getCreatedAt()

    @LastModifiedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastModifiedAt;
}
