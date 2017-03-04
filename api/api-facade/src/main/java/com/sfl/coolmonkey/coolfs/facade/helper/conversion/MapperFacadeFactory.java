package com.sfl.coolmonkey.coolfs.facade.helper.conversion;

import com.sfl.coolmonkey.coolfs.api.model.storage.FileOriginModel;
import com.sfl.coolmonkey.coolfs.service.storage.model.FileOrigin;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/1/15
 * Time: 6:48 PM
 */
@Component
@SuppressWarnings({
        "pmd:NcssMethodCount",
        "squid:S138",
})
public class MapperFacadeFactory extends AbstractFactoryBean<MapperFacade> {

    //region Constructors
    public MapperFacadeFactory() {
    }
    //endregion

    //region Public methods
    @Override
    public Class<?> getObjectType() {
        return MapperFacade.class;
    }

    @Override
    public MapperFacade createInstance() {
        final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(FileOriginModel.class, FileOrigin.class).byDefault().register();
        return mapperFactory.getMapperFacade();
    }
    //endregion

    //region Utility methods
    //endregion
}
