/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * 
 * The Original Code is Riot.
 * 
 * The Initial Developer of the Original Code is
 * Neteye GmbH.
 * Portions created by the Initial Developer are Copyright (C) 2006
 * the Initial Developer. All Rights Reserved.
 * 
 * Contributor(s):
 *   Felix Gnass [fgnass at neteye dot de]
 * 
 * ***** END LICENSE BLOCK ***** */
package org.riotfamily.common.web.filter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.riotfamily.common.web.util.ServletUtils;
import org.springframework.beans.factory.InitializingBean;

/**
 * Utility class that can be used to add a timestamp to a URL.
 * 
 * @see ExpiresHeaderFilterPlugin
 * @author Felix Gnass [fgnass at neteye dot de]
 * @since 6.4
 */
public class ResourceStamper implements InitializingBean {

	public static final String DFAULT_TIME_PATTERN = "ddHHmm";
	
	public static final Pattern DEFAULT_RECOGNITION_PATTERN =
			Pattern.compile("(^.*/\\d{14}/.+$)|(^.+[?&][0-9]+$)");

	private String timePattern = DFAULT_TIME_PATTERN;
	
	private Pattern recognitionPattern = DEFAULT_RECOGNITION_PATTERN;
	
	private String startupTime;
	
	private SimpleDateFormat format;
	
	
	public void setTimePattern(String timePattern) {
		this.timePattern = timePattern;
	}
	
	public void setRecognitionPattern(Pattern recognitionPattern) {
		this.recognitionPattern = recognitionPattern;
	}
	
	public void afterPropertiesSet() throws Exception {
		format = new SimpleDateFormat(timePattern);
		startupTime = now();
	}
	
	private String now() {
		return format.format(new Date());
	}
	
	public String stamp(String url) {
		return stamp(url, false);
	}
	
	public String stamp(String url, boolean now) {
		if (isStamped(url)) {
			return url;
		}
		return ServletUtils.addParameter(url, now ? now() : startupTime, null);
	}
	
	protected boolean isStamped(String url) {
		return recognitionPattern.matcher(url).matches();
	}

}
