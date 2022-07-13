package com.clususinterview.fxdealstask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmittedFileReportDto {

    private String sourceFileName;

    private Integer validDealsCount;

    private Integer invalidDealsCount;

    private Integer importDuration;

    private Date importDate;
}
