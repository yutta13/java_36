package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionsTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        gotoGroupPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }


}
