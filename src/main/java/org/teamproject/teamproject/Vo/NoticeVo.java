package org.teamproject.teamproject.Vo;

import java.sql.Date;

public class NoticeVo {

    private int noticeId;
    private String noticeTitle;
    private String noticeContent;
    private Date noticeCreation;
    private int userId;
    private String userName;


    public NoticeVo() {

    }


    public NoticeVo(String noticeTitle, String noticeContent, Date noticeCreation, int userId) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeCreation = noticeCreation;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public java.sql.Date getNoticeCreation() {
        return noticeCreation;
    }

    public void setNoticeCreation(java.sql.Date noticeCreation) {
        this.noticeCreation = noticeCreation;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
