package com.lizhao.my.shop.web.admin.web.controller;

import com.lizhao.my.shop.commons.dto.BaseResult;
import com.lizhao.my.shop.commons.dto.PageInfo;
import com.lizhao.my.shop.domain.TbContent;
import com.lizhao.my.shop.web.admin.service.TbContentService;
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
import java.util.List;

@Controller
@RequestMapping(value = "content")
public class ContentController {
    @Autowired
    private TbContentService tbContentService;
    @ModelAttribute
    public TbContent getTbContent(Long id) {
        TbContent tbContent = null;
        // id不为空，则从数据库获取
        if (id!=null){
            tbContent = tbContentService.getById(id);
        }
        else {
            tbContent = new TbContent();
        }
        return tbContent;
    }

    /**
     * 跳转到列表页
     * @return
     */

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {

        return "content_list";
    }

    /**
     * 跳转表单页
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(){

        return "content_form";
    }

    /**
     * 保存
     * @param tbContent
     * @param redirectAttributes
     * @return
     * 这里只能用RedirectAttributes ，不能用RequestAttributes,因为重定向之后，请求属性会丢失
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Model model, TbContent tbContent, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = tbContentService.save(tbContent);
        if(baseResult.getStatus()==200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/list";
        }
        // 保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "content_form";
        }
    }


    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        if(StringUtils.isNoneBlank(ids)) {
            String[] idArray = ids.split(",");
            tbContentService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除成功");
        }
        else {
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<TbContent> page(HttpServletRequest httpServletRequest, TbContent tbContent){
        String strDraw = httpServletRequest.getParameter("draw");
        String strStart = httpServletRequest.getParameter("start");
        String strLength = httpServletRequest.getParameter("length");
        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);
        // 封装DataTables需要的结果
        PageInfo<TbContent> pageInfo = tbContentService.page(start, length, draw, tbContent);
        return pageInfo;
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(TbContent tbContent) {
        return "content_detail";
    }
}
