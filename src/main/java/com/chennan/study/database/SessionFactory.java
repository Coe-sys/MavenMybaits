package com.chennan.study.database;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * demo class
 * 每个基于 MyBatis 的应用都是以一个 SqlSessionFactory 的实例为核心的。SqlSessionFactory 的实例可以通过 SqlSessionFactoryBuilder 获得。
 * 而 SqlSessionFactoryBuilder 则可以从 XML 配置文件或一个预先定制的 Configuration 的实例构建出 SqlSessionFactory 的实例。
 * @author 陈楠
 * @date 2019/11/27 14:06
 * To change this template use File | Settings | File Templates.
 */
public class SessionFactory {
    static String resource = "mybatis-config.xml";
    public static SqlSessionFactory sessionFactory;

    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSession(){
        //return sessionFactory.openSession(true);true自动提交事务/false手动提交事务默认
        return sessionFactory.openSession();
    }
}