package com.example.jbehavedemo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.either;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.example.Messenger;
import com.example.messenger.MessageService;
import com.example.messenger.MessageServiceSimpleImpl;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class MessengerSteps {

    private static final String VALID_SERVER = "inf.ug.edu.pl";
    private static final String INVALID_SERVER = "inf.ug.edu.eu";

    private static final String VALID_MESSAGE = "some message";
    private static final String INVALID_MESSAGE = "a";

    private static String testServer;
    private static String testMessage;

    private Messenger messenger;
    private MessageService messServ;

    @Given("a messenger")
    public void messengerSetup(){
        messServ = new MessageServiceSimpleImpl();
        messenger = new Messenger(messServ);
    }

    @When("testing connection with Invalid Server")
    public void connectionFail(){
        testServer = INVALID_SERVER;
    }

    @When("testing connection with Valid Server")
    public void connectionSucceed(){
        testServer = VALID_SERVER;
    }

    @Then("testConnection should return $result")
    public void checkTestConnectionResult(int result){
        assertEquals(result, messenger.testConnection(testServer));
    }

    @When("sending valid message")
    public void sendingValidMessage()
    {
        testMessage = VALID_MESSAGE;
    }

    @When("sending invalid message")
    public void sendingInvalidMessage()
    {
        testMessage = INVALID_MESSAGE;
    }

    @Then("sendMessage should return $r1 or $r2")
    public void checkSendMessageResult(int r1, int r2){
        assertThat(messenger.sendMessage(VALID_SERVER, testMessage), either(equalTo(r1)).or(equalTo(r2)));
    }
}