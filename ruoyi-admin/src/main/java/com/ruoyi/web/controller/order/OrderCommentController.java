package com.ruoyi.web.controller.order;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.ServiceConstants;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.page.CustomPageInfo;
import com.ruoyi.common.utils.page.PageInfoUtils;
import com.ruoyi.system.domain.dto.PZOrderDTO;
import com.ruoyi.system.domain.dto.ZZOrderDTO;
import com.ruoyi.system.domain.vo.UserCommentVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.OrderComment;
import com.ruoyi.system.service.IOrderCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

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
    @Log(title = "查询点评；列表 ", businessType = BusinessType.OTHER)
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
    @PreAuthorize("@ss.hasPermi('system:comment:export')")
    @Log(title = "用户点评 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderComment orderComment)
    {
        List<OrderComment> list = orderCommentService.selectOrderCommentList(orderComment);
        ExcelUtil<OrderComment> util = new ExcelUtil<OrderComment>(OrderComment.class);
        util.exportExcel(response, list, "用户点评 数据");
    }

    /**
     * 获取用户点评 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(orderCommentService.selectOrderCommentById(id));
    }

    /**
     * 新增用户点评 
     */
    @PreAuthorize("@ss.hasPermi('system:comment:add')")
    @Log(title = "用户点评 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderComment orderComment)
    {
        return toAjax(orderCommentService.insertOrderComment(orderComment));
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
