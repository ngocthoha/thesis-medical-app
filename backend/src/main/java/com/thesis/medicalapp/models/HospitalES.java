package com.thesis.medicalapp.models;

import com.thesis.medicalapp.indices.Indices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = Indices.HOSPITAL_INDEX)
public class HospitalES {
    @Id
    private String id;
    private String name;
    private Address address;
    private String hospitalImageUrl;
}
