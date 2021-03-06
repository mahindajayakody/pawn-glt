package com.dass.pawning.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.dass.core.bean.Trace;

@Entity
@Table(name="tblpawnertype")
public class PawnerType extends Trace {
	private int pawnerTypeId;
	private String code;
	private String description;

	@Transient
	public int getRecordId() {		
		return pawnerTypeId;
	}

	public void setRecordId(int recordId) {	
		this.pawnerTypeId=recordId;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="InvTab")
    @TableGenerator(name="InvTab",	table="ID_GEN",
    		pkColumnName="ID_NAME",
    		valueColumnName="ID_VALUE",
    		allocationSize=1,
    		pkColumnValue="PawnerType")
	@Column(name="PWTID")
	public int getPawnerTypeId(){
		return pawnerTypeId;
	}
	public void setPawnerTypeId(int pawnerTypeId){
		this.pawnerTypeId=pawnerTypeId;
	}
	
	@Column(name="CODE")
	public String getCode(){
		return this.code;
	}
	
	public void setCode(String code){
		this.code=code;
	}
	
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description){
		this.description=description;
	}
	@Override
	public String toString() {
		StringBuilder stringBuilder=new StringBuilder();
		stringBuilder.append("PWTID="+pawnerTypeId);
		stringBuilder.append(",CODE="+code);
		stringBuilder.append(",DESCRIPTION="+description);
		return stringBuilder.toString();
	}
}
