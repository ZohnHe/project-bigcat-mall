package com.mall.bit.cqt.mall.controller;

import com.mall.bit.cqt.mall.abstracts.AbstractBaseController;
import com.mall.bit.cqt.mall.entity.Item;
import com.mall.bit.cqt.mall.entity.PageInfo;
import com.mall.bit.cqt.mall.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@Api(value = "商品",tags = "商品模块")
@RequestMapping(value = {"item"})
public class ItemController extends AbstractBaseController<Item, ItemService> {

    @Resource
    private ItemService itemService;
    /**
     * 查询所有数据并跳转到列表页
     * @param
     * @return
     */
    @GetMapping(value = {"list"})
    @ApiOperation(value = "跳转商品详情页", notes = "GET请求")
    public String list() {
        return "/item/list";
    }

    /**
     * 跳转到管理用户页面
     * 如果是编辑，则显示用户信息
     * @return
     */
    @GetMapping(value = {"update"})
    @ApiOperation(value = "跳转商品修改页", notes = "GET请求")
    public String update(String id,HttpServletRequest request) {
        Item item = itemService.getById(Long.valueOf(id));
        request.getSession().setAttribute("item",item);
        return "/item/update";
    }

    @GetMapping(value = {"insert"})
    @ApiOperation(value = "跳转商品增加页", notes = "GET请求")
    public String insert(){
        return "/item/insert";
    }

    /**
     * 从session中获取信息，返回商品信息
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "getMsg")
    public Item getMsg(HttpServletRequest request){
        Item item = (Item) request.getSession().getAttribute("item");
        return item;
    }

    /**
     * 修改和添加
     * @param item
     * @param redirectAttributes
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = {"save"})
    @ApiOperation(value = "保存商品", notes = "POST请求")
    public String save(Item item, RedirectAttributes redirectAttributes){

//        if (beanValidator(item, redirectAttributes)) {
//            service.save(item);
//             addMessage(redirectAttributes, "保存成功");
//        }
        itemService.save(item);

        return "1";
    }

    @GetMapping(value = {"delete"})
    @ApiOperation(value = "删除商品", notes = "GET请求")
    public String delete(Item item){
        itemService.delete(item);
        return  "/item/list" ;
    }

    /**
     * 展示分页信息
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping(value = "page")
    @ApiOperation(value = "分页展示商品", notes = "GET请求")
    public PageInfo<Item> pageUser(Item entity, HttpServletRequest request){
        //从客户端获取分页所需参数信息
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength =request.getParameter("length");
        //转换格式
        int draw = StringUtils.isBlank(strDraw) ? 0 : Integer.parseInt(strDraw);
        int start = StringUtils.isBlank(strStart) ? 0 : Integer.parseInt(strStart);
        int length = StringUtils.isBlank(strLength) ? 10 : Integer.parseInt(strLength);
        //创建分页信息实例化对象，接收分页信息
        PageInfo<Item> pageInfo = service.page(entity,start,length,draw);

        return pageInfo;
    }

}
