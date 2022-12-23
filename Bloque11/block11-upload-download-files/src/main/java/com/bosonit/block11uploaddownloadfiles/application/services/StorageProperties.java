package com.bosonit.block11uploaddownloadfiles.application.services;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
    private String location = "C:\\Users\\juanjose.jasso\\Documents\\Repositorios\\Formacion-Bosonit\\Bloque11\\block11-upload-download-files";
    public String getLocation() {
        return location;
    }
}
