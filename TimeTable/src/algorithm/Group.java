package algorithm;


public class Group {
    private final int groupId;
    private final int groupSize;
    private final int moduleIds[];
    private final String groupname;


    public Group(int groupId,String groupname, int groupSize, int moduleIds[]){
        this.groupId = groupId;
        this.groupname=groupname;
        this.groupSize = groupSize;
        this.moduleIds = moduleIds;
    }
 
    
    public int getGroupId(){
        return this.groupId;
    }
    
    public String getGroupName(){
        return this.groupname;
    }

    public int getGroupSize(){
        return this.groupSize;
    }
        

    public int[] getModuleIds(){
        return this.moduleIds;
    }
    
    
    @Override
    public String toString(){
      String res="groupid="+this.groupId+"group size="+this.groupSize;
      res+="modules=";
      for(int i=0;i<this.moduleIds.length;i++){
          res+=moduleIds[i]+",";
      }
      return res;
    }
}
