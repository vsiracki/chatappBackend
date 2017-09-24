/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatapp.common.utils;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Yousef
 */
@Component
public class MapperUtil {
    @Autowired
    Mapper mapper;
    
    public <E, T> List<T> getDtoList(List<E> entityList, Class<T> dtoClass) {

        List<T> dtoList = new ArrayList<T>();

        for (E entity : entityList) {
            dtoList.add(mapper.map(entity, dtoClass));
        }

        return dtoList;
    }
    
}
