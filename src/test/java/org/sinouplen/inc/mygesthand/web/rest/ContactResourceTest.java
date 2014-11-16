package org.sinouplen.inc.mygesthand.web.rest;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.sinouplen.inc.mygesthand.Application;
import org.sinouplen.inc.mygesthand.domain.Contact;
import org.sinouplen.inc.mygesthand.repository.ContactRepository;
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
 * Test class for the ContactResource REST controller.
 *
 * @see ContactResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ContactResourceTest {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    private static final DateTime DEFAULT_BIRTHDAY = new DateTime(0L);
    private static final DateTime UPDATED_BIRTHDAY = new DateTime().withMillisOfSecond(0);
    private static final String DEFAULT_BIRTHDAY_STR = dateTimeFormatter.print(DEFAULT_BIRTHDAY);

    private static final String DEFAULT_NATIONALITY = "SAMPLE_TEXT";
    private static final String UPDATED_NATIONALITY = "UPDATED_TEXT";

    private static final String DEFAULT_BIRTH_COUNTRY = "SAMPLE_TEXT";
    private static final String UPDATED_BIRTH_COUNTRY = "UPDATED_TEXT";

    private static final String DEFAULT_QUALITY = "SAMPLE_TEXT";
    private static final String UPDATED_QUALITY = "UPDATED_TEXT";

    private static final String DEFAULT_AGE_RANGE = "SAMPLE_TEXT";
    private static final String UPDATED_AGE_RANGE = "UPDATED_TEXT";

    private static final String DEFAULT_MUTATION_INDICATOR = "SAMPLE_TEXT";
    private static final String UPDATED_MUTATION_INDICATOR = "UPDATED_TEXT";

    private static final String DEFAULT_NATIONALITY_NATURE = "SAMPLE_TEXT";
    private static final String UPDATED_NATIONALITY_NATURE = "UPDATED_TEXT";

    private static final DateTime DEFAULT_DATE_OF_ENTRY = new DateTime(0L);
    private static final DateTime UPDATED_DATE_OF_ENTRY = new DateTime().withMillisOfSecond(0);
    private static final String DEFAULT_DATE_OF_ENTRY_STR = dateTimeFormatter.print(DEFAULT_DATE_OF_ENTRY);

    private static final DateTime DEFAULT_DATE_OF_RECIPT = new DateTime(0L);
    private static final DateTime UPDATED_DATE_OF_RECIPT = new DateTime().withMillisOfSecond(0);
    private static final String DEFAULT_DATE_OF_RECIPT_STR = dateTimeFormatter.print(DEFAULT_DATE_OF_RECIPT);

    private static final DateTime DEFAULT_DATE_OF_EXPIRY = new DateTime(0L);
    private static final DateTime UPDATED_DATE_OF_EXPIRY = new DateTime().withMillisOfSecond(0);
    private static final String DEFAULT_DATE_OF_EXPIRY_STR = dateTimeFormatter.print(DEFAULT_DATE_OF_EXPIRY);

    private static final DateTime DEFAULT_DATE_OF_QUALIFICATION = new DateTime(0L);
    private static final DateTime UPDATED_DATE_OF_QUALIFICATION = new DateTime().withMillisOfSecond(0);
    private static final String DEFAULT_DATE_OF_QUALIFICATION_STR = dateTimeFormatter.print(DEFAULT_DATE_OF_QUALIFICATION);

    private static final String DEFAULT_LICENSE_STATUS = "SAMPLE_TEXT";
    private static final String UPDATED_LICENSE_STATUS = "UPDATED_TEXT";

    private static final String DEFAULT_TYPE_OF_APPLICATION = "SAMPLE_TEXT";
    private static final String UPDATED_TYPE_OF_APPLICATION = "UPDATED_TEXT";

    private static final String DEFAULT_LICENSE_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_LICENSE_NUMBER = "UPDATED_TEXT";

    private static final String DEFAULT_SEX = "SAMPLE_TEXT";
    private static final String UPDATED_SEX = "UPDATED_TEXT";

    private static final Boolean DEFAULT_INDIVIDUAL_ACCIDENT_INSURANCE = false;
    private static final Boolean UPDATED_INDIVIDUAL_ACCIDENT_INSURANCE = true;
    private static final Boolean DEFAULT_ALLOWS_TRADE_EAMIL = false;
    private static final Boolean UPDATED_ALLOWS_TRADE_EAMIL = true;
    private static final Boolean DEFAULT_ALLOWS_ADDRESS = false;
    private static final Boolean UPDATED_ALLOWS_ADDRESS = true;
    private static final String DEFAULT_CIVILITY = "SAMPLE_TEXT";
    private static final String UPDATED_CIVILITY = "UPDATED_TEXT";

    private static final String DEFAULT_MARRIED_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_MARRIED_NAME = "UPDATED_TEXT";

    private static final String DEFAULT_FOREIGN_BIRTH_CITY = "SAMPLE_TEXT";
    private static final String UPDATED_FOREIGN_BIRTH_CITY = "UPDATED_TEXT";

    private static final String DEFAULT_FRENCH_BIRTH_CITY = "SAMPLE_TEXT";
    private static final String UPDATED_FRENCH_BIRTH_CITY = "UPDATED_TEXT";

    private static final String DEFAULT_DEPARTEMENT_BIRTH = "SAMPLE_TEXT";
    private static final String UPDATED_DEPARTEMENT_BIRTH = "UPDATED_TEXT";

    private static final String DEFAULT_ADDRESS_1 = "SAMPLE_TEXT";
    private static final String UPDATED_ADDRESS_1 = "UPDATED_TEXT";

    private static final String DEFAULT_ADDRESS_2 = "SAMPLE_TEXT";
    private static final String UPDATED_ADDRESS_2 = "UPDATED_TEXT";

    private static final String DEFAULT_ADDRESS_3 = "SAMPLE_TEXT";
    private static final String UPDATED_ADDRESS_3 = "UPDATED_TEXT";

    private static final String DEFAULT_FRENCH_POSTAL_CODE = "SAMPLE_TEXT";
    private static final String UPDATED_FRENCH_POSTAL_CODE = "UPDATED_TEXT";

    private static final String DEFAULT_FRENCH_CITY = "SAMPLE_TEXT";
    private static final String UPDATED_FRENCH_CITY = "UPDATED_TEXT";

    private static final String DEFAULT_FOREIGN_POSTAL_CODE = "SAMPLE_TEXT";
    private static final String UPDATED_FOREIGN_POSTAL_CODE = "UPDATED_TEXT";

    private static final String DEFAULT_FOREIGN_CITY = "SAMPLE_TEXT";
    private static final String UPDATED_FOREIGN_CITY = "UPDATED_TEXT";

    private static final String DEFAULT_COUNTRY = "SAMPLE_TEXT";
    private static final String UPDATED_COUNTRY = "UPDATED_TEXT";

    private static final String DEFAULT_HOME_PHONE_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_HOME_PHONE_NUMBER = "UPDATED_TEXT";

    private static final String DEFAULT_CELLULAR_PHONE_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_CELLULAR_PHONE_NUMBER = "UPDATED_TEXT";

    private static final String DEFAULT_WORK_PHONE_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_WORK_PHONE_NUMBER = "UPDATED_TEXT";

    private static final String DEFAULT_FAX_NUMBER = "SAMPLE_TEXT";
    private static final String UPDATED_FAX_NUMBER = "UPDATED_TEXT";

    private static final DateTime DEFAULT_LICENSE_STATUS_DATE = new DateTime(0L);
    private static final DateTime UPDATED_LICENSE_STATUS_DATE = new DateTime().withMillisOfSecond(0);
    private static final String DEFAULT_LICENSE_STATUS_DATE_STR = dateTimeFormatter.print(DEFAULT_LICENSE_STATUS_DATE);


    @Inject
    private ContactRepository contactRepository;

    private MockMvc restContactMockMvc;

    private Contact contact;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ContactResource contactResource = new ContactResource();
        ReflectionTestUtils.setField(contactResource, "contactRepository", contactRepository);
        this.restContactMockMvc = MockMvcBuilders.standaloneSetup(contactResource).build();
    }

    @Before
    public void initTest() {
        contact = new Contact();
        contact.setBirthday(DEFAULT_BIRTHDAY);
        contact.setNationality(DEFAULT_NATIONALITY);
        contact.setBirth_country(DEFAULT_BIRTH_COUNTRY);
        contact.setQuality(DEFAULT_QUALITY);
        contact.setAge_range(DEFAULT_AGE_RANGE);
        contact.setMutation_indicator(DEFAULT_MUTATION_INDICATOR);
        contact.setNationality_nature(DEFAULT_NATIONALITY_NATURE);
        contact.setDate_of_entry(DEFAULT_DATE_OF_ENTRY);
        contact.setDate_of_recipt(DEFAULT_DATE_OF_RECIPT);
        contact.setDate_of_expiry(DEFAULT_DATE_OF_EXPIRY);
        contact.setDate_of_qualification(DEFAULT_DATE_OF_QUALIFICATION);
        contact.setLicense_status(DEFAULT_LICENSE_STATUS);
        contact.setType_of_application(DEFAULT_TYPE_OF_APPLICATION);
        contact.setLicense_number(DEFAULT_LICENSE_NUMBER);
        contact.setSex(DEFAULT_SEX);
        contact.setIndividual_accident_insurance(DEFAULT_INDIVIDUAL_ACCIDENT_INSURANCE);
        contact.setAllows_trade_eamil(DEFAULT_ALLOWS_TRADE_EAMIL);
        contact.setAllows_address(DEFAULT_ALLOWS_ADDRESS);
        contact.setCivility(DEFAULT_CIVILITY);
        contact.setMarried_name(DEFAULT_MARRIED_NAME);
        contact.setForeign_birth_city(DEFAULT_FOREIGN_BIRTH_CITY);
        contact.setFrench_birth_city(DEFAULT_FRENCH_BIRTH_CITY);
        contact.setDepartement_birth(DEFAULT_DEPARTEMENT_BIRTH);
        contact.setAddress_1(DEFAULT_ADDRESS_1);
        contact.setAddress_2(DEFAULT_ADDRESS_2);
        contact.setAddress_3(DEFAULT_ADDRESS_3);
        contact.setFrench_postal_code(DEFAULT_FRENCH_POSTAL_CODE);
        contact.setFrench_city(DEFAULT_FRENCH_CITY);
        contact.setForeign_postal_code(DEFAULT_FOREIGN_POSTAL_CODE);
        contact.setForeign_city(DEFAULT_FOREIGN_CITY);
        contact.setCountry(DEFAULT_COUNTRY);
        contact.setHome_phone_number(DEFAULT_HOME_PHONE_NUMBER);
        contact.setCellular_phone_number(DEFAULT_CELLULAR_PHONE_NUMBER);
        contact.setWork_phone_number(DEFAULT_WORK_PHONE_NUMBER);
        contact.setFax_number(DEFAULT_FAX_NUMBER);
        contact.setLicense_status_date(DEFAULT_LICENSE_STATUS_DATE);
    }

    @Test
    @Transactional
    public void createContact() throws Exception {
        // Validate the database is empty
        assertThat(contactRepository.findAll()).hasSize(0);

        // Create the Contact
        restContactMockMvc.perform(post("/app/rest/contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isOk());

        // Validate the Contact in the database
        List<Contact> contacts = contactRepository.findAll();
        assertThat(contacts).hasSize(1);
        Contact testContact = contacts.iterator().next();
        assertThat(testContact.getBirthday()).isEqualTo(DEFAULT_BIRTHDAY);
        assertThat(testContact.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
        assertThat(testContact.getBirth_country()).isEqualTo(DEFAULT_BIRTH_COUNTRY);
        assertThat(testContact.getQuality()).isEqualTo(DEFAULT_QUALITY);
        assertThat(testContact.getAge_range()).isEqualTo(DEFAULT_AGE_RANGE);
        assertThat(testContact.getMutation_indicator()).isEqualTo(DEFAULT_MUTATION_INDICATOR);
        assertThat(testContact.getNationality_nature()).isEqualTo(DEFAULT_NATIONALITY_NATURE);
        assertThat(testContact.getDate_of_entry()).isEqualTo(DEFAULT_DATE_OF_ENTRY);
        assertThat(testContact.getDate_of_recipt()).isEqualTo(DEFAULT_DATE_OF_RECIPT);
        assertThat(testContact.getDate_of_expiry()).isEqualTo(DEFAULT_DATE_OF_EXPIRY);
        assertThat(testContact.getDate_of_qualification()).isEqualTo(DEFAULT_DATE_OF_QUALIFICATION);
        assertThat(testContact.getLicense_status()).isEqualTo(DEFAULT_LICENSE_STATUS);
        assertThat(testContact.getType_of_application()).isEqualTo(DEFAULT_TYPE_OF_APPLICATION);
        assertThat(testContact.getLicense_number()).isEqualTo(DEFAULT_LICENSE_NUMBER);
        assertThat(testContact.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testContact.getIndividual_accident_insurance()).isEqualTo(DEFAULT_INDIVIDUAL_ACCIDENT_INSURANCE);
        assertThat(testContact.getAllows_trade_eamil()).isEqualTo(DEFAULT_ALLOWS_TRADE_EAMIL);
        assertThat(testContact.getAllows_address()).isEqualTo(DEFAULT_ALLOWS_ADDRESS);
        assertThat(testContact.getCivility()).isEqualTo(DEFAULT_CIVILITY);
        assertThat(testContact.getMarried_name()).isEqualTo(DEFAULT_MARRIED_NAME);
        assertThat(testContact.getForeign_birth_city()).isEqualTo(DEFAULT_FOREIGN_BIRTH_CITY);
        assertThat(testContact.getFrench_birth_city()).isEqualTo(DEFAULT_FRENCH_BIRTH_CITY);
        assertThat(testContact.getDepartement_birth()).isEqualTo(DEFAULT_DEPARTEMENT_BIRTH);
        assertThat(testContact.getAddress_1()).isEqualTo(DEFAULT_ADDRESS_1);
        assertThat(testContact.getAddress_2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testContact.getAddress_3()).isEqualTo(DEFAULT_ADDRESS_3);
        assertThat(testContact.getFrench_postal_code()).isEqualTo(DEFAULT_FRENCH_POSTAL_CODE);
        assertThat(testContact.getFrench_city()).isEqualTo(DEFAULT_FRENCH_CITY);
        assertThat(testContact.getForeign_postal_code()).isEqualTo(DEFAULT_FOREIGN_POSTAL_CODE);
        assertThat(testContact.getForeign_city()).isEqualTo(DEFAULT_FOREIGN_CITY);
        assertThat(testContact.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testContact.getHome_phone_number()).isEqualTo(DEFAULT_HOME_PHONE_NUMBER);
        assertThat(testContact.getCellular_phone_number()).isEqualTo(DEFAULT_CELLULAR_PHONE_NUMBER);
        assertThat(testContact.getWork_phone_number()).isEqualTo(DEFAULT_WORK_PHONE_NUMBER);
        assertThat(testContact.getFax_number()).isEqualTo(DEFAULT_FAX_NUMBER);
        assertThat(testContact.getLicense_status_date()).isEqualTo(DEFAULT_LICENSE_STATUS_DATE);
    }

    @Test
    @Transactional
    public void getAllContacts() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        // Get all the contacts
        restContactMockMvc.perform(get("/app/rest/contacts"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[0].id").value(contact.getId().intValue()))
            .andExpect(jsonPath("$.[0].birthday").value(DEFAULT_BIRTHDAY_STR))
            .andExpect(jsonPath("$.[0].nationality").value(DEFAULT_NATIONALITY.toString()))
            .andExpect(jsonPath("$.[0].birth_country").value(DEFAULT_BIRTH_COUNTRY.toString()))
            .andExpect(jsonPath("$.[0].quality").value(DEFAULT_QUALITY.toString()))
            .andExpect(jsonPath("$.[0].age_range").value(DEFAULT_AGE_RANGE.toString()))
            .andExpect(jsonPath("$.[0].mutation_indicator").value(DEFAULT_MUTATION_INDICATOR.toString()))
            .andExpect(jsonPath("$.[0].nationality_nature").value(DEFAULT_NATIONALITY_NATURE.toString()))
            .andExpect(jsonPath("$.[0].date_of_entry").value(DEFAULT_DATE_OF_ENTRY_STR))
            .andExpect(jsonPath("$.[0].date_of_recipt").value(DEFAULT_DATE_OF_RECIPT_STR))
            .andExpect(jsonPath("$.[0].date_of_expiry").value(DEFAULT_DATE_OF_EXPIRY_STR))
            .andExpect(jsonPath("$.[0].date_of_qualification").value(DEFAULT_DATE_OF_QUALIFICATION_STR))
            .andExpect(jsonPath("$.[0].license_status").value(DEFAULT_LICENSE_STATUS.toString()))
            .andExpect(jsonPath("$.[0].type_of_application").value(DEFAULT_TYPE_OF_APPLICATION.toString()))
            .andExpect(jsonPath("$.[0].license_number").value(DEFAULT_LICENSE_NUMBER.toString()))
            .andExpect(jsonPath("$.[0].sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.[0].individual_accident_insurance").value(DEFAULT_INDIVIDUAL_ACCIDENT_INSURANCE.booleanValue()))
            .andExpect(jsonPath("$.[0].allows_trade_eamil").value(DEFAULT_ALLOWS_TRADE_EAMIL.booleanValue()))
            .andExpect(jsonPath("$.[0].allows_address").value(DEFAULT_ALLOWS_ADDRESS.booleanValue()))
            .andExpect(jsonPath("$.[0].civility").value(DEFAULT_CIVILITY.toString()))
            .andExpect(jsonPath("$.[0].married_name").value(DEFAULT_MARRIED_NAME.toString()))
            .andExpect(jsonPath("$.[0].foreign_birth_city").value(DEFAULT_FOREIGN_BIRTH_CITY.toString()))
            .andExpect(jsonPath("$.[0].french_birth_city").value(DEFAULT_FRENCH_BIRTH_CITY.toString()))
            .andExpect(jsonPath("$.[0].departement_birth").value(DEFAULT_DEPARTEMENT_BIRTH.toString()))
            .andExpect(jsonPath("$.[0].address_1").value(DEFAULT_ADDRESS_1.toString()))
            .andExpect(jsonPath("$.[0].address_2").value(DEFAULT_ADDRESS_2.toString()))
            .andExpect(jsonPath("$.[0].address_3").value(DEFAULT_ADDRESS_3.toString()))
            .andExpect(jsonPath("$.[0].french_postal_code").value(DEFAULT_FRENCH_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.[0].french_city").value(DEFAULT_FRENCH_CITY.toString()))
            .andExpect(jsonPath("$.[0].foreign_postal_code").value(DEFAULT_FOREIGN_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.[0].foreign_city").value(DEFAULT_FOREIGN_CITY.toString()))
            .andExpect(jsonPath("$.[0].country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.[0].home_phone_number").value(DEFAULT_HOME_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.[0].cellular_phone_number").value(DEFAULT_CELLULAR_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.[0].work_phone_number").value(DEFAULT_WORK_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.[0].fax_number").value(DEFAULT_FAX_NUMBER.toString()))
            .andExpect(jsonPath("$.[0].license_status_date").value(DEFAULT_LICENSE_STATUS_DATE_STR));
    }

    @Test
    @Transactional
    public void getContact() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        // Get the contact
        restContactMockMvc.perform(get("/app/rest/contacts/{id}", contact.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(contact.getId().intValue()))
            .andExpect(jsonPath("$.birthday").value(DEFAULT_BIRTHDAY_STR))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY.toString()))
            .andExpect(jsonPath("$.birth_country").value(DEFAULT_BIRTH_COUNTRY.toString()))
            .andExpect(jsonPath("$.quality").value(DEFAULT_QUALITY.toString()))
            .andExpect(jsonPath("$.age_range").value(DEFAULT_AGE_RANGE.toString()))
            .andExpect(jsonPath("$.mutation_indicator").value(DEFAULT_MUTATION_INDICATOR.toString()))
            .andExpect(jsonPath("$.nationality_nature").value(DEFAULT_NATIONALITY_NATURE.toString()))
            .andExpect(jsonPath("$.date_of_entry").value(DEFAULT_DATE_OF_ENTRY_STR))
            .andExpect(jsonPath("$.date_of_recipt").value(DEFAULT_DATE_OF_RECIPT_STR))
            .andExpect(jsonPath("$.date_of_expiry").value(DEFAULT_DATE_OF_EXPIRY_STR))
            .andExpect(jsonPath("$.date_of_qualification").value(DEFAULT_DATE_OF_QUALIFICATION_STR))
            .andExpect(jsonPath("$.license_status").value(DEFAULT_LICENSE_STATUS.toString()))
            .andExpect(jsonPath("$.type_of_application").value(DEFAULT_TYPE_OF_APPLICATION.toString()))
            .andExpect(jsonPath("$.license_number").value(DEFAULT_LICENSE_NUMBER.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.individual_accident_insurance").value(DEFAULT_INDIVIDUAL_ACCIDENT_INSURANCE.booleanValue()))
            .andExpect(jsonPath("$.allows_trade_eamil").value(DEFAULT_ALLOWS_TRADE_EAMIL.booleanValue()))
            .andExpect(jsonPath("$.allows_address").value(DEFAULT_ALLOWS_ADDRESS.booleanValue()))
            .andExpect(jsonPath("$.civility").value(DEFAULT_CIVILITY.toString()))
            .andExpect(jsonPath("$.married_name").value(DEFAULT_MARRIED_NAME.toString()))
            .andExpect(jsonPath("$.foreign_birth_city").value(DEFAULT_FOREIGN_BIRTH_CITY.toString()))
            .andExpect(jsonPath("$.french_birth_city").value(DEFAULT_FRENCH_BIRTH_CITY.toString()))
            .andExpect(jsonPath("$.departement_birth").value(DEFAULT_DEPARTEMENT_BIRTH.toString()))
            .andExpect(jsonPath("$.address_1").value(DEFAULT_ADDRESS_1.toString()))
            .andExpect(jsonPath("$.address_2").value(DEFAULT_ADDRESS_2.toString()))
            .andExpect(jsonPath("$.address_3").value(DEFAULT_ADDRESS_3.toString()))
            .andExpect(jsonPath("$.french_postal_code").value(DEFAULT_FRENCH_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.french_city").value(DEFAULT_FRENCH_CITY.toString()))
            .andExpect(jsonPath("$.foreign_postal_code").value(DEFAULT_FOREIGN_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.foreign_city").value(DEFAULT_FOREIGN_CITY.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.home_phone_number").value(DEFAULT_HOME_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.cellular_phone_number").value(DEFAULT_CELLULAR_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.work_phone_number").value(DEFAULT_WORK_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.fax_number").value(DEFAULT_FAX_NUMBER.toString()))
            .andExpect(jsonPath("$.license_status_date").value(DEFAULT_LICENSE_STATUS_DATE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingContact() throws Exception {
        // Get the contact
        restContactMockMvc.perform(get("/app/rest/contacts/{id}", 1L))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContact() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        // Update the contact
        contact.setBirthday(UPDATED_BIRTHDAY);
        contact.setNationality(UPDATED_NATIONALITY);
        contact.setBirth_country(UPDATED_BIRTH_COUNTRY);
        contact.setQuality(UPDATED_QUALITY);
        contact.setAge_range(UPDATED_AGE_RANGE);
        contact.setMutation_indicator(UPDATED_MUTATION_INDICATOR);
        contact.setNationality_nature(UPDATED_NATIONALITY_NATURE);
        contact.setDate_of_entry(UPDATED_DATE_OF_ENTRY);
        contact.setDate_of_recipt(UPDATED_DATE_OF_RECIPT);
        contact.setDate_of_expiry(UPDATED_DATE_OF_EXPIRY);
        contact.setDate_of_qualification(UPDATED_DATE_OF_QUALIFICATION);
        contact.setLicense_status(UPDATED_LICENSE_STATUS);
        contact.setType_of_application(UPDATED_TYPE_OF_APPLICATION);
        contact.setLicense_number(UPDATED_LICENSE_NUMBER);
        contact.setSex(UPDATED_SEX);
        contact.setIndividual_accident_insurance(UPDATED_INDIVIDUAL_ACCIDENT_INSURANCE);
        contact.setAllows_trade_eamil(UPDATED_ALLOWS_TRADE_EAMIL);
        contact.setAllows_address(UPDATED_ALLOWS_ADDRESS);
        contact.setCivility(UPDATED_CIVILITY);
        contact.setMarried_name(UPDATED_MARRIED_NAME);
        contact.setForeign_birth_city(UPDATED_FOREIGN_BIRTH_CITY);
        contact.setFrench_birth_city(UPDATED_FRENCH_BIRTH_CITY);
        contact.setDepartement_birth(UPDATED_DEPARTEMENT_BIRTH);
        contact.setAddress_1(UPDATED_ADDRESS_1);
        contact.setAddress_2(UPDATED_ADDRESS_2);
        contact.setAddress_3(UPDATED_ADDRESS_3);
        contact.setFrench_postal_code(UPDATED_FRENCH_POSTAL_CODE);
        contact.setFrench_city(UPDATED_FRENCH_CITY);
        contact.setForeign_postal_code(UPDATED_FOREIGN_POSTAL_CODE);
        contact.setForeign_city(UPDATED_FOREIGN_CITY);
        contact.setCountry(UPDATED_COUNTRY);
        contact.setHome_phone_number(UPDATED_HOME_PHONE_NUMBER);
        contact.setCellular_phone_number(UPDATED_CELLULAR_PHONE_NUMBER);
        contact.setWork_phone_number(UPDATED_WORK_PHONE_NUMBER);
        contact.setFax_number(UPDATED_FAX_NUMBER);
        contact.setLicense_status_date(UPDATED_LICENSE_STATUS_DATE);
        restContactMockMvc.perform(post("/app/rest/contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contact)))
            .andExpect(status().isOk());

        // Validate the Contact in the database
        List<Contact> contacts = contactRepository.findAll();
        assertThat(contacts).hasSize(1);
        Contact testContact = contacts.iterator().next();
        assertThat(testContact.getBirthday()).isEqualTo(UPDATED_BIRTHDAY);
        assertThat(testContact.getNationality()).isEqualTo(UPDATED_NATIONALITY);
        assertThat(testContact.getBirth_country()).isEqualTo(UPDATED_BIRTH_COUNTRY);
        assertThat(testContact.getQuality()).isEqualTo(UPDATED_QUALITY);
        assertThat(testContact.getAge_range()).isEqualTo(UPDATED_AGE_RANGE);
        assertThat(testContact.getMutation_indicator()).isEqualTo(UPDATED_MUTATION_INDICATOR);
        assertThat(testContact.getNationality_nature()).isEqualTo(UPDATED_NATIONALITY_NATURE);
        assertThat(testContact.getDate_of_entry()).isEqualTo(UPDATED_DATE_OF_ENTRY);
        assertThat(testContact.getDate_of_recipt()).isEqualTo(UPDATED_DATE_OF_RECIPT);
        assertThat(testContact.getDate_of_expiry()).isEqualTo(UPDATED_DATE_OF_EXPIRY);
        assertThat(testContact.getDate_of_qualification()).isEqualTo(UPDATED_DATE_OF_QUALIFICATION);
        assertThat(testContact.getLicense_status()).isEqualTo(UPDATED_LICENSE_STATUS);
        assertThat(testContact.getType_of_application()).isEqualTo(UPDATED_TYPE_OF_APPLICATION);
        assertThat(testContact.getLicense_number()).isEqualTo(UPDATED_LICENSE_NUMBER);
        assertThat(testContact.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testContact.getIndividual_accident_insurance()).isEqualTo(UPDATED_INDIVIDUAL_ACCIDENT_INSURANCE);
        assertThat(testContact.getAllows_trade_eamil()).isEqualTo(UPDATED_ALLOWS_TRADE_EAMIL);
        assertThat(testContact.getAllows_address()).isEqualTo(UPDATED_ALLOWS_ADDRESS);
        assertThat(testContact.getCivility()).isEqualTo(UPDATED_CIVILITY);
        assertThat(testContact.getMarried_name()).isEqualTo(UPDATED_MARRIED_NAME);
        assertThat(testContact.getForeign_birth_city()).isEqualTo(UPDATED_FOREIGN_BIRTH_CITY);
        assertThat(testContact.getFrench_birth_city()).isEqualTo(UPDATED_FRENCH_BIRTH_CITY);
        assertThat(testContact.getDepartement_birth()).isEqualTo(UPDATED_DEPARTEMENT_BIRTH);
        assertThat(testContact.getAddress_1()).isEqualTo(UPDATED_ADDRESS_1);
        assertThat(testContact.getAddress_2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testContact.getAddress_3()).isEqualTo(UPDATED_ADDRESS_3);
        assertThat(testContact.getFrench_postal_code()).isEqualTo(UPDATED_FRENCH_POSTAL_CODE);
        assertThat(testContact.getFrench_city()).isEqualTo(UPDATED_FRENCH_CITY);
        assertThat(testContact.getForeign_postal_code()).isEqualTo(UPDATED_FOREIGN_POSTAL_CODE);
        assertThat(testContact.getForeign_city()).isEqualTo(UPDATED_FOREIGN_CITY);
        assertThat(testContact.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testContact.getHome_phone_number()).isEqualTo(UPDATED_HOME_PHONE_NUMBER);
        assertThat(testContact.getCellular_phone_number()).isEqualTo(UPDATED_CELLULAR_PHONE_NUMBER);
        assertThat(testContact.getWork_phone_number()).isEqualTo(UPDATED_WORK_PHONE_NUMBER);
        assertThat(testContact.getFax_number()).isEqualTo(UPDATED_FAX_NUMBER);
        assertThat(testContact.getLicense_status_date()).isEqualTo(UPDATED_LICENSE_STATUS_DATE);
        ;
    }

    @Test
    @Transactional
    public void deleteContact() throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);

        // Get the contact
        restContactMockMvc.perform(delete("/app/rest/contacts/{id}", contact.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Contact> contacts = contactRepository.findAll();
        assertThat(contacts).hasSize(0);
    }
}
