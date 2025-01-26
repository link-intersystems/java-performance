package com.link_intersystems.mapping.mapper.dozer;

import com.link_intersystems.mapping.PersonMapper;
import com.link_intersystems.mapping.mapper.MapperTest;

public class DozerMapperTest extends MapperTest {

    @Override
    protected PersonMapper createMapper() {
        return new DozserPersonMapper();
    }
}
