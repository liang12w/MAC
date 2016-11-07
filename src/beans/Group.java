package beans;
// Generated Nov 7, 2016 2:03:08 PM by Hibernate Tools 4.3.1



/**
 * Group generated by hbm2java
 */
public class Group  implements java.io.Serializable {


     private int grpId;
     private String grpGroupName;
     private GroupMembers groupMembers;

    public Group() {
    }

	
    public Group(int grpId) {
        this.grpId = grpId;
    }
    public Group(int grpId, String grpGroupName, GroupMembers groupMembers) {
       this.grpId = grpId;
       this.grpGroupName = grpGroupName;
       this.groupMembers = groupMembers;
    }
   
    public int getGrpId() {
        return this.grpId;
    }
    
    public void setGrpId(int grpId) {
        this.grpId = grpId;
    }
    public String getGrpGroupName() {
        return this.grpGroupName;
    }
    
    public void setGrpGroupName(String grpGroupName) {
        this.grpGroupName = grpGroupName;
    }
    public GroupMembers getGroupMembers() {
        return this.groupMembers;
    }
    
    public void setGroupMembers(GroupMembers groupMembers) {
        this.groupMembers = groupMembers;
    }




}


