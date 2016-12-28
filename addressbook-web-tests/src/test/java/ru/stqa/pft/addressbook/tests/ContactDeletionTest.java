package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by uttabondarenko on 28.12.16.
 */
public class ContactDeletionTest extends TestBase {
  @Test

  public void contactDeletionTests() {
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
  }

}
