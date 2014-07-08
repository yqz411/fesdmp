/** 
 * Project Name:fesdmp 
 * File Name:StatisticViewController.java 
 * Package Name:cn.bjfu.fesdmp.web.data 
 * Date:2014年7月8日 下午3:54:22 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.web.data;  

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.bjfu.fesdmp.web.BaseController;

/** 
 * ClassName:StatisticViewController <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年7月8日 下午3:54:22 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
@Controller
@RequestMapping("/statistic")
public class StatisticViewController extends BaseController {

	@RequestMapping(value = "/portal", method = RequestMethod.GET)
	public String statisticViewPage() {
		return "/statisticView/portalStatisticView";
	}
	
}
 