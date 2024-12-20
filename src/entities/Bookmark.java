package entities;

import constants.KidFriendlyStatus;

public abstract class Bookmark {
    private long id;
    private String title;
    private String profileUrl;

    private KidFriendlyStatus kidFriendlyStatus = KidFriendlyStatus.UNKNOWN;


    private User kidFriendlyMarkedBy;

    private User sharedBy;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public User getKidFriendlyMarkedBy() {
        return kidFriendlyMarkedBy;
    }

    public void setKidFriendlyMarkedBy(User kidFriendlyMarkedBy) {
        this.kidFriendlyMarkedBy = kidFriendlyMarkedBy;
    }

    public User getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(User sharedBy) {
        this.sharedBy = sharedBy;
    }


    public void setKidFriendlyStatus(String kidFriendlyStatus) {
        kidFriendlyStatus = kidFriendlyStatus;
    }

    public KidFriendlyStatus getKidFriendlyStatus() {
        return kidFriendlyStatus;
    }

    public void setKidFriendlyStatus(KidFriendlyStatus kidFriendlyStatus) {
        this.kidFriendlyStatus = kidFriendlyStatus;
    }

    public abstract boolean isKidFriendelyEligible();

    public abstract String getItemData();
}
