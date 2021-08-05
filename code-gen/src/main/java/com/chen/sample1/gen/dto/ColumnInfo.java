package com.chen.sample1.gen.dto;

import java.io.Serializable;


public class ColumnInfo implements Serializable {
    private String columnName;
    private String dataType;
    private String columnComment;
    private String columnKey;
    private String extra;

    public ColumnInfo(){}

    public ColumnInfo(String columnName, String dataType, String columnComment, String columnKey, String extra){
        this.columnName = columnName;
        this.dataType = dataType;
        this.columnComment = columnComment;
        this.columnKey = columnKey;
        this.extra = extra;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
