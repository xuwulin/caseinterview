package com.xwl.spring.tx.service.impl;

import java.util.List;

import com.xwl.spring.tx.service.BookShopService;
import com.xwl.spring.tx.service.Cashier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cashier")
public class CashierImpl implements Cashier {

	@Autowired
	private BookShopService bookShopService;
	
	@Transactional
	@Override
	public void checkout(int userId, List<String> isbns) {
		for (String isbn : isbns) {
			// 调用BookShopService中买东西的方法
			// purchase()方法也使用了事务
			// 事务的传播行为：一个方法运行在了一个开启了事务的方法中时，Propagation.REQUIRED：默认值，使用原来的事务
			// 使用的是checkout()这个方法的事务
			bookShopService.purchase(userId, isbn);
		}
	}

}
