package uofg.zehuilyu.queueapi.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uofg.zehuilyu.queueapi.entity.*;
import uofg.zehuilyu.queueapi.mapper.CustomerMapper;
import uofg.zehuilyu.queueapi.mapper.UserCustomerMapper;
import uofg.zehuilyu.queueapi.mapper.UserServiceMapper;
import uofg.zehuilyu.queueapi.mapper.WindowMapper;
import uofg.zehuilyu.queueapi.repository.CustomerRepository;
import uofg.zehuilyu.queueapi.repository.UserCustomerRepository;
import uofg.zehuilyu.queueapi.repository.UserServiceRepository;
import uofg.zehuilyu.queueapi.repository.WindowRepository;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private UserCustomerRepository userCustomerRepository;

    @Autowired
    private UserServiceRepository userServiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WindowRepository windowRepository;

    @Resource
    private UserCustomerMapper userCustomerMapper;

    @Resource
    private UserServiceMapper userServiceMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private WindowMapper windowMapper;


    @RequestMapping("/user/login")
    public Result<?> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("type") Integer type
    ) {
        if (type == 1) {
            UserCustomer userCustomer = userCustomerRepository.selectByUsername(username, password);

            if (userCustomer != null) {
                StpUtil.login(userCustomer.getId());
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId", userCustomer.getId());
                jsonObject.put("username", userCustomer.getUsername());
                jsonObject.put("type", type);
                jsonObject.put("token", tokenInfo.getTokenValue());
                return Result.OK(jsonObject);
            }
            return Result.error("Wrong account or password!");
        } else {
            UserService userService = userServiceRepository.selectByUsername(username, password);

            if (userService != null) {
                StpUtil.login(userService.getId());
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("userId", userService.getId());
                jsonObject.put("username", userService.getUsername());
                jsonObject.put("type", type);
                jsonObject.put("token", tokenInfo.getTokenValue());
                return Result.OK(jsonObject);
            }
            return Result.error("Wrong account or password!");
        }
    }

    @RequestMapping("/user/checkOnlyUser")
    public Result<?> login(@RequestParam("username") String username,@RequestParam("type") Integer type) {
        if (type == 1) {
            LambdaQueryWrapper<UserCustomer> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserCustomer::getUsername, username);
            Integer integer = userCustomerMapper.selectCount(wrapper);
            return getResult(integer);
        } else {
            LambdaQueryWrapper<UserService> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserService::getUsername, username);
            Integer integer = userServiceMapper.selectCount(wrapper);
            return getResult(integer);
        }
    }

    public Result<?> getResult(Integer row) {
        if (row == 0) {
            return Result.OK();
        } else {
            return Result.error("already exist");
        }
    }
    @RequestMapping("/user/register")
    public Result<?> register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("type") Integer type
    ) {
        try {
            if (type == 1) {
                Customer customer = new Customer();
                customer.setCustomer_level(1);
                customer.setName(username);
                customer.setCreate_time(new Date());
                customerMapper.insert(customer);
                UserCustomer userCustomer = new UserCustomer();
                userCustomer.setId(customer.getId());
                userCustomer.setUsername(username);
                userCustomer.setPassword(password);
                userCustomerMapper.insert(userCustomer);
                StpUtil.login(userCustomer.getId());
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId", userCustomer.getId());
                jsonObject.put("username", userCustomer.getUsername());
                jsonObject.put("type", type);
                jsonObject.put("token", tokenInfo.getTokenValue());
                return Result.OK(jsonObject);
            } else {
                ServiceWindow serviceWindow = new ServiceWindow();
                serviceWindow.setName(username);
                serviceWindow.setStatus(1);
                serviceWindow.setCreate_time(new Date());
                windowMapper.insert(serviceWindow);
                UserService userService = new UserService();
                userService.setId(serviceWindow.getId());
                userService.setUsername(username);
                userService.setPassword(password);

                userServiceMapper.insert(userService);

                StpUtil.login(userService.getId());
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("userId", userService.getId());
                jsonObject.put("username", userService.getUsername());
                jsonObject.put("type", type);
                jsonObject.put("token", tokenInfo.getTokenValue());
                return Result.OK(jsonObject);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return Result.error("Fail to register!");
        }

    }

}
