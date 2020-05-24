package org.dice_research.opal.github;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Review {

	public String htmlUrl;
	public String submittedAt;
	public String userLogin;

	/**
	 * @see https://en.wikipedia.org/wiki/Time_in_Germany
	 */
	public Date getDate() {
		if (submittedAt == null) {
			return null;
		} else {
			return Date.from(Instant.from(DateTimeFormatter.ISO_INSTANT.parse(submittedAt)));
		}

	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(htmlUrl);
		stringBuilder.append(" ");
		stringBuilder.append(getDate().toString());
		stringBuilder.append(" (");
		stringBuilder.append(submittedAt);
		stringBuilder.append(") ");
		stringBuilder.append(userLogin);
		stringBuilder.append(System.lineSeparator());
		return stringBuilder.toString();
	}
}