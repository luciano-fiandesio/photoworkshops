package com.lf.photoworkshops.service;

import com.lf.photoworkshops.config.audit.AuditEventConverter;
import com.lf.photoworkshops.domain.PersistentAuditEvent;
import com.lf.photoworkshops.repository.PersistenceAuditEventRepository;
import org.joda.time.LocalDateTime;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service for managing audit events.
 * <p/>
 * <p>
 * This is the default implementation to support SpringBoot Actuator AuditEventRepository
 * </p>
 */
@Service
public class AuditEventService {

    @Inject
    private PersistenceAuditEventRepository persistenceAuditEventRepository;

    @Inject
    private AuditEventConverter auditEventConverter;

    public List<AuditEvent> findAll() {
        return auditEventConverter.convertToAuditEvent(persistenceAuditEventRepository.findAll());
    }

    public List<AuditEvent> findByDates(LocalDateTime fromDate, LocalDateTime toDate) {
        final List<PersistentAuditEvent> persistentAuditEvents =
                persistenceAuditEventRepository.findByDates(fromDate, toDate);

        return auditEventConverter.convertToAuditEvent(persistentAuditEvents);
    }
}
