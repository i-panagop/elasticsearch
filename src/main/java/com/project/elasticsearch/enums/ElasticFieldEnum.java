package com.project.elasticsearch.enums;

import com.project.elasticsearch.utils.Constants;

public enum ElasticFieldEnum {

    STATE(Constants.STATE, null),
    VISIBLE(Constants.VISIBLE, null),
    APPROVED(Constants.APPROVED, null),
    TITLE_ANALYZED(Constants.TITLE_ANALYZED, null),
    ARTIST_ID_ANALYZED(Constants.ARTIST_ID_ANALYZED, null),
    GALLERIES_ID_ANALYZED(Constants.GALLERIES_ID_ANALYZED, Constants.GALLERIES),
    SUBMITTED_BY_ID_ANALYZED(Constants.SUBMITTED_BY_ID_ANALYZED, null);

    private String id;
    private String nestedPath;
    
    ElasticFieldEnum(String id, String nestedPath) {
        this.id = id;
        this.nestedPath = nestedPath;
    }

    public static ElasticFieldEnum findById(String id){
        for (ElasticFieldEnum elEnum : values()){
            if(elEnum.getId().equals(id)){
                return elEnum;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNestedPath() {
        return nestedPath;
    }

    public void setNestedPath(String nestedPath) {
        this.nestedPath = nestedPath;
    }
}
