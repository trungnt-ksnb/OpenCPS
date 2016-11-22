/**
 * OpenCPS is the open source Core Public Services software Copyright (C)
 * 2016-present OpenCPS community This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Affero General
 * Public License as published by the Free Software Foundation, either version 3
 * of the License, or any later version. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Affero General Public License for more details. You should have received a
 * copy of the GNU Affero General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>
 */

package org.opencps.dossiermgt.comparator;

import org.opencps.dossiermgt.model.DossierTemplate;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

public class DossierTemplateNameComparator extends OrderByComparator{

	public static final String ORDER_BY_ASC =
	    "opencps_dossiertemplate.templateName ASC";

	public static final String ORDER_BY_DESC =
	    "opencps_dossiertemplate.templateName DESC";

	public static final String[] ORDER_BY_FIELDS = {
		"templateName"
	};

	public DossierTemplateNameComparator() {

		this(false);
	}

	public DossierTemplateNameComparator(boolean ascending) {

		_ascending = ascending;
	}
	@Override
	public int compare(Object arg0, Object arg1) {

		DossierTemplate dossierTemplate0 = (DossierTemplate) arg0;
		DossierTemplate dossierTemplate1 = (DossierTemplate) arg1;
		String templateName0 =
		    StringUtil.lowerCase(dossierTemplate0.getTemplateName());
		String templateName1 =
		    StringUtil.lowerCase(dossierTemplate1.getTemplateName());

		int value = templateName0.compareTo(templateName1);
		
		return _ascending ? value : -value;
	}

	@Override
	public String getOrderBy() {

		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {

		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {

		return _ascending;
	}
	private boolean _ascending;
}
