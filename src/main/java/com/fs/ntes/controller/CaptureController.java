package com.fs.ntes.controller;

import com.fs.dreams.dto.FResponse;
import com.fs.ntes.service.CaptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fengsong
 * @description:
 * @time 2017-07-12 18:58
 **/
@Controller
public class CaptureController {

    @Autowired
    private CaptureService captureService;


    @RequestMapping("test")
    public FResponse test(){
        captureService.captureArtist("周杰伦");
        return new FResponse();
    }

}
