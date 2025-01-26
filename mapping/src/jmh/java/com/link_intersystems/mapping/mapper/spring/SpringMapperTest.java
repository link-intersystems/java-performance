package com.link_intersystems.mapping.mapper.spring;

import com.link_intersystems.mapping.PersonMapper;
import com.link_intersystems.mapping.mapper.MapperTest;

public class SpringMapperTest extends MapperTest {

    @Override
    protected PersonMapper createMapper() {
        return new SpringPersonMapper();
    }
}