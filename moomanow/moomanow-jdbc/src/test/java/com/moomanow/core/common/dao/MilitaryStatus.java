package com.moomanow.core.common.dao;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MilitaryStatus generated by hbm2java
 */
@Entity
@Table(name="JOB_N_MILITARY_STATUS"
)
public class MilitaryStatus  implements com.moomanow.core.common.bean.EntityBean,java.io.Serializable {


     private Long idMilitaryStatus;
     private Integer seq;
     private String name;
     private String isVacancy;
     private String status;
     private Date createDate;
     private String createUser;
     private Date updateDate;
     private String updateUser;
     private List<VaPdMilitaryStatus> vaPdMilitaryStatuses = new ArrayList<VaPdMilitaryStatus>(0);

    public MilitaryStatus() {
    }
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID_MILITARY_STATUS", unique=true, nullable=false)
    public Long getIdMilitaryStatus() {
        return this.idMilitaryStatus;
    }
    
    public void setIdMilitaryStatus(Long idMilitaryStatus) {
        this.idMilitaryStatus = idMilitaryStatus;
    }

    
    @Column(name="SEQ")
    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    
    @Column(name="NAME")
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="IS_VACANCY", length=1)
    public String getIsVacancy() {
        return this.isVacancy;
    }
    
    public void setIsVacancy(String isVacancy) {
        this.isVacancy = isVacancy;
    }

    
    @Column(name="STATUS", length=1)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    
    @Column(name="CREATE_DATE", length=0)
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    
    @Column(name="CREATE_USER")
    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    
    @Column(name="UPDATE_DATE", length=0)
    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    
    @Column(name="UPDATE_USER")
    public String getUpdateUser() {
        return this.updateUser;
    }
    
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }


@OneToMany(fetch=FetchType.LAZY, mappedBy="militaryStatus")
    public List<VaPdMilitaryStatus> getVaPdMilitaryStatuses() {
        return this.vaPdMilitaryStatuses;
    }
    
    public void setVaPdMilitaryStatuses(List<VaPdMilitaryStatus> vaPdMilitaryStatuses) {
        this.vaPdMilitaryStatuses = vaPdMilitaryStatuses;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("idMilitaryStatus").append("='").append(getIdMilitaryStatus()).append("' ");			
      buffer.append("seq").append("='").append(getSeq()).append("' ");			
      buffer.append("name").append("='").append(getName()).append("' ");			
      buffer.append("isVacancy").append("='").append(getIsVacancy()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("createDate").append("='").append(getCreateDate()).append("' ");			
      buffer.append("createUser").append("='").append(getCreateUser()).append("' ");			
      buffer.append("updateDate").append("='").append(getUpdateDate()).append("' ");			
      buffer.append("updateUser").append("='").append(getUpdateUser()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MilitaryStatus) ) return false;
		 MilitaryStatus castOther = ( MilitaryStatus ) other; 
         
		 return ( (this.getIdMilitaryStatus()==castOther.getIdMilitaryStatus()) || ( this.getIdMilitaryStatus()!=null && castOther.getIdMilitaryStatus()!=null && this.getIdMilitaryStatus().equals(castOther.getIdMilitaryStatus()) ) )
 && ( (this.getSeq()==castOther.getSeq()) || ( this.getSeq()!=null && castOther.getSeq()!=null && this.getSeq().equals(castOther.getSeq()) ) )
 && ( (this.getName()==castOther.getName()) || ( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
 && ( (this.getIsVacancy()==castOther.getIsVacancy()) || ( this.getIsVacancy()!=null && castOther.getIsVacancy()!=null && this.getIsVacancy().equals(castOther.getIsVacancy()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getCreateDate()==castOther.getCreateDate()) || ( this.getCreateDate()!=null && castOther.getCreateDate()!=null && this.getCreateDate().equals(castOther.getCreateDate()) ) )
 && ( (this.getCreateUser()==castOther.getCreateUser()) || ( this.getCreateUser()!=null && castOther.getCreateUser()!=null && this.getCreateUser().equals(castOther.getCreateUser()) ) )
 && ( (this.getUpdateDate()==castOther.getUpdateDate()) || ( this.getUpdateDate()!=null && castOther.getUpdateDate()!=null && this.getUpdateDate().equals(castOther.getUpdateDate()) ) )
 && ( (this.getUpdateUser()==castOther.getUpdateUser()) || ( this.getUpdateUser()!=null && castOther.getUpdateUser()!=null && this.getUpdateUser().equals(castOther.getUpdateUser()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getIdMilitaryStatus() == null ? 0 : this.getIdMilitaryStatus().hashCode() );
         result = 37 * result + ( getSeq() == null ? 0 : this.getSeq().hashCode() );
         result = 37 * result + ( getName() == null ? 0 : this.getName().hashCode() );
         result = 37 * result + ( getIsVacancy() == null ? 0 : this.getIsVacancy().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getCreateDate() == null ? 0 : this.getCreateDate().hashCode() );
         result = 37 * result + ( getCreateUser() == null ? 0 : this.getCreateUser().hashCode() );
         result = 37 * result + ( getUpdateDate() == null ? 0 : this.getUpdateDate().hashCode() );
         result = 37 * result + ( getUpdateUser() == null ? 0 : this.getUpdateUser().hashCode() );
         
         
         return result;
   }   


}

