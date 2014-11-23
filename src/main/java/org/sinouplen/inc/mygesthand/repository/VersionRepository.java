package org.sinouplen.inc.mygesthand.repository;

import org.sinouplen.inc.mygesthand.domain.Version;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Version entity.
 */
public interface VersionRepository extends JpaRepository<Version, Long> {

}
