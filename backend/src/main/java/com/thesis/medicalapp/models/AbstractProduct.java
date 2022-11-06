package com.thesis.medicalapp.models;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class AbstractProduct extends AuditLog {
    private String name;
    private Integer registrationNumber;
    private String price;
    private Double favorite;
    @Type(type="text")
    private String info;
    private String imageUrl;
}
