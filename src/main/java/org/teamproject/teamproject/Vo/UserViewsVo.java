package org.teamproject.teamproject.Vo;


public class UserViewsVo {

    private int userId;
    private int projectId;

    public UserViewsVo() {}

    public UserViewsVo(int userId, int projectId) {
        this.userId = userId;
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}



