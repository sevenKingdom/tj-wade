package com.carry.control.model.po;

import java.io.Serializable;

public class ConstructionPlan implements Serializable {
    private static final long serialVersionUID = 5140224039425783832L;
    private Long id;
    private String bridgeName;
    private String pierNum;
    private String structure;
    private String process;
    private String category;
    private Short isNeedDemonstrate;
    private Long constructionPlanFile;
    private Short isNeedApprove;
    private Short isAlreadyDemonstrate;
    private Long classId;
    private Long technicianId;
    private String whether;
    private Long inspectorId;
    private Long createdAt;
    private String point;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBridgeName() {
        return bridgeName;
    }

    public void setBridgeName(String bridgeName) {
        this.bridgeName = bridgeName;
    }

    public String getPierNum() {
        return pierNum;
    }

    public void setPierNum(String pierNum) {
        this.pierNum = pierNum;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Short getIsNeedDemonstrate() {
        return isNeedDemonstrate;
    }

    public void setIsNeedDemonstrate(Short isNeedDemonstrate) {
        this.isNeedDemonstrate = isNeedDemonstrate;
    }

    public Long getConstructionPlanFile() {
        return constructionPlanFile;
    }

    public void setConstructionPlanFile(Long constructionPlanFile) {
        this.constructionPlanFile = constructionPlanFile;
    }

    public Short getIsNeedApprove() {
        return isNeedApprove;
    }

    public void setIsNeedApprove(Short isNeedApprove) {
        this.isNeedApprove = isNeedApprove;
    }

    public Short getIsAlreadyDemonstrate() {
        return isAlreadyDemonstrate;
    }

    public void setIsAlreadyDemonstrate(Short isAlreadyDemonstrate) {
        this.isAlreadyDemonstrate = isAlreadyDemonstrate;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(Long technicianId) {
        this.technicianId = technicianId;
    }

    public String getWhether() {
        return whether;
    }

    public void setWhether(String whether) {
        this.whether = whether;
    }

    public Long getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(Long inspectorId) {
        this.inspectorId = inspectorId;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "ConstructionPlan{" +
                "id=" + id +
                "bridgeName=" + bridgeName +
                "pierNum=" + pierNum +
                "structure=" + structure +
                "process=" + process +
                "category=" + category +
                "isNeedDemonstrate=" + isNeedDemonstrate +
                "constructionPlanFile=" + constructionPlanFile +
                "isNeedApprove=" + isNeedApprove +
                "isAlreadyDemonstrate=" + isAlreadyDemonstrate +
                "classId=" + classId +
                "technicianId=" + technicianId +
                "whether=" + whether +
                "inspectorId=" + inspectorId +
                "cratedAt=" + createdAt +
                '}';
    }
}