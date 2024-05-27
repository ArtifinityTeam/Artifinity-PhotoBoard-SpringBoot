package org.teamproject.teamproject.Vo;



import java.sql.Timestamp;
import java.util.Date;

public class ProjectVo {

    private int projectId;
    private String projectName;
    private Timestamp projectCreation;
    private int userId;
    private String userImage; // userImage 필드 추가
    private String content;
    private int tagId;
    private int views;
    private String tagName;
    private String filePath;
    private String fileName;
    private int background;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public ProjectVo() {
    }

    public ProjectVo(int projectId, String projectName, Timestamp projectCreation, String userImage, int userId, String content, int tagId, int views) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectCreation = projectCreation;
        this.userImage = userImage;
        this.userId = userId;
        this.content = content;
        this.tagId = tagId;
        this.views = views;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Timestamp getProjectCreation() {
        return projectCreation;
    }

    public void setProjectCreation(Timestamp projectCreation) {
        this.projectCreation = projectCreation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserImage() {
        return userImage; // userImage의 getter 메서드 추가
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage; // userImage의 setter 메서드 추가
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getViews() { return views; }

    public void setViews(int views) { this.views = views; }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}