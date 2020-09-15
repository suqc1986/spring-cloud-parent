package com.biancheng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.zuul.context.RequestContext;

@RestController
public class LocalController {
    @GetMapping("/local/{id}")
    public String local(@PathVariable String id) {
    	RequestContext ctx = RequestContext.getCurrentContext();
        return id;
    }
}
