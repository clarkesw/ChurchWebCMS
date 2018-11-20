/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.controller;


import com.milford.churchcms.dao.Calendar;
import com.milford.churchcms.util.DateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/test")
    public List<Calendar> getTest(){
      
        List<Calendar> calList = new ArrayList<>();
      //  calList.add(new Calendar("First Event", DateUtil.returnStringDate("2018-11-26", "10:00:00"),  DateUtil.returnStringDate("2018-11-26", "11:00:00")));
      //   calList.add(new Calendar("First Event", DateUtil.returnStringDate("2018-11-24", "10:00:00"),  DateUtil.returnStringDate("2018-11-24", "11:00:00")));
         
        return calList;
    }
}

/*
{
  "defaultDate": "11-15-18",
  "editable": false,
  "eventLimit": true,
  "events": [{"title": "First Event","start": "2018-11-26T10:00:00","end": "2018-11-26T10:30:00"}]
}
*/
