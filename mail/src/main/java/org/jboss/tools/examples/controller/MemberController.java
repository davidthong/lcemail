/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.tools.examples.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.tools.examples.model.GigInformation;
import org.jboss.tools.examples.model.Member;
import org.jboss.tools.examples.service.MemberRegistration;
import org.joda.time.LocalTime;


// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://www.cdi-spec.org/faq/#accordion6
@Model
public class MemberController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private MemberRegistration memberRegistration;

    @Produces
    @Named
    private Member newMember;

    @PostConstruct
    public void initNewMember() {
        newMember = new Member();
    }

    
    
    
    
    public void register() throws Exception {
        try {
        	
        	sendMail();
            memberRegistration.register(newMember);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
            facesContext.addMessage(null, m);
            initNewMember();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
        }
    }

    private void sendMail() {
    	  //Setting up configurations for the email connection to the Google SMTP server using TLS
       
    
    
    	Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("davidthongmusic@gmail.com", "goof1981");
            }
        });
        try {
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);
           
            
            StringBuilder email = new StringBuilder();
            
            NumberFormat usdCostFormat = NumberFormat.getCurrencyInstance(Locale.US);
           // usdCostFormat.setMinimumFractionDigits( 1 );
           // usdCostFormat.setMaximumFractionDigits( 2 );
           // System.out.println( usdCostFormat.format(displayVal.doubleValue()) );
            
            
            GigInformation gig1 = new GigInformation();
            
            gig1.setArtist("David Thong");
            gig1.setDate(new GregorianCalendar(2019, 1, 15).getTime());
            gig1.setVenue("Tysons Biergarten");
            gig1.setStreet("8346 Leesburg Pike");
            gig1.setCity("Tysons");
            gig1.setState("VA");
            gig1.setZip("22203");
            gig1.setArtist("David Thong");
            BigDecimal cost = new BigDecimal("150.00");
            BigDecimal amountDue = new BigDecimal("22.50");
            gig1.setCost(usdCostFormat.format(cost.doubleValue()));
            gig1.setAmountDue(usdCostFormat.format(amountDue.doubleValue()));
            gig1.setStartTime(LocalTime.now());
            
            
            
            
            
            
            List<GigInformation> gigs = new ArrayList<GigInformation>();
            
            gigs.add(gig1);
            
         
            
            email.append("<style type=\"text/css\">\n" + 
            		".tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #729ea5;border-collapse: collapse;}\n" + 
            		".tftable th {font-size:12px;background-color:#acc8cc;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;text-align:left;}\n" + 
            		".tftable tr {background-color:#d4e3e5;}\n" + 
            		".tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;}\n" + 
            		".tftable tr:hover {background-color:#ffffff;}\n" + 
            		"</style>\n" + 
            		"\n" + 
            		"<table class=\"tftable\" border=\"1\">\n" + 
            		"<tr><th>Booking Date<th>Venue</th><th>Street</th><th>City</th><th>State</th><th>Zip</th><th>Artist</th>"
            		+ "<th>Cost</th><th>Amt Due</th><th>Start Time</th><th>End Time</th><th>Load-In Time</th><th>Production</th>"
            		+ "<th>Sound</th><th>Additional Terms</th><th>Open Artist</th><th>Open Start</th><th>Open End</th></tr>");
            		
                for (GigInformation gig : gigs) {
                	
                	email.append("<tr><td>");
                	email.append(gig.getDate() + "</td><td>");
                	email.append(gig.getVenue() + "</td><td>");
                	email.append(gig.getStreet() + "</td><td>");
                	email.append(gig.getCity() + "</td><td>");
                	email.append(gig.getState() + "</td><td>");
                	email.append(gig.getZip() + "</td><td>");
                	email.append(gig.getArtist() + "</td><td>");
                	email.append(gig.getCost() + "</td><td>");
                	email.append(gig.getAmountDue() + "</td><td>");
                	email.append(gig.getStartTime() + "</td><td>");
                	
                	
                
                	
                	
                }
            		
            		
//            		
//            		"<tr><td>1/5/2019</td>"
//            		+ "<td>Tysons Biergarten</td>"
//            		+ "<td>8346 Leesburg Pike</td><td>Tysons</td><td>Row:1 Cell:5</td><td>Row:1 Cell:6</td><td>Row:1 Cell:7</td><td>Row:1 Cell:8</td><td>Row:1 Cell:9</td><td>Row:1 Cell:10</td><td>Row:1 Cell:11</td><td>Row:1 Cell:12</td><td>Row:1 Cell:13</td><td>Row:1 Cell:14</td><td>Row:1 Cell:15</td><td>Row:1 Cell:16</td><td>Row:1 Cell:17</td><td>Row:1 Cell:18</td></tr>\n" + 
//            		"</table>");

            
           msg.setContent(email.toString(), "text/html; charset=utf-8");
            //Storing the comma seperated values to email addresses
            String to = "davidthongmusic@gmail.com, davidthonglastcall@gmail.com";
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
           InternetAddress[] address = InternetAddress.parse(to, false);
            //Setting the recepients from the address variable
            
      //      Address 
    //        msg.addRecipient(Message.RecipientType.TO, InternetAddress.parse("davidthongmusic@gmail.com"));
       //     msg.addRecipient(Message.RecipientType.TO, InternetAddress.parse("davidthonglastcall@gmail.com"));
        //    msg.addRecipient
            
            msg.setRecipients(Message.RecipientType.TO, address);
            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
            msg.setSubject("Last Call Weekly Reminder TEST" + timeStamp);
            msg.setSentDate(new Date());
           // msg.setText(text.toString());
            
            msg.setText(email.toString(), "utf-8", "html");
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            System.out.println("Mail has been sent successfully");
        } catch (MessagingException mex) {
            System.out.println("Unable to send an email" + mex);
        }
	}





	private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }

}
