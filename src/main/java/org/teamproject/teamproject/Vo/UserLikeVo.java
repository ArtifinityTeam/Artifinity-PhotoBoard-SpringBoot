package org.teamproject.teamproject.Vo;

public class UserLikeVo {

    private long likeId;
    private long userId;
    private long projectId;

    public UserLikeVo() {
    }

    public UserLikeVo(long likeId, long userId, long projectId) {
        this.likeId = likeId;
        this.userId = userId;
        this.projectId = projectId;
    }

    public long getLikeId() {
        return likeId;
    }

    public void setLikeId(long likeId) {
        this.likeId = likeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}
