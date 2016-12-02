package entities;
// Generated Nov 21, 2016 3:22:25 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * UserInfo generated by hbm2java
 */
@JsonIgnoreProperties(value={"adminAccountFrozenLog","followsesForFollowerId","adminCommentReviewLogs",
		"adminMomentsReviewLogs","followsByUsrId","likeses", 
		"commentses", "momentses","groupMemberses", "userPassword",
		"usrId","securityId","firstLogin","lastLogin"})
public class UserInfo  implements java.io.Serializable {


     private Integer usrId;
     private String usrName;
     private String usrNickname;
     private String usrDesc;
     private String usrPhotoUri;
     private String usrEmail;
     private Integer usrLoginType;
     private Integer usrAccountStatus;
     private String securityId;
     private Date firstLogin;
     private Date lastLogin;
     private AdminAccountFrozenLog adminAccountFrozenLog;
     private Set followsesForFollowerId = new HashSet(0);
     private Set adminCommentReviewLogs = new HashSet(0);
     private Set adminMomentsReviewLogs = new HashSet(0);
     private Follows followsByUsrId;
     private Set likeses = new HashSet(0);
     private Set commentses = new HashSet(0);
     private Set momentses = new HashSet(0);
     private Set groupMemberses = new HashSet(0);
     private UserPassword userPassword;

    public UserInfo() {
    }

    public UserInfo(String usrName, String usrNickname, String usrDesc, String usrPhotoUri, String usrEmail, Integer usrLoginType, Integer usrAccountStatus, String securityId, Date firstLogin, Date lastLogin, AdminAccountFrozenLog adminAccountFrozenLog, Set followsesForFollowerId, Set adminCommentReviewLogs, Set adminMomentsReviewLogs, Follows followsByUsrId, Set likeses, Set commentses, Set momentses, Set groupMemberses, UserPassword userPassword) {
       this.usrName = usrName;
       this.usrNickname = usrNickname;
       this.usrDesc = usrDesc;
       this.usrPhotoUri = usrPhotoUri;
       this.usrEmail = usrEmail;
       this.usrLoginType = usrLoginType;
       this.usrAccountStatus = usrAccountStatus;
       this.securityId = securityId;
       this.firstLogin = firstLogin;
       this.lastLogin = lastLogin;
       this.adminAccountFrozenLog = adminAccountFrozenLog;
       this.followsesForFollowerId = followsesForFollowerId;
       this.adminCommentReviewLogs = adminCommentReviewLogs;
       this.adminMomentsReviewLogs = adminMomentsReviewLogs;
       this.followsByUsrId = followsByUsrId;
       this.likeses = likeses;
       this.commentses = commentses;
       this.momentses = momentses;
       this.groupMemberses = groupMemberses;
       this.userPassword = userPassword;
    }
   
    public Integer getUsrId() {
        return this.usrId;
    }
    
    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }
    public String getUsrName() {
        return this.usrName;
    }
    
    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }
    public String getUsrNickname() {
        return this.usrNickname;
    }
    
    public void setUsrNickname(String usrNickname) {
        this.usrNickname = usrNickname;
    }
    public String getUsrDesc() {
        return this.usrDesc;
    }
    
    public void setUsrDesc(String usrDesc) {
        this.usrDesc = usrDesc;
    }
    public String getUsrPhotoUri() {
        return this.usrPhotoUri;
    }
    
    public void setUsrPhotoUri(String usrPhotoUri) {
        this.usrPhotoUri = usrPhotoUri;
    }
    public String getUsrEmail() {
        return this.usrEmail;
    }
    
    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }
    public Integer getUsrLoginType() {
        return this.usrLoginType;
    }
    
    public void setUsrLoginType(Integer usrLoginType) {
        this.usrLoginType = usrLoginType;
    }
    public Integer getUsrAccountStatus() {
        return this.usrAccountStatus;
    }
    
    public void setUsrAccountStatus(Integer usrAccountStatus) {
        this.usrAccountStatus = usrAccountStatus;
    }
    public String getSecurityId() {
        return this.securityId;
    }
    
    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }
    public Date getFirstLogin() {
        return this.firstLogin;
    }
    
    public void setFirstLogin(Date firstLogin) {
        this.firstLogin = firstLogin;
    }
    public Date getLastLogin() {
        return this.lastLogin;
    }
    
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    public AdminAccountFrozenLog getAdminAccountFrozenLog() {
        return this.adminAccountFrozenLog;
    }
    
    public void setAdminAccountFrozenLog(AdminAccountFrozenLog adminAccountFrozenLog) {
        this.adminAccountFrozenLog = adminAccountFrozenLog;
    }
    public Set getFollowsesForFollowerId() {
        return this.followsesForFollowerId;
    }
    
    public void setFollowsesForFollowerId(Set followsesForFollowerId) {
        this.followsesForFollowerId = followsesForFollowerId;
    }
    public Set getAdminCommentReviewLogs() {
        return this.adminCommentReviewLogs;
    }
    
    public void setAdminCommentReviewLogs(Set adminCommentReviewLogs) {
        this.adminCommentReviewLogs = adminCommentReviewLogs;
    }
    public Set getAdminMomentsReviewLogs() {
        return this.adminMomentsReviewLogs;
    }
    
    public void setAdminMomentsReviewLogs(Set adminMomentsReviewLogs) {
        this.adminMomentsReviewLogs = adminMomentsReviewLogs;
    }
    public Follows getFollowsByUsrId() {
        return this.followsByUsrId;
    }
    
    public void setFollowsByUsrId(Follows followsByUsrId) {
        this.followsByUsrId = followsByUsrId;
    }
    public Set getLikeses() {
        return this.likeses;
    }
    
    public void setLikeses(Set likeses) {
        this.likeses = likeses;
    }
    public Set getCommentses() {
        return this.commentses;
    }
    
    public void setCommentses(Set commentses) {
        this.commentses = commentses;
    }
    public Set getMomentses() {
        return this.momentses;
    }
    
    public void setMomentses(Set momentses) {
        this.momentses = momentses;
    }
    public Set getGroupMemberses() {
        return this.groupMemberses;
    }
    
    public void setGroupMemberses(Set groupMemberses) {
        this.groupMemberses = groupMemberses;
    }
    public UserPassword getUserPassword() {
        return this.userPassword;
    }
    
    public void setUserPassword(UserPassword userPassword) {
        this.userPassword = userPassword;
    }




}


