package io.billkoch.examples;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.callback.FlywayCallback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class SeedDataFlywayCallback implements FlywayCallback {

    @Override
    public void afterMigrate(Connection connection) {
        log.info("About to execute afterMigrate()...");

        try {
            URL seedSqlLocation = Resources.getResource("db/seed.sql");
            String sql = Resources.toString(seedSqlLocation, Charsets.UTF_8);
            connection.prepareStatement(sql).execute();

        } catch (SQLException | IOException e) {
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
