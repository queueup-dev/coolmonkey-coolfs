package com.sfl.coolmonkey.coolfs.api.rest.resources.anonymous;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * User: Suren Aznauryan
 * Company: SFL LLC
 * Date: 9/3/15
 * Time: 5:20 PM
 */
@Component
@Path("/")
@Produces("application/json")
@Consumes("application/json")
public class AnonymousResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnonymousResource.class);

    //region Dependencies
    //endregion

    //region Constructors
    public AnonymousResource() {
        LOGGER.debug("Initializing anonymous resource");
    }
    //endregion

    //region Public methods
    @GET
    @Path("heartbeat")
    public Response getHeartBeat() {
        return Response.ok("OK").build();
    }
    //endregion
}
