/**
 * OpenCPS is the open source Core Public Services software
 * Copyright (C) 2016-present OpenCPS community
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>
 */

package org.opencps.datamgt.util.comparator;

import org.opencps.datamgt.model.DictVersion;

import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Dunglt
 */

public class DictVersionValidatedFromComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "validatedFrom ASC";

	public static final String ORDER_BY_DESC = "validatedFrom DESC";

	public static final String[] ORDER_BY_FIELDS = {
		"validatedFrom"
	};

	public DictVersionValidatedFromComparator() {
		this(
			false);
	}

	public DictVersionValidatedFromComparator(boolean ascending) {
		this._ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {

		DictVersion dictVersion1 = (DictVersion) obj1;
		DictVersion dictVersion2 = (DictVersion) obj2;
		int compareValue = DateUtil
			.compareTo(dictVersion1
				.getValidatedFrom(), dictVersion2
					.getValidatedFrom());
		return _ascending ? compareValue : -compareValue;
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
