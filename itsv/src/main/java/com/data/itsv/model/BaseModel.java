package com.data.itsv.model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalPageNumI,totalNumI,rn,pageSize,pageIndex;
	private String name,desc,state,states;
	private Integer id;
	private Date createTime;
	private String lastUpdateTime;
	private boolean isSelected;
	

}
