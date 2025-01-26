package com.link_intersystems.mapping.mapper.java;

import com.link_intersystems.mapping.PersonMapper;
import com.link_intersystems.mapping.mapper.MapperTest;

public class JavaMapperTest extends MapperTest {

    @Override
    protected PersonMapper createMapper() {
        return new JavaMapper();
    }
}
