package com.example.webguidemo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class TestSteps {
	
	private final Pages pages;

	public TestSteps(Pages pages) {
		this.pages = pages;
	}
	
	@Given("user is on Home page")
    public void userIsOnHomePage(){        
        pages.home().open();        
    }

    @When("user inputs data ($name, $mail, $phone, $country, $company, $message) into form")
    public void userInputsDataIntoForm(String name, String mail,
                                       String phone, String country,
                                       String company, String message){
        pages.home().FillForm(name, mail, phone, country, company, message);
    }

    @Then("form is accepted")
    public void formIsAccepted(){
        
    }
}
