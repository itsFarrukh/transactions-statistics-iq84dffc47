package com.n26.controller;

import com.n26.model.Statistics;
import com.n26.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;
    @RequestMapping(method = RequestMethod.GET)
    public Statistics get(){
        return statisticsService.get();
    }
}
