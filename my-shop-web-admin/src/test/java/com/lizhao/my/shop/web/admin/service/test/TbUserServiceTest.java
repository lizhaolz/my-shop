package com.lizhao.my.shop.web.admin.service.test;

import com.lizhao.my.shop.domain.TbUser;
import com.lizhao.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {
    @Autowired
    TbUserService tbUserService;

    @Test
    public void testSelectAll(){
        List<TbUser> tbUsers = tbUserService.selectAll();
        for (TbUser tbUser: tbUsers
             ) {
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("vvvvvvvvvv");
        tbUser.setPhone("2343322");
        tbUser.setEmail("543ew@.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserService.save(tbUser);
    }

    @Test
    public void testDelete() {
        tbUserService.delete(14L);
    }

    @Test
    public void testGetById(){
        TbUser tbUser = tbUserService.getById(9L);
        System.out.println(tbUser.getUsername());
        System.out.println(tbUser.getId());
    }

    @Test
    public void  testGetUsername(){
        List<TbUser> tbUsers = tbUserService.selectByUsername("李朝");
        for (TbUser tbUser:tbUsers
             ) {
            System.out.println(tbUser.getUsername());
        }
    }


    @Test
    public void testUpdate(){
        // 注意这里getId()方法get到的值的属性就是update时，你所知道的tbUser的值，所以你在select语句时
        // 一定要select他的id值，不然这里不知道更新哪一条记录
        TbUser tbUser = tbUserService.getById(8L);
        tbUser.setUsername("zzzzzzz");

        //tbUser.setUpdated(new Date());
        //System.out.println("###################");
        //System.out.println(tbUser.getId());
        //System.out.println("#######################");
        tbUserService.update(tbUser);
        //TbUser tbUser1 = tbUserService.getById(4L);
        //System.out.println(tbUser1.getUsername());
    }

    @Test
    public void testMd5() {
        System.out.println(DigestUtils.md5DigestAsHex("123".getBytes()));
    }
}
