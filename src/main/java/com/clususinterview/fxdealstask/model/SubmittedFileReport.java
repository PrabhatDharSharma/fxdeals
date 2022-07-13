package com.clususinterview.fxdealstask.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "submiited_file_report")
public class SubmittedFileReport {

    @Id
    @SequenceGenerator(
            name="submitted_file_report__sequence",
            sequenceName = "submitted_file_report__sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "submitted_file_report__sequence"
    )
    private Long id;

    @Column(name = "source_file_name", nullable = false)
    private String sourceFileName;

    @Column(name = "valid__fx_deals_count")
    private Integer validDealsCount;

    @Column(name = "invalid_fx_deals_count")
    private Integer invalidDealscount;

    @Column(name = "import_duration")
    private int importDuration;

    @Column(name = "import_timestamp")
    private Date importDate;

    public String getSourceFileName() {
        return sourceFileName;
    }


    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }


    public int getValidDealsCount() {
        return validDealsCount;
    }


    public void setValidDealsCount(Integer validDealsCount) {
        this.validDealsCount = validDealsCount;
    }

    public int getInvalidDealsCount() {
        return invalidDealscount;
    }

    public void setInvalidDealsCount(Integer invalidDealsCount) {
        this.invalidDealscount = invalidDealsCount;
    }

    public int getImportDuration() {
        return importDuration;
    }

    public void setImportDuration(int importDuration) {
        this.importDuration = importDuration;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }
}
