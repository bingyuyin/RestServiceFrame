package com.suiyu.web.application;

import org.atmosphere.config.service.*;
import org.atmosphere.cpr.AtmosphereResource;

/**
 * Created by yinbing on 1/13/2016.
 */
@ManagedService(path = "/test")
public class AtmosphereManagedService {

    @Get
    public void onGet(AtmosphereResource atmosphereResource){

    }

    @Post
    public void onPost(AtmosphereResource atmosphereResource){

    }

    @Put
    public void onPut(AtmosphereResource atmosphereResource){

    }

    @Delete
    public void onDelete(AtmosphereResource atmosphereResource){

    }

}
