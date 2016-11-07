package beans;
// Generated Nov 7, 2016 2:03:08 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * AdminCommentReviewLog generated by hbm2java
 */
public class AdminCommentReviewLog  implements java.io.Serializable {


     private int adcCommentId;
     private Admin admin;
     private Comments comments;
     private UserInfo userInfo;
     private Date adcCommentRemoveTime;
     private String adcCommentRemoveMsg;

    public AdminCommentReviewLog() {
    }

	
    public AdminCommentReviewLog(Comments comments) {
        this.comments = comments;
    }
    public AdminCommentReviewLog(Admin admin, Comments comments, UserInfo userInfo, Date adcCommentRemoveTime, String adcCommentRemoveMsg) {
       this.admin = admin;
       this.comments = comments;
       this.userInfo = userInfo;
       this.adcCommentRemoveTime = adcCommentRemoveTime;
       this.adcCommentRemoveMsg = adcCommentRemoveMsg;
    }
   
    public int getAdcCommentId() {
        return this.adcCommentId;
    }
    
    public void setAdcCommentId(int adcCommentId) {
        this.adcCommentId = adcCommentId;
    }
    public Admin getAdmin() {
        return this.admin;
    }
    
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public Comments getComments() {
        return this.comments;
    }
    
    public void setComments(Comments comments) {
        this.comments = comments;
    }
    public UserInfo getUserInfo() {
        return this.userInfo;
    }
    
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    public Date getAdcCommentRemoveTime() {
        return this.adcCommentRemoveTime;
    }
    
    public void setAdcCommentRemoveTime(Date adcCommentRemoveTime) {
        this.adcCommentRemoveTime = adcCommentRemoveTime;
    }
    public String getAdcCommentRemoveMsg() {
        return this.adcCommentRemoveMsg;
    }
    
    public void setAdcCommentRemoveMsg(String adcCommentRemoveMsg) {
        this.adcCommentRemoveMsg = adcCommentRemoveMsg;
    }




}


