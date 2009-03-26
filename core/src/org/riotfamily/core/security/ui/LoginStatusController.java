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
package org.riotfamily.core.security.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.riotfamily.common.util.ResourceUtils;
import org.riotfamily.core.security.session.LoginManager;
import org.riotfamily.core.security.session.SessionMetaData;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LoginStatusController implements Controller {

	private String viewName = ResourceUtils.getPath(
			LoginStatusController.class, "LoginStatusView.ftl");
	
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		if (request.getParameter("update") != null) {
			//Currently this is just used for session keep-alive.
			//So nothing to do here ...
			return null;
		}
		else {
			SessionMetaData data = LoginManager.getSessionMetaData(request);
			return new ModelAndView(viewName, "sessionData", data);
		}
	}

}
