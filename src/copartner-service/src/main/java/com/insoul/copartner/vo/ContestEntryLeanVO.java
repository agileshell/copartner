package com.insoul.copartner.vo;

public class ContestEntryLeanVO {

    private Long id;

    private ProjectLeanVO project;

    private UserLeanVO user;

    private long votes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectLeanVO getProject() {
        return project;
    }

    public void setProject(ProjectLeanVO project) {
        this.project = project;
    }

    public UserLeanVO getUser() {
        return user;
    }

    public void setUser(UserLeanVO user) {
        this.user = user;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

}
