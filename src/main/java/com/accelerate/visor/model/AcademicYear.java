package com.accelerate.visor.model;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
@JsonIgnoreType
@javax.persistence.Entity


@Table(name = "academicYear")
@javax.persistence.SequenceGenerator(name = "default_gen", sequenceName = "academicYear_id_seq", allocationSize = 1)
public class AcademicYear extends Entity {

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private Integer lowerCalendarYear;

    @Column(nullable = false, unique = true)
    private Integer upperCalendarYear;

    /*public AcademicYear(String code, String name, Integer lowerCalendarYear, Integer upperCalendarYear) {
        this.name = name;
        this.code = code;
        if (lowerCalendarYear == null || upperCalendarYear == null || upperCalendarYear - lowerCalendarYear != 1) {
            throw new AccelerateException("Please add Correct Lower and Upper AY");
        }
        this.lowerCalendarYear = lowerCalendarYear;
        this.upperCalendarYear = upperCalendarYear;
    }*/

    @Deprecated
    public AcademicYear(String code, String name) {
        this.name = name;
        this.code = code;
        if (!name.isEmpty()) {
            this.lowerCalendarYear = Integer.parseInt(name.split("-")[0]);
            this.upperCalendarYear = Integer.parseInt(name.split("-")[1]);
        }
    }

    public AcademicYear() {
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<Integer> getApplicableYears() {
        return Arrays.asList(name.split("-")).stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
    }

    public String getPreviousYearAcademicYearName() {
        return String.format("%s-%s", getApplicableYears().get(0) - 1, getApplicableYears().get(0));
    }

    public Integer getLowerCalendarYear() {
        return lowerCalendarYear;
    }

    public Integer getUpperCalendarYear() {
        return upperCalendarYear;
    }

    @Override
    public String toString() {
        return "AcademicYear{" +
                "code='" + code + '\'' +
                '}';
    }

    public String getNextYearAcademicYearName() {
        return String.format("%s-%s", getApplicableYears().get(1), getApplicableYears().get(1) + 1);
    }

    public String getCurrentAcademicYearFormattedName() {
        return String.format("%s-%s",
                String.valueOf(getApplicableYears().get(0)),
                String.valueOf(getApplicableYears().get(1)).substring(2, 4));

    }

    public boolean isCurrentOrLastAcademicYear(AcademicYear academicYear) {
        return this.equals(academicYear) || getPreviousYearAcademicYearName().equals(academicYear.getName());
    }

    public boolean is2122Onwards() {
        return Integer.parseInt(getCode()) >= 2122;
    }


    public boolean isNextAcademicYearOf(AcademicYear academicYear) {
        return getLowerCalendarYear() - academicYear.getLowerCalendarYear() == 1;
    }

    public boolean isPrevAcademicYearOf(AcademicYear academicYear) {
        return academicYear.getLowerCalendarYear() - getLowerCalendarYear() == 1;
    }
}
