package org.aksw.sparqlmap.r2rmltestcases;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import org.aksw.sparqlmap.DockerHelper;
import org.aksw.sparqlmap.DockerHelper.DBConnConfig;
import org.aksw.sparqlmap.core.db.Connector;
import org.aksw.sparqlmap.core.db.DBAccessConfigurator;
import org.aksw.sparqlmap.core.db.impl.MySQLConnector;
import org.aksw.sparqlmap.core.db.impl.MySQLDataTypeHelper;
import org.aksw.sparqlmap.core.mapper.translate.DataTypeHelper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jolbox.bonecp.BoneCPDataSource;
import com.spotify.docker.client.DockerException;

public class MySQLR2RMLTestCase extends R2RMLTest {

	

  public MySQLR2RMLTestCase(String testCaseName, String r2rmlLocation,
			String outputLocation, String referenceOutput,
			String dbFileLocation, boolean createDM) {
		super(testCaseName, r2rmlLocation, outputLocation, referenceOutput,
				dbFileLocation, createDM);
	}
  
  
	private static Logger log = LoggerFactory.getLogger(MySQLR2RMLTestCase.class);

  @BeforeClass
  public static void startMySQLDocker() throws DockerException, InterruptedException {
   // this approach will work for most dev setups
      try {
        dbconf =  DockerHelper.startDirectOrDockerizedMySQL();
      } catch (Exception e) {
        log.error("FAiled to start Docker contaier", e);
        dbIsReachable = false;
      }
  }

  @AfterClass
  public static void doTeardownHost() throws DockerException, InterruptedException {
    DockerHelper.stopDirectOrDockerizedMysql();
    
  }
	

	


	@Parameters(name="{0}")
	public static Collection<Object[]> data() {
		return data(getTestCaseLocations());
		
	}
	
	public static String getTestCaseLocations() {
		
		return "./src/test/resources/testcases/mysql/";
	}
	@Override
	DataTypeHelper getDataTypeHelper() {
	  return new MySQLDataTypeHelper();
	}



}
