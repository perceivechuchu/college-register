package com.college.register;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.eclipse.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

import com.college.register.constants.DatabaseConstants;

import jakarta.nosql.Settings;
import jakarta.nosql.document.DocumentCollectionManager;
import jakarta.nosql.document.DocumentCollectionManagerFactory;
import jakarta.nosql.document.DocumentConfiguration;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;

@ApplicationScoped
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
  @Database(value = DatabaseType.DOCUMENT, provider = "student-database")
  public DocumentCollectionManager getManager() {
    return managerFactory.get(DatabaseConstants.DATABASE);

  }
}