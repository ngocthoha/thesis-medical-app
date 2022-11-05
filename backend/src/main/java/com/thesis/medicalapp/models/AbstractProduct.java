package com.thesis.medicalapp.models;
import lombok.Data;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class AbstractProduct extends AuditLog {
    private String name;
    private Integer registrationNumber;
    private String price;
    private Double favorite;
    private String info;
    private String imageUrl;
}
