/**
* OpenCPS is the open source Core Public Services software
* Copyright (C) 2016-present OpenCPS community

* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Affero General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* any later version.

* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Affero General Public License for more details.
* You should have received a copy of the GNU Affero General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>
*/
package org.opencps.accountmgt.util.comparator;

import org.opencps.accountmgt.model.Citizen;

import com.liferay.portal.kernel.util.OrderByComparator;


public class CitizenAccountStatusComparator extends OrderByComparator{
	public static final String ORDER_BY_ASC =
					"opencps_acc_citizen.accountStatus ASC";

	public static final String ORDER_BY_DESC =
					"opencps_acc_citizen.accountStatus DESC";

	public static final String[] ORDER_BY_FIELDS = {
					"accountStatus"
				};

	public CitizenAccountStatusComparator() {
		this(false);
	}
		
	public CitizenAccountStatusComparator(boolean ascending) {

		_ascending = ascending;
	}

	@Override
	public int compare(Object arg0, Object arg1) {
		
		Citizen citizen1 = (Citizen) arg0;
		Citizen citizen2 = (Citizen) arg1;
		
		Integer accountStatus1 = citizen1.getAccountStatus();
		Integer accountStatus2 = citizen2.getAccountStatus();
		
		int value = accountStatus1.compareTo(accountStatus2);
		
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
