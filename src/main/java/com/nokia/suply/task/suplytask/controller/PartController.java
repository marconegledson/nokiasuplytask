package com.nokia.suply.task.suplytask.controller;

import com.nokia.suply.task.suplytask.service.PartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PartController {

    private final PartService partService;
}
