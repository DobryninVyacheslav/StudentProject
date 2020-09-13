package edu.javacourse.register.business;

import edu.javacourse.register.dao.MarriageDao;
import edu.javacourse.register.domain.MarriageCertificate;
import edu.javacourse.register.view.MarriageRequest;
import edu.javacourse.register.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarriageManager {

    private static final Logger LOG = LoggerFactory.getLogger(MarriageManager.class);
    @Autowired
    private MarriageDao marriageDao;

    public void setMarriageDao(MarriageDao marriageDao) {
        this.marriageDao = marriageDao;
    }

    public MarriageResponse findMarriageCertificate(MarriageRequest request) {
        LOG.info("findMarriageCertificate called");
        MarriageCertificate marriageCertificate = marriageDao.findMarriageCertificate(request);

        return new MarriageResponse();
    }
}
