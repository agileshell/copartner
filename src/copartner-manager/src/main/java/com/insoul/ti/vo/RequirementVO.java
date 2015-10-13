package com.insoul.ti.vo;

import com.insoul.copartner.vo.UserLeanVO;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年10月13日 下午3:14:29
 */
public class RequirementVO extends BaseVO {

    private Integer type = 1;// 1加入团队， 2寻求搭档， 3寻求融资， 4寻求融智，5投资项目

    private Long projectId;// 关联项目
    
    private String projectName;

    private String content;// 需求内容

    private String status = "active";// 状态 active,inactive

    private Long userId;// 所属者
    
    private UserLeanVO user;

    private Long likeCount = 0L;// 收藏次数

    private Long commentCount = 0L;// 评论次数

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public UserLeanVO getUser() {
        return user;
    }

    public void setUser(UserLeanVO user) {
        this.user = user;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }
}