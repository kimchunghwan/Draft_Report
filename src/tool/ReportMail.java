package tool;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ReportMail {

	public static final String ADDRESS_PREFIX = "ADDR_";
	public static final String ADDRESS_TO = "ADDR_TO";
	public static final String ADDRESS_CC = "ADDR_CC";
	public static final String ADDRESS_BCC = "ADDR_BCC";
	public static final String ADDRESS_FROM = "ADDR_FROM";
	public static final String SMTP_HOST = "mail.smtp.host";
	public static final String SMTP_PORT = "mail.smtp.port";

	public static Message init(ReportData reportData) throws Exception {

		Properties props = new Properties();
		final String userId = reportData.userId;
		final String password = reportData.password;
		props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", reportData.smtpHost);
		props.put("mail.smtp.port", reportData.smtpPort);

		// TODO case by case
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userId, password);
			}
		});

		Message message = new MimeMessage(session);

		message.setRecipients(Message.RecipientType.TO, getAddressList(Message.RecipientType.TO, reportData));
		message.setRecipients(Message.RecipientType.CC, getAddressList(Message.RecipientType.CC, reportData));
		message.setRecipients(Message.RecipientType.BCC, getAddressList(Message.RecipientType.BCC, reportData));
		message.setFrom(new InternetAddress(reportData.getItem(ADDRESS_FROM).lstValue.get(0)));
		return message;

	}

	private static InternetAddress[] getAddressList(RecipientType type, ReportData reportData) throws AddressException {
		InternetAddress[] lstAddressTo = new InternetAddress[reportData
				.getItem(ADDRESS_PREFIX + type.toString().toUpperCase()).lstValue.size()];

		for (int i = 0; i < lstAddressTo.length; i++) {
			lstAddressTo[i] = new InternetAddress(
					reportData.getItem(ADDRESS_PREFIX + type.toString().toUpperCase()).lstValue.get(i));
		}
		return lstAddressTo;
	}

}
