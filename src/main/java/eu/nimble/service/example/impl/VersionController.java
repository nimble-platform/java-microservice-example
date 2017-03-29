package eu.nimble.service.example.impl;

import eu.nimble.service.example.swagger.api.VersionApi;
import eu.nimble.service.example.swagger.model.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class VersionController implements VersionApi {

    private String versionString;

    private String serviceId;

    @Autowired
    public VersionController(@Value("${build.version}") String versionString,
                             @Value("${spring.application.name}") String serviceId) {
        this.versionString = versionString;
        this.serviceId = serviceId;
    }

    public ResponseEntity<Version> versionGet() {
        return new ResponseEntity<>(VersionFactory.create(serviceId, versionString), HttpStatus.OK);
    }

    private static class VersionFactory {
        static Version create(String serviceId, String version) {
            Version v = new Version();
            v.setVersion(version);
            v.setServiceId(serviceId);
            return v;
        }
    }
}
