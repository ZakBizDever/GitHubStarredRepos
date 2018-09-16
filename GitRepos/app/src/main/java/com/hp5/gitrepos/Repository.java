package com.hp5.gitrepos;

/**
 * Created by Zàk.J on 15/09/2018.
 */

public class Repository {

    private String repoName;
    private String repoDescription;
    private String repoOwnerAvatarUrl;
    private String repoOwnerName;
    private String repoStarsCount;

    public Repository(String repoName, String repoDescription, String repoOwnerAvatarUrl, String repoOwnerName, String repoStarsCount) {
        this.repoName = repoName;
        this.repoDescription = repoDescription;
        this.repoOwnerAvatarUrl = repoOwnerAvatarUrl;
        this.repoOwnerName = repoOwnerName;
        this.repoStarsCount = repoStarsCount;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoDescription() {
        return repoDescription;
    }

    public void setRepoDescription(String repoDescription) {
        this.repoDescription = repoDescription;
    }

    public String getRepoOwnerAvatarUrl() {
        return repoOwnerAvatarUrl;
    }

    public void setRepoOwnerAvatarUrl(String repoOwnerAvatarUrl) {
        this.repoOwnerAvatarUrl = repoOwnerAvatarUrl;
    }

    public String getRepoOwnerName() {
        return repoOwnerName;
    }

    public void setRepoOwnerName(String repoOwnerName) {
        this.repoOwnerName = repoOwnerName;
    }

    public String getRepoStarsCount() {
        return repoStarsCount;
    }

    public void setRepoStarsCount(String repoStarsCount) {
        this.repoStarsCount = repoStarsCount;
    }
}
