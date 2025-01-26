package com.link_intersystems.mapping.mapper.mapstruct;

import com.link_intersystems.mapping.PersonMapper;
import com.link_intersystems.mapping.mapper.MapperTest;
import org.mapstruct.factory.Mappers;

public class MapstructMapperTest extends MapperTest {

    @Override
    protected PersonMapper createMapper() {
        return Mappers.getMapper(MapstructPersonMapper.class);
    }
}