package io.billkoch.examples;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.callback.FlywayCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.sql.Connection;

@Slf4j
public class SeedDataFlywayCallback implements FlywayCallback {

    private final JdbcTemplate jdbcTemplate;

    public SeedDataFlywayCallback(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterMigrate(Connection connection) {
        log.info("About to execute afterMigrate()...");

        try {
            Long peopleCount = jdbcTemplate.queryForObject("select count(*) from people", Long.class);
            if(peopleCount == 0) {
                log.info("No existing people found.  Running seed.sql");
                String seedSql = Resources.toString(Resources.getResource("db/seed.sql"), Charsets.UTF_8);
                jdbcTemplate.execute(seedSql);
            } else {
                log.info("Found existing people.  Ignoring seed.sql");
            }
        } catch (IOException e) {
            throw new FailedToLoadSeedDataException(e);
        }
        log.info("Finished executing afterMigrate()...");
    }

    @Override
    public void beforeClean(Connection connection) {
    }

    @Override
    public void afterClean(Connection connection) {
    }

    @Override
    public void beforeMigrate(Connection connection) {
    }

    @Override
    public void beforeEachMigrate(Connection connection, MigrationInfo info) {
    }

    @Override
    public void afterEachMigrate(Connection connection, MigrationInfo info) {
    }

    @Override
    public void beforeValidate(Connection connection) {
    }

    @Override
    public void afterValidate(Connection connection) {
    }

    @Override
    public void beforeBaseline(Connection connection) {
    }

    @Override
    public void afterBaseline(Connection connection) {
    }

    @Override
    public void beforeRepair(Connection connection) {
    }

    @Override
    public void afterRepair(Connection connection) {
    }

    @Override
    public void beforeInfo(Connection connection) {
    }

    @Override
    public void afterInfo(Connection connection) {
    }

    class FailedToLoadSeedDataException extends RuntimeException {
        public FailedToLoadSeedDataException(Throwable cause) {
            super(cause);
        }
    }
}
