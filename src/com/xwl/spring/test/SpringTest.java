package com.xwl.spring.test;

import com.xwl.spring.beans.Book;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ★bean的作用域
 * 可以通过scope属性来指定bean的作用域
 * -singleton：默认值。当IOC容器一创建就会创建bean的实例，而且是单例的，每次得到的都是同一个
 * -prototype：原型的。当IOC容器一创建不再实例化该bean，每次调用getBean方法时再实例化该bean，而且每调用一次创建一个对象
 * -request：每次请求实例化一个bean
 * -session：在一次会话中共享一个bean
 */
public class SpringTest {

    //01.Spring Bean的作用域之间有什么区别

    //创建IOC容器对象
    ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");

    @Test
    public void testBook() {
        Book book = (Book) ioc.getBean("book");
        Book book2 = (Book) ioc.getBean("book");
        System.out.println(book == book2);
    }
}
