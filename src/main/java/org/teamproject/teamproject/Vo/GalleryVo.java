package org.teamproject.teamproject.Vo;



public class GalleryVo {

    private int galleryId;
    private String galleryName;
    private String galleryCreation_date;
    private int userId;
    private int projectId;

    public GalleryVo(){

    }
    public GalleryVo(int galleryId, String galleryName, String galleryCreation_date,
                     int userId, int projectId) {

    }


    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    public String getGalleryName() {
        return galleryName;
    }

    public void setGalleryName(String galleryName) {
        this.galleryName = galleryName;
    }

    public String getGalleryCreation_date() {
        return galleryCreation_date;
    }

    public void setGalleryCreation_date(String galleryCreation_date) {
        this.galleryCreation_date = galleryCreation_date;
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

