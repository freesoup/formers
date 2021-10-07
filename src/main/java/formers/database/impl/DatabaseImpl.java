package formers.database.impl;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import formers.core.authentication.Authorization;
import formers.core.database.Database;
import formers.core.exception.DatabaseException;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;
import formers.database.dto.AccountDto;
import formers.database.dto.FormFormatDto;
import formers.database.dto.FormResponseDto;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DatabaseImpl implements Database {
    private static String hostName = "localhost";
    private static int portNo = 27017;
    private static MongoClientURI URI = new MongoClientURI("REDACTED");
    private static MongoClient client = new MongoClient(URI);
    private String databaseName = "formersdb";
    private String formFormatCollection = "form_format";
    private String accountCollection = "account";
    private String formResponseCollection = "form_response";

    @Override
    public void submitResponse(FormResponse responses) {
        Gson gson = new Gson();
        String jsonInString = gson.toJson(responses);


        MongoDatabase database = getMongoDatabase();

        MongoCollection<FormResponseDto> collection = database.getCollection(formResponseCollection, FormResponseDto.class);

        FormResponseDto formResponse = new FormResponseDto(responses.getFormID(), responses.getRecipient(),
                jsonInString);

        collection.insertOne(formResponse);
    }

    @Override
    public void submitNewForm(FormFormat form) {
        Gson gson = new Gson();

        String jsonInString = gson.toJson(form);

        MongoDatabase database = getMongoDatabase();

        MongoCollection<FormFormatDto> collection = database.getCollection(formFormatCollection, FormFormatDto.class);

        FormFormatDto formFormat = new FormFormatDto(form.getID(), form.getOwner(), jsonInString);

        collection.insertOne(formFormat);
    }

    @Override
    public List<FormFormat> getAllFormFormat(String userName) {
        final List<FormFormat> formFormats = new ArrayList<FormFormat>();

        MongoDatabase database = getMongoDatabase();

        MongoCollection<FormFormatDto> collection = database.getCollection(formFormatCollection, FormFormatDto.class);


        Block<FormFormatDto> addListBlock = new Block<FormFormatDto>() {
            @Override
            public void apply(final FormFormatDto document) {
                Gson gson = new Gson();
                FormFormat format = gson.fromJson(document.getFormFormatJson(), FormFormat.class);
                formFormats.add(format);
            }
        };

        collection.find(eq("formOwner", userName)).forEach(addListBlock);

        return formFormats;

    }

    @Override
    public List<FormResponse> getAllFormResponse(String formID) {
        final List<FormResponse> formResponses = new ArrayList<FormResponse>();

        MongoDatabase database = getMongoDatabase();

        MongoCollection<FormResponseDto> collection = database.getCollection(formResponseCollection, FormResponseDto.class);

        Block<FormResponseDto> addListBlock = new Block<FormResponseDto>() {
            @Override
            public void apply(final FormResponseDto document) {
                Gson gson = new Gson();
                FormResponse response = gson.fromJson(document.getStringJson(), FormResponse.class);
                formResponses.add(response);
            }
        };

        collection.find(eq("formID", formID)).forEach(addListBlock);

        return formResponses;
    }

    @Override
    public FormFormat getForm(String iD) throws DatabaseException {
        Gson gson = new Gson();

        MongoDatabase database = getMongoDatabase();

        MongoCollection<FormFormatDto> collection = database.getCollection(formFormatCollection, FormFormatDto.class);

        if (iD == null) {
            throw new DatabaseException(
                    "Error retrieving form of form ID " + iD + ". Check to ensure that form ID is correct.");
        }

        FormFormatDto targetDoc = collection.find(eq("formID", iD)).first();

        if (targetDoc == null) {
            throw new DatabaseException(
                    "Error retrieving form of form ID " + iD + ". Check to ensure that form ID is correct.");
        }

        String formJSON = targetDoc.getFormFormatJson();

        FormFormat form = gson.fromJson(formJSON, FormFormat.class);

        return form;
    }

    @Override
    public FormResponse getFormResult(String user, String formID) {
        return null;
    }

    @Override
    public String getPass(String user) throws DatabaseException {
        MongoDatabase database = getMongoDatabase();

        MongoCollection<AccountDto> collection = database.getCollection(accountCollection, AccountDto.class);

        try {
            AccountDto acdto = collection.find(eq("user", user)).first();

            if (acdto == null) {
                return null;
            }
            return acdto.getHashedPass();
        } catch (Exception e) {
            throw new DatabaseException("Out database is offline.");
        }
    }

    @Override
    public boolean addNewAccount(String user, String hashedPass) {
        MongoDatabase database = getMongoDatabase();

        MongoCollection<AccountDto> collection = database.getCollection(accountCollection, AccountDto.class);

        AccountDto acdtoCheck = collection.find(eq("user", user)).first();

        if (acdtoCheck != null) {
            return false;
        }

        AccountDto acdto = new AccountDto(user, hashedPass, Authorization.ADMIN);

        collection.insertOne(acdto);

        return true;
    }

    @Override
    public Authorization getAuthority(String user) {
        MongoDatabase database = getMongoDatabase();

        MongoCollection<AccountDto> collection = database.getCollection(accountCollection, AccountDto.class);

        AccountDto acdtoCheck = collection.find(eq("user", user)).first();

        if (acdtoCheck == null) {
            return Authorization.STRANGER;
        } else {
            return acdtoCheck.getAuthority();
        }
    }

    @Override
    public void deleteAllTracesOf(String formIdToBeDeleted, String user) throws DatabaseException {
        MongoDatabase database = getMongoDatabase();

        MongoCollection<FormResponseDto> responseCollection = database.getCollection(formResponseCollection, FormResponseDto.class);
        MongoCollection<FormFormatDto> formatCollection = database.getCollection(formFormatCollection, FormFormatDto.class);

        responseCollection.deleteMany(eq("formID", formIdToBeDeleted));

        formatCollection.deleteMany(eq("formID", formIdToBeDeleted));
    }

    private MongoDatabase getMongoDatabase() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        return client.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);
    }
}
