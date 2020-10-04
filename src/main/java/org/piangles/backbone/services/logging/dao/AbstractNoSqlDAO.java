package org.piangles.backbone.services.logging.dao;

import org.bson.Document;
import org.piangles.backbone.services.logging.LogEvent;
import org.piangles.core.dao.DAOException;

import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AbstractNoSqlDAO implements LoggingDAO
{
	private static final String HOST = "34.202.160.243";
	private static final int PORT = 27017;
	private static final String DATABASE = "piangles";
	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "dbpassword";
	private static final String PREFIX = "mongodb";
	private static final String COLLECTION = "Logs";
	private static String URL;

	private com.mongodb.client.MongoClient mongoClient;
	private MongoDatabase database;

	public AbstractNoSqlDAO()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(PREFIX).append("://").append(USERNAME).append(":").append(PASSWORD)
				.append("@").append(HOST).append(":").append(PORT).append("/")
				.append(DATABASE);

		URL = sb.toString();
		ConnectionString connectionString = new ConnectionString(URL);
		try
		{
			mongoClient = MongoClients.create(connectionString);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public MongoDatabase getDatabase()
	{
		database = mongoClient.getDatabase(DATABASE);
		return database;
	}

	@Override
	public void insertLog(LogEvent logEvent) throws DAOException
	{
		Document document = new Document();
		Gson gson = new Gson();
		MongoCollection<Document> collection = getDatabase().getCollection(COLLECTION);
		document.put(logEvent.getCategory().name(), gson.toJson(logEvent));
		collection.insertOne(document);
	}

}
