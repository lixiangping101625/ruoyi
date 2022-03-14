package com.ruoyi.web.controller.order;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.domain.UserComment;
import com.ruoyi.system.service.IUserCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户点评 Controller
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@RestController
@RequestMapping("/comment")
public class UserCommentController extends BaseController
{
    @Autowired
    private IUserCommentService userCommentService;

    /**
     * 查询用户点评 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:comment:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        // TODO: 2022/3/14 待订单做完再完善
        startPage();
        List<UserComment> list = userCommentService.selectUserCommentList();

        return AjaxResult.success(list);
    }

    /**
     * 导出用户点评 列表
     */
    @PreAuthorize("@ss.hasPermi('system:comment:export')")
    @Log(title = "用户点评 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserComment userComment)
    {
        List<UserComment> list = userCommentService.selectUserCommentList(userComment);
        ExcelUtil<UserComment> util = new ExcelUtil<UserComment>(UserComment.class);
        util.exportExcel(response, list, "用户点评 数据");
    }

    /**
     * 获取用户点评 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userCommentService.selectUserCommentById(id));
    }

    /**
     * 新增用户点评 
     */
    @PreAuthorize("@ss.hasPermi('system:comment:add')")
    @Log(title = "用户点评 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserComment userComment)
    {
        return toAjax(userCommentService.insertUserComment(userComment));
    }

    /**
     * 修改用户点评 
     */
    @PreAuthorize("@ss.hasPermi('system:comment:edit')")
    @Log(title = "用户点评 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserComment userComment)
    {
        return toAjax(userCommentService.updateUserComment(userComment));
    }

    /**
     * 删除用户点评 
     */
    @PreAuthorize("@ss.hasPermi('system:comment:remove')")
    @Log(title = "用户点评 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userCommentService.deleteUserCommentByIds(ids));
    }
}
