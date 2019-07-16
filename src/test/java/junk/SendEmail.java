package junk;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.testng.annotations.Test;

public class SendEmail {

    @Test
    public void sendEmail() throws EmailException {

        Email email = new SimpleEmail(); // Create Object
        email.setHostName("smtp.googlemail.com"); // Set SMTP hostname
        email.setSmtpPort(2); // Set port
        email.setAuthenticator(new DefaultAuthenticator("greendevald95@mail.com", "nikita95")); // Set email/password
        email.setSSLOnConnect(true); // SSL true
        email.setFrom("greendevald95@gmail.com"); // set FROM
        email.setSubject("TestMail"); // set Subject
        email.setMsg("This is a test mail ... :-)"); // Set message
        email.addTo("greendevald95@mail.com"); // Set recipients
        email.send(); // Send Email
    }



}
