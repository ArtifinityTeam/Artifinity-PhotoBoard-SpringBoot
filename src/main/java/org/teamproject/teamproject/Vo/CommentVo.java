package org.teamproject.teamproject.Vo;


import java.sql.Timestamp;

public class CommentVo {

    private long commentId;
    private String content;
    private int projectId;
    private int userId; // UserVo의 userId와 타입이 일치해야 합니다.
    private String userName;
    private String userImage; // userImage 필드 추가
    private java.sql.Timestamp created_at;
    private java.sql.Timestamp updated_at;

    public CommentVo() {
    }

    public CommentVo(long commentId, String content, int projectId, int userId, String userName, String userImage,  java.sql.Timestamp created_at, java.sql.Timestamp updated_at) {
        this.commentId = commentId;
        this.content = content;
        this.projectId = projectId;
        this.userId = userId;
        this.userName = userName;
        this.userImage = userImage;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage; // userImage의 getter 메서드 추가
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage; // userImage의 setter 메서드 추가
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}

