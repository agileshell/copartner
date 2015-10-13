package com.insoul.ti.req;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午7:02:30
 */
public class ContestEntryListRequest extends PageRequest {

    private Long userId;// 参赛项目名称

    private Long contestId;// 参与的大赛ID

    @Override
    protected ContestEntryListRequest Q() {
        StringBuilder sb = new StringBuilder();
        boolean appended = false;
        if (userId != null && userId > 0L) {
            sb.append("userId").append("=").append(userId);
            appended = true;
        }
        if (contestId != null && contestId > 0L) {
            if (appended) {
                sb.append("&");
            }
            sb.append("contestId").append("=").append(contestId);
            appended = true;
        }
        getQuery().setQueryString(sb.toString());
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }
}
