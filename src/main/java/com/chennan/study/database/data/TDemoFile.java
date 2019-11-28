package com.chennan.study.database.data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * demo class
 * 文件表的持久化类
 * @author 陈楠
 * @date 2019/11/27 11:10
 * To change this template use File | Settings | File Templates.
 */
public class TDemoFile implements Serializable {
    /**
     * 文件ID
     */
    private Integer fileId;
    /**
     * 文件名（逻辑名称：展示给用户看的名称）
     */
    private String fileName;
    /**
     * 文件路径（物理名称--磁盘中的名称）
     */
    private String fileUrl;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件长度
     */
    private Integer fileSize;
    /**
     * 操作时间
     */
    private Timestamp operTime;

    public TDemoFile() {
    }

    public TDemoFile(String fileName, String fileUrl, String fileType,
                     Integer fileSize, Timestamp operTime) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.operTime = operTime;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }
}