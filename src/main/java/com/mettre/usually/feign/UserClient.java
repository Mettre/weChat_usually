package com.mettre.usually.feign;

import com.mettre.usually.base.Result;
import com.mettre.usually.dto.UserDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@FeignClient("account")
@RestController
public interface UserClient {

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    Result<UserDto> findUserInfo(@RequestParam("userId") String userId);
}
