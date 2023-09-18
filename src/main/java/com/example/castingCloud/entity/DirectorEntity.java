package com.example.castingCloud.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "director")
@Table(name = "director")
@Data
public class DirectorEntity {
    @Id
    private int directorId;
    private String directorEmail;
    private String directorPassword;
    private String directorPhoneNumber;
    private String directorName;
    private String directorCompany;
    private String directorProfile;
}
