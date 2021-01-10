# Connecting to MongoDB with JNoSQL

---
## Background
JNoSQL documentation (http://www.jnosql.org/) has limitation when it comes to showing how to pass credentials to the MongoDB. I then built a quick project (Student Register) on MicroProfile with Open Liberty to provide the solution.

```
public class DocumentCollectionManagerProducer {

  private DocumentConfiguration configuration;

  private DocumentCollectionManagerFactory managerFactory;

  @PostConstruct
  public void init() {
    configuration = new MongoDBDocumentConfiguration();
    Settings settings =  Settings.builder()
    		.put(DatabaseConstants.JARKATA_NOSQL_HOST_1_KEY, DatabaseConstants.MONGODB_HOST)
    		.put(DatabaseConstants.JARKATA_NOSQL_USER_KEY, DatabaseConstants.MONGODB_USERNAME)
    		.put(DatabaseConstants.JARKATA_NOSQL_PASSWORD_KEY, DatabaseConstants.MONGODB_PASSWORD)
    		.put(DatabaseConstants.MONGODB_AUTHENTICATION_MECHANISM_KEY, DatabaseConstants.MONGODB_AUTHENTICATION_MECHANISM_VALUE)
    		.put(DatabaseConstants.MONGODB_AUTHENTICATION_SOURCE_KEY, DatabaseConstants.DATABASE)
    		.build();
    managerFactory = configuration.get(settings);
  }

  @Produces
  @Database(value = DatabaseType.DOCUMENT)
  public DocumentCollectionManager getManager() {
    return managerFactory.get(DatabaseConstants.DATABASE);

  }
}
```
### Project Setup
```
git clone project
cd college-register
mvn clean install
mvn liberty:run
```
Next, open swagger: http://localhost:9080/openapi/ui/
