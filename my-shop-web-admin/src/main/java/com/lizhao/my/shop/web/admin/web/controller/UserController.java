package com.lizhao.my.shop.web.admin.web.controller;

import com.lizhao.my.shop.commons.dto.BaseResult;
import com.lizhao.my.shop.commons.dto.PageInfo;
import com.lizhao.my.shop.domain.TbUser;
import com.lizhao.my.shop.web.admin.abstracts.AbstractBaseController;
import com.lizhao.my.shop.web.admin.service.TbUserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.BaseStream;

/**
 * 用户管理
 */

// 所有得访问都是以user开头
@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractBaseController<TbUser, TbUserService> {


    @ModelAttribute
    public TbUser getTbUser(Long id) {
        TbUser tbUser = null;
         // id不为空，则从数据库获取
        if (id!=null){
            tbUser = service.getById(id);
        }
        else {
            tbUser = new TbUser();
        }
        return tbUser;
    }



    /**
     * 跳转用户表单页
     * @return
     */
    @Override
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(){

        return "user_form";
    }

    /**
     * 保存
     * @param tbUser
     * @param redirectAttributes
     * @return
     * 这里只能用RedirectAttributes ，不能用RequestAttributes,因为重定向之后，请求属性会丢失
     */
    @Override
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(tbUser);
        if(baseResult.getStatus()==200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/user/list";
        }
        // 保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "user_form";
        }
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        if(StringUtils.isNoneBlank(ids)) {
            String[] idArray = ids.split(",");
            service.deleteMulti(idArray);
            baseResult = BaseResult.success("删除用户成功");
        }
        else {
            baseResult = BaseResult.fail("删除用户失败");
        }
        return baseResult;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest httpServletRequest, TbUser tbUser) throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        String strDraw = httpServletRequest.getParameter("draw");
        String strStart = httpServletRequest.getParameter("start");
        String strLength = httpServletRequest.getParameter("length");
        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);
        System.out.println("xxx"+ tbUser.getUsername());
        PageInfo<TbUser> pageInfo = service.page(start, length, draw, tbUser);

        return pageInfo;
    }

    @Override
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail() {
        return "user_detail";
    }

    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "user_list";
    }
}
