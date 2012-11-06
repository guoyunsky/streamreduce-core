package com.streamreduce.rest.dto.response;

import com.streamreduce.connections.AuthType;

import java.util.List;

public class ConnectionResponseDTO extends AbstractOwnableResponseSobaDTO {

    private String providerId;
    private String url;
    private String type;
    private AuthType authType;
    private String identity;
    private List<OutboundConfigurationResponseDTO> outboundConfigurations;
    private boolean broken;
    private String lastErrorMessage;


    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public List<OutboundConfigurationResponseDTO> getOutboundConfigurations() {
        return outboundConfigurations;
    }

    public void setOutboundConfigurations(List<OutboundConfigurationResponseDTO> outboundConfigurations) {
        this.outboundConfigurations = outboundConfigurations;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }

}
