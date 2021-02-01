package com.data.itsv.model.vo;

import com.data.itsv.model.VFdevice;
import lombok.Data;

@Data
public class VFdeviceVo extends VFdevice {
    /**
     * 设备类型
     * */
    private VFdtemplateVo fdTemplate;
    //所属服务名称
    private String pagNmae;
    //目录id
    private  String dirId;
}
