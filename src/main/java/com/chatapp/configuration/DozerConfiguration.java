/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.configuration;

import java.util.Arrays;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Yousef
 */
@Configuration
public class DozerConfiguration {

    @Bean("org.dozer.Mapper")
    public DozerBeanMapper dozerBean(){
        List<String> mappingFiles = Arrays.asList("dozzer-mapping.xml");
        
        DozerBeanMapper dozerMapper = new DozerBeanMapper();
        dozerMapper.setMappingFiles(mappingFiles);
        return dozerMapper;
    }
    
}
