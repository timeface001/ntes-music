package com.fs.dreams.controller;

import com.fs.dreams.dto.FResponse;
import com.fs.dreams.service.CaptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public FResponse test() {
        captureService.captureArtist("周杰伦");
        return new FResponse();
    }

    public static boolean isLimit(Long storeId, Long maxNum, Long maxCount, Long sta) {
        if (maxNum != null && maxNum.longValue() > 0L) {
            return maxCount.compareTo(maxNum) > 0 ? false : sta + maxCount.longValue() <= maxNum.longValue();
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(isLimit(0L,12L,10L,0L));
    }

}
