package com.insoul.copartner.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.insoul.copartner.util.CustomDateSerializer;

public class QuestionDetailVO implements Serializable {

    private static final long serialVersionUID = 4889120000840392742L;

    private Long questionId;

    private String title;// 标题

    private Long categoryId;// 问题类别Id

    private String categoryName;// 问题类别

    private String content;// 内容

    private UserLeanVO questioner;// 提问者

    private UserLeanVO tutor;// 导师

    private List<AnswerVO> answers;// 回答

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date created;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserLeanVO getQuestioner() {
        return questioner;
    }

    public void setQuestioner(UserLeanVO questioner) {
        this.questioner = questioner;
    }

    public UserLeanVO getTutor() {
        return tutor;
    }

    public void setTutor(UserLeanVO tutor) {
        this.tutor = tutor;
    }

    public List<AnswerVO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerVO> answers) {
        this.answers = answers;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
