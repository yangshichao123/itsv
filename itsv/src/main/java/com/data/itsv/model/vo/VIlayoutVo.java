package com.data.itsv.model.vo;

import com.data.itsv.model.VIlayout;
import com.data.itsv.model.VIlayoutDetail;
import lombok.Data;

import java.util.List;

@Data
public class VIlayoutVo extends VIlayout {
    /**
     * @author wym
     * @说明：布局详细信息
     * */
    private List<VIlayoutDetailVo> detailList;
}
