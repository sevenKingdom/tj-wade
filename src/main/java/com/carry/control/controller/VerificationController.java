package com.carry.control.controller;

import com.carry.control.model.po.UserData;
import com.carry.util.IdentityVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songxianying on 17/8/6.
 */
@RestController
@RequestMapping(value = "/verification")
public class VerificationController {
    @Autowired
    private IdentityVerification identityVerification;

    @RequestMapping(value = "/verification" ,method = RequestMethod.GET)
    public UserData verification (String token) {
        return identityVerification.verification(token);
    }

    @RequestMapping(value = "/shortVerification" ,method = RequestMethod.GET)
    public String shortVerification( String token) {
        return identityVerification.shortVerification(token);
    }

    @RequestMapping(value = "/getPlanAuthor" ,method = RequestMethod.GET)
    public String getPlanAuthor(Long id) {
        return identityVerification.getPlanAuthor(id);
    }
}
