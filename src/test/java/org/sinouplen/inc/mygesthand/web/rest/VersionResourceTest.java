package org.sinouplen.inc.mygesthand.web.rest;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.sinouplen.inc.mygesthand.Application;
import org.sinouplen.inc.mygesthand.domain.Version;
import org.sinouplen.inc.mygesthand.repository.VersionRepository;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VersionResource REST controller.
 *
 * @see VersionResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class VersionResourceTest {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    private static final DateTime DEFAULT_CREATE_DATE = new DateTime(0L);
    private static final DateTime UPDATED_CREATE_DATE = new DateTime().withMillisOfSecond(0);
    private static final String DEFAULT_CREATE_DATE_STR = dateTimeFormatter.print(DEFAULT_CREATE_DATE);

    private static final String DEFAULT_VERSION = "SAMPLE_TEXT";
    private static final String UPDATED_VERSION = "UPDATED_TEXT";


    @Inject
    private VersionRepository versionRepository;

    private MockMvc restVersionMockMvc;

    private Version version;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        VersionResource versionResource = new VersionResource();
        ReflectionTestUtils.setField(versionResource, "versionRepository", versionRepository);
        this.restVersionMockMvc = MockMvcBuilders.standaloneSetup(versionResource).build();
    }

    @Before
    public void initTest() {
        version = new Version();
        version.setCreate_date(DEFAULT_CREATE_DATE);
        version.setVersion(DEFAULT_VERSION);
    }

    @Test
    @Transactional
    public void createVersion() throws Exception {
        // Validate the database is empty
        assertThat(versionRepository.findAll()).hasSize(0);

        // Create the Version
        restVersionMockMvc.perform(post("/app/rest/versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(version)))
            .andExpect(status().isOk());

        // Validate the Version in the database
        List<Version> versions = versionRepository.findAll();
        assertThat(versions).hasSize(1);
        Version testVersion = versions.iterator().next();
        assertThat(testVersion.getCreate_date()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testVersion.getVersion()).isEqualTo(DEFAULT_VERSION);
    }

    @Test
    @Transactional
    public void getAllVersions() throws Exception {
        // Initialize the database
        versionRepository.saveAndFlush(version);

        // Get all the versions
        restVersionMockMvc.perform(get("/app/rest/versions"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[0].id").value(version.getId().intValue()))
            .andExpect(jsonPath("$.[0].create_date").value(DEFAULT_CREATE_DATE_STR))
            .andExpect(jsonPath("$.[0].version").value(DEFAULT_VERSION.toString()));
    }

    @Test
    @Transactional
    public void getVersion() throws Exception {
        // Initialize the database
        versionRepository.saveAndFlush(version);

        // Get the version
        restVersionMockMvc.perform(get("/app/rest/versions/{id}", version.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(version.getId().intValue()))
            .andExpect(jsonPath("$.create_date").value(DEFAULT_CREATE_DATE_STR))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVersion() throws Exception {
        // Get the version
        restVersionMockMvc.perform(get("/app/rest/versions/{id}", 1L))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVersion() throws Exception {
        // Initialize the database
        versionRepository.saveAndFlush(version);

        // Update the version
        version.setCreate_date(UPDATED_CREATE_DATE);
        version.setVersion(UPDATED_VERSION);
        restVersionMockMvc.perform(post("/app/rest/versions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(version)))
            .andExpect(status().isOk());

        // Validate the Version in the database
        List<Version> versions = versionRepository.findAll();
        assertThat(versions).hasSize(1);
        Version testVersion = versions.iterator().next();
        assertThat(testVersion.getCreate_date()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testVersion.getVersion()).isEqualTo(UPDATED_VERSION);
        ;
    }

    @Test
    @Transactional
    public void deleteVersion() throws Exception {
        // Initialize the database
        versionRepository.saveAndFlush(version);

        // Get the version
        restVersionMockMvc.perform(delete("/app/rest/versions/{id}", version.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Version> versions = versionRepository.findAll();
        assertThat(versions).hasSize(0);
    }
}
