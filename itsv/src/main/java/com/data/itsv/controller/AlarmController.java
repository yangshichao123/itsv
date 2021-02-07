package com.data.itsv.controller;

import com.data.itsv.aspect.BusinessType;
import com.data.itsv.aspect.Log;
import com.data.itsv.aspect.OperatorType;
import com.data.itsv.model.*;
import com.data.itsv.model.vo.Message;
import com.data.itsv.model.vo.VFdeviceVo;
import com.data.itsv.service.DictionaryService;
import com.data.itsv.service.VAlarmSetService;
import com.data.itsv.service.VFdeviceService;
import com.data.itsv.service.VServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *   post新增,get读取,put完整更新,patch部分更新,delete删除
 * @author tomsun28
 * @date 14:40 2018/3/8
 */
@Api(value = "设备相关接口")
@RestController
@RequestMapping("/alarm")
public class AlarmController extends BaseAction {

    @Autowired
    private VServerService vServerService;
    @Autowired
    private VFdeviceService vFdeviceService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private VAlarmSetService vAlarmSetService;


    @ApiOperation(value = "添加字典", notes = "POST请求json格式参数")
    @PostMapping("/dictionary/addDictionary")
    @CrossOrigin
    @Log(title = "添加字典",logType = BusinessType.OPT_LOG,operatorType = OperatorType.SYSTEM)
    public Message addDictionary(@RequestBody Dictionary dictionary) {
       boolean isTrue=dictionaryService.addDictionary(dictionary);
       if(!isTrue)
           new Message().error("添加失败");
        return new Message().ok("success","");
    }
    @ApiOperation(value = "修改字典", notes = "POST请求json格式参数")
    @PostMapping("/dictionary/updateDictionary")
    @CrossOrigin
    @Log(title = "修改字典",logType = BusinessType.OPT_LOG,operatorType = OperatorType.SYSTEM)
    public Message updateDictionary(@RequestBody Dictionary dictionary) {
       boolean isTrue=dictionaryService.updateDictionary(dictionary);
       if(!isTrue)
           new Message().error("修改失败");
        return new Message().ok("success","");
    }
    @ApiOperation(value = "删除字典", notes = "POST请求json格式参数")
    @PostMapping("/dictionary/deleteDictionary")
    @CrossOrigin
    @Log(title = "删除字典",logType = BusinessType.OPT_LOG,operatorType = OperatorType.SYSTEM)
    public Message deleteDictionary(@RequestBody Dictionary dictionary) {
       boolean isTrue=dictionaryService.deleteDictionary(dictionary);
       if(!isTrue)
           new Message().error("删除字典失败");
        return new Message().ok("success","");
    }

    @ApiOperation(value = "查询字典", notes = "POST请求json格式参数")
    @PostMapping("/dictionary/getDictionary")
    @CrossOrigin
    @Log(title = "查询字典",logType = BusinessType.OPT_LOG,operatorType = OperatorType.SYSTEM)
    public Message getDictionary(@RequestBody Dictionary dictionary) {
       List<Dictionary> dictionaries=dictionaryService.getDictionary(dictionary);
        return new Message().ok("success",dictionaries);
    }

   /* @ApiOperation(value = "添加告警信息", notes = "POST请求json格式参数")
    @PostMapping("/addAlarm")
    @CrossOrigin
    public Message getServer(@RequestBody VAlarm vAlarm) {
        List<VServer> data =vServerService.getServer(vAlarm);
        return new Message().ok("success",data);
    }
*/

}
