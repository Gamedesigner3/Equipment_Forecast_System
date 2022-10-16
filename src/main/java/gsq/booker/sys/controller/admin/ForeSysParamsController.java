package gsq.booker.sys.controller.admin;

import gsq.booker.sys.common.ServiceResultEnum;
import gsq.booker.sys.entity.Para;
import gsq.booker.sys.service.AdminUserService;
import gsq.booker.sys.util.PageQueryUtil;
import gsq.booker.sys.util.Result;
import gsq.booker.sys.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class ForeSysParamsController {

    @Resource
    private AdminUserService adminUserService;

    @GetMapping("/params")
    public String paramsPage(HttpServletRequest request) {
        request.setAttribute("path", "params");
        return "admin/params";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/params/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String ,Object> param) {
        if (StringUtils.isEmpty(param.get("page")) || StringUtils.isEmpty(param.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(param);
        return ResultGenerator.genSuccessResult(adminUserService.getParaPage(pageUtil));
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/params/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody Para para) {
        if (Objects.isNull(para.getId())
                || Objects.isNull(para.getIdx1())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        String result = adminUserService.updateParams(para);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 详情
     */
    @GetMapping("/params/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer id) {
        Para para = adminUserService.getParaById(id);
        if (para == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult(para);
    }

}