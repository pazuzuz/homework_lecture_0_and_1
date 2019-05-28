package stqa.pft.addressbook.tests;

import entity.User;
import org.testng.annotations.Test;
import stqa.pft.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {
    @Test
    public void testGroupModification(){
        applicationManager.getSessionHelper().loginAs(User.ADMIN);
        applicationManager.getNavigationHelper().goToGroupPage();
        applicationManager.getGroupHelper().initGroupModification();
        applicationManager.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        applicationManager.getGroupHelper().submitGroupModification();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}
