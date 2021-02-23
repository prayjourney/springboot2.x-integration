package com.zgy.bootintegration.controller;

import com.zgy.bootintegration.service.UpdateAgeTaskService;
import com.zgy.bootintegration.service.UpdateGenderTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: z.g.y
 * @description:
 * @date: Created in 2020/6/1 0:48
 * @modified:
 */
@RestController
@RequestMapping("updateservice")
public class UpdateServiceController {
    @Autowired
    private UpdateAgeTaskService updateAgeTaskService;

    @Autowired
    private UpdateGenderTaskService updateGenderTaskService;

    @RequestMapping("agegender")
    public String updateAgeGender() throws InterruptedException {
        updateAgeTaskService.updateAge();
        updateGenderTaskService.updateGender();
        return "提交了一个任务！";
    }


}
