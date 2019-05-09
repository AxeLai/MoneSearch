/**
 * 
 */
package com.mone.search.util;


import com.mone.search.model.Money;

/**
 * @Project: crm-model
 * @Title: MoneyUtil.java
 * @Package com.yuwang.crm.model.common
 * @Description: 货币类型常用方法支持
 * @author liuboen liuboen@zba.com
 * @date 2011-6-17 下午07:58:06
 * @version V1.0  
 */

public class MoneyUtil {
	/**
	 * 获取元到分
	 * @param dollar
	 * @return
	 */
	public static Long getDollarToCent(String dollar){
		 Money money =new Money(dollar);
		 return money.getCent();
	}
	/**
	 * 获取分到元
	 * @param cent
	 * @return
	 */
	public static String getCentToDollar(long cent){
		Money m=new Money();
		m.setCent(cent);
		return m.toString();
	}
	
}
