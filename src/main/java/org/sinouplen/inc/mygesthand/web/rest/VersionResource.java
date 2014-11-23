package org.sinouplen.inc.mygesthand.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.sinouplen.inc.mygesthand.domain.Version;
import org.sinouplen.inc.mygesthand.repository.VersionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Version.
 */
@RestController
@RequestMapping("/app")
public class VersionResource {

    private final Logger log = LoggerFactory.getLogger(VersionResource.class);

    @Inject
    private VersionRepository versionRepository;

    /**
     * POST  /rest/versions -> Create a new version.
     */
    @RequestMapping(value = "/rest/versions",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Version version) {
        log.debug("REST request to save Version : {}", version);
        versionRepository.save(version);
    }

    /**
     * GET  /rest/versions -> get all the versions.
     */
    @RequestMapping(value = "/rest/versions",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Version> getAll() {
        log.debug("REST request to get all Versions");
        return versionRepository.findAll();
    }

    /**
     * GET  /rest/versions/:id -> get the "id" version.
     */
    @RequestMapping(value = "/rest/versions/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Version> get(@PathVariable Long id) {
        log.debug("REST request to get Version : {}", id);
        return Optional.ofNullable(versionRepository.findOne(id))
            .map(version -> new ResponseEntity<>(
                version,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rest/versions/:id -> delete the "id" version.
     */
    @RequestMapping(value = "/rest/versions/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Version : {}", id);
        versionRepository.delete(id);
    }
}
