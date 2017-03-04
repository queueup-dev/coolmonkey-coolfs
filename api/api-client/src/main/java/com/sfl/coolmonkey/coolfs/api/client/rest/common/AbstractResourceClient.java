package com.sfl.coolmonkey.coolfs.api.client.rest.common;

import javax.ws.rs.client.Client;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/20/15
 * Time: 2:27 PM
 */
public class AbstractResourceClient {

    //region Properties
    private String apiPath;

    private Client client;
    //endregion

    //region Constructors
    public AbstractResourceClient() {
    }

    public AbstractResourceClient(final Client client, final String apiPath) {
        this.apiPath = apiPath;
        this.client = client;
    }
    //endregion

    //region Properties getters and setters
    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(final String apiPath) {
        this.apiPath = apiPath;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(final Client client) {
        this.client = client;
    }
    //endregion
}
