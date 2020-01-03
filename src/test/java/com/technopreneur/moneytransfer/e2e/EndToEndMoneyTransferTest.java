package com.technopreneur.moneytransfer.e2e;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.technopreneur.moneytransfer.MoneyTransferApp;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EndToEndMoneyTransferTest {


    private final String TRANSFER_QUEUE_MESSAGE ="Your transfers are succesfully queued ! Please refer Data Section for Transaction Id." +
            "You can quote this transaction Id for any queries related to the transfer. You can check status at /transfer/status/{transactionId} endpoint";


    @Test
    public void test() throws IOException {
        MoneyTransferApp.main(new String[]{"value"});
        //Step 1. Create two accounts
        print("==============================");
        print("Step 1. Create two accounts : POST  http://localhost:4567/accounts");
        String content = "{\n" +
                " accountId:1,\n" +
                " name:Scott,\n" +
                " balance:1000\n" +
                "}";
        final StringEntity entity = new StringEntity(content, ContentType.APPLICATION_JSON);
        HttpUriRequest createAccounts1 = RequestBuilder.create("POST")
                .setUri("http://localhost:4567/accounts")
                .setEntity(entity)
                .build();
        print("Step 1a. Input for first Account");
        print(content);
        HttpResponse createAccount1Response = HttpClientBuilder.create().build().execute(createAccounts1 );
        //print("Output for first Account");
        //print(EntityUtils.toString(createAccount1Response.getEntity()));
        content = "{\n" +
                " accountId:2,\n" +
                " name:Tiger,\n" +
                " balance:1000\n" +
                "}";
        HttpUriRequest createAccounts2 = RequestBuilder.create("POST")
                .setUri("http://localhost:4567/accounts")
                .setEntity(new StringEntity(content, ContentType.APPLICATION_JSON))
                .build();

        print("Step 1b. Input for second Account");
        print(content);
        HttpResponse createAccount2Response = HttpClientBuilder.create().build().execute(createAccounts2 );
        //print(EntityUtils.toString(createAccount2Response.getEntity()));

        print("==============================");
        //Step 2: Check existing accounts value
        print("==============================");
        print("Step 2. Get  accounts : GET  http://localhost:4567/accounts");
        HttpUriRequest requestAccounts = RequestBuilder.create("GET")
                .setUri("http://localhost:4567/accounts")
                .build();
        HttpResponse accountsResponse = HttpClientBuilder.create().build().execute(requestAccounts );
        String accountsString = EntityUtils.toString(accountsResponse.getEntity());
        print("Output showing created Accounts");
        print(accountsString);
        JsonObject accountsJson = (JsonObject) JsonParser.parseString(accountsString);
        JsonArray accounts = accountsJson.getAsJsonArray("data");
        JsonObject account1 = accounts.get(0).getAsJsonObject();
        JsonObject account2 = accounts.get(1).getAsJsonObject();
        assertEquals(account1.get("accountId").getAsInt(), 1);
        assertEquals(account1.get("balance").getAsInt(), 1000);
        assertEquals(account2.get("accountId").getAsInt(), 2);
        assertEquals(account2.get("balance").getAsInt(), 1000);

        //Step3 , Execute the Transfers
        print("==============================");
        print("Step 3. Execute the Transfer of 100 : POST  http://localhost:4567/transfer");
        HttpUriRequest request = RequestBuilder.create("POST")
                .setUri("http://localhost:4567/transfer")
                .setEntity(new StringEntity("{\n" +
                        " fromAccount:1,\n" +
                        " toAccount:2,\n" +
                        " amount:100\n" +
                        "}", ContentType.APPLICATION_JSON))
                .build();
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request );
        String json = EntityUtils.toString(httpResponse.getEntity());
        print("Output Confirming transfer queued");
        print(json);
        print("==============================");
        JsonObject output = (JsonObject) JsonParser.parseString(json);
        assertEquals(httpResponse.getStatusLine().getStatusCode(),HttpStatus.SC_OK);
        String transactionId = output.get("data").getAsString();
        assertNotNull(transactionId);
        assertEquals(output.get("status").getAsString(),"SUCCESS");
        assertEquals(output.get("message").getAsString(),TRANSFER_QUEUE_MESSAGE);

        //Step 4 , check transfer status now
        print("==============================");
        print("Step 4. Get  Transfer status : GET  http://localhost:4567/transfer/status/{"+transactionId+"}");
        HttpUriRequest transferStatusRequest = RequestBuilder.create("GET")
                .setUri("http://localhost:4567/transfer/status/"+transactionId)
                .build();
        HttpResponse transferStatusResponse = HttpClientBuilder.create().build().execute(transferStatusRequest );
        String transferStatus = EntityUtils.toString(transferStatusResponse.getEntity());
        print("Output showing Transfer Status");
        print(transferStatus);
        print("==============================");
        //Step 5 , check account Balance now.
        print("==============================");
        print("Step 5. Check Account Balance to verify the transfer : GET  http://localhost:4567/accounts");
        accountsResponse = HttpClientBuilder.create().build().execute(requestAccounts );
        accountsString = EntityUtils.toString(accountsResponse.getEntity());
        print("Output showing updated Accounts Balances");
        print(accountsString);
        print("==============================");
        accountsJson = (JsonObject) JsonParser.parseString(accountsString);
        accounts = accountsJson.getAsJsonArray("data");
        account1 = accounts.get(0).getAsJsonObject();
        account2 = accounts.get(1).getAsJsonObject();
        assertEquals(account1.get("accountId").getAsInt(), 1);
        assertEquals(account1.get("balance").getAsInt(), 900);
        assertEquals(account2.get("accountId").getAsInt(), 2);
        assertEquals(account2.get("balance").getAsInt(), 1100);

    }


    private void print(String content) throws IOException {
        System.out.println(content);
    }
}
