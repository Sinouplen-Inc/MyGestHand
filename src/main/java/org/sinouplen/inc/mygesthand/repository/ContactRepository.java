package org.sinouplen.inc.mygesthand.repository;

import org.sinouplen.inc.mygesthand.domain.Contact;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Contact entity.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
