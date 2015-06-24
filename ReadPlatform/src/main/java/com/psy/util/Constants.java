/**
 * Constants.java
 * com.psy.util
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年5月25日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.util;

 
/**
 * ClassName:Constants
 *
 * TODO(通用静态变量)
 *
 * @project psy
 *
 * @author xiao
 *
 * @date   2015年5月25日 上午11:29:08	
 *
 * @class com.psy.util.Constants
 *
 */ 
public class Constants {
	
	/** 保存在session中的用户信息的键名 */
	public static final String SESSION_USER_KEY = "___session_user_key";// 登录帐号对象
	
	/**	------------ 豆瓣isbn地址  ------------- */
	public static final String ISBN_URL = "https://api.douban.com/v2/book/isbn/"; 
	
	//评分、阅读排行
	/**	------------ 阅读排行  ------------- */
	public static final String RANK_READ = "00A"; 
	/**	------------ 评分排行  ------------- */
	public static final String RANK_SCORE = "00B"; 
	
	//评分、阅读排行
	/**	推荐类别--学校 */
	public static final String RECOMMEND_SCHOOL = "00A"; 
	/**	推荐类别--出版社 */
	public static final String RECOMMEND_PUBLISH = "00B"; 
	/**	推荐类别--教师 */
	public static final String RECOMMEND_TEA = "00C"; 
	/**	推荐类别--学生 */
	public static final String RECOMMEND_STU = "00D"; 
	
	//整体记录类别
	/**	记录类别--想读 */
	public static final String RECORD_READ_WANT = "00A"; 
	/**	记录类别--在读 */
	public static final String RECORD_READING = "00B"; 
	/**	记录类别--读过（已读） */
	public static final String RECORD_READED = "00C"; 
	/**	记录类别--评分 */
	public static final String RECORD_SCORE = "00D"; 
	/**	记录类别--评论 */
	public static final String RECORD_COMMENT = "00E"; 
	

}
