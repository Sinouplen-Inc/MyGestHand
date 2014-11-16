package org.sinouplen.inc.mygesthand.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.sinouplen.inc.mygesthand.domain.Contact;
import org.sinouplen.inc.mygesthand.repository.ContactRepository;
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
 * REST controller for managing Contact.
 */
@RestController
@RequestMapping("/app")
public class ContactResource {

    private final Logger log = LoggerFactory.getLogger(ContactResource.class);

    @Inject
    private ContactRepository contactRepository;

    /**
     * POST  /rest/contacts -> Create a new contact.
     */
    @RequestMapping(value = "/rest/contacts",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Contact contact) {
        log.debug("REST request to save Contact : {}", contact);
        contactRepository.save(contact);
    }

    /**
     * GET  /rest/contacts -> get all the contacts.
     */
    @RequestMapping(value = "/rest/contacts",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Contact> getAll() {
        log.debug("REST request to get all Contacts");
        return contactRepository.findAll();
    }

    /**
     * GET  /rest/contacts/:id -> get the "id" contact.
     */
    @RequestMapping(value = "/rest/contacts/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Contact> get(@PathVariable Long id) {
        log.debug("REST request to get Contact : {}", id);
        return Optional.ofNullable(contactRepository.findOne(id))
            .map(contact -> new ResponseEntity<>(
                contact,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rest/contacts/:id -> delete the "id" contact.
     */
    @RequestMapping(value = "/rest/contacts/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Contact : {}", id);
        contactRepository.delete(id);
    }
}
