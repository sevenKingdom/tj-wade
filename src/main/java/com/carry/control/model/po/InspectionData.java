package com.carry.control.model.po;

/**
 * Created by songxianying on 17/8/13.
 */
public class InspectionData {
    private Long inspectorid;
    private Long planid;
    private String quality;
    private String lowquality;
    private Integer style;
    private Long createdat;
    private Long classid;
    private String token;
    private String department;

    public Long getInspectorid() {
        return inspectorid;
    }

    public void setInspectorid(Long inspectorid) {
        this.inspectorid = inspectorid;
    }

    public Long getPlanid() {
        return planid;
    }

    public void setPlanid(Long planid) {
        this.planid = planid;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getLowquality() {
        return lowquality;
    }

    public void setLowquality(String lowquality) {
        this.lowquality = lowquality;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public Long getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Long createdat) {
        this.createdat = createdat;
    }

    public Long getClassid() {
        return classid;
    }

    public void setClassid(Long classid) {
        this.classid = classid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
