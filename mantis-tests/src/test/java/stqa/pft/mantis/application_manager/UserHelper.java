package stqa.pft.mantis.application_manager;

import stqa.pft.mantis.model.MailMessage;
import stqa.pft.mantis.model.User;

import javax.mail.MessagingException;
import java.util.List;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }


    public void register(User user) throws MessagingException {
        app.remote().createUser(user);
        app.registration().start(user);
        List<MailMessage> mailMessages = app.remote().waitForMail(user, 60000);
        String confirmationLink = app.remote().findConfirmationLink(mailMessages, user);
        app.registration().finish(confirmationLink, user);
    }

    public void changePassword(User user, String newPassword) throws MessagingException {
        app.remote().initTelnetSession();
        List<MailMessage> mailMessages = app.remote().waitForMail(user, 60000);
        String confirmationLink = app.remote().findConfirmationLink(mailMessages, user);
        System.out.println(confirmationLink);
//        setNewPassword(user, confirmationLink, newPassword);
    }

    private void setNewPassword(User user,String confirmationLink, String newPassword) {
        driver.get(confirmationLink);
    }


}
