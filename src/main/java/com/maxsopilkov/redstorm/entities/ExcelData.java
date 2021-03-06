package com.maxsopilkov.redstorm.entities;

import javax.persistence.*;

//@Entity
//@Table(name = "excel_data")
public class ExcelData {
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private GDP gdp;
    private Double unempl;
    private Double hdi;
    private Double ioh;
    private Integer army;
    private Double milexp;
    private Integer resources;
    private Integer conflicts;
    private Boolean nuclear;
    private Boolean inWar;

    public ExcelData() {}

    public ExcelData(String name, GDP gdp, Double unempl, Double hdi, Double ioh, Integer army, Double milexp, Integer resources, Integer conflicts, Boolean nuclear, Boolean inWar) {
        this.name = name;
        this.gdp = gdp;
        this.unempl = unempl;
        this.hdi = hdi;
        this.ioh = ioh;
        this.army = army;
        this.milexp = milexp;
        this.resources = resources;
        this.conflicts = conflicts;
        this.nuclear = nuclear;
        this.inWar = inWar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GDP getGdp() {
        return gdp;
    }

    public void setGdp(GDP gdp) {
        this.gdp = gdp;
    }

    public Double getUnempl() {
        return unempl;
    }

    public void setUnempl(Double unempl) {
        this.unempl = unempl;
    }

    public Double getHdi() {
        return hdi;
    }

    public void setHdi(Double hdi) {
        this.hdi = hdi;
    }

    public Double getIoh() {
        return ioh;
    }

    public void setIoh(Double ioh) {
        this.ioh = ioh;
    }

    public Integer getArmy() {
        return army;
    }

    public void setArmy(Integer army) {
        this.army = army;
    }

    public Double getMilexp() {
        return milexp;
    }

    public void setMilexp(Double milexp) {
        this.milexp = milexp;
    }

    public Integer getResources() {
        return resources;
    }

    public void setResources(Integer resources) {
        this.resources = resources;
    }

    public Integer getConflicts() {
        return conflicts;
    }

    public void setConflicts(Integer conflicts) {
        this.conflicts = conflicts;
    }

    public Boolean getNuclear() {
        return nuclear;
    }

    public void setNuclear(Boolean nuclear) {
        this.nuclear = nuclear;
    }

    public Boolean getInWar() {
        return inWar;
    }

    public void setInWar(Boolean inWar) {
        this.inWar = inWar;
    }
}
