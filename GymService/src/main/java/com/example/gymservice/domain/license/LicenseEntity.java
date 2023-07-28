package com.example.gymservice.domain.license;

import com.example.gymservice.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Entity
public class LicenseEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer licenseSeq;
    @Setter
    private String licenseName;
    @Setter
    private Integer count;
    @Setter
    private Integer period;

    private LicenseEntity(Integer licenseSeq, String licenseName, Integer count, Integer period) {
        this.licenseSeq = licenseSeq;
        this.licenseName = licenseName;
        this.count = count;
        this.period = period;
    }

    protected LicenseEntity() {

    }

    public static LicenseEntity of(Integer licenseSeq, String licenseName, Integer count, Integer period) {
        return new LicenseEntity(licenseSeq, licenseName, count, period);
    }
}
