package com.boot.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.spring.entity.PostEntity;

import io.swagger.annotations.Api;

@Api
@Controller
@RequestMapping("/swg")
public class SwaggerTest {

	/**
	 * 移动到项目包路径下测试
	 * 不指定method的时候，swagger会生成 GET, HEAD, OPTIONS, PATCH, POST, PUT, DELETE
	 * 加上 produces swagger页面无返回，直接访问URI有返回？？！
	 * 
	 * Swagger会扫描该包下所有Controller(ResetController)定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）
	 * 常用注解：
	 * |@ApiOperation注解来给API增加说明；@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
	 * |@ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	 * |@ApiImplicitParams({
     * |   @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
     * |   @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
     * |})
     * |@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
     * |@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	 */

	@RequestMapping(value="/swaggerConf", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PostEntity swagger(@RequestBody PostEntity ety){
//		return "This will be shows in swagger's page response";
		return ety;
	}

	@RequestMapping(value="/swaggerConf", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
//	public String swagge2(@RequestBody PostEntity ety){
	public PostEntity swagge2(@RequestParam PostEntity ety){
		return ety;
	}

	@RequestMapping(value="/swaggerConf3", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String swagger3(@RequestParam String ety){
//		return "swagger3 will be shows in swagger's page response";
		return ety;
	}

	@RequestMapping(value="/swaggerConf4", method=RequestMethod.GET)
	@ResponseBody
	public String swagger4(String ety){
		return ety;
	}
}
