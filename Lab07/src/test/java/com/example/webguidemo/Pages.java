package com.example.webguidemo;

import org.jbehave.web.selenium.WebDriverProvider;

import com.example.webguidemo.pages.Home;

public class Pages {

	private WebDriverProvider driverProvider;

	private Home home;

	public Pages(WebDriverProvider driverProvider) {
		this.driverProvider = driverProvider;
	}

	public Home home() {
		if (home == null) {
			home = new Home(driverProvider);
		}
		return home;
	}
}
