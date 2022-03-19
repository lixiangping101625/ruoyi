package com.ruoyi.web.controller.order;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.ServiceConstants;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.page.CustomPageInfo;
import com.ruoyi.common.utils.page.PageInfoUtils;
import com.ruoyi.system.domain.dto.PZOrderDTO;
import com.ruoyi.system.domain.dto.ZZOrderDTO;
import com.ruoyi.system.domain.vo.UserCommentVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.OrderComment;
import com.ruoyi.system.service.IOrderCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户点评 Controller
 * 
 * @author ruoyi
 * @date 2022-03-19
 */
@RestController
@RequestMapping("/comment")
public class OrderCommentController extends BaseController
{
    @Autowired
    private IOrderCommentService orderCommentService;

    /**
     * 查询用户点评 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:comment:list')")
    @PostMapping("/list/{userId}")
    @Log(title = "查询用户点评列表 ", businessType = BusinessType.OTHER)
    public AjaxResult list(@PathVariable Long userId)
    {
        if (userId == null) {
            return AjaxResult.error("用户id不能为空~");
        }
        if (!userId.equals(SecurityUtils.getUserId())) {
            return AjaxResult.error("只能查询本人的点评列表~");
        }
        startPage();
        List<UserCommentVO> list = orderCommentService.selectList(userId);
        if (list.size() > 0) {
            list.forEach(userCommentVO -> {
                if (userCommentVO.getCategoryId() == ServiceConstants.CATE_PZ) {
                    PZOrderDTO pzOrderDTO = JSON.parseObject(userCommentVO.getSnapData(), PZOrderDTO.class);
                    userCommentVO.setPzOrderDTO(pzOrderDTO);
                }
                else if (userCommentVO.getCategoryId() == ServiceConstants.CATE_ZZFF) {
                    ZZOrderDTO zzOrderDTO = JSON.parseObject(userCommentVO.getSnapData(), ZZOrderDTO.class);
                    userCommentVO.setZzOrderDTO(zzOrderDTO);
                }
//                userCommentVO.setSnapData(null);
            });
        }
        CustomPageInfo<UserCommentVO> pageInfo = PageInfoUtils.wrapperData(list);
        return AjaxResult.success(pageInfo);
    }

    /**
     * 导出用户点评 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:comment:export')")
//    @Log(title = "用户点评 ", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, OrderComment orderComment)
//    {
//        List<OrderComment> list = orderCommentService.selectOrderCommentList(orderComment);
//        ExcelUtil<OrderComment> util = new ExcelUtil<OrderComment>(OrderComment.class);
//        util.exportExcel(response, list, "用户点评 数据");
//    }

    /**
     * 获取用户点评 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:comment:query')")
    @GetMapping(value = "/detail/{id}/{userId}")
    public AjaxResult getInfo(@PathVariable("id") Long id,
                              @PathVariable("userId") Long userId)
    {
        // TODO: 2022/3/19 暂时不需要
        return AjaxResult.error();
    }

    /**
     * 新增用户点评 
     */
//    @PreAuthorize("@ss.hasPermi('system:comment:add')")
    @Log(title = "新增用户点评 ", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestParam("orderId") Long orderId,
                          @RequestParam("orderNo") String orderNo,
                          @RequestParam("content") String content,
                          @RequestParam("score") Integer score,
                          @RequestParam("userId") Long userId,
                          @RequestPart("imgs") MultipartFile[] imgs)
    {
        if (orderId == null) {
            return AjaxResult.error("订单id不能为空~");
        }
        if (orderNo == null) {
            return AjaxResult.error("订单编码不能为空~");
        }
        if (StringUtils.isEmpty(content)) {
            return AjaxResult.error("点评内容不能为空~");
        }
        if (!userId.equals(SecurityUtils.getUserId())) {
            return AjaxResult.error("只能评论自己的订单~");
        }
        return toAjax(orderCommentService.addComment(orderId,orderNo,content,score,userId, imgs));
    }

    /**
     * 修改用户点评 
     */
    @PreAuthorize("@ss.hasPermi('system:comment:edit')")
    @Log(title = "用户点评 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderComment orderComment)
    {
        return toAjax(orderCommentService.updateOrderComment(orderComment));
    }

    /**
     * 删除用户点评 
     */
    @PreAuthorize("@ss.hasPermi('system:comment:remove')")
    @Log(title = "用户点评 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderCommentService.deleteOrderCommentByIds(ids));
    }
}
