package com.psy;


import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;


/**
 * ClassName:AbstractTestCase
 *
 * TODO
 * 基于Spring和JUnit4.7的新测试基类
 * 继承此基类可以完成所有Spring bean的测试工作，包括DAO和Service
 * 对于Action，建议使用Spring-mock来完成测试工作.
 * 对于spring TestContext测试框架，默认在事务中运行，可以用@Rollback(false)标注测试方法来取消事务功能
 *
 * @project psy
 *
 * @author xiao
 *
 * @date   2015年4月23日 上午10:36:30	
 *
 * @class com.psy.AbstractTestCase
 *
 */ 
//指定Spring配置文件所在的位置，可以指定一个或多个Spring配置文件
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners({TransactionalTestExecutionListener.class})
@Transactional
@TransactionConfiguration( defaultRollback = true )
public abstract class AbstractTestCase extends AbstractJUnit4SpringContextTests {

	@Before
	public void init() throws Exception {
		
	}
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
}
