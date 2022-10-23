package integration;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

public class ContainerizedDbIntegrationTest {

    private static final int PORT = 3308;
    private static final String PASSWORD = "example";

    public static MySQLContainer mysql;

    static {
         mysql = (MySQLContainer) new MySQLContainer(DockerImageName.parse("mysql:latest"))
                 .withPassword(PASSWORD)
                .withExposedPorts(PORT);
         mysql.start();
    }

    protected String getDbPassword() {
        return PASSWORD;
    }

    protected String getDbUrl(){
        return "jdbc:mysql://"+mysql.getHost()+":"+mysql.getFirstMappedPort()+"/";
    }

    protected String getDb() {
        return "DemoApplicationTest";
    }

    protected String getConnectionString() {
        return getDbUrl() + getDb();
    }

    protected void runMigration(double level) {
        String url = getDbUrl();
        String db = getDb();
        Flyway flyway = new Flyway(
                new FluentConfiguration()
                        .schemas(db)
                        .defaultSchema(db)
                        .createSchemas(true)
                        .target(Double.toString(level))
                        .dataSource(url, "root", PASSWORD)
        );
        flyway.migrate();
    }

}
