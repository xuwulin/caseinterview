package com.xwl.spring.tx.service.impl;

import com.xwl.spring.tx.dao.BookShopDao;
import com.xwl.spring.tx.service.BookShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务的属性：
 * 	1.★propagation：用来设置事务的传播行为
 * 		事务的传播行为：一个方法运行在了一个开启了事务的方法中时，当前方法是使用原来的事务还是开启一个新的事务
 * 		-Propagation.REQUIRED：默认值，使用原来的事务
 * 		-Propagation.REQUIRES_NEW：将原来的事务挂起，开启一个新的事务
 * 	2.★isolation：用来设置事务的隔离级别
 * 		-Isolation.REPEATABLE_READ：可重复读，MySQL默认的隔离级别
 * 		-Isolation.READ_COMMITTED：读已提交，Oracle默认的隔离级别，开发时通常使用的隔离级别
 *
 * 	数据库事务并发问题：
 * 	假设现在有两个事务：Transaction01和Transaction02并发执行
 * 	1）、脏读（读到了别人更新但没有提交的数据）
 * 		1、Transaction01将某条记录的AGE值从20修改为30.
 * 		2、Transaction02读取了Transaction01更新后的值：30.
 * 		3、Transaction01回滚，AGE值恢复到了20
 * 		4、Transaction02读取到的30就是一个无效的值
 *  2）、不可重复读（读到了别人更新且已提交的数据，但是第一次和第二次数据不一致）
 *  	1、Transaction01读取了AGE值为20
 *  	2、Transaction02将AGE值修改为30
 *  	3、Transaction01再次读取AGE值为30，和第一次读取不一致
 *  3）、幻读（读到了别人更新且已提交的数据，第二次读到的数据比第一多了很多，就好像是出现了幻觉一样，称为幻读）
 *  	1、Transaction01读取了STUDENT表中的一部分数据
 *  	2、Transaction02向STUDENT表中插入了新的行
 *  	3、Transaction01读取了STUDENT时，多出了一些行
 *
 *  隔离级别：
 *  数据库系统必须具有隔离并发运行各个事务的能力，使它们不会相互影响，避免各种并发问题。
 *  一个事务与其他事务隔离的程度称为隔离级别。SQL标准中规定了多种事务隔离级别，
 *  不同隔离级别对应不同的干扰程度，隔离级别越高，数据一致性就越好，但并发性越弱
 *  1）、读未提交：READ UNCOMMITTED
 *  允Transaction01读取Transaction02未提交的修改
 *  2）、读已提交：READ COMMITTED（开发常用）
 *  要求Transaction01只能读取Transaction02已提交的修改
 *  3）、可重复读：REPEATABLE_READ
 *  要求Transaction01可以多次从一个字段中读取到相同的值，即Transaction01执行期间禁止其他事物对这个字段进行更新
 *  4）、串行化：SERIALIZABLE
 *  确保Transaction01可以多次从一个表中读取到相同的行，在Transaction01执行期间，禁止其他事物对这个表进行添加、更新、删除操作
 *  可以避免任何并发问题，但性能十分低下
 *
 *  各个隔离级别解决并发问题的能力如下：
 *  						脏读	不可重复读	  幻读
 *  READ UNCOMMITTED		有		 有			  有
 *  READ COMMITTED			无		 有			  有
 *  REPEATABLE_READ			无		 无			  有
 *  SERIALIZABLE			无		 无			  无
 *
 *  数据库对事物隔离级别的支持
 *  						Oracle		MySQL
 *  READ UNCOMMITTED		×			√
 *  READ COMMITTED	        √（默认）   √
 *  REPEATABLE_READ         ×           √（默认）
 *  SERIALIZABLE            √           √
 *
 *
 * @Transactional注解
 * 	该注解可以添加到类上，也可以添加到方法上
 * 	如果添加到类上，那么类中所有的方法都添加上了事务
 * 	如果添加到方法上，只有添加了该注解的方法才添加了事务
 */
//@Transactional
@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookShopDao bookShopDao;

	//1.请简单介绍Spring支持的常用数据库事务传播属性和事务隔离级别？

	// propagation = Propagation.REQUIRES_NEW:将原来的事务挂起，开启一个新的事务
//	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	// propagation = Propagation.REQUIRED：使用原来的事务
	// isolation = Isolation.DEFAULT 使用默认的事务隔离级别，MySQL为：REPEATABLE_READ可重复读，即可以多次从一个字段中读取到相同的值
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	@Override
	public void purchase(int userId, String isbn) {
		// 1.获取要买的图书的价格
		double bookPrice = bookShopDao.getBookPriceByIsbn(isbn);
//		System.out.println(bookPrice);
		// 2.更新图书的库存
		bookShopDao.updateBookStock(isbn);
		// 3.更新用户的余额
		bookShopDao.updateAccountBalance(userId, bookPrice);
//		double bookPriceByIsbn = bookShopDao.getBookPriceByIsbn(isbn);
//		System.out.println(bookPriceByIsbn);
	}

}
