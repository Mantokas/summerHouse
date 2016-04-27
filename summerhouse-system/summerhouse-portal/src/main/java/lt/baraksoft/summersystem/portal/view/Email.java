package lt.baraksoft.summersystem.portal.view;

import java.io.Serializable;

public class Email implements Serializable {
	private static final long serialVersionUID = -769871578783833761L;

	private String recipient;
	private String sender;
	private String subject;
	private String messageContent;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

}
