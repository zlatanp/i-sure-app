package com.ftn.service;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created by zlatan on 1/15/18.
 */
public interface EmailService {

   void sendEmail(String to, String attachmentName) throws MessagingException, IOException;
}
