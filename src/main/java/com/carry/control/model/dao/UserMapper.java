package com.carry.control.model.dao;

import com.carry.control.model.po.Configdata;
import com.carry.control.model.po.UserCreat;
import com.carry.control.model.po.UserData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by songxianying on 17/7/23.
 */
@FeignClient(value = "service-model")
public interface UserMapper {
    @GetMapping (value = "/mysql/login")
    public UserCreat login (@RequestParam("name") String name,
                                          @RequestParam("password") String password );
    @GetMapping (value = "/mysql/logoff")
    public long logoff(@RequestParam("id") long id);

    @RequestMapping(value = "/mysql/createuser" ,method = RequestMethod.POST)
    public Map<String,Object> creatUser(@RequestBody UserCreat userCreat) ;

    @RequestMapping(value = "/mysql/verification" ,method = RequestMethod.GET)
    public UserData verification(@RequestParam("token") String token) ;

    @RequestMapping(value = "/mysql/shortVerification" ,method = RequestMethod.GET)
    public String shortVerification(@RequestParam("token") String token) ;

    @RequestMapping(value = "/mysql/getPlanAuthor" ,method = RequestMethod.GET)
    public String getPlanAuthor(@RequestParam("id") Long id);

    @RequestMapping(value = "/updateUserScore",method = RequestMethod.GET)
    public Long updateUserScore(@RequestParam("id") Long id, @RequestParam("score") Integer score);
}
